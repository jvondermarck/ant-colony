package V1.javaTest;

import V1.javaClass.Colonie;
import V1.javaClass.Graphe;
import V1.javaClass.Noeud;
import V1.javaClass.Soldat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SoldatTest {

    static final int WIDTH = 13;
    static final int HEIGHT = 19;

    Graphe g;
    Soldat s;
    Colonie c;

    @BeforeEach
    void setUp() {
        g = new Graphe(WIDTH, HEIGHT);
        c = new Colonie();
        s = new Soldat(0,0,c,g);
    }

    @Test
    void getPositionActuel() {
        Noeud actual = s.getPositionActuel();
        s.getPositionActuel().setCoordonneNoeud(1);
        Noeud expected = s.getPositionActuel();
        assertEquals(expected,actual);
    }

    @Test
    void recherchePositionActuel() {
        s.recherchePositionActuel(0,2);
        Noeud actual = s.getPositionActuel();

        Noeud expected = g.rechercherNoeud(0,2);
        assertEquals(expected,actual);
    }

    @Test
    void testToString() {
        g.mettreObstacle(0,1);
        s.randomDirection(g);
        assertEquals("Soldat n°1 - Colonie n°1 --> se trouve au Noeud n°20", s.toString());
    }
}