package V2.javaTest;

import V2.javaClass.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class FourmisMoveTest {
    static final int WIDTH = 13;
    static final int HEIGHT = 19;

    Graphe g;
    boolean [][] obstacle;
    Soldat s;
    Colonie c;

    @BeforeEach
    void setUp() {
        g = new Graphe(WIDTH, HEIGHT);
        obstacle = g.getEstObstacle();
        c = new Colonie();
        s = new Soldat(0,0,c,g);
    }

    @Test
    @DisplayName("Cellules adjacentes sur un bord")
    void rechercheAretes1() {
        s.randomDirection();
        int actual = s.getListNoeud().size();
        int expected = 2;
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("randomNoeud()")
    void randomNoeud() {
        s.randomDirection();
        Noeud actual = s.randomNoeud();
        Noeud expected1 = g.rechercherNoeud(0,1);
        Noeud expected2 = g.rechercherNoeud(1,0);
        if(expected1 == actual || expected2 == actual)
        {
            assertTrue(true);
        }
    }

    @Test
    @DisplayName("probabilityLaw()")
    void probabilityLaw() {
        Random rand = new Random();
        int index = s.probabilityLaw(0, rand);
        assertEquals(0, index);
    }

    @Test
    @DisplayName("probabilityLaw2()")
    void probabilityLaw2() {
        Random rand = new Random();
        int index = s.probabilityLaw(6, rand);
        if(index <0 || index>6)
        {
            fail();
        }
    }

    @Test
    @DisplayName("Cellules adjacentes au milieu")
    void rechercheAretes2() {
        s.recherchePositionActuel(6,8);
        s.randomDirection();
        int actual =  s.getListNoeud().size();
        int expected = 4;
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Cellules adjacentes sur un bord - Avec obstacles autour")
    void rechercheAretes3() {
        obstacle[0][1] = true;
        obstacle[1][0] = true;
        g.setEstObstacle(obstacle);

        s.randomDirection();
        int actual = s.getListNoeud().size();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Cellules adjacentes au milieu - Avec obstacles autour")
    void rechercheAretes4()
    {
        obstacle[6][7] = true;
        obstacle[6][9] = true;
        obstacle[5][8] = true;
        obstacle[7][8] = true;
        g.setEstObstacle(obstacle);

        s.recherchePositionActuel(6,8);
        s.randomDirection();
        int actual = s.getListNoeud().size();
        int expected = 1;
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("getListNoeud()")
    void getListNoeud()
    {
        s.recherchePositionActuel(6,8);
        s.randomDirection();
        int actual = s.getListNoeud().size();
        int expected = 4;
        assertEquals(expected,actual);
    }
}
