package fr.insa.zoppi;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Conteneur principal
        BorderPane root = new BorderPane();

        // ---------------------------------------------------
        // 1. MENU LATÉRAL GAUCHE (VBox)
        // ---------------------------------------------------
        VBox menuGauche = new VBox(15); // Espacement de 15px entre les éléments
        menuGauche.setPadding(new Insets(20));
        menuGauche.setStyle("-fx-background-color: #2c3e50;");
        menuGauche.setPrefWidth(250);

        // Liste déroulante des niveaux
        Label labelNiveau = new Label("Sélectionner un niveau :");
        labelNiveau.setTextFill(Color.WHITE);
        ComboBox<String> comboNiveaux = new ComboBox<>();
        comboNiveaux.getItems().addAll("Niveau 1 (RDC)", "Niveau 2");
        comboNiveaux.setValue("Niveau 1 (RDC)");
        comboNiveaux.setMaxWidth(Double.MAX_VALUE);

        // Liste déroulante des pièces
        Label labelPiece = new Label("Sélectionner une pièce :");
        labelPiece.setTextFill(Color.WHITE);
        ComboBox<String> comboPieces = new ComboBox<>();
        comboPieces.getItems().addAll("Toutes les pièces", "Pièce 1", "Pièce 2", "Pièce 3", "Pièce 4");
        comboPieces.setValue("Toutes les pièces");
        comboPieces.setMaxWidth(Double.MAX_VALUE);

        // Affichage simulé du devis
        Label labelDevis = new Label("\nDevis Total :");
        labelDevis.setTextFill(Color.WHITE);
        labelDevis.setFont(Font.font("System", javafx.scene.text.FontWeight.BOLD, 14));
        Label valeurDevis = new Label("0.00 €");
        valeurDevis.setTextFill(Color.LIGHTGREEN);

        menuGauche.getChildren().addAll(labelNiveau, comboNiveaux, labelPiece, comboPieces, labelDevis, valeurDevis);
        root.setLeft(menuGauche);


        // ---------------------------------------------------
        // 2. ZONE CENTRALE (Plan 2D)
        // ---------------------------------------------------
        Pane zoneCentrale = new Pane();
        zoneCentrale.setStyle("-fx-background-color: #ecf0f1;");
        
        // Dessin des pièces selon l'exemple du sujet
        dessinerPlan(zoneCentrale);
        
        root.setCenter(zoneCentrale);


        // ---------------------------------------------------
        // 3. CONFIGURATION DE LA FENÊTRE
        // ---------------------------------------------------
        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Devis Bâtiment - Interface JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Méthode pour générer les pièces du plan d'exemple
     */
    private void dessinerPlan(Pane pane) {
        // Décalage (offset) pour centrer le plan dans la zone
        double startX = 100;
        double startY = 80;

        // Modélisation des 4 pièces selon l'image du document
        ajouterPiece(pane, "Pièce 4", startX, startY, 300, 150);
        ajouterPiece(pane, "Pièce 1", startX + 300, startY, 300, 250);
        ajouterPiece(pane, "Pièce 3", startX, startY + 150, 300, 250);
        ajouterPiece(pane, "Pièce 2", startX + 300, startY + 250, 300, 150);
    }

    /**
     * Méthode utilitaire pour dessiner une pièce rectangulaire
     */
    private void ajouterPiece(Pane pane, String nom, double x, double y, double largeur, double hauteur) {
        // Création des murs (le rectangle)
        Rectangle rect = new Rectangle(x, y, largeur, hauteur);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(2);

        // Création du label de la pièce
        Text texte = new Text(nom);
        texte.setFont(Font.font("Arial", 16));
        
        // Calcul pour centrer le texte dans le rectangle
        // Note : On estime la largeur du texte pour l'ajustement
        texte.setX(x + largeur / 2 - 25); 
        texte.setY(y + hauteur / 2 + 5);

        // Ajout du rectangle et du texte au Pane central
        pane.getChildren().addAll(rect, texte);
    }

    public static void main(String[] args) {
        launch(args);
    }
}