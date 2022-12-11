package agh.ics.oop.MapElements;
import agh.ics.oop.Vector2d;

public abstract class AbstractWorldMapElement implements IMapElement {

    public Vector2d pos;

    public AbstractWorldMapElement(Vector2d pos1) {
        this.pos = pos1;
    }

    public Vector2d getPosition() {
        return pos;
    }
}
