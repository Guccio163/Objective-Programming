import agh.ics.oop.Tools.OptionsParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Testy metody OptionsParser ponieważ nie było takowych
// metoda place jest mocno testowana przy AbstractWorldTeście, gdzie jest też postawienie dwóch zwierząt na jednym polu

public class OptionsParserTest {

    int flag = 0;
    @Test
    void parserTestOne(){

        String[] coordinates1 = new String[] {"f", "r", "b","l", "left","backward"};
        try{
            new OptionsParser().parse(coordinates1);
        }
        catch (IllegalArgumentException e){
            flag = 1;
        }

        assertNotEquals(1, flag);

    }

    @Test
    void parserTestTwo(){

        String[] coordinates2 = new String[] {"a", "f", "r", "b","l","right","backward"};
        try{
            new OptionsParser().parse(coordinates2);
        }
        catch (IllegalArgumentException e){
            flag = 1;
        }

        assertEquals(1, flag);
    }

    @Test
    void parserTestThree(){

        String[] coordinates3 = new String[] {"f", "r", "b", "l", "leftx","right","backward","forwardx"};
        try{
            new OptionsParser().parse(coordinates3);
        }
        catch (IllegalArgumentException e){
            flag = 1;
        }

        assertEquals(1, flag);
    }
}
