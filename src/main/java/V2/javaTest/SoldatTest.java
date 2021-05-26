package V2.javaTest;

import V2.javaClass.Colonie;
import V2.javaClass.Graphe;
import V2.javaClass.Noeud;
import V2.javaClass.Soldat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        s = new Soldat(0,1,c,g);
    }

    @Test
    void positionDuSoldat() {
        Noeud actual = s.getPositionActuel();
        s.getPositionActuel().setCoordonneNoeud(1);
        Noeud expected = s.getPositionActuel();
        assertEquals(expected,actual);
    }

    @Test
    void getNombreSoldat() {

        assertEquals(1, s.getNumeroSoldat());
    }

    @Test
    void testToString() {
        assertEquals("Soldat n°2  | Colonie n°2 -> à un stock de 0 nourriture stockée | Noeud n°2", s.toString());
    }
}