package agh.ics.oop;


import static java.lang.System.out;

public class Animal {
    private MapDirection Aorientation;
    private Vector2d Aposition;
    private IWorldMap map;

    public Animal(IWorldMap worldmap) {
        map = worldmap;
        Aorientation = MapDirection.NORTH;
        Aposition = new Vector2d(2, 2);
    }

    public Animal(IWorldMap worldmap, Vector2d start) {
        map = worldmap;
        Aposition = start;

        if(!worldmap.place(this)){
            out.println("Postion" + start.toString() + "%s is already occupied.");
    }
}


    public String toString() {
        return String.format("position %s, orientation %s", Aposition.toString(), Aorientation.OrientationtoShort());
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
        if (map.canMoveTo(vector))
            Aposition = vector;
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
