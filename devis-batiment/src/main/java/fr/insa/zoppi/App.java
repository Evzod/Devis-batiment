package fr.insa.zoppi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {

    private VBox zoneFormulaire;
    private static Devis devis;
    private static Pane zoneDessin;
    public static int espacementVBox = 10;
    public static double coeffDessin = 10;
    private static Etage etagePrecedent;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        devis = new Devis();
        TreeItem<ClasseGenerique> racineProjet = new TreeItem<>(devis);
        devis.setTreeItem(racineProjet);
        racineProjet.setExpanded(true);
        TreeView<ClasseGenerique> arbre = new TreeView<>(racineProjet);
        arbre.setStyle("-fx-font-size: 13px");

        zoneFormulaire = new VBox(25);
        zoneFormulaire.setPadding(new Insets(10));
        zoneFormulaire.getChildren().add(new Label("Sélectionnez un élément pour le configurer."));

        arbre.getSelectionModel().selectedItemProperty().addListener((property, ancienClic, nouveauClic) -> {
            if (nouveauClic != null) {
                afficherFormulaire(nouveauClic.getValue());
            }
        });

        VBox menuGauche = new VBox(espacementVBox);
        menuGauche.setPadding(new Insets(10));
        VBox.setVgrow(arbre, Priority.ALWAYS);

        ScrollPane scrollFormulaire = new ScrollPane(zoneFormulaire);
        scrollFormulaire.setFitToWidth(true);
        scrollFormulaire.setStyle("-fx-background-color: transparent;");
        
        menuGauche.getChildren().addAll(new Label("Explorateur"), arbre);

        zoneDessin = new Pane();
        zoneDessin.setStyle("-fx-background-color: white;");
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(menuGauche, scrollFormulaire, zoneDessin);
        splitPane.setDividerPositions(0.175, 0.35);

        Scene scene = new Scene(splitPane);
        stage.setScene(scene);
        stage.setTitle("Devis-Bâtiment");
        stage.setMaximized(true);
        stage.show();

    }

    public void afficherFormulaire(ClasseGenerique objet) {
        initFormulaire(zoneFormulaire, objet);
        if (objet instanceof Devis) {
            devis.formulaire(zoneFormulaire);
        } else if (objet instanceof Batiment) {
            ((Batiment) objet).formulaire(zoneFormulaire);
        } else if (objet instanceof Appartement) {
            ((Appartement) objet).formulaire(zoneFormulaire);
        } else if (objet instanceof Etage) {
            ((Etage) objet).formulaire(zoneFormulaire);
        } else if (objet instanceof Piece) {
            ((Piece) objet).formulaire(zoneFormulaire);
        }
    }

    public static void initFormulaire(VBox zone, ClasseGenerique objet) {
        zone.getChildren().clear();
        Label titre = new Label("Configuration : " + objet.nom);
        titre.setWrapText(true);
        titre.setFont(new Font("Arial", 16));
        titre.setStyle("-fx-font-weight: bold;");
        zone.getChildren().add(titre);
    }
    
    public static VBox creerQuestion(String texteQuestion, Control champReponse, Button valider) {
        VBox bloc = new VBox(espacementVBox);
        bloc.getChildren().addAll(new Label(texteQuestion), champReponse, valider);
        return bloc;
    }

    public static VBox creerCheckbox(String texteCheckbox, TextField champRevetement, Button valider) {
        VBox bloc = new VBox(espacementVBox);
        bloc.setStyle("-fx-border-color: lightgray; -fx-border-radius: 5; -fx-padding: 5;");
        
        CheckBox checkBox = new CheckBox(texteCheckbox);
        checkBox.setSelected(!champRevetement.getText().isBlank());
        
        champRevetement.disableProperty().bind(checkBox.selectedProperty().not());
        valider.disableProperty().bind(checkBox.selectedProperty().not());

        bloc.getChildren().addAll(checkBox, new Label("Revêtement :"), champRevetement, valider);
        return bloc;
    }

    public static VBox creerNom(ClasseGenerique objet, VBox zoneFormulaire) {
        TextField fieldNom = new TextField(objet.nom);
        Button boutonNom = new Button("Valider");
        boutonNom.setOnAction(evt -> {
            objet.nom = fieldNom.getText();
            objet.noeud.setValue(null);
            objet.noeud.setValue(objet);
            App.initFormulaire(zoneFormulaire, objet);
            objet.formulaire(zoneFormulaire);
        });
        return App.creerQuestion("Nom de l'élément", fieldNom, boutonNom);
    }


    public static VBox creerCoordo(ClasseGeometrique objet, VBox zoneFormulaire) {
        TextField fieldX1 = new TextField(Double.toString(objet.x1));
        fieldX1.setPrefWidth(60);
        TextField fieldY1 = new TextField(Double.toString(objet.y1));
        fieldY1.setPrefWidth(60);
        TextField fieldX2 = new TextField(Double.toString(objet.x2));
        fieldX2.setPrefWidth(60);
        TextField fieldY2 = new TextField(Double.toString(objet.y2));
        fieldY2.setPrefWidth(60);

        HBox ligne1 = new HBox(10, new Label("Coin 1 - X :"), fieldX1, new Label("Y :"), fieldY1);
        HBox ligne2 = new HBox(10, new Label("Coin 2 - X :"), fieldX2, new Label("Y :"), fieldY2);

        Button boutonValider = App.creerBouton("Valider");
        
        boutonValider.setOnAction(evt -> {
            objet.x1 = Double.parseDouble(fieldX1.getText());
            objet.y1 = Double.parseDouble(fieldY1.getText());
            objet.x2 = Double.parseDouble(fieldX2.getText());
            objet.y2 = Double.parseDouble(fieldY2.getText());
            App.updateDessin(objet.etage);
        });

        VBox bloc = new VBox(espacementVBox);
        bloc.setStyle("-fx-border-color: lightgray; -fx-border-radius: 5; -fx-padding: 10;");
        bloc.getChildren().addAll(new Label("Coordonnées de l'élément"), ligne1, ligne2, boutonValider);

        return bloc;
    }

    public static Button creerBouton(String texteBouton) {
        Button bouton = new Button(texteBouton);
        //Actions pour la mise en forme du bouton
        return bouton;
    }

    public static void updateDessin(Etage etage) {
        zoneDessin.getChildren().clear();
        if (etage == null) {
            etage = etagePrecedent;
        } else {
            etagePrecedent = etage;
        }
        if (etage.apparts!=null) {
            for (Appartement appart : etage.apparts) {
                for (Piece piece : appart.pieces) {
                    piece.dessiner(zoneDessin);
                }
                appart.dessiner(zoneDessin);
            }
        } else if (etage.pieces!=null) {
            for (Piece piece : etage.pieces) {
                piece.dessiner(zoneDessin);
            }
        } else {
            System.out.println("pas bon");
        }
        etage.dessiner(zoneDessin);
    }

    public static Rectangle rectangle(double x1, double y1, double x2, double y2) {
        double startX = (zoneDessin.getWidth()/2) + coeffDessin*Math.min(x1, x2);
        double startY = (zoneDessin.getHeight()/2) + coeffDessin*Math.min(y1, y2);
        double largeur = coeffDessin*Math.abs(x2 - x1);
        double hauteur = coeffDessin*Math.abs(y2 - y1);

        return new Rectangle(startX, startY, largeur, hauteur);
    }
    
    public static void sauvegarderProjet(Devis devis, String cheminFichier) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cheminFichier))) {
            oos.writeObject(devis); 
            System.out.println("Projet sauvegardé avec succès !");
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }
    
    public static Devis chargerProjet(String cheminFichier) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cheminFichier))) {
            return (Devis) ois.readObject(); 
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement : " + e.getMessage());
            return null;
        }
    }
    
}