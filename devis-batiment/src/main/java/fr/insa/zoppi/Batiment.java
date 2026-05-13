package fr.insa.zoppi;


import java.util.ArrayList;
import java.util.List;

public class Batiment {
    private String idBatiment; // [cite: 104]
    private String typeBatiment; // maison ou immeuble [cite: 105]
    private int nbreNiveaux; // [cite: 105]
    private List<Niveau> niveaux;

    public Batiment(String idBatiment, String typeBatiment) {
        this.idBatiment = idBatiment;
        this.typeBatiment = typeBatiment;
        this.niveaux = new ArrayList<>();
    }
    // Méthodes pour ajouter des niveaux...
}