package fr.insa.zoppi;

import java.util.*;

public class Batiment_legacy {
    ArrayList<Etage> etages;
    long prixTotal;

    public Batiment(int nbEtage) {
        etages = new ArrayList<Etage>();
        for (int i = 0; i < nbEtage; i++) {
            etages.add(new Etage(i+1));
        }
    }

    public long getprixTotal() {
        updatePrixTotal();
        return prixTotal;
    }


    private void updatePrixTotal() {
        prixTotal = 0;
        for (Etage etage : etages) {
            prixTotal += etage.getprixTotal();
        }
    }
}
