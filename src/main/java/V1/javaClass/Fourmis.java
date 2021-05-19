package V1.javaClass;

/**
 * Interface Fourmis qui oblige a n'importe quel fourmis d'avoir une coordonnée X et Y (ligne et colonne) où elle sera situé sur le graphe.
 */
public interface Fourmis {
    /**
     * Mettre la position x.
     *
     * @param x l'entier de la coordonnée X
     */
    void setX(int x);

    /**
     * Mettre la position y.
     *
     * @param y l'entier de la coordonnée Y
     */
    void setY(int y);
}
