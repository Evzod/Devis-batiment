package fr.insa.zoppi;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Etage extends ClasseGeometrique{
    ArrayList<Appartement> apparts;
    ArrayList<Piece> pieces;
    double hauteur = 2.5;
    boolean isImmeuble;

    public Etage(TreeItem<ClasseGenerique> noeudParent, String typeBatiment) {
        if (typeBatiment.equals("Immeuble")) {
            apparts = new ArrayList<Appartement>();
            this.isImmeuble = true; //true pour immeuble
        } else if (typeBatiment.equals("Maison")) {
            pieces = new ArrayList<Piece>();
            this.isImmeuble = false; //false pour maison
        } else {
            System.out.println("ni un immeuble ni une maison...s");
        }
        noeud = new TreeItem<ClasseGenerique>(this);
        noeudParent.getChildren().add(noeud);
        noeudParent.setExpanded(true);
        nom = "Nouvel Étage";
        x1 = -15;
        y1 = -10;
        x2 = 15;
        y2 = 10;
        etage = this;
        App.updateDessin(etage);
    }

    @Override
    public String toString() {
        return "Étage : " + nom;
    }

    @Override
    public void formulaire(VBox zoneFormulaire) {
        VBox boxCoordo = App.creerCoordo(this, zoneFormulaire);
        VBox boxNom = App.creerNom(this, zoneFormulaire);
        TextField fieldHauteur = new TextField(Double.toString(hauteur));
        Button boutonHauteur = App.creerBouton("Valider");
        boutonHauteur.setOnAction(evt-> {
            hauteur = Double.parseDouble(fieldHauteur.getText());
        });
        VBox boxHauteur = App.creerQuestion("Hauteur sous plafond (m)", fieldHauteur, boutonHauteur);
        Button bouton;
        if (isImmeuble) {
            bouton = App.creerBouton("Ajouter un appartement");
            bouton.setOnAction(evt -> {
                apparts.add(new Appartement(noeud, this));
                noeud.setExpanded(true);
            });
        } else {
            bouton = App.creerBouton("Ajouter une pièce");
            bouton.setOnAction(evt -> {
                pieces.add(new Piece(noeud, this));
                noeud.setExpanded(true);
            });
        }
        zoneFormulaire.getChildren().addAll(bouton, boxCoordo, boxNom, boxHauteur);
        App.updateDessin(this);
    }

    @Override
    public void dessiner(Pane zoneDessin) {
        Rectangle rect = App.rectangle(x1, y1, x2, y2);
        rect.setFill(Color.TRANSPARENT);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(0.5*App.coeffDessin);
        zoneDessin.getChildren().add(rect);
    }
}
