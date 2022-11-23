package agh.ics.oop.maps;
import agh.ics.oop.MapVisualizer;
import agh.ics.oop.Vector2d;
import agh.ics.oop.elements.AbstractWorldMapElement;
import java.util.List;
import java.util.ArrayList;


public abstract class AbstractWorldMap implements IWorldMap {

    public final Vector2d leftBottomCorner;
    public final Vector2d rightTopCorner;

    /* zrobienie AbstractWorldMapElement ma sens choćby tutaj do stworzenia listy rozmieszczenia */
    protected List<AbstractWorldMapElement> elementsList = new ArrayList<>();


    public AbstractWorldMap (Vector2d leftBottom, Vector2d rightTop){
        this.leftBottomCorner = leftBottom;
        this.rightTopCorner = rightTop;
    }


    public boolean canMoveTo(Vector2d pos){
        return !isOccupied(pos) && pos.follows(leftBottomCorner) && pos.precedes(rightTopCorner);
    }

    public boolean place(AbstractWorldMapElement elem){
        if(canMoveTo(elem.getPosition())){
            elementsList.add(elem);
            return true;
        }
        return false;
    }

    public boolean isOccupied(Vector2d pos1){
        if(elementsList.size() > 0){
            // czy na liście mamy już punkt o tych współrzędnych
            return elementsList.stream().anyMatch(x->x.isAt(pos1));
        }
        return false;
    }

    public AbstractWorldMapElement objectAt(Vector2d pos1){
        return elementsList.stream().filter(x->x.isAt(pos1)).findAny().orElse(null);
    }

    public String toString(){
        return new MapVisualizer(this).draw(leftBottomCorner, rightTopCorner);
    }

    // dorzucone żeby animal mógł wpływać na elementy z mapy przy changeposition
    public void removeElem(AbstractWorldMapElement elem){
        elementsList.remove(elem);
    }

    // dorzucone dla testów
    public int getElementsSize() {
        return elementsList.size();}

}
