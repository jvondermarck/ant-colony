package V1.javaTest;
import V1.javaClass.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FourmiMoveTest {

    static final int WIDTH = 13;
    static final int HEIGHT = 19;

    Graphe g;
    Soldat s;
    Reine r;
    int[][] taille;

    @BeforeEach
    void setUp() {
        g = new Graphe(WIDTH, HEIGHT);
        r = new Reine(0,0,g);
        s = new Soldat(0,0, r.getColonie(), g);
    }

    @DisplayName("randomDirection avec une possibilité de déplacement")
    @Test
    void randomDirection()
    {
        g.mettreObstacle(0,1);
        s.randomDirection(g);

        assertTrue(s.getX() == 1 && s.getY() == 0);
    }

    @DisplayName("randomDirection avec 4 possibilités de déplacement")
    @Test
    void randomDirectionV2()
    {
        s = new Soldat(2,2, r.getColonie(), g);
        s.randomDirection(g);

        Noeud a = g.rechercherNoeud(2,1);
        Noeud b = g.rechercherNoeud(2,3);
        Noeud c = g.rechercherNoeud(1,2);
        Noeud d = g.rechercherNoeud(3,2);

        if(a == s.getPositionActuel())
        {
            assertEquals(a,s.getPositionActuel());
        } else if(b == s.getPositionActuel())
        {
            assertEquals(b,s.getPositionActuel());
        } else if(c == s.getPositionActuel())
        {
            assertEquals(c,s.getPositionActuel());
        } else if(d == s.getPositionActuel())
        {
            assertEquals(d,s.getPositionActuel());
        } else {
            fail();
        }

    }

    @Test
    void getX()
    {
        g.mettreObstacle(0,1);
        s.randomDirection(g);

        assertEquals(1, s.getX());
    }

    @Test
    void getY()
    {
        g.mettreObstacle(0,1);
        s.randomDirection(g);

        assertEquals(0, s.getY());
    }

    @Test
    void setX()
    {
        s.setX(3);

        assertEquals(3, s.getX());
    }

    @Test
    void setY()
    {
        s.setY(2);

        assertEquals(2, s.getY());
    }


}
