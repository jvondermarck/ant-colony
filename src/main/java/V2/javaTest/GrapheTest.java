package V2.javaTest;

import V2.javaClass.Colonie;
import V2.javaClass.Graphe;
import V2.javaClass.Noeud;
import V2.javaClass.Soldat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GrapheTest {
    static final int WIDTH = 13;
    static final int HEIGHT = 19;

    Graphe g;
    Soldat s;
    Colonie c;
    int[][] taille;

    @BeforeEach
    void setUp() {
        g = new Graphe(WIDTH, HEIGHT);
        taille = g.getTaille();
    }

    @Test
    void creationEmplacementNoeud()
    {
        int num = 1;

        for (int i=0; i<taille.length; i++)
        {
            for (int j=0; j<taille[i].length; j++)
            {
                int actual = taille[i][j];
                if (num == taille[i][j])
                {
                    assertEquals(num, actual);
                }
            }
        }
    }

    // On ne peut pas faire un test de getEmplacementNoeud vu que c'est une méthode appelé dans creationEmplacementNoeud()

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
    @DisplayName("Mettre de la nourriture")
    void mettreNourriture2()
    {
        g.mettreNourriture(0,3,23);
        assertEquals(23,g.getQuantityFood()[0][3]);
    }



}




