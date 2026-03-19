import java.util.ArrayList;

public class Etage {
    int numeroEtage;
    long prixBase = 100000;
    long prixTotal;
    ArrayList<Piece> pieces;

    public Etage(int numeroEtage) {
        this.numeroEtage = numeroEtage;
        prixTotal = prixBase;
        System.out.println("Etage numéro " + numeroEtage + " créé");
        createPieces();
    }

    private void createPieces() {
        System.out.println("Combien de pièces a l'étage numéro " + numeroEtage + " ?");
        int nbPieces;
        do {
            nbPieces = Lire.i();
        } while (nbPieces <= 0);
        pieces = new ArrayList<Piece>(nbPieces);
        for (int i = 0; i < nbPieces; i++) {
            pieces.add(new Piece(numeroEtage, i+1));
        }
    }

    public long getprixTotal() {
        updatePrixTotal();
        return prixTotal;
    }

    private void updatePrixTotal() {
        prixTotal = prixBase;
        for (Piece piece : pieces) {
            prixTotal += piece.getprixTotal();
        }
    }
}
