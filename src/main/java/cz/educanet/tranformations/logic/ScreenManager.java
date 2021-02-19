package cz.educanet.tranformations.logic;

import cz.educanet.tranformations.logic.models.Coordinate;

import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class ScreenManager {

    private Set<Coordinate> selectedPoints = new HashSet<>();

    public void select(Coordinate coordinate) {
        selectedPoints.add(coordinate);
    }

    public void unselect(Coordinate coordinate) {
        selectedPoints.remove(coordinate);
    }

    public boolean isSelected(Coordinate coordinate) {
        return selectedPoints.contains(coordinate);
    }

    public Set<Coordinate> getSelectedPoints() {
        return selectedPoints;
    }

    public int edgeFunction(Coordinate x, Coordinate y, Coordinate z) {
        return (y.getY() - z.getY()) * (x.getX() - z.getX()) - (z.getX() - y.getX()) * (z.getY() - x.getY());
    }

    public boolean isFilledIn(Coordinate coordinate) { // TODO: Implement this
        Coordinate[] arr = selectedPoints.toArray(new Coordinate[]{});
        Coordinate xx = arr[0];
        Coordinate yy = arr[1];
        Coordinate zz = arr[2];
        int xxx = ((coordinate.getX() - zz.getX()) * (yy.getY() - zz.getY())) + ((coordinate.getY()) - zz.getY()) * (zz.getX() - yy.getX());
        int yyy = ((coordinate.getX() - zz.getX()) * (zz.getY() - xx.getY())) + ((coordinate.getY()) - zz.getY()) * (xx.getX() - zz.getX());
        int zzz = edgeFunction(xx, yy, zz) - xxx - yyy;
        int minimum = Math.min(edgeFunction(xx, yy, zz), 0);
        int maximum = Math.max(edgeFunction(xx, yy, zz), 0);
        if(xxx < minimum || xxx > maximum) {
            return true;
        } else if(yyy < minimum || yyy > maximum) {
            return true;
        } else {
            return zzz >= minimum && zzz <= maximum;
        }
    }
}
