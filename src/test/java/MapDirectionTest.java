import agh.ics.oop.MapDirection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest{
    @Test
    public void nextTest(){
        assertEquals(MapDirection.SOUTH, MapDirection.EAST.next());
    }

    @Test
    public void previousTest(){
        assertEquals(MapDirection.EAST, MapDirection.NORTH.previous());
    }
}
