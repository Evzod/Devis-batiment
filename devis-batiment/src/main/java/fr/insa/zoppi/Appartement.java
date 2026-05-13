package fr.insa.zoppi;

import java.util.ArrayList;
import java.util.List;

public class Appartement {
    private String idAppart; // [cite: 111]
    private int nbrePieces; // [cite: 112]
    private List<Piece> pieces;

    public Appartement(String idAppart) {
        this.idAppart = idAppart;
        this.pieces = new ArrayList<>();
    }
    // Méthodes pour ajouter des pièces...
}
