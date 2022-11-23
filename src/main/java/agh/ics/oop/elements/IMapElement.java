package agh.ics.oop.elements;

import agh.ics.oop.Vector2d;

public interface IMapElement {

    Vector2d getPosition();
    boolean isAt(Vector2d position);
}
