package agh.ics.oop;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.out;
import agh.ics.oop.AbstractWorldMapElement;
import agh.ics.oop.IWorldMap;
import agh.ics.oop.AbstractWorldMap;

public class GrassField extends AbstractWorldMap{

    public final int GrassNo;
    public final int firstplant;
    public final int lastplant;


    public GrassField (int GrassNo){
        super(new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE), new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE));
        firstplant = 0;
        lastplant = (int) Math.sqrt(GrassNo*10);
        this.GrassNo = GrassNo;
        plantGrass(GrassNo);
    }



    // plantujemy trawę jak możemy
    public void plantGrass(int NewGrassNo)
    {
        int counter = 0;
        int freeSpace = (int) Math.pow((this.lastplant - this.firstplant),2) - GrassNo;

        if(freeSpace<NewGrassNo){
            throw new IllegalArgumentException("nie ma miejsca na tyle trawy");}


        for(int i = 0; i < lastplant; i++)
            for(int j = 0; j < lastplant; j++){

                if(this.place(new Grass(new Vector2d (Vector2d.randomInt(this.firstplant, this.lastplant), Vector2d.randomInt(this.firstplant, this.lastplant)))));
                    counter += 1;
                if (counter == GrassNo)
                    return;
            }
    }


    // funkcja umieszczająca jakikolwiek element (AbWrlMapEl) na zadanym miejscu; jak zwierze to zjada
    @Override
    public boolean place(AbstractWorldMapElement elem) {
        // czy mieści się na mapie
        if(!elem.getPosition().follows(this.leftBottomCorner) || !elem.getPosition().precedes(this.rightTopCorner))
            return false;

        // czy już zajęte
        if(isOccupied(elem.getPosition())){
            // sprawdzamy co stoi na naszym polu
            AbstractWorldMapElement prevPositioned = this.objectAt(elem.getPosition());

            // jak zwierze spotka trawe
            if(elem instanceof Animal && prevPositioned instanceof Grass){
                ElemList.remove(prevPositioned);
                ElemList.add(elem);
                plantGrass(1);
                return true;
            }
            // jak trawa+trawa/zwierze+zwierze
            return false;

        }
        ElemList.add(elem);
        return true;
    }




    // funkcje do rysowania mapy; wyznaczania jej granic

    public Vector2d rightTopCorner(){
        Vector2d result = new Vector2d(0,0);
        for(AbstractWorldMapElement elem : ElemList){
            result = result.lowerLeft(elem.getPosition());
        }
        return result;
    }

    public Vector2d leftBottomCorner(){
        Vector2d result = new Vector2d(0,0);
        for(AbstractWorldMapElement elem : ElemList){
            result = result.upperRight(elem.getPosition());
        }
        return result;
    }
}
