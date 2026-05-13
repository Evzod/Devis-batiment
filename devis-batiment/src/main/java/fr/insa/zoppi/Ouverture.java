package fr.insa.zoppi;




// Les ouvertures dans les murs [cite: 124]
public class Ouverture {
    private String type; // "Porte" ou "Fenetre"
    private double largeur;
    private double hauteur;

    public Ouverture(String type) {
        this.type = type;
        if (type.equalsIgnoreCase("Porte")) {
            this.largeur = 0.90; // 
            this.hauteur = 2.10; // 
        } else if (type.equalsIgnoreCase("Fenetre")) {
            this.largeur = 1.20; // 
            this.hauteur = 1.20; // 
        }
    }
}
