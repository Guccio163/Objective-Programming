package agh.ics.oop.MapElements;
import agh.ics.oop.Vector2d;

public class Grass extends AbstractWorldMapElement{

    public Grass(Vector2d pos1){
        super(pos1);
    }

    public String toString(){ return String.format("(%d, %d)G", this.pos.x, this.pos.y);
    }

    public String getImageString()
    {
        return "src/main/resources/grass.png";
    }
}
