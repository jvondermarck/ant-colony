//package V1.javaTest;
//import V2.essai.Aretes;
//import V1.javaClass.Graphe;
//import V1.javaClass.Noeud;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//class AretesTest {
//
//    static final int WIDTH = 13;
//    static final int HEIGHT = 19;
//
//    Graphe g;
//    boolean [][] obstacle;
//
//    @BeforeEach
//    void setUp() {
//        g = new Graphe(WIDTH, HEIGHT);
//    }
//
//    @Test
//    @DisplayName("Cellules adjacentes sur un bord avec un obstacle")
//    void rechercheAretes1() {
//        g.mettreObstacle(0,1);
//        Noeud actual = Aretes.rechercheAretes(g, 0,0);
//        Noeud expected = g.rechercherNoeud(1,0);
//        assertEquals(expected,actual);
//    }
//
//    @Test
//    @DisplayName("Cellules adjacentes sur un bord - blocage de la fourmis")
//    void rechercheAretes3() {
//        g.mettreObstacle(0,1);
//        g.mettreObstacle(1,0);
//
//        Noeud actual = Aretes.rechercheAretes(g, 0,0);
//        Noeud expected = g.rechercherNoeud(0,0);
//        assertEquals(expected, actual);
//    }
//}