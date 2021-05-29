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
    @DisplayName("setQuantityFood()")
    void test2()
    {
        r.getColonie().setQuantityFood(15);
        assertEquals("Colonie n°5 -> à un stock de 15 nourriture stockée", r.getColonie().toString());
    }

    @Test
    @DisplayName("getEvaporationParam()")
    void getEvaporationParam()
    {
        r.setParametreColonie(5,5,5);
        assertEquals(5, r.getColonie().getEvaporationParam());
    }

    @Test
    @DisplayName("getFoodParam()")
    void getFoodParam()
    {
        r.setParametreColonie(5,55,5);
        assertEquals(55, r.getColonie().getFoodParam());
    }

    @Test
    @DisplayName("getPheromoneParam()")
    void getPheromoneParam()
    {
        r.setParametreColonie(533,55,0);
        assertEquals(0, r.getColonie().getPheromoneParam());
    }

    @Test
    @DisplayName("setEvaporationParam()")
    void setEvaporationParam()
    {
        r.getColonie().setEvaporationParam(678);
        assertEquals(678, r.getColonie().getEvaporationParam());
    }

    @Test
    @DisplayName("setFoodParam()")
    void setFoodParam()
    {
        r.getColonie().setFoodParam(67548);
        assertEquals(67548, r.getColonie().getFoodParam());
    }

    @Test
    @DisplayName("setPheromoneParam()")
    void setPheromoneParam()
    {
        r.getColonie().setPheromoneParam(8);
        assertEquals(8, r.getColonie().getPheromoneParam());
    }

}