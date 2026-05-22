package fr.insa.zoppi;

import java.util.ArrayList;

import javafx.scene.control.TreeItem;

public class Etage {
    ArrayList<Appartement> apparts;
    float hauteur;
    TreeItem<Object> noeud;

    public Etage(TreeItem<Object> noeudParent) {
        noeud = new TreeItem<Object>(this);
        noeudParent.getChildren().add(noeud);
    }
}
