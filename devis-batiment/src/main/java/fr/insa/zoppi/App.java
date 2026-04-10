package fr.insa.zoppi;

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.geometry.Pos;

/*
 * JavaFX App
 */
public class App extends Application {


    private Scene scene;
    static Label message;
    static Button bouton;
    static TextField entree;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        message = new Label("Devis bâtiment");
        message.setFont(new Font(40));

        entree = new TextField();

        bouton = new Button("Valider");
        bouton.setOnAction(evt -> {
            newDevis1();
        });

        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(100);
        root.setVgap(100);
        root.add(message, 0, 0);
        root.add(entree, 0, 1);
        root.add(bouton, 0, 2);//le centrer si possible, jsp commet faire

        scene = new Scene(root, 650, 400);
        stage.setScene(scene);
        stage.setTitle("Devis-bâtiment");
        stage.show();

    }

    private static void newDevis1() {
        message.setText("Combien d'étages a le bâtiment ?");
        bouton.setOnAction(evt -> newDevis2());
    }

    private static void newDevis2() {
        int nbEtage = 0;
        try {
            nbEtage = Integer.parseInt(entree.getText());
        } catch (Exception e) {
            message.setText("Erreur : entrez un entier");
            return;
        }
        Batiment bat = new Batiment(nbEtage);
        message.setText("Le prix total du bâtiment est : " + bat.getprixTotal());
        bouton.setOnAction(evt -> newDevis1());
    }

}