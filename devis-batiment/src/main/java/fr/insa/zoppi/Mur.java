package fr.insa.zoppi;

import java.util.ArrayList;
import java.util.List;

public class Mur {
    private String idMur; // [cite: 122]
    private Point debut; // [cite: 123]
    private Point fin; // [cite: 123]
    private List<Ouverture> ouvertures; // [cite: 124]
    private List<Revetement> revetements; // Un mur peut comporter des revêtements [cite: 120]

    public Mur(String idMur, Point debut, Point fin) {
        this.idMur = idMur;
        this.debut = debut;
        this.fin = fin;
        this.ouvertures = new ArrayList<>();
        this.revetements = new ArrayList<>();
    }

    public void ajouterOuverture(Ouverture o) {
        this.ouvertures.add(o);
    }

    public void ajouterRevetement(Revetement r) {
        this.revetements.add(r);
    }
    
    // Méthode pour calculer la longueur du mur (racine carrée de (x2-x1)^2 + (y2-y1)^2)
    public double getLongueur() {
        // A implémenter : calcul de la distance entre le point de début et de fin
        return 0.0; 
    }
}