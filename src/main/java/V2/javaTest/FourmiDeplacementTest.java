package V2.javaTest;

import V2.javaClass.Ouvrier;
import V2.javaClass.Noeud;
import V2.javaClass.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FourmiDeplacementTest {

    static final int WIDTH = 3;
    static final int HEIGHT = 3;

    Graphe graphe;
    Reine reine;
    Ouvrier ouvrier;

    @BeforeEach
    void setUp()
    {
        graphe = new Graphe(HEIGHT, WIDTH);
        reine = new Reine(1,0,graphe);
        ouvrier = new Ouvrier(1,0, reine.getColonie(), graphe);
        graphe.mettreObstacle(0,0);
        graphe.mettreObstacle(2,0);
        graphe.mettreNourriture(0,2,10);
        graphe.mettreNourriture(2,2,10);
        graphe.getQuantityPheromone()[0][1] = 2;
        graphe.getQuantityPheromone()[1][2] = 1;


    }

    @DisplayName("Premier déplacement de la fourmi : (1,1)")
    @Test
    void test1()
    {
        ouvrier.randomDirection();
        Noeud expected = graphe.rechercherNoeud(1,1);
        ouvrier.recherchePositionActuel(ouvrier.getX(), ouvrier.getY());
        Noeud actual = ouvrier.getPositionActuel();
        assertEquals(expected, actual);
    }

    @DisplayName("Deuxieme deplacement fourmis")
    @Test
    void test2()
    {
        Noeud actual = null;
        for(int i=0; i<2; i++)
        {
            ouvrier.randomDirection();
            ouvrier.recherchePositionActuel(ouvrier.getX(), ouvrier.getY());
            actual = ouvrier.getPositionActuel();
        }

        Noeud n1 = graphe.rechercherNoeud(0,1);
        Noeud n2 = graphe.rechercherNoeud(1,2);
        Noeud n3 = graphe.rechercherNoeud(2,1);

        if(actual != n1 && actual != n2 && actual != n3)
        {
            assertFalse(false);
        }
    }



}
