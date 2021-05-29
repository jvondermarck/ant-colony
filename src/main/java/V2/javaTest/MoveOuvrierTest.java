package V2.javaTest;

import V2.javaClass.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MoveOuvrierTest {
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

    @DisplayName("paramOuvrier() - aVisite une cellule au bord")
    @Test
    void paramOuvrier() {
        s.setaVisite(0,1,true);
        s.randomDirection();
        Noeud expected = g.rechercherNoeud(1,0);
        assertEquals(expected,s.getPositionActuel());
    }

    @DisplayName("paramOuvrier() - aVisite les deux cellules du bord, donc 2 choix possibles")
    @Test
    void paramOuvrier2() {
        s.setaVisite(0,1,true);
        s.setaVisite(1,0,true);
        s.randomDirection();
        assertEquals(2, s.getListNoeud().size());
    }

    @DisplayName("rechercheBestPheromone() : ouvrier qui prend la meilleure cellule de phéromones")
    @Test
    void rechercheBestPheromone() {
        int[][] phero = g.getQuantityPheromone();
        phero[2][3] = 100;
        phero[2][1] = 50;

        s.recherchePositionActuel(2,2);
        g.setQuantityPheromone(phero);
        s.randomDirection();
        Noeud expected = g.rechercherNoeud(2,3);
        assertEquals(expected, s.getPositionActuel());
    }

    @DisplayName("isEtatRetour()")
    @Test
    void isEtatRetour() {
        assertFalse(s.isEtatRetour());
    }

    @DisplayName("getListNoeudRetour()")
    @Test
    void getListNoeudRetour() {
        assertEquals(1,s.getListNoeudRetour().size());
    }

    @DisplayName("getQuantityFoodTaken()")
    @Test
    void getQuantityFoodTaken() {
        s.setQuantityFoodTaken(5);
        assertEquals(5,s.getQuantityFoodTaken());
    }

    @DisplayName("getPositionActuel()")
    @Test
    void getPositionActuel() {
        Noeud epected = g.rechercherNoeud(0,0);
        assertEquals(epected,s.getPositionActuel());
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
    @DisplayName("setListNoeudRetour()")
    void setListNoeudRetour()
    {
        s.setListNoeudRetour(g.rechercherNoeud(1,1));
        assertEquals(2, s.getListNoeudRetour().size());
    }

    @Test
    @DisplayName("setaVisite()")
    void setaVisite()
    {
        s.setaVisite(0,1,true);
        Noeud ex = g.rechercherNoeud(1,0);
        s.randomDirection();
        assertEquals(ex, s.getPositionActuel());
    }

    @Test
    @DisplayName("setEtatRetour()")
    void setEtatRetour()
    {
        s.setEtatRetour(true);
        assertTrue(s.isEtatRetour());
    }

    @Test
    @DisplayName("setQuantityFoodTaken()")
    void setQuantityFoodTaken()
    {
        s.setQuantityFoodTaken(5);
       assertEquals(5, s.getQuantityFoodTaken());
    }

    @Test
    @DisplayName("setPositionActuel()")
    void setPositionActuel()
    {
        s.setPositionActuel(g.rechercherNoeud(0,2));
        assertEquals(g.rechercherNoeud(0,2), s.getPositionActuel());
    }










}
