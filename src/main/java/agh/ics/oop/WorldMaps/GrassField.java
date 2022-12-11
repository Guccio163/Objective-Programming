package agh.ics.oop.WorldMaps;
import agh.ics.oop.Tools.MapVisualizer;
import agh.ics.oop.Vector2d;
import agh.ics.oop.MapElements.AbstractWorldMapElement;
import agh.ics.oop.MapElements.Animal;
import agh.ics.oop.MapElements.Grass;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField extends AbstractWorldMap {

    public int grassQuantity;
    public final int bottomLeftCorner;
    public final int upperRightCorner;


    // mapa z nieskończonym obszarem, ale trawki spawnią sie tylko we współrzędnych (0,0) - (sqrt(10x), sqrt(10x))
    public GrassField (int newGrass){
        super(new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE), new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE));
        bottomLeftCorner = 0;
        upperRightCorner = (int) Math.sqrt(newGrass *10);
        this.grassQuantity = 0;
        this.plantGrass(newGrass);
        this.grassQuantity = newGrass;
    }


    // plantujemy trawę jak możemy
    public void plantGrass(int NewGrassNo)
    {
        int counter = 0;
        int freeSpace = (int) Math.pow((upperRightCorner - bottomLeftCorner), 2) - grassQuantity;

        if(freeSpace<NewGrassNo){
            throw new IllegalArgumentException("not enough space for declared grass");}

        while(counter<NewGrassNo){
            int xRandom = ThreadLocalRandom.current().nextInt(this.bottomLeftCorner, this.upperRightCorner +1);
            int yRandom = ThreadLocalRandom.current().nextInt(this.bottomLeftCorner, this.upperRightCorner +1);

            try{
                this.place(new Grass(new Vector2d (xRandom, yRandom)));
                counter += 1;
            }
            catch(IllegalArgumentException e) {
                System.out.println("already taken");
            }
        }
    }


    // funkcja umieszczająca jakikolwiek element (AbWrlMapEl) na zadanym miejscu; jak zwierze to zjada
    @Override
    public boolean place(AbstractWorldMapElement element) {
        if(!(element.getPosition().follows(this.leftBottomCorner) && element.getPosition().precedes(this.rightTopCorner)))
            return false;

        if(isOccupied(element.getPosition())){
            // sprawdzamy co stoi na naszym polu
            AbstractWorldMapElement currentElement = this.objectAt(element.getPosition());

            // jak zwierze spotka trawe
            if(element instanceof Animal && currentElement instanceof Grass){
                elementsList.remove(currentElement);
                elementsList.put(element.getPosition(), element);
                // boundariesObserver nic nie robi bo dalej jest jeden obiekt na tym samym miejscu
                plantGrass(1);
                return true;
            }
            // jak trawa+trawa/zwierze+zwierze/trawa postawiona tam gdzie zwierze
            throw new IllegalArgumentException("Position " + element.getPosition().toString() + " is already taken");

        }
        elementsList.put(element.getPosition(), element);
        this.boundariesObserver.occupiedByX.add(element.pos);
        this.boundariesObserver.occupiedByY.add(element.pos);
        return true;
    }

    public Vector2d getMapLeftBottom(){
        return boundariesObserver.lowerLeft();
    }

    public Vector2d getMapRightUpper(){
        return boundariesObserver.upperRight();
    }

    public String toString(){
        return new MapVisualizer(this).draw(this.getMapLeftBottom(), this.getMapRightUpper());
    }
}
