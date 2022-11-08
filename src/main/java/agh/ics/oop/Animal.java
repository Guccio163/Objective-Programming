package agh.ics.oop;


public class Animal {
    private MapDirection Aorientation;
    private Vector2d Aposition;
    private final Vector2d BottomLeft;
    private final Vector2d TopRight;

    public Animal() {
        Aorientation = MapDirection.NORTH;
        Aposition = new Vector2d(2, 2);
        BottomLeft = new Vector2d(0, 0);
        TopRight = new Vector2d(4, 4);
    }

    public String toString() {
        return String.format("position %s, orientation %s", Aposition.toString(), Aorientation.toString());
    }

    public boolean isAt(Vector2d position) {
        return Aposition.equals(position);
    }

    public void move1(MoveDirection[] directions) {
        if (directions == null || directions.length == 0)
            return;

        for (MoveDirection direction : directions)
            move(direction);
    }

    public void move(MoveDirection direction) {
        switch(direction){
            case LEFT -> Aorientation=Aorientation.previous();
            case RIGHT -> Aorientation=Aorientation.next();
            case FORWARD -> changePosition(Aorientation.toUnitVector());
            default -> changePosition(Aorientation.next().next().toUnitVector());
        }
    }

    private void changePosition(Vector2d movement) {
        Vector2d vector = Aposition.add(movement);
        if (IsInBoundaries(vector))
            Aposition = vector;
    }

    private boolean IsInBoundaries(Vector2d pos) {
        return  BottomLeft.x <= pos.x &&
                  pos.x <= TopRight.x &&
                BottomLeft.y <= pos.y &&
                pos.y <= TopRight.y;
    }

    // potrzebne do testów
    public Vector2d getPos(){
        return Aposition;
    }

    public MoveDirection[] toDir(String[] arguments)
    {
        return new OptionsParser().parse(arguments);
    }

    // chyba najsensowniejsze rozwiązanie problemu ze stringowym (zamiast Movedirectionowym) argumentem
    public void move2(String[] arguments)
    {
        MoveDirection[] directions = new OptionsParser().parse(arguments);

        if(directions == null || directions.length == 0)
            return;

        for(MoveDirection direction : directions)
            move(direction);
    }
}


    // Odpowiedź na pytanie nr 10
    // Robimy tablicę dwuwymiarową true/false która oddaje obraz naszego świata: gdzie stoją umieszczone już zwierzęta
    // (true jak jakieś pole jest zajęte i false jak nie), następnie przekazujemy ją w konstruktorze nowego Animala oraz
    // move; jak ktoś chce zrobić tam gdzie już jakieś stoi albo ruszyć na pole gdzie już jakieś stoi to nie pozwalamy
