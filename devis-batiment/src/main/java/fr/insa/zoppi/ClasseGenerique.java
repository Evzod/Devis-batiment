package fr.insa.zoppi;

import javafx.scene.control.TreeItem;
import javafx.scene.layout.VBox;

public abstract class ClasseGenerique {
    String nom;
    TreeItem<ClasseGenerique> noeud;

    public abstract void formulaire(VBox zoneFormulaire);
}
