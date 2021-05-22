package V1.javaTest;

import V1.javaClass.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class ReineTest {

    static final int WIDTH = 13;
    static final int HEIGHT = 19;

    Graphe g;
    Reine r;

    @BeforeEach
    void setUp() {
        g = new Graphe(WIDTH, HEIGHT);
        r = new Reine(5,5,g);
    }

    @Test
    void giveBirthSoldier()
    {
        int amount = 5;
        r.giveBirthSoldier(amount);
        ArrayList<Soldat> theSoldiers = r.getTheSoldiers();
        int actual = theSoldiers.size();

        assertEquals(amount, actual);
    }

    @Test
    @DisplayName("Dimensions du graphe")
    void insertionSoldat()
    {
        try
        {
            r.giveBirthSoldier(-6);
            fail("Vous avez crée des soldats impossibles");
        }
        catch (NumberFormatException exception)
        {
            // OK
        }
        catch (Exception exception)
        {
            fail("Exception de mauvais type : "
                    + exception.getClass().getSimpleName());
        }
    }

    @Test
    @DisplayName("Dimensions du graphe")
    void insertionReine()
    {
        try
        {
            Reine reine = new Reine(-7,-7,g);
            fail("Vous avez crée des soldats impossibles");
        }
        catch (NumberFormatException exception)
        {
            // OK
        }
        catch (Exception exception)
        {
            fail("Exception de mauvais type : "
                    + exception.getClass().getSimpleName());
        }
    }
}
