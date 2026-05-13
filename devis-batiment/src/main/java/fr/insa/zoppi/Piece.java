package fr.insa.zoppi;

import java.util.ArrayList;
import java.util.List;

public class Piece {
    private String idPiece; // [cite: 116]
    private String usage; // chambre, salle de bain, etc. [cite: 117]
    private List<Mur> murs; // les murs qui la délimitent [cite: 117]
    private Revetement sol; // [cite: 118]
    private Revetement plafond; // un plafond ou pas [cite: 119]

    public Piece(String idPiece, String usage) {
        this.idPiece = idPiece;
        this.usage = usage;
        this.murs = new ArrayList<>();
    }
    // Méthodes pour ajouter des murs, définir le sol et le plafond...
}
