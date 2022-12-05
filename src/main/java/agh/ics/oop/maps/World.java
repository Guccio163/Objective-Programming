package agh.ics.oop.maps;
import agh.ics.oop.*;

import java.util.ArrayList;

import static java.lang.System.out;

public class World {
    public static void main(String[] args) {

        MoveDirection[] directions = new OptionsParser().parse(args);
        GrassField map;
        map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        ArrayList<IPositionChangeObserver> observers = new ArrayList <>();
        observers.add(map);
        IEngine engine = new SimulationEngine(directions, map, positions, observers);

        out.println(map);
        engine.run();
        out.println(map);


    }
}
