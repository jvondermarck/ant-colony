package V2.javaTest;

import V2.javaClass.Ouvrier;
import V2.javaClass.Noeud;
import V2.javaClass.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

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
            actual = ouvrier.getPositionActuel();
        }

        Noeud n1 = graphe.rechercherNoeud(0,1);
        Noeud n2 = graphe.rechercherNoeud(1,2);
        Noeud n3 = graphe.rechercherNoeud(2,1);

        if(actual != n1 || actual == n2 || actual == n3)
        {
            fail();
        }
    }

    @DisplayName("Probabilité - trois possibilités de déplacements")
    @Test
    void test3()
    {
        double proba1 = 0;
        double proba2 = 0;
        double proba3 = 0;
        double probaTheorique1 = 1./2;
        double probaTheorique2 = 1./3;
        double probaTheorique3 = 1./6;

        PrintStream out = System.out;
        System.setOut(new PrintStream(OutputStream.nullOutputStream()));

        int iteration = 6000;
        Ouvrier ouv = new Ouvrier(1,1,reine.getColonie(), graphe);
        while(iteration!=0)
        {
            Noeud actual = null;
            for(int i=0; i<1; i++)
            {
                ouv.randomDirection();
                ouv.recherchePositionActuel(ouv.getX(), ouv.getY());
                actual = ouv.getPositionActuel();
            }

            Noeud n1 = graphe.rechercherNoeud(0,1);
            Noeud n2 = graphe.rechercherNoeud(1,2);
            Noeud n3 = graphe.rechercherNoeud(2,1);

            if(actual == n1)
            {
                proba1++;
            }
            if(actual == n2)
            {
                proba2++;
            }
            if( actual == n3)
            {
                proba3++;
            }
            iteration--;
        }

        System.setOut(out);
        System.out.println("Probabilité pour la case (0,1) : " + proba1);
        System.out.println("Probabilité pour la case (0,1) theorique : " + 6000*probaTheorique1);
        System.out.println("Probabilité pour la case (1,2) : " + proba2);
        System.out.println("Probabilité pour la case (1,2) theorique : " + 6000*probaTheorique2);
        System.out.println("Probabilité pour la case (2,1) : " + proba3);
        System.out.println("Probabilité pour la case (2,1) theorique : " + 6000*probaTheorique3);
    }



}
