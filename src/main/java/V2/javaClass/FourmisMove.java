package V2.javaClass;

/**
 * La classe abstraite modifiable {@code Figure}.
 * <p>
 *
 */

public interface FourmisMove extends Fourmis {

    /**
     * Cette méthode cherche la future cellule où se déplacera la fourmi ouvrière.
     * Et tire de facon aléatoire en prenant toutes les précautions possibles qui sont les obstacles
     *
     * @param g le graphe où se trouve la fourmi.
     */
    void randomDirection(Graphe g);

    int getX();

    int getY();
}

// Comme cette interface extends l'interface Fourmis, on est pas obligé de remettre les méthodes de l'interface Fourmis