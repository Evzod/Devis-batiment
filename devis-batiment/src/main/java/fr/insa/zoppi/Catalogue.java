package fr.insa.zoppi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Catalogue {
    private HashMap<Integer, Revetement> listeRevetements;

    public Catalogue() {
        listeRevetements = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("devis-batiment\\src\\main\\java\\fr\\insa\\zoppi\\catalogue.txt"))) {
            String ligne;
            boolean premiereLigne = true;

            while ((ligne = br.readLine()) != null) {
                if (premiereLigne) {
                    premiereLigne = false;
                    continue; 
                }
                
                String[] colonnes = ligne.split(";");

                int id = Integer.parseInt(colonnes[0]);
                String nom = colonnes[1];
                boolean pourMur = colonnes[2].equals("1");
                boolean pourSol = colonnes[3].equals("1");
                boolean pourPlafond = colonnes[4].equals("1");
                double prix = Double.parseDouble(colonnes[5]);
                Revetement rev = new Revetement(id, nom, pourMur, pourSol, pourPlafond, prix);
                listeRevetements.put(id, rev);
            }

        } catch (IOException e) {
            System.out.println("Erreur de lecture : " + e.getMessage());
            System.out.println("Chemin absolu cherché : " + new java.io.File("catalogue.txt").getAbsolutePath());
        }
    }
    
    public Revetement getRevetement(int id) {
        return listeRevetements.get(id);
    }
}