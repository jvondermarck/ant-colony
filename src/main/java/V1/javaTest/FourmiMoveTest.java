package V1.javaTest;
import V1.javaClass.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        s = new Soldat(0,0,r.getColonie(),g);
    }

    @Test
    void randomDirection()
    {
        s.randomDirection(g);
        Aretes arr = new Aretes(g,0,0);
        ArrayList<Integer> listX = arr.getListX();
        ArrayList<Integer> listY = arr.getListY();

        assertTrue(listX.contains(s.getX()) && listY.contains(s.getY()));
    }


}
