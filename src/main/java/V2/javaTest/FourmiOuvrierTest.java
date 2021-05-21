package V2.javaTest;

import V2.javaClass.AntFacade;
import V2.javaClass.AntFacadeController;
import V2.javaClass.Display;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.BitSet;

import static org.junit.jupiter.api.Assertions.*;


public class FourmiOuvrierTest {

    static final int WIDTH = 13;
    static final int HEIGHT = 19;

    AntFacadeController appli;


    @BeforeEach
    void setUp()
    {
        appli = new AntFacade(50);
        appli.createGrid(WIDTH, HEIGHT);
        appli.createColony(0,0);

        for (int i = 1; i < HEIGHT; i += 2)
        {
            int decalage = ((i - 1) % 4) / 2;
            for (int j = decalage; j < WIDTH - 1 + decalage; j++)
            {
                appli.putObstacle(i,j);
            }
        }

        appli.createWorkers(1);
    }

    @Test
    @DisplayName(("Placer de la nourriture sur la fourmilière"))
    void testPutFoodonFourmiliere()
    {
        try
        {
            appli.putFood(0, 0,15);
            fail("Obstacle placé sur la fourmilière");
        }
        catch (IllegalArgumentException exception)
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
    @DisplayName(("Placer de la nourriture sur un obstacle"))
    void testPutFoodonObstacle()
    {
        try
        {
            appli.putObstacle(1,1);
            appli.putFood(1, 1,15);
            fail("Obstacle placé sur la fourmilière");
        }
        catch (IllegalArgumentException exception)
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
    @DisplayName(("Placer un obstacle sur de la nourriture"))
    void testPutObstacleOnFood()
    {
        try
        {
            appli.putFood(1, 1,15);
            appli.putObstacle(1,1);
            fail("Obstacle placé sur de la nourriture");
        }
        catch (IllegalArgumentException exception)
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
    @DisplayName("Déplacement élémentaire")
    void testDeplacementElemtaire()
    {
        BitSet[][] bitsets = appli.play(1, false);
        BitSet actual = bitsets[0][1];
        boolean ouvrierPresent = actual.get(3);
        assertTrue(ouvrierPresent, "Ouvrier absent : bitsets[0][1] = " + actual);
    }

    @Test
    @DisplayName("Fourmi ouvrière bloquée")
    void test5()
    {
        appli.putObstacle(0, 1);
        BitSet[][] bitsets = appli.play(1, false);
        BitSet actual = bitsets[0][0];
        boolean ouvrierPresent = actual.get(3);
        assertTrue(ouvrierPresent, "Ouvrier absent : bitsets[0][0] = " + actual);
    }

    /*
    @Test
    @DisplayName("Trajet aller-retour")
    void test6()
    {
        BitSet[][] bitsets;

        boolean surFourmiliere;
        do
        {
            bitsets = appli.play(2, false);
            surFourmiliere= bitsets[0][0].get(3);
        }
        while (surFourmiliere);

        appli.putFood(HEIGHT-1,0,15);
        appli.setParameters(0,10,0);

        int i = 0;
        int j = 2;
        Display w = new Display( WIDTH, HEIGHT, 50 );
        while (i < HEIGHT)
        {
            BitSet actual = bitsets[i][j];
            boolean ouvrierPresent = actual.get(3);
            assertTrue(ouvrierPresent,
                    "bitsets[" + i + "][" + j + "] = " + bitsets[i][j]);
            //bitsets = appli.play(1, false);
            w.update(appli.play(1, false));

            if (i % 4 == 0 && j < WIDTH - 1)
                j++;
            else if (i % 4 == 2 && j > 0)
                j--;
            else
                i++;
        }
        int k = HEIGHT-1;
        int l = 2;
        while (k >= 0)
        {
            BitSet actual = bitsets[i][j];
            boolean ouvrierPresent = actual.get(3);
            assertTrue(ouvrierPresent,
                    "bitsets[" + k + "][" + l + "] = " + bitsets[i][j]);
            //bitsets = appli.play(1, false);
            w.update(appli.play(1, false));

            if (k % 4 == 0 && l < WIDTH - 1)
                l--;
            else if (k % 4 == 2 && l > 0)
                l++;
            else
                l--;
        }
    }
    */

    @Test
    @DisplayName("Fourmi prend toute la nourriture d'un endroit")
    void test7()
    {
        appli.putFood(0, 2,30);
        appli.setParameters(0,10,0);

        BitSet[][] bitsets = appli.play(1, false);

        int i = 1;
        while (bitsets[0][2].get(5))
        {
            bitsets = appli.play(i, false);
            i++;
        }

        assertFalse(bitsets[0][2].get(5));
    }

}
