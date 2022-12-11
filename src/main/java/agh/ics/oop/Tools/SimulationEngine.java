package agh.ics.oop.Tools;
import agh.ics.oop.Observers.IPositionChangeObserver;
import agh.ics.oop.MoveDirection;
import agh.ics.oop.Vector2d;
import agh.ics.oop.MapElements.Animal;
import agh.ics.oop.WorldMaps.AbstractWorldMap;
import agh.ics.oop.WorldMaps.IWorldMap;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] directions;
    private final AbstractWorldMap worldMap;
    public final List<Animal> animals = new ArrayList<>();

    //endregion

    public SimulationEngine(MoveDirection[] directions1, AbstractWorldMap worldMap1, Vector2d[] startingPos, ArrayList<IPositionChangeObserver> observers){
        directions = directions1;
        worldMap = worldMap1;

        for(Vector2d place : startingPos){

            try {
                Animal animal = new Animal(place, worldMap);
                animals.add(animal);
            }
            catch (IllegalArgumentException e) {
                out.printf("%s Animal not placed.%n",e);
            }
        }
    }

    public void run()
    {
        int i = 0;
        out.println(worldMap);
        for(MoveDirection direction: directions)
        {
            animals.get(i%animals.size()).move(direction);
            i+=1;
        }
    }
}
