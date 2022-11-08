package agh.ics.oop;

import static java.lang.System.out;

public class World {
    public static void main(String[] args) {


        Animal animal = new Animal();

        out.println(animal);

        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);

        out.println(animal);

        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);

        out.println(animal);

        MoveDirection[] moves = new OptionsParser().parse(args);

        animal.move1(moves);

        out.println(animal);
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
