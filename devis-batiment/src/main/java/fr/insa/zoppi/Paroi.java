package fr.insa.zoppi;

public class Paroi {
    private String revetement;

    public Paroi(String nom) {
        System.out.println("Voulez vous un revêtement sur votre " + nom + " ?");
        if (Lire.b()) {
            System.out.println("Entrez le nom du revêtement"); //à modifier à termes
            revetement = Lire.S();
        }
    }

    public String getRevetement() {
        return revetement;
    }
}
