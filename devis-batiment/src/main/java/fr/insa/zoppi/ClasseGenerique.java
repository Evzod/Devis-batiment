package fr.insa.zoppi;

import java.io.Serializable;

import javafx.scene.control.TreeItem;
import javafx.scene.layout.VBox;

public abstract class ClasseGenerique implements Serializable {
    String nom;
    transient TreeItem<ClasseGenerique> noeud;

    public abstract void formulaire(VBox zoneFormulaire);

    public void setTreeItem(TreeItem<ClasseGenerique> noeud) {
        this.noeud = noeud;
    }

    public TreeItem<ClasseGenerique> getTreeItem() {
        return noeud;
    }
}
