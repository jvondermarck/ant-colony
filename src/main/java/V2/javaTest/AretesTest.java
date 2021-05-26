package V2.javaTest;

import V2.javaClass.Aretes;
import V2.javaClass.Graphe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AretesTest {

    static final int WIDTH = 13;
    static final int HEIGHT = 19;

    Graphe g;
    Boolean [][] obstacle;

    @BeforeEach
    void setUp() {
        g = new Graphe(WIDTH, HEIGHT);
        obstacle = g.getEstObstacle();
    }

    @Test
    @DisplayName("Cellules adjacentes sur un bord")
    void rechercheAretes1() {
        Aretes a = new Aretes(g, 0,0);
        int actual = a.getListX().size();
        int expected = 2;
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Cellules adjacentes au milieu")
    void rechercheAretes2() {
        Aretes a = new Aretes(g, 6,8);
        int actual = a.getListX().size();
        int expected = 4;
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Cellules adjacentes sur un bord - Avec obstacles autour")
    void rechercheAretes3() {
        obstacle[0][1] = true;
        obstacle[1][0] = true;
        g.setEstObstacle(obstacle);

        Aretes a = new Aretes(g, 0, 0);
        int actual = a.getListX().size();
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
        Aretes a = new Aretes(g, 6,8);
        int actual = a.getListX().size();
        int expected = 1;
        assertEquals(expected,actual);
    }
}