package fr.insa.zoppi;

public class Point {
    private float x, y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    //obsolète
    /*public float[] getPoint() {
        float[] c = new float[2];
        c[0] = x;
        c[1] = y;
        return c;
    }*/

    public float distance() {
        float d = (float) Math.sqrt(x*x+y*y);
        return d;
    }
    
    public float distance(Point point) { 
        float dx = Math.abs(x - point.getX());
        float dy = Math.abs(y - point.getY());
        float d = (float) Math.sqrt(dx*dx+dy*dy);
        return d;
    }

    public void deplacer(float dx, float dy) {
        x += dx;
        y += dy;
    }

    /*public Point pointMilieu(Point point) {
        return new Point((this.x + point.getX())/2, (this.y + point.getY())/2);
    }

    public Point[] voisinsProches(Point[] points) {
        Point[] l = new Point[2];
        for (int i = 0; i<points.length; i++) {
            if (l[0]==null||distance(points[i])<distance(l[0])) {
                l[0] = points[i];
            } else if (l[1]==null||distance(points[i])<distance(l[1])) {
                l[1] = points[i];
            }
        }
        return l;
    }*/

    
}
