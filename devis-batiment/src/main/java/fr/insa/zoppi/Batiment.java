package fr.insa.zoppi;

import java.util.*;

public class Batiment {
    private String typeBatiment;
    private int nbEtage = 1;
    private ArrayList<Etage> etages;
    long prixTotal;

    public Batiment(String typeBatiment) {
        this.typeBatiment = typeBatiment;
        etages = new ArrayList<Etage>();
    }


}