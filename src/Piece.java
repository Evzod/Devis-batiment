public class Piece {
    int numeroPiece;
    int numeroEtage;
    long prixBase = 20000;
    long prixTotal;

    public Piece(int numeroEtage, int numeroPiece) {
        this.numeroPiece = numeroPiece;
        this.numeroEtage = numeroEtage;
        prixTotal = prixBase;
        System.out.println("Pièce numéro " + numeroPiece + " créé dans l'étage numéro " + numeroEtage);
    }

    public long getprixTotal() {
        updatePrixTotal();
        return prixTotal;
    }

    private void updatePrixTotal() {
        prixTotal = prixBase;
    }
}
