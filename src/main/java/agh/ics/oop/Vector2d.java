package agh.ics.oop;

import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;

    public static void main(String[] args) {
        Vector2d position1 = new Vector2d(1, 2);
        System.out.println(druk(position1));
        Vector2d position2 = new Vector2d(-2, 1);
        System.out.println(druk(position2));
        System.out.println(druk(position1.add(position2)));
    }

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static String druk(Vector2d cos) {
        return "(" + cos.x + "," + cos.y + ")";
    }

    public String toString(int x, int y) {
        return String.format("(%s,%s)", x, y);
    }
    public int compareByX(Vector2d other) {
        if(this.x != other.x){
            return this.x-other.x;
        }
        else
            return this.y- other.y;
    }

    public int compareByY(Vector2d other) {
        if(this.y != other.y){
            return this.y-other.y;
        }
        else
            return this.x- other.x;
    }

    public boolean precedes(Vector2d other) { return !( other.x < x || other.y < y); }

    public boolean follows(Vector2d other) { return !( other.x > x || other.y > y); }

    public Vector2d add(Vector2d other) {
        return new Vector2d(x + other.x, y + other.y);
    }

    public Vector2d substract(Vector2d other) {
        return new Vector2d(x - other.x, y - other.y);}

    public Vector2d upperRight(Vector2d other) {
        return new Vector2d(Math.max(x, other.x), Math.max(y, other.y));
    }

    public Vector2d lowerLeft(Vector2d other) {
        return new Vector2d(Math.min(x, other.x), Math.min(y, other.y));
    }

    public Vector2d opposite() {
        return new Vector2d(x * (-1), y * (-1));
    }

    public boolean equals(Object other) {
        if(this == other)
            return true;
        if(other == null || this.getClass() != other.getClass())
            return false;
        Vector2d vector = (Vector2d) other;
        return x == vector.x && y == vector.y;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

