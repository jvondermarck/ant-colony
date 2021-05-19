package V1.javaTest;

import V1.javaClass.AntFacade;
import V1.javaClass.AntFacadeController;
import V1.javaClass.Colonie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColonieTest {

    static final int WIDTH = 13;
    static final int HEIGHT = 19;

    AntFacadeController appli;

    @Test
    @DisplayName("Création d'une colonie")
    void creationColonie()
    {
        appli = new AntFacade(0);
        appli.createGrid(WIDTH, HEIGHT);
        Colonie c = new Colonie();
        assertEquals("Colonie n°1", c.toString());
    }
}