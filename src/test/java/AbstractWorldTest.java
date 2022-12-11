import agh.ics.oop.*;
import agh.ics.oop.Observers.IPositionChangeObserver;
import agh.ics.oop.Observers.MapBoundary;
import agh.ics.oop.Tools.OptionsParser;
import agh.ics.oop.WorldMaps.GrassField;
import agh.ics.oop.Tools.IEngine;
import agh.ics.oop.Tools.SimulationEngine;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

// acutally trzeba zrobić tak żeby mapa printowała się tak duża żeby wszystkie zwierzaki
// (nawet te poza grassfieldem) były widoczne


public class AbstractWorldTest {
    @Test
    public void GrassTest() {

        String[] args = {};
        MoveDirection[] directions = new OptionsParser().parse(args);
        GrassField map = new GrassField(120);
        Vector2d[] positions = { };
        Vector2d[] finalPositions = { };

        TestMap(directions,map,positions,finalPositions,120);
    }

    @Test
    public void StackTest() {
        String[] args = {"f","b"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        GrassField map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(2,2), new Vector2d(2,2), new Vector2d(3,4) ,new Vector2d(2,2), new Vector2d(3,4) ,new Vector2d(2,2), new Vector2d(3,4)  };
        Vector2d[] finalPositions = {new Vector2d(2,3), new Vector2d(3,3) };

        TestMap(directions,map,positions,finalPositions,12);
    }

    @Test
    public void StackPlaceTest() {
        String[] args = {"f","f","f","f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        GrassField map = new GrassField(9);
        Vector2d[] positions = { new Vector2d(0,0), new Vector2d(0,1), new Vector2d(1,0), new Vector2d(1,1)};
        Vector2d[] finalPositions = { new Vector2d(0,0), new Vector2d(0,2), new Vector2d(1,0), new Vector2d(1,2)};
        TestMap(directions, map, positions,finalPositions,13);
    }

    @Test
    public void OverallTest() {
        String[] args = {"f","b","r","l","f","f","r","r","f","f","f","f","f","f","f","f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        GrassField map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) , new Vector2d(2,2), new Vector2d(3,4) };
        Vector2d[] finalPositions = {new Vector2d(2,-1), new Vector2d(3,7) };

        TestMap(directions,map,positions,finalPositions,12);
    }

    @Test
    public void OverallTest2() {
        String[] args = {"f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        GrassField map = new GrassField(40);
        Vector2d[] positions = { new Vector2d(0,0), new Vector2d(2,2) };
        Vector2d[] finalPositions = {new Vector2d(0,1), new Vector2d(2,3) };

        TestMap(directions,map,positions,finalPositions,42);
    }

    private void TestMap(MoveDirection[] directions, GrassField map, Vector2d[] startingPositions, Vector2d[] finalOccupiedPositions, int final_elements) {
        ArrayList<IPositionChangeObserver> observers = new ArrayList<>();
        observers.add(map);
        IEngine engine = new SimulationEngine(directions, map, startingPositions, observers);
        engine.run();
        out.println(map.toString());

        for (Vector2d finishing : finalOccupiedPositions)
            assertTrue(map.isOccupied(finishing));

        out.println(map.getElementsSize() == final_elements);
        assertEquals(map.getElementsSize(), final_elements);
    }
}
