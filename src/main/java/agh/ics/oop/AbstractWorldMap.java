package agh.ics.oop;
import agh.ics.oop.Vector2d;
import agh.ics.oop.IWorldMap;
import java.util.List;
import java.util.ArrayList;


public abstract class AbstractWorldMap implements IWorldMap{

    public final Vector2d leftBottomCorner;
    public final Vector2d rightTopCorner;

    /* zrobienie AbstractWorldMapElement ma sens choćby tutaj do stworzenia listy rozmieszczenia */
    protected List<AbstractWorldMapElement> ElemList = new ArrayList<AbstractWorldMapElement>();


    public AbstractWorldMap (Vector2d leftBottomCorner1, Vector2d rightTopCorner1){
        this.leftBottomCorner = leftBottomCorner1;
        this.rightTopCorner = rightTopCorner1;
    }


    public boolean canMoveTo(Vector2d pos){
        if(isOccupied(pos) != true && pos.precedes(rightTopCorner) == true && pos.follows(leftBottomCorner) == true){
            return true;
        }
        return false;
    }

    public boolean place(AbstractWorldMapElement elem){
        if(canMoveTo(elem.getPosition()) == true){
            ElemList.add(elem);
            return true;
        }
        return false;
    }

    public boolean isOccupied(Vector2d pos1){
        if(ElemList.size() > 0){
            // czy na liście mamy już punkt o tych współrzędnych
            return ElemList.stream().anyMatch(x->x.isAt(pos1));
        }
        return false;
    }

    public AbstractWorldMapElement objectAt(Vector2d pos1){
        return ElemList.stream().filter(x->x.isAt(pos1)).findAny().orElse(null);
    }

    public String toString(){
        return new MapVisualizer(this).draw(leftBottomCorner, rightTopCorner);
    }

    // dorzucone żeby animal mógł wpływać na elementy z mapy przy chandeposition
    public void removeElem(AbstractWorldMapElement elem){
        ElemList.remove(elem);
    }

    // dorzucone dla testów
    public int getElementsSize() {
        return ElemList.size();}

}
