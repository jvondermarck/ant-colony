package V2.javaTest;

import V2.javaClass.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ColonieTest {

    static final int WIDTH = 13;
    static final int HEIGHT = 19;

    AntFacadeController appli;
    Graphe g;
    Reine r;

    @BeforeEach
    void setUp()
    {
        g = new Graphe(15,15);
        r = new Reine(0,0,g);
    }

    @Test
    @DisplayName("Création d'une colonie")
    void creationColonie()
    {
        assertEquals("Colonie n°1 -> à un stock de 0 nourriture stockée", r.getColonie().toString());
    }

    @Test
    @DisplayName("Parametres de la colonie sont correctes : foodParam, pheromone, evaporaman")
    void test1()
    {
        r.setParametreColonie(10,15,20);
        assertEquals(10, r.getColonie().getEvaporationParam());
        assertEquals(15, r.getColonie().getFoodParam());
        assertEquals(20, r.getColonie().getPheromoneParam());
    }

    @Test
    @DisplayName("Quantité de nourriture de la colonie")
    void test2()
    {
        r.getColonie().setQuantityFood(15);
        assertEquals("Colonie n°3 -> à un stock de 15 nourriture stockée", r.getColonie().toString());
    }

}