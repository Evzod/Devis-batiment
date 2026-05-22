package fr.insa.zoppi;

import java.util.*;

import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.VBox;

public class Batiment {
    private String typeBatiment;
    private int nbEtage = 1;
    private ArrayList<Etage> etages;
    long prixTotal;
    TreeItem<Object> noeud;
    String nom = "MIA 2";

    public Batiment(TreeItem<Object> noeudParent, String typeBatiment) {
        this.typeBatiment = typeBatiment;
        etages = new ArrayList<Etage>();
        noeud = new TreeItem<>(this);
        noeudParent.getChildren().add(noeud);

    }

    @Override
    public String toString() {
        return typeBatiment + " : " + nom;
    }

    public void formulaire(VBox zoneFormulaire) {
        Button boutonEtage = App.creerBouton("Ajouter un étage");
        boutonEtage.setOnAction(evt -> {
            etages.add(new Etage(noeud));
        });
        zoneFormulaire.getChildren().add(boutonEtage);
    }
}