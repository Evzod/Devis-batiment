package fr.insa.zoppi;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    private VBox zoneFormulaire;
    private Devis devis;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        devis = new Devis();
        TreeItem<Object> racineProjet = new TreeItem<>(devis);
        racineProjet.setExpanded(true);
        TreeView<Object> arbre = new TreeView<>(racineProjet);
        
        zoneFormulaire = new VBox(15);
        zoneFormulaire.setPadding(new Insets(10));
        zoneFormulaire.getChildren().add(new Label("Sélectionnez un élément pour le configurer."));

        arbre.getSelectionModel().selectedItemProperty().addListener((property, ancienClic, nouveauClic) -> {
            if (nouveauClic != null) {
                afficherFormulaire(nouveauClic.getValue());
            }
        });

        VBox menuGauche = new VBox(10);
        menuGauche.setPadding(new Insets(10));
        VBox.setVgrow(arbre, Priority.ALWAYS);

        ScrollPane scrollFormulaire = new ScrollPane(zoneFormulaire);
        scrollFormulaire.setFitToWidth(true);
        scrollFormulaire.setStyle("-fx-background-color: transparent;");
        
        menuGauche.getChildren().addAll(new Label("Explorateur"), arbre, scrollFormulaire);

        Pane zoneDessin = new Pane();
        zoneDessin.setStyle("-fx-background-color: white;");
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(menuGauche, zoneDessin);
        splitPane.setDividerPositions(0.3);

        Scene scene = new Scene(splitPane, 1200, 700);
        stage.setScene(scene);
        stage.setTitle("Devis-Bâtiment");
        stage.show();

    }

    public void afficherFormulaire(Object objet) {
        zoneFormulaire.getChildren().clear();

        Label titre = new Label("Configuration : " + objet.toString());
        titre.setFont(new Font("Arial", 16));
        titre.setStyle("-fx-font-weight: bold;");
        zoneFormulaire.getChildren().add(titre);

        if (objet instanceof Devis) {
            devis.formulaire(zoneFormulaire);
        }/* else if (objet instanceof Batiment) {
            ((Batiment) objet).formulaire(zoneFormulaire);
        } else if (objet instanceof Appartement) {
            ((Appartement) objet).formulaire(zoneFormulaire);
        } else if (objet instanceof Etage) {
            ((Etage) objet).formulaire(zoneFormulaire);
        } else if (objet instanceof Piece) {
            ((Piece) objet).formulaire(zoneFormulaire);
        }*/

    }
    
    public static VBox creerQuestion(String texteQuestion, Control champReponse) {
        VBox bloc = new VBox(2);
        bloc.getChildren().addAll(new Label(texteQuestion), champReponse);
        return bloc;
    }

    public static VBox creerCheckbox(String texteCheckbox, String texteChamp) {
        VBox bloc = new VBox(5);
        bloc.setStyle("-fx-border-color: lightgray; -fx-border-radius: 5; -fx-padding: 5;");
        
        CheckBox checkBox = new CheckBox(texteCheckbox);
        checkBox.setSelected(true);
        
        TextField champRevetement = new TextField();
        champRevetement.setPromptText("Ex: Peinture, Carrelage...");
        
        champRevetement.disableProperty().bind(checkBox.selectedProperty().not());

        bloc.getChildren().addAll(checkBox, new Label(texteChamp), champRevetement);
        return bloc;
    }

    public static Button creerBouton(String texteBouton) {
        Button bouton = new Button(texteBouton);
        //Actions pour la mise en forme du bouton
        return bouton;
    }

}