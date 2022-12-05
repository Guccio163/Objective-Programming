package agh.ics.oop.maps;
import agh.ics.oop.IPositionChangeObserver;
import agh.ics.oop.MapVisualizer;
import agh.ics.oop.Vector2d;
import agh.ics.oop.elements.AbstractWorldMapElement;

import java.util.HashMap;


public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    public final Vector2d leftBottomCorner;
    public final Vector2d rightTopCorner;

    /* zrobienie AbstractWorldMapElement ma sens choćby tutaj do stworzenia listy rozmieszczenia */
    protected HashMap<Vector2d, AbstractWorldMapElement> elementsList = new HashMap<>();


    public AbstractWorldMap (Vector2d leftBottom, Vector2d rightTop){
        this.leftBottomCorner = leftBottom;
        this.rightTopCorner = rightTop;
    }


    public boolean canMoveTo(Vector2d pos){
        return !isOccupied(pos) && pos.follows(leftBottomCorner) && pos.precedes(rightTopCorner);
    }

    public boolean place(AbstractWorldMapElement elem){


        if (canMoveTo(elem.getPosition())) {
            elementsList.put(elem.getPosition(), elem);
            return true;
        }



        return false;
    }

    public boolean isOccupied(Vector2d pos1){
        return elementsList.containsKey(pos1);
    }

    public AbstractWorldMapElement objectAt(Vector2d pos1){
        return elementsList.getOrDefault(pos1, null);
    }

    public String toString(){
        return new MapVisualizer(this).draw(leftBottomCorner, rightTopCorner);
    }

    // dorzucone żeby animal mógł wpływać na elementy z mapy przy changeposition
    public void removeElem(AbstractWorldMapElement elem){
        elementsList.remove(elem.getPosition());
    }

    // dorzucone dla testów
    public int getElementsSize() {
        return elementsList.size();}

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        elementsList.put(newPosition, elementsList.get(oldPosition));
        elementsList.remove(oldPosition);
    }
}
