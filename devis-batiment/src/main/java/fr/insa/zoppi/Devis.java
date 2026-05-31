package fr.insa.zoppi;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Devis extends ClasseGenerique{
    ArrayList<Batiment> batiments;
    long prixDevis;
    private static Catalogue catalogue;


    public Devis() {
        batiments = new ArrayList<Batiment>();
        nom = "Devis bâtiment";
        catalogue = new Catalogue();
    }


    @Override
    public String toString() {
        return "Devis bâtiment";
    }

    public void formulaire(VBox zoneFormulaire) {
        Button boutonImmeuble = App.creerBouton("Ajouter un immeuble");
        boutonImmeuble.setOnAction(evt -> {
            batiments.add(new Batiment(noeud, "Immeuble"));
        });
        Button boutonMaison = App.creerBouton("Ajouter une maison");
        boutonMaison.setOnAction(evt -> {
            batiments.add(new Batiment(noeud, "Maison"));
        });

        Button boutonSauvegarde = App.creerBouton("Sauvegarder le devis");
        boutonSauvegarde.setOnAction(evt -> {
            App.sauvegarderProjet(this, "sauvegarde_devis");
        });


        TextField fieldCharge = new TextField();
        fieldCharge.setPromptText("Nom du fichier");
        Button boutonCharge = App.creerBouton("Charger");
        boutonCharge.setOnAction(evt -> {
            App.chargerProjet(fieldCharge.getText());
        });
        VBox boxCharge = App.creerQuestion("Charger un devis", fieldCharge, boutonCharge);

        
        Button boutonPrix = App.creerBouton("Calculer le prix total");
        Label labelPrix = new Label("......");
        HBox boxPrix = new HBox(labelPrix, new Label("   €"));
        boutonPrix.setOnAction(evt -> {
            labelPrix.setText(Double.toString(this.calculPrix()));
        });

        zoneFormulaire.getChildren().addAll(boutonImmeuble, boutonMaison, boutonSauvegarde, boxCharge, boutonCharge, boutonPrix, new Label("Prix total : "), boxPrix);
    }

    private double calculPrix() {
        double prix = 0;
        for (Batiment batiment : batiments) {
            for (Etage etage : batiment.etages) {
                if (etage.apparts!=null) {
                    for (Appartement appart : etage.apparts) {
                        for (Piece piece : appart.pieces) {
                            prix += piece.getPrix();
                        }
                    }
                } else if (etage.pieces!=null) {
                    for (Piece piece : etage.pieces) {
                        prix += piece.getPrix();
                    }
                }
            }
        }        
        return prix;
    }

    public static double prixRevetement(double surface, String revetement) {
        double prixUnitaire = 25; //prix standard
        for (int i=1; i<=18; i++) {
            if (catalogue.getRevetement(i).getNom().equals(revetement)) {
                prixUnitaire = catalogue.getRevetement(i).getPrixUnitaire();
                break;
            }
        }
        return surface*prixUnitaire;
    }
}
