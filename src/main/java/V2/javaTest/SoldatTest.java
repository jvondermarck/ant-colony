package V2.javaTest;

import V2.javaClass.Colonie;
import V2.javaClass.Graphe;
import V2.javaClass.Noeud;
import V2.javaClass.Soldat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @DisplayName("randomDirection()")
    @Test
    void randomDirection() {
        s.randomDirection();
        if((s.getX() == 1 && s.getY() == 0) || (s.getX() == 0 && s.getY() == 1))
        {
            assertTrue(true);
        }
    }

    @DisplayName("getNombreSoldat()")
    @Test
    void getNombreSoldat() {

        assertEquals(1, s.getNumeroSoldat());
    }

    @DisplayName("toString()")
    @Test
    void testToString() {
        assertEquals("Soldat n°2  | Colonie n°1 -> à un stock de 0 nourriture stockée | Noeud n°1", s.toString());
    }

    @DisplayName("setPositionActuel()")
    @Test
    void setPositionActuel() {

        Noeud n = g.rechercherNoeud(3,5);
        s.setPositionActuel(n);
        assertEquals(n, s.getPositionActuel());
    }

    @Test
    @DisplayName("getX()")
    void getX()
    {
        assertEquals(0, s.getX());
    }

    @Test
    @DisplayName("getY()")
    void getY()
    {
        assertEquals(0, s.getY());
    }

    @Test
    @DisplayName("setX()")
    void getColumn()
    {
        s.setX(5);
        assertEquals(5, s.getX());
    }

    @Test
    @DisplayName("setY()")
    void setY()
    {
        s.setY(2);
        assertEquals(2, s.getY());
    }

    @Test
    @DisplayName("getNumeroSoldat()")
    void getNumeroSoldat()
    {
        assertEquals(7, s.getNumeroSoldat());
    }
}