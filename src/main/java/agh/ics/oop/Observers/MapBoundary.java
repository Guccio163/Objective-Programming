package agh.ics.oop.Observers;
import agh.ics.oop.Vector2d;
import com.sun.source.tree.Tree;
import java.util.TreeSet;


// realnie będziemy przechowywać wektory, co stoi na danym polu to już przeczytamy z listy w mapie
public class MapBoundary implements IPositionChangeObserver{

    public TreeSet<Vector2d> occupiedByX;
    public TreeSet<Vector2d> occupiedByY;

    public MapBoundary(){
        super();
        occupiedByX = new TreeSet<>(Vector2d::compareByX);
        occupiedByY = new TreeSet<>(Vector2d::compareByY);
    }


    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        if ( oldPosition != null ) {
            occupiedByX.remove(oldPosition);
            occupiedByY.remove(oldPosition);
        }

        if ( newPosition != null ) {
            occupiedByX.add(newPosition);
            occupiedByY.add(newPosition);
        }
    }


    public Vector2d lowerLeft () {
        return new Vector2d(occupiedByX.first().x, occupiedByY.first().y);
    }

    public Vector2d upperRight () {
        return new Vector2d(occupiedByX.last().x, occupiedByY.last().y);
    }

}
