package V2.javaTest;

import V2.javaClass.Graphe;
import V2.javaClass.Noeud;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class NoeudTest {

    static final int WIDTH = 13;
    static final int HEIGHT = 19;

    Graphe g;

    @BeforeEach
    void setUp() {
        g = new Graphe(WIDTH, HEIGHT);
    }


    @Test
    @DisplayName("Recherche d'un noeud a un endroit")
    void creationNoeud()
    {
        Noeud n = g.rechercherNoeud(0,3);
        assertEquals("Noeud n°4", n.toString());
    }

    @Test
    @DisplayName("Recherche du nombre de noeud total crées")
    void nombreNoeud()
    {
        int expected = HEIGHT*WIDTH;
        int actual = g.getNbrNoeudDansGraphe();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Création d'un noeud supplémentaire")
    void insertionNoeud()
    {
        try
        {
            Noeud n = new Noeud(g,1);
            fail("Noeud crée hors champs du graphe");
        }
        catch (IndexOutOfBoundsException exception)
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
