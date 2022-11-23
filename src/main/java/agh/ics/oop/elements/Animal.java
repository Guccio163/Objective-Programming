package agh.ics.oop.elements;
import agh.ics.oop.*;
import agh.ics.oop.maps.GrassField;
import agh.ics.oop.maps.IWorldMap;

public class Animal extends AbstractWorldMapElement{

    private MapDirection animalOrient;
    public IWorldMap animalMap;
    public Animal(Vector2d start, IWorldMap map) {
        super(start);
        animalMap = map;
        animalOrient = MapDirection.NORTH;

        if(!map.place(this)){
            throw new IllegalArgumentException("this position is already taken");
        }
    }


    public String toString() {
        return String.format("%s", animalOrient.orientationToShort());
    }

    public void move(MoveDirection direction) {
        switch(direction){
            case LEFT -> animalOrient = animalOrient.previous();
            case RIGHT -> animalOrient = animalOrient.next();
            case FORWARD -> changePosition(animalOrient.toUnitVector());
            default -> changePosition(animalOrient.next().next().toUnitVector());
        }
    }

    private void changePosition(Vector2d movement) {
        Vector2d vector = pos.add(movement);

        if(!animalMap.canMoveTo(vector))
        {
            AbstractWorldMapElement obstacle = (AbstractWorldMapElement) animalMap.objectAt(vector);

            if(obstacle instanceof Grass) {
                GrassField map = (GrassField) animalMap;
                map.removeElem(obstacle);
                pos = vector;
                map.plantGrass(1);
            }
            return;
        }
        pos = vector;
    }
}


    // Odpowiedź na pytanie nr 10
    // Robimy tablicę dwuwymiarową true/false która oddaje obraz naszego świata: gdzie stoją umieszczone już zwierzęta
    // (true jak jakieś pole jest zajęte i false jak nie), następnie przekazujemy ją w konstruktorze nowego Animala oraz
    // move; jak ktoś chce zrobić tam gdzie już jakieś stoi albo ruszyć na pole gdzie już jakieś stoi to nie pozwalamy
