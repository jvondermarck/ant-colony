package V2.javaTest;

import V2.javaClass.Colonie;
import V2.javaClass.Graphe;
import V2.javaClass.Noeud;
import V2.javaClass.Soldat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GrapheTest {
    static final int WIDTH = 13;
    static final int HEIGHT = 19;

    Graphe g;
    Soldat s;
    Colonie c;

    @BeforeEach
    void setUp() {
        g = new Graphe(WIDTH, HEIGHT);
        c = new Colonie();
    }

    @Test
    void creationEmplacementNoeud()
    {
        int num = 1;

        for (int i=0; i<g.getRow(); i++)
        {
            for (int j=0; j<g.getColumn(); j++)
            {
                Noeud n = g.rechercherNoeud(i,j);
                if (num == n.getCoordonneNoeud())
                {
                    assertEquals(num, n.getCoordonneNoeud());
                }
            }
        }
    }

    @Test
    void rechercherNoeud()
    {
        Noeud n = g.rechercherNoeud(0,3);
        int actual = n.getCoordonneNoeud();
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    void mettreObstacle()
    {
        g.mettreObstacle(0,3);
        boolean actual = g.getEstObstacle()[0][3];
        assertTrue(actual);
    }

    @Test
    @DisplayName("Dimensions du graphe")
    void insertionNoeud()
    {
        try
        {
            Graphe g1 = new Graphe(-5,-7);
            fail("Vous avez crée un graphe impossible");
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
    @DisplayName("Mettre de la nourriture avec des valeurs negatives")
    void mettreNourriture1()
    {
        try
        {
            Graphe g1 = new Graphe(10,6);
            g1.mettreNourriture(3,-5,-8);
            fail("Vous avez mit des valeurs négatifs");
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
    @DisplayName("mettreNourriture()")
    void mettreNourriture2()
    {
        g.mettreNourriture(0,3,23);
        assertEquals(23,g.getQuantityFood()[0][3]);
    }

    @Test
    @DisplayName("rechercherCoord(Noeud)")
    void rechercherCoord()
    {
        ArrayList<Integer> coord = g.rechercherCoord(g.rechercherNoeud(1,2));
        if(coord.get(0) == 1 && coord.get(1) == 2)
        {
            assertTrue(true);
        }
    }

    @Test
    @DisplayName("gestionNourriture(x,y,c)")
    void gestionNourriture()
    {
        int[][] food = g.getQuantityFood();
        food[1][2] = 100;
        g.setQuantityFood(food);
        c.setFoodParam(5);
        int quantite = g.gestionNourriture(1,2, c);
        assertEquals(95, g.getQuantityFood()[1][2]);
    }

    @Test
    @DisplayName("getEstObstacle()")
    void getEstObstacle()
    {
        boolean[][] bool = g.getEstObstacle();
        bool[1][2] = true;
        g.setEstObstacle(bool);
        assertTrue(g.getEstObstacle()[1][2]);
    }

    @Test
    @DisplayName("getEstNourriture()")
    void getEstNourriture()
    {
        boolean[][] bool = g.getEstNourriture();
        bool[1][2] = true;
        g.setEstNourriture(bool);
        assertTrue(g.getEstNourriture()[1][2]);
    }

    @Test
    @DisplayName("getQuantityFood()")
    void getQuantityFood()
    {
        int[][] bool = g.getQuantityFood();
        bool[1][2] = 1234;
        g.setQuantityFood(bool);
        assertEquals(1234, g.getQuantityFood()[1][2]);
    }

    @Test
    @DisplayName("getQuantityPheromone()")
    void getQuantityPheromone()
    {
        int[][] bool = g.getQuantityPheromone();
        bool[1][2] = 1234;
        g.setQuantityPheromone(bool);
        assertEquals(1234, g.getQuantityPheromone()[1][2]);
    }

    @Test
    @DisplayName("getRow()")
    void getRow()
    {
        assertEquals(WIDTH, g.getRow());
    }

    @Test
    @DisplayName("getColumn()")
    void getColumn()
    {
        assertEquals(HEIGHT, g.getColumn());
    }
}




