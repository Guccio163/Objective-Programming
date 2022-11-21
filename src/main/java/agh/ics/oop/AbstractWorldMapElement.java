package agh.ics.oop;

public abstract class AbstractWorldMapElement{
    protected Vector2d pos;


    public Vector2d getPosition()
    {
        return pos;
    }
    public AbstractWorldMapElement (Vector2d pos1){
        this.pos = pos1;
    }

    public boolean isAt(Vector2d pos1){
        return this.getPosition().equals(pos1);
    }
}
