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
    @DisplayName("getCoordonneNoeud()")
    void getCoordonneNoeud()
    {
        Noeud n = g.rechercherNoeud(0,2);
        assertEquals(3, n.getCoordonneNoeud());
    }

    @Test
    @DisplayName("setCoordonneNoeud()")
    void setCoordonneNoeud()
    {
        Noeud n = g.rechercherNoeud(0,2);
        n.setCoordonneNoeud(67);
        assertEquals(67, n.getCoordonneNoeud());
    }


}
