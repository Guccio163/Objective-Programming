package agh.ics.oop.maps;
import agh.ics.oop.MapVisualizer;
import agh.ics.oop.Vector2d;
import agh.ics.oop.elements.AbstractWorldMapElement;
import agh.ics.oop.elements.Animal;
import agh.ics.oop.elements.Grass;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField extends AbstractWorldMap {

    public int grassQuantity;
    public final int firstplant;
    public final int lastplant;


    public GrassField (int newGrass){
        super(new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE), new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE));
        firstplant = 0;
        lastplant = (int) Math.sqrt(newGrass *10);
        this.grassQuantity = 0;
        plantGrass(newGrass);
        this.grassQuantity = newGrass;
    }


    // plantujemy trawę jak możemy
    public void plantGrass(int NewGrassNo)
    {
        int counter = 0;
        int freeSpace = (int) Math.pow((this.lastplant - this.firstplant),2) - grassQuantity;

        if(freeSpace<NewGrassNo){
            throw new IllegalArgumentException("nie ma miejsca na tyle trawy");}


        while(counter<NewGrassNo){
            int xRandom = ThreadLocalRandom.current().nextInt(this.firstplant, this.lastplant+1);
            int yRandom = ThreadLocalRandom.current().nextInt(this.firstplant, this.lastplant+1);

            if(this.place(new Grass(new Vector2d (xRandom, yRandom))))
                counter += 1;
        }
    }


    // funkcja umieszczająca jakikolwiek element (AbWrlMapEl) na zadanym miejscu; jak zwierze to zjada
    @Override
    public boolean place(AbstractWorldMapElement elem) {
        if(!(elem.getPosition().follows(this.leftBottomCorner) && elem.getPosition().precedes(this.rightTopCorner)))
            return false;

        if(isOccupied(elem.getPosition())){
            // sprawdzamy co stoi na naszym polu
            AbstractWorldMapElement prevPositioned = this.objectAt(elem.getPosition());

            // jak zwierze spotka trawe
            if(elem instanceof Animal && prevPositioned instanceof Grass){
                elementsList.remove(prevPositioned);
                elementsList.put(elem.getPosition(), elem);
                plantGrass(1);
                return true;
            }
            // jak trawa+trawa/zwierze+zwierze/trawa postawiona tam gdzie zwierze
            return false;

        }
        elementsList.put(elem.getPosition(), elem);
        return true;
    }


    public String toString(){
        return new MapVisualizer(this).draw(new Vector2d(firstplant, firstplant), new Vector2d(lastplant, lastplant));
    }
}
