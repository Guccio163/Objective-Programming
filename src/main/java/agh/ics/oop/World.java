package agh.ics.oop;

import static java.lang.System.out;

public class World {
    public static void main(String[] args) {
        out.println("system wystartował");
        run(args);
        out.println("system zakończył działanie");
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
//timestamp
//edit to view if desc. will change
