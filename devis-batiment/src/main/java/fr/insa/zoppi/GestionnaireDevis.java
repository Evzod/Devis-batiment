package fr.insa.zoppi;

import java.io.FileWriter;
import java.io.IOException;

public class GestionnaireDevis {

    // Calcule le devis : Surface totale par revêtement * Prix total [cite: 142]
    public double calculerDevisTotal(Batiment batiment) {
        double devisTotal = 0.0;
        
        // Logique à implémenter :
        // 1. Parcourir tous les niveaux, appartements, pièces et murs.
        // 2. Calculer les surfaces :
        //    - Surface d'un mur = longueur du mur * hauteur sous plafond du niveau.
        //    - Soustraire la surface des ouvertures (portes et fenêtres).
        // 3. Multiplier chaque surface nette par le prix unitaire du revêtement associé[cite: 133].
        
        return devisTotal;
    }

    // Sauvegarde en fichier texte [cite: 143]
    public void sauvegarderDonnees(Batiment batiment, String cheminFichier) {
        try (FileWriter writer = new FileWriter(cheminFichier)) {
            // Sauvegarder la définition des points, des pièces et des niveaux [cite: 144]
            writer.write("--- Données du Bâtiment ---\n");
            writer.write("ID: " + batiment.getIdBatiment() + "\n");
            // Parcourir et écrire les données géométriques...
            
            System.out.println("Données sauvegardées avec succès.");
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }
}