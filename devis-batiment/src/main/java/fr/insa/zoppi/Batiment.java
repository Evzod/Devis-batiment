package fr.insa.zoppi;

import java.util.*;

import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.VBox;

public class Batiment extends ClasseGenerique{
    private String typeBatiment;
    ArrayList<Etage> etages;
    long prixTotal;

    public Batiment(TreeItem<ClasseGenerique> noeudParent, String typeBatiment) {
        this.typeBatiment = typeBatiment;
        nom = "Nouveau bâtiment";
        etages = new ArrayList<Etage>();
        noeud = new TreeItem<>(this);
        noeudParent.getChildren().add(noeud);
        noeudParent.setExpanded(true);
    }

    @Override
    public String toString() {
        return typeBatiment + " : " + nom;
    }

    public void formulaire(VBox zoneFormulaire) {
        VBox boxNom = App.creerNom(this, zoneFormulaire);
        Button boutonEtage = App.creerBouton("Ajouter un étage");
        boutonEtage.setOnAction(evt -> {
            etages.add(new Etage(noeud, typeBatiment));
            noeud.setExpanded(true);
        });
        zoneFormulaire.getChildren().addAll(boutonEtage, boxNom);
    }
}