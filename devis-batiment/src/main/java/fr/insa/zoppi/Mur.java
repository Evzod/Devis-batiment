package fr.insa.zoppi;

import java.util.ArrayList;
import java.util.List;

public class Mur {
    double x1, x2, y1, y2;
    String revetement = "";
    int nbPortes = 0;
    int nbFenetres = 0;

    public Mur(double x1, double y1, double x2, double y2) {
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
    
}