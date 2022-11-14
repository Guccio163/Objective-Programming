package agh.ics.oop;

import static java.lang.System.out;

public class World {
    public static void main(String[] args) {

        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
    }

    public static void run(String[] argums) {
        out.println("Start");
        for (String argum : argums) {
            switch (argum) {
                case "f" -> {
                    out.println("Zwierzak idzie do przodu");
                }
                case "b" -> {
                    out.println("Zwierzak idzie do tyłu");
                }
                case "r" -> {
                    out.println("Zwierzak skręca w prawo");
                }
                case "l" -> {
                    out.println("Zwierzak skręca w lewo");
                }
                default -> {
                }
            }
        }
        out.print("Stop \n");
    }
}
