package fr.insa.zoppi;

import java.util.ArrayList;

public class Appartement {
    int numeroAppart;
    int numeroEtage;
    long prixBase = 50000;
    long prixTotal;
    ArrayList<Piece> pieces;
    float hauteur;

    public Appartement(int numeroEtage, int numeroAppart, float hauteur) {
        this.numeroAppart = numeroAppart;
        this.numeroEtage = numeroEtage;
        this.hauteur = hauteur;
        prixTotal = prixBase;
        System.out.println("Appartement numéro " + numeroAppart + " créé dans l'étage numéro " + numeroEtage);
        createPieces();
    }

    private void createPieces() {
        int nbPieces;
        do {
            System.out.println("Combien de pièces a l'appartement numéro " + numeroEtage + " ?");
            nbPieces = Lire.i();
        } while (nbPieces <= 0);
        pieces = new ArrayList<Piece>(nbPieces);
        for (int i = 0; i < nbPieces; i++) {
            pieces.add(new Piece(numeroEtage, numeroAppart, i+1, hauteur));
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
