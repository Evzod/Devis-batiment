public class MainDevis {
    public static void main(String[] args) {
        boolean boucle;
        do {
            newDevis();
            System.out.println("Voulez vous faire un autre devis ?");
            boucle = Lire.b();
        } while (boucle);
    }

    private static void newDevis() {
        int nbEtage;
        System.out.println("Combien d'étages a le bâtiment ?");
        do {
            nbEtage = Lire.i();
        } while (nbEtage <= 0);
        Batiment bat = new Batiment(nbEtage);
    }
}
