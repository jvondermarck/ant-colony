package V2.javaTest;

import V2.javaClass.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OuvrierTest {
    static final int WIDTH = 13;
    static final int HEIGHT = 19;

    Graphe g;
    Ouvrier s;
    Colonie c;

    @BeforeEach
    void setUp() {
        g = new Graphe(WIDTH, HEIGHT);
        c = new Colonie();
        s = new Ouvrier(0,0,c,g);
    }

    @DisplayName("getPositionActuel()")
    @Test
    void getPositionActuel() {
        Noeud actual = s.getPositionActuel();
        s.getPositionActuel().setCoordonneNoeud(1);
        Noeud expected = s.getPositionActuel();
        assertEquals(expected,actual);
    }

    @DisplayName("recherchePositionActuel()")
    @Test
    void recherchePositionActuel() {
        s.recherchePositionActuel(1,2);
        Noeud expected = g.rechercherNoeud(1,2);
        assertEquals(expected,s.getPositionActuel());
    }

    @DisplayName("getNumeroWorker()")
    @Test
    void getNombreSoldat() {

        assertEquals(1, s.getNumeroWorker());
    }

    @DisplayName("toString()")
    @Test
    void testToString() {
        assertEquals("Ouvrier n°2 | Colonie n°1 -> à un stock de 0 nourriture stockée | Noeud n°1 | Transporte : 0 kg de nourriture | Retourne à la colonie : false", s.toString());
    }

    @DisplayName("doitRetravailler()")
    @Test
    void doitRetravailler() {

        boolean bool = s.doitRetravailler();
        assertTrue(bool);
    }
}
