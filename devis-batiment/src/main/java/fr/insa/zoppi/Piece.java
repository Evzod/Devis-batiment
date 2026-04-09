package fr.insa.zoppi;

import java.util.ArrayList;

public class Piece {
    int numeroPiece;
    int numeroEtage;
    long prixBase = 20000;
    long prixTotal;
    float hauteur;
    ArrayList<Mur> murs;
    Paroi plafond, sol;


    public Piece(int numeroEtage, int numeroAppart, int numeroPiece, float hauteur) {
        this.numeroPiece = numeroPiece;
        this.numeroEtage = numeroEtage;
        this.hauteur = hauteur;
        prixTotal = prixBase;
        System.out.println("Pièce numéro "+numeroPiece+" créé dans l'appartement numéro "+numeroAppart+" (étage "+numeroEtage+")");
        initParois();
    }

    private void initParois() {
        sol = new Paroi("sol");
        System.out.println("Voulez vous un plafond ?");
        if (Lire.b()) {
            plafond = new Paroi("plafond");
        } else {
            plafond = null;
        }
    }

    public long getprixTotal() {
        updatePrixTotal();
        return prixTotal;
    }

    private void updatePrixTotal() {
        prixTotal = prixBase;
    }
}
