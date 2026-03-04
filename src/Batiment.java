public class Batiment {
    Etage[] etages;

    public Batiment(int nbEtage) {
        etages = new Etage[nbEtage];
        for (int i = 0; i < etages.length; i++) {
            etages[i] = new Etage(i+14);
        }
    }
}
