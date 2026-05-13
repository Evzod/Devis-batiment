package fr.insa.zoppi;

import java.util.ArrayList;
import java.util.List;

public class Niveau {
    private String idNiveau; // [cite: 107]
    private int nbreAppartements; // [cite: 108]
    private double hauteurSousPlafond; // [cite: 109]
    private List<Appartement> appartements;

    public Niveau(String idNiveau, double hauteurSousPlafond) {
        this.idNiveau = idNiveau;
        this.hauteurSousPlafond = hauteurSousPlafond; // identique pour un niveau donné [cite: 100]
        this.appartements = new ArrayList<>();
    }
    // Méthodes pour ajouter des appartements...
}
