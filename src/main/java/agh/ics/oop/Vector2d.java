package agh.ics.oop;


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
        return "(" + x + "," + y + ")";
    }

    public boolean precedes(Vector2d other) {
        if (other.x > x)
            return false;
        if (other.y > y)
            return true;
        else
            return true;
    }

    public boolean follows(Vector2d other) {
        if (other.x < x)
            return false;
        if (other.y < y)
            return true;
        else
            return true;
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(x + other.x, y + other.y);
    }

    public Vector2d substract(Vector2d other) {
        return new Vector2d(x - other.x, y - other.y);

    }

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

    public enum MoveDirection {
        FORWARD,
        BACKWARD,
        RIGHT,
        LEFT;
    }

    public enum MapDirection {
        NORTH,
        SOUTH,
        WEST,
        EAST;

        public String toString() {
            return switch (this) {
                case NORTH -> "Północ";
                case SOUTH -> "Południe";
                case WEST -> "Zachód";
                case EAST -> "Wschód";
            };
        }

        public MapDirection next() {
            return switch (this) {
                case NORTH -> SOUTH;
                case SOUTH -> WEST;
                case WEST -> EAST;
                case EAST -> NORTH;
            };
        }

        public MapDirection previous() {
            return switch (this) {
                case NORTH -> EAST;
                case SOUTH -> NORTH;
                case WEST -> SOUTH;
                case EAST -> WEST;
            };
        }

        public Vector2d toUnitVector() {
            return switch (this) {
                case NORTH -> new Vector2d(0, 1);
                case SOUTH -> new Vector2d(1, 0);
                case WEST -> new Vector2d(0, -1);
                case EAST -> new Vector2d(-1, 0);
            };
        }
    }
}

