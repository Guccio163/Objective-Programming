package agh.ics.oop;
import agh.ics.oop.elements.Animal;
import agh.ics.oop.maps.IWorldMap;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class SimulationEngine implements IEngine{

    private final MoveDirection[] directions;
    private final IWorldMap worldMap;
    public final List<Animal> animals = new ArrayList<>();

    //endregion

    public SimulationEngine(MoveDirection[] directions1, IWorldMap worldMap1, Vector2d[] startingPos){
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
        out.println(worldMap.toString());
        for(MoveDirection direction: directions)
        {
            animals.get(i%animals.size()).move(direction);
            i+=1;
        }
    }
}
