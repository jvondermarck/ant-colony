package V2.javaClass;

/**
 * La classe abstraite modifiable {@code Figure}.
 * <p>
 *
 */

public interface FourmisMove extends Fourmis {

    void randomDirection(Graphe g, Object o, Reine r);

    int getX();

    int getY();
}

// Comme cette interface extends l'interface Fourmis, on est pas obligé de remettre les méthodes de l'interface Fourmis