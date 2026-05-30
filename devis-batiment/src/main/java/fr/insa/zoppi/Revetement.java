package fr.insa.zoppi;

public class Revetement {
    private int id;
    private String nom;
    private boolean pourMur;
    private boolean pourSol;
    private boolean pourPlafond;
    private double prixUnitaire;

    public Revetement(int id, String nom, boolean pourMur, boolean pourSol, boolean pourPlafond, double prixUnitaire) {
        this.id = id;
        this.nom = nom;
        this.pourMur = pourMur;
        this.pourSol = pourSol;
        this.pourPlafond = pourPlafond;
        this.prixUnitaire = prixUnitaire;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public double getPrixUnitaire() { return prixUnitaire; }
    public boolean isPourMur() { return pourMur; }
    public boolean isPourSol() { return pourSol; }
    public boolean isPourPlafond() { return pourPlafond; }

}