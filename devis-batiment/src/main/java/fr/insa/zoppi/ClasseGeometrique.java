package fr.insa.zoppi;

import javafx.scene.layout.Pane;

public abstract class ClasseGeometrique extends ClasseGenerique {
    double x1, x2, y1, y2;

    public abstract void dessiner(Pane zoneDessin);
}
