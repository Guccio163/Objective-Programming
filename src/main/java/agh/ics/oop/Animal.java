package agh.ics.oop;




public class Animal extends AbstractWorldMapElement{
    private MapDirection Aorientation;
    public IWorldMap map1;

    public Animal(Vector2d start, IWorldMap worldmap) {
        super(start);
        map1 = worldmap;
        Aorientation = MapDirection.NORTH;

        if(worldmap.place(this)==false){
            throw new IllegalArgumentException("this position is already taken");
        }
    }



    public String toString() {
        return String.format("position %s, orientation %s", pos.toString(), Aorientation.OrientationtoShort());
    }

    public boolean isAt(Vector2d position) {
        return pos.equals(position);
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
        Vector2d vector = pos.add(movement);
        if (map1.canMoveTo(vector))
            pos = vector;


        if(!map1.canMoveTo(vector))
        {
            AbstractWorldMapElement elem = (AbstractWorldMapElement) map1.objectAt(vector);

            if(elem instanceof Grass) {
                GrassField map = (GrassField) map1;
                map.removeElem(elem);
                pos = vector;
                map.plantGrass(1);
            }
            return;
        }

        pos = vector;
    }


    // potrzebne do testów
    public Vector2d getPos(){
        return pos;
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
