package fr.insa.zoppi;

public class Mur extends Paroi {
    private Point p1, p2;

    public Mur(Point p1, Point p2) {
        super("mur");
        this.p1 = p1;
        this.p2 = p2;
    }

    public void setPoint(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public float longueur() {
        return p1.distance(p2);
    }
}
