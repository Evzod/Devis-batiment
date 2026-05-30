package fr.insa.zoppi;

import java.io.Serializable;

public class Mur implements Serializable{
    double x1, x2, y1, y2;
    String revetement = "";
    int nbPortes = 0;
    int nbFenetres = 0;
    double hauteur;
    final double airePorte = 1.89; // 0,9x2,1
    final double aireFenetre = 1.44; //1,2x1,2

    public Mur(double hauteur, double x1, double y1, double x2, double y2) {
        this.hauteur = hauteur;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void setNbPortes(int nbPortes) {
        this.nbPortes = nbPortes;
    }

    public void setNbFenetres(int nbFenetres) {
        this.nbFenetres = nbFenetres;
    }

    public double getPrix() {
        double aire = hauteur*Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2))-(nbPortes*airePorte+nbFenetres*aireFenetre);
        return Devis.prixRevetement(aire, revetement);
    }
    
}