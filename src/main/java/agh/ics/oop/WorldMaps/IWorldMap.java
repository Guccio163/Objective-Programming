package agh.ics.oop.WorldMaps;
import agh.ics.oop.Observers.MapBoundary;
import agh.ics.oop.Vector2d;
import agh.ics.oop.MapElements.AbstractWorldMapElement;

import java.util.Map;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo */
public interface IWorldMap {
    /** Indicate if any object can move to the given position.
     * @return True if the object can move to that position.*/
    boolean canMoveTo(Vector2d position);

    /** Place an animal on the map.
     * @return True if the animal was placed.*/
    boolean place(AbstractWorldMapElement element);

    /** Return true if given position on the map is occupied.
     * @return True if the position is occupied.*/
    boolean isOccupied(Vector2d position);

    /** Return an object at a given position.
     * @return Object or null if the position is not occupied.*/
    Object objectAt(Vector2d position);


    int getElementsSize();
}
