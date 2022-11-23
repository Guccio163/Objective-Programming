package agh.ics.oop.elements;
import agh.ics.oop.Vector2d;

public class Grass extends AbstractWorldMapElement{

    public Grass(Vector2d pos1){
        super(pos1);
    }

    public String toString(){
        return "*";
    }

}
