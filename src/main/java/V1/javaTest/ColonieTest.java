package V1.javaTest;

import V1.javaClass.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColonieTest {

    @Test
    @DisplayName("Création d'une colonie")
    void creationColonie()
    {
        Graphe g = new Graphe(3,3);
        Reine r = new Reine(1,1,g);
        assertEquals("Colonie n°1", r.getColonie().toString());
    }
}