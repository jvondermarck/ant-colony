package V2.javaTest;

import V2.javaClass.Graphe;
import V2.javaClass.Ouvrier;
import V2.javaClass.Reine;
import V2.javaClass.Soldat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
    void giveBirthWorker()
    {
        int amount = 5;
        r.giveBirthWorker(amount);
        ArrayList<Ouvrier> theWorkers = r.getTheWorkers();
        int actual = theWorkers.size();

        assertEquals(amount, actual);
    }

    @Test
    @DisplayName("Création des soldats impossibles")
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
    @DisplayName("Création de reine impossible")
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

    @Test
    @DisplayName("getTheSoldiers()")
    void getTheSoldiers()
    {
        r.giveBirthSoldier(3);
        assertEquals(3, r.getTheSoldiers().size());
    }

    @Test
    @DisplayName("getTheWorkers()")
    void getTheWorkers()
    {
        r.giveBirthWorker(5);
        assertEquals(5, r.getTheWorkers().size());
    }

    @Test
    @DisplayName("getColonie()")
    void getColonie()
    {
        assertEquals("Colonie n°1 -> à un stock de 0 nourriture stockée", r.getColonie().toString());
    }

    @Test
    @DisplayName("setParametreColonie()")
    void setParametreColonie()
    {
        r.setParametreColonie(3,4,5);
        if(r.getColonie().getEvaporationParam() == 3 && r.getColonie().getFoodParam() == 4 && r.getColonie().getPheromoneParam() == 5)
        {
            assertTrue(true);
        }
    }
}
