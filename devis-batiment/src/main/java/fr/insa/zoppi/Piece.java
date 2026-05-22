package fr.insa.zoppi;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.VBox;

public class Piece {
    private String idPiece;
    private String usage;
    private List<Mur> murs;  
    private float x1, y1 = 2;
    private float x2, y2 = 5;
    TreeItem<Object> noeud;

    public Piece(TreeItem<Object> noeudParent) {
        noeud = new TreeItem<Object>(this);
        noeudParent.getChildren().add(noeud);
        this.murs = new ArrayList<>();
    }

    public void formulaire(VBox zoneFormulaire) {
        TextField fieldX1 = new TextField(Float.toString(x1));
        Button boutonX1 = new Button("Valider");
        boutonX1.setOnAction(evt -> {
            
        });
        VBox QuestionX1 = App.creerQuestion("Coordonée de x1", fieldX1, boutonX1);
        zoneFormulaire.getChildren().add(QuestionX1);
    }
    
}
