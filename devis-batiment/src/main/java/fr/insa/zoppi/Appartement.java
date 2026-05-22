package fr.insa.zoppi;

import java.util.ArrayList;
import java.util.List;

public class Appartement {
    int numeroAppart;
    int numeroEtage;
    long prixBase = 50000;
    long prixTotal;
    ArrayList<Piece> pieces;
    float hauteur;

    public Appartement(String idAppart) {
        this.idAppart = idAppart;
        this.pieces = new ArrayList<>();
    }
    // Méthodes pour ajouter des pièces...
}
