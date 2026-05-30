package fr.insa.zoppi;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Appartement extends ClasseGeometrique{
    ArrayList<Piece> pieces;
    double hauteur;

    public Appartement(TreeItem<ClasseGenerique> noeudParent, Etage etage) {
        this.etage = etage;
        pieces = new ArrayList<Piece>();
        noeud = new TreeItem<ClasseGenerique>(this);
        noeudParent.getChildren().add(noeud);
        noeudParent.setExpanded(true);
        nom = "Nouvel Appartement";
        x1 = -10;
        y1 = -7;
        x2 = 10;
        y2 = 7;
    }

    @Override
    public String toString() {
        return "Appartement : " + nom;
    }

    @Override
    public void formulaire(VBox zoneFormulaire) {
        VBox boxCoordo = App.creerCoordo(this, zoneFormulaire);
        VBox boxNom = App.creerNom(this, zoneFormulaire);
        Button boutonPiece = App.creerBouton("Ajouter une pièce");
            boutonPiece.setOnAction(evt -> {
                pieces.add(new Piece(noeud, etage));
                noeud.setExpanded(true);
                App.updateDessin(etage);
            });
        zoneFormulaire.getChildren().addAll(boutonPiece, boxCoordo, boxNom);
    }

    @Override
    public void dessiner(Pane zoneDessin) {
        Rectangle rect = App.rectangle(x1, y1, x2, y2);
        rect.setFill(Color.TRANSPARENT);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(0.25*App.coeffDessin);
        zoneDessin.getChildren().add(rect);
        rect.getStrokeDashArray().addAll(5.0, 10.0);
    }
}
