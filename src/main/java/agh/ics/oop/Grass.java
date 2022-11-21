package agh.ics.oop;
import agh.ics.oop.AbstractWorldMapElement;

public class Grass extends AbstractWorldMapElement{

    public Grass(Vector2d pos1){
        super(pos1);
    }

    public String toString(){
        return "*";
    }

}
