package fr.insa.zoppi;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.VBox;
import java.io.*;

public class Devis extends ClasseGenerique{
    static ArrayList<Batiment> batiments;
    long prixDevis;


    public Devis() {
        batiments = new ArrayList<Batiment>();
        nom = "Devis bâtiment";
    }

    public void setTreeItem(TreeItem<ClasseGenerique> noeud) {
        this.noeud = noeud;
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
        zoneFormulaire.getChildren().addAll(boutonImmeuble, boutonMaison);
    }

    public void lireCatalogue() {
        try {
            BufferedReader catalogue=new BufferedReader(new FileReader("catalogue.txt"));
            String lignelue;// Ligne lue depuis le fichier
            System.out.println("Localité recherchée");
            String recherche=Lire.S();

            while((lignelue=catalogue.readLine())!=null) {
                String[] mots = lignelue.split(";");
                if (mots[3].equals(recherche)){
                    System.out.println(lignelue);
                }
            }
            catalogue.close();
        }

        catch(FileNotFoundException err) {
            System.out.println( "Erreur :le fichier n'existe pas !\n "+err);
        }
        catch (IOException err) {
            System.out.println(" Erreur :\n "+err);
        }
    }

}
