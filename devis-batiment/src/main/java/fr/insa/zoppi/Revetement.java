package fr.insa.zoppi;

// Définition d'un revêtement du catalogue [cite: 130]
public class Revetement {
    private String id; // [cite: 131]
    private String typeRevetement; // [cite: 132]
    private double prixUnitaire; // (euros/m2) [cite: 133]

    public Revetement(String id, String typeRevetement, double prixUnitaire) {
        this.id = id;
        this.typeRevetement = typeRevetement;
        this.prixUnitaire = prixUnitaire;
    }
    // Getters et Setters...
}
