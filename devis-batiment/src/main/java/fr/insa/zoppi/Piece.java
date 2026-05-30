package fr.insa.zoppi;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Piece extends ClasseGeometrique{
    private String usage;
    boolean plafond = true;
    String revetementPlafond = "";
    private Mur murs[];

    public Piece(TreeItem<ClasseGenerique> noeudParent, Etage etage) {
        this.etage = etage;
        noeud = new TreeItem<ClasseGenerique>(this);
        noeudParent.getChildren().add(noeud);
        murs = new Mur[4];
        nom = "Nouvelle Pièce";
        x1 = -5;
        y1 = -5;
        x2 = 5;
        y2 = 5;
        murs[0] = new Mur(x1, y1, x2, y1);//haut
        murs[1] = new Mur(x2, y1, x2, y2);//droite
        murs[2] = new Mur(x1, y2, x2, y2);//bas
        murs[3] = new Mur(x1, y1, x1, y2);//gauche
        App.updateDessin(etage);
    }
    
    @Override
    public String toString() {
        return "Pièce : " + nom;
    }

    public void formulaire(VBox zoneFormulaire) {
        VBox boxCoordo = App.creerCoordo(this, zoneFormulaire);
        VBox boxNom = App.creerNom(this, zoneFormulaire);
        
        zoneFormulaire.getChildren().addAll(boxCoordo, boxNom);
        
        for (int i=0; i<4; i++) {
            final int index = i;

            Label titre = new Label("Mur " + (index+1));
            titre.setFont(new Font("Arial", 13));

            VBox bloc = new VBox(App.espacementVBox);
            bloc.setStyle("-fx-border-color: lightgray; -fx-border-radius: 5; -fx-padding: 5;");
        
            VBox boxRevetement = boxRevetementMur(index);

            TextField fieldPortes = new TextField(Integer.toString(murs[index].nbPortes));
            Button boutonPortes = App.creerBouton("Valider");
            boutonPortes.setOnAction(evt -> {
                murs[index].setNbPortes(Integer.parseInt(fieldPortes.getText()));
            });
            VBox boxPortes = App.creerQuestion("Nombre de portes", fieldPortes, boutonPortes);
            
            TextField fieldFenetres = new TextField(Integer.toString(murs[index].nbFenetres));
            Button boutonFenetres = App.creerBouton("Valider");
            boutonFenetres.setOnAction(evt -> {
                murs[index].setNbFenetres(Integer.parseInt(fieldFenetres.getText()));
            });
            VBox boxFenetres = App.creerQuestion("Nombre de fenêtres", fieldFenetres, boutonFenetres);

            bloc.getChildren().addAll(titre, boxPortes, boxFenetres, boxRevetement);
            zoneFormulaire.getChildren().add(bloc);
        }

        CheckBox checkPlafond = new CheckBox("Plafond dans la pièce");
        checkPlafond.setSelected(plafond);
        checkPlafond.selectedProperty().addListener((property, ancienneValeur, nouvelleValeur) -> {
            plafond = nouvelleValeur;
        });
        
        TextField fieldRevetementPlafond = new TextField(revetementPlafond);
        Button boutonRevetementPlafond = App.creerBouton("Valider");
        boutonRevetementPlafond.setOnAction(evt -> {
            revetementPlafond = fieldRevetementPlafond.getText();
        });
        VBox boxPlafond = App.creerCheckbox("Revêtement sur le plafond ?", fieldRevetementPlafond, boutonRevetementPlafond);
        boxPlafond.visibleProperty().bind(checkPlafond.selectedProperty());
        boxPlafond.managedProperty().bind(checkPlafond.selectedProperty());

        zoneFormulaire.getChildren().addAll(checkPlafond, boxPlafond);
        
    }

    public VBox boxRevetementMur(int i) {
        TextField fieldRevetement = new TextField(murs[i].revetement);
        Button boutonRevetement = App.creerBouton("Valider");
        boutonRevetement.setOnAction(evt -> {
            murs[i].revetement = fieldRevetement.getText();
        });
        VBox boxRevetement = App.creerCheckbox("Revêtement sur le mur " + Integer.toString(i+1) + " ?", fieldRevetement, boutonRevetement);
        return boxRevetement;
    }

    @Override
    public void dessiner(Pane zoneDessin) {
        Rectangle rect = App.rectangle(x1, y1, x2, y2);
        rect.setFill(Color.TRANSPARENT);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(0.2*App.coeffDessin);
        zoneDessin.getChildren().add(rect);
    }
}
