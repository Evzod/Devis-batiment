package fr.insa.zoppi;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.VBox;

public class Devis {
    ArrayList<Batiment> batiments;
    long prixDevis;
    TreeItem<Object> noeud;

    public Devis() {
        batiments = new ArrayList<Batiment>();
    }

    public void setTreeItem(TreeItem<Object> noeud) {
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
}
