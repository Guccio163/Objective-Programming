package agh.ics.oop;

public class Vector2dTest {

    @Test
    public void equalsTest(Object other){
        Vector2d first = new Vector2d(1, 2);
        Vector2d second = new Vector2d(2, 1);
        assertFalse(first.equals(second));
    }

    @Test
    public void toStringTest(){
        Vector2d first = new Vector2d(1, 2);
        assertEquals("1,2)", first.toString());

    }

    @Test
    public void precedesTest(){
        Vector2d first = new Vector2d(1, 2);
        Vector2d second = new Vector2d(2, 1);
        assertFalse(first.precedes(second));

    }

    @Test
    public void followsTest(Vector2d other){
        Vector2d first = new Vector2d(1, 2);
        Vector2d second = new Vector2d(2, 1);
        assertFalse(first.follows(second));
    }

    @Test
    public void upperRightTest(Vector2d other){
        Vector2d first = new Vector2d(1, 2);
        Vector2d second = new Vector2d(2, 1);
        assertEquals(new Vector2d(2, 2), first.upperRight(second));
    }

    @Test
    public void lowerLeftTest(Vector2d other){
        Vector2d first = new Vector2d(1, 2);
        Vector2d second = new Vector2d(2, 1);
        assertEquals(new Vector2d(1, 1), first.lowerLeft(second));
    }

    @Test
    public void addTest(Vector2d other){
        Vector2d first = new Vector2d(1, 2);
        Vector2d second = new Vector2d(2, 1);
        assertEquals(new Vector2d(3, 3), first.add(second));

    }

    @Test
    public void substractTest(Vector2d other){
        Vector2d first = new Vector2d(1, 2);
        Vector2d second = new Vector2d(2, 1);
        assertEquals(new Vector2d(-1, 1), first.substract(second));

    }

    @Test
    public void oppositeTest(){
        Vector2d first = new Vector2d(1, 2);
        assertEquals(new Vector2d(-1, -2), first.opposite());

    }
}
