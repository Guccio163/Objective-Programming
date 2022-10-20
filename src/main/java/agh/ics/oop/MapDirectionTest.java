package agh.ics.oop;


public class MapDirectionTest{
    @Test
    public void nextTest(){
        assertEquals(Vector2d.MapDirection.SOUTH, Vector2d.MapDirection.NORTH.next());
    }

    @Test
    public void previousTest(){
        assertEquals(Vector2d.MapDirection.EAST, Vector2d.MapDirection.NORTH);
    }
}
