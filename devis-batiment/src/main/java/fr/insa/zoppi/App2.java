package fr.insa.zoppi;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App2 extends Application {

    private VBox zoneFormulaire;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        TreeItem<String> racineProjet = new TreeItem<>("Devis bâtiment");
        racineProjet.setExpanded(true);

        // --- BRANCHE IMMEUBLE ---
        TreeItem<String> immeuble = new TreeItem<>("Immeuble : Le Batignolles");
        TreeItem<String> etageImm = new TreeItem<>("Étage 1 (Immeuble)");
        TreeItem<String> appart = new TreeItem<>("Appartement 101");
        TreeItem<String> pieceImm = new TreeItem<>("Pièce : Salon (Appart 101)");
        
        appart.getChildren().add(pieceImm);
        etageImm.getChildren().add(appart); // L'appart est dans l'étage
        immeuble.getChildren().add(etageImm);

        // --- BRANCHE MAISON ---
        TreeItem<String> maison = new TreeItem<>("Maison : Villa Rose");
        TreeItem<String> etageMaison = new TreeItem<>("Étage 0 (Maison)");
        TreeItem<String> pieceMaison = new TreeItem<>("Pièce : Cuisine (Maison)");
        
        etageMaison.getChildren().add(pieceMaison); // La pièce est DIRECTEMENT dans l'étage
        maison.getChildren().add(etageMaison);

        racineProjet.getChildren().add(immeuble);
        racineProjet.getChildren().add(maison);
        TreeView<String> arbre = new TreeView<>(racineProjet);

        // ---------------------------------------------------
        // 2. LA ZONE DE FORMULAIRE ET L'ÉCOUTEUR
        // ---------------------------------------------------
        
        zoneFormulaire = new VBox(15); // Espacement augmenté pour la lisibilité
        zoneFormulaire.setPadding(new Insets(10));
        zoneFormulaire.getChildren().add(new Label("Sélectionnez un élément pour le configurer."));

        arbre.getSelectionModel().selectedItemProperty().addListener((property, ancienClic, nouveauClic) -> {
            if (nouveauClic != null) {
                afficherFormulaire(nouveauClic.getValue());
            }
        });

        // ---------------------------------------------------
        // 3. MISE EN PAGE GLOBALE
        // ---------------------------------------------------
        
        VBox menuGauche = new VBox(10);
        menuGauche.setPadding(new Insets(10));
        VBox.setVgrow(arbre, Priority.ALWAYS);
        
        // On met le formulaire dans un ScrollPane car la "Pièce" a beaucoup de questions !
        ScrollPane scrollFormulaire = new ScrollPane(zoneFormulaire);
        scrollFormulaire.setFitToWidth(true);
        scrollFormulaire.setStyle("-fx-background-color: transparent;");

        menuGauche.getChildren().addAll(new Label("Explorateur"), arbre, scrollFormulaire);

        Pane zoneDessin = new Pane();
        zoneDessin.setStyle("-fx-background-color: white;");
        
        // Petit rectangle décoratif pour représenter une pièce
        Rectangle pieceDessin = new Rectangle(100, 100, 200, 150);
        pieceDessin.setFill(Color.LIGHTBLUE);
        pieceDessin.setStroke(Color.BLACK);
        zoneDessin.getChildren().add(pieceDessin);

        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(menuGauche, zoneDessin);
        splitPane.setDividerPositions(0.3);

        Scene scene = new Scene(splitPane, 1200, 700);
        stage.setScene(scene);
        stage.setTitle("Devis-Bâtiment");
        stage.show();
    }

    private void afficherFormulaire(String nomElement) {
        zoneFormulaire.getChildren().clear();

        Label titre = new Label("Configuration : " + nomElement);
        titre.setFont(new Font("Arial", 16));
        titre.setStyle("-fx-font-weight: bold;");
        zoneFormulaire.getChildren().add(titre);

        if (nomElement.contains("Étage")) {
            zoneFormulaire.getChildren().addAll(
                creerBlocQuestion("Longueur de l'étage (m) :", new TextField()),
                creerBlocQuestion("Largeur de l'étage (m) :", new TextField()),
                new Button("Ajouter une Pièce/Appartement")
            );
        } 
        else if (nomElement.contains("Appartement")) {
            zoneFormulaire.getChildren().addAll(
                creerBlocQuestion("Nom/Numéro de l'appartement :", new TextField()),
                new Button("Ajouter une Pièce")
            );
        } 
        else if (nomElement.contains("Pièce")) {
            // --- FORMULAIRE COMPLEXE D'UNE PIÈCE ---
            
            // 1. Nom et fenêtres
            zoneFormulaire.getChildren().addAll(
                creerBlocQuestion("Nom/Fonction de la pièce :", new TextField()),
                creerBlocQuestion("Nombre de fenêtres/portes :", new TextField("0"))
            );

            // 2. Coordonnées (Deux coins opposés)
            HBox coordP1 = new HBox(5, new Label("X1:"), new TextField(), new Label("Y1:"), new TextField());
            HBox coordP2 = new HBox(5, new Label("X2:"), new TextField(), new Label("Y2:"), new TextField());
            zoneFormulaire.getChildren().addAll(new Label("Deux coins opposés :"), coordP1, coordP2);

            // 3. Murs
            zoneFormulaire.getChildren().add(creerBlocAvecCheckbox("Revêtement mural", "Type de revêtement :"));

            // 4. Plafond
            zoneFormulaire.getChildren().add(creerBlocAvecCheckbox("Présence d'un Plafond", "Revêtement plafond :"));

            // 5. Sol
            zoneFormulaire.getChildren().add(creerBlocAvecCheckbox("Présence d'un Sol", "Revêtement sol :"));
            
            Button validerPiece = new Button("Sauvegarder la pièce");
            validerPiece.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
            zoneFormulaire.getChildren().add(validerPiece);
        }
    }

    // --- Méthodes utilitaires pour créer l'interface visuelle rapidement ---

    private VBox creerBlocQuestion(String texteQuestion, Control champReponse) {
        VBox bloc = new VBox(2);
        bloc.getChildren().addAll(new Label(texteQuestion), champReponse);
        return bloc;
    }


    private VBox creerBlocAvecCheckbox(String texteCheckbox, String texteChamp) {
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
}