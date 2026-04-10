package fr.insa.zoppi;

import java.util.ArrayList;

public class Etage {
    int numeroEtage;
    long prixBase = 100000;
    long prixTotal;
    ArrayList<Appartement> apparts;
    float hauteur;

    public Etage(int numeroEtage) {
        this.numeroEtage = numeroEtage;
        prixTotal = prixBase;
        App.message.setText("Etage numéro " + numeroEtage + " créé");
        //initHauteur();
        //createApparts();
    }

    private void createApparts() {
        App.message.setText("Combien d'appartements a l'étage numéro " + numeroEtage + " ?");
        int nbApparts;
        do {
            nbApparts = Lire.i();
        } while (nbApparts <= 0);
        apparts = new ArrayList<Appartement>(nbApparts);
        for (int i = 0; i < nbApparts; i++) {
            apparts.add(new Appartement(numeroEtage, i+1, hauteur));
        }
    }

    public void initHauteur() {
        do {
            App.message.setText("Quelle est la hauteur de l'étage numéro " + numeroEtage + " ?");
            hauteur = Lire.f();
        } while (hauteur <= 0);
    }

    public long getprixTotal() {
        updatePrixTotal();
        return prixTotal;
    }

    private void updatePrixTotal() {
        prixTotal = prixBase;
        for (Appartement appart : apparts) {
            prixTotal += appart.getprixTotal();
        }
    }
}
