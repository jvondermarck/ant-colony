package V1.javaClass;
import java.util.ArrayList;

/**
 * La classe FourmisMove utilisé uniquement par des fourmis qui on le droit de se déplacer {@code Figure}.
 * <p>
 */
public abstract class FourmisMove implements Fourmis {
    /**
     *  La position X de la fourmi sur la colonie
     */
    protected int x;
    /**
     * La position Y de la fourmi sur la colonie
     */
    protected int y;
    /**
     * Le noeud où se trouve la fourmis
     */
    protected Noeud positionActuel;

    /**
     * Définit la fourmi comme fourmi qui peut se déplacer et prend ses coordonnées.
     *
     * @param x la coordonné x (ligne)
     * @param y la coordonné y (colonne)
     */
    public FourmisMove(int x, int y) {
        setX(x);
        setY(y);
    }

    /**
     * Cette méthode cherche la future cellule où se déplacera la fourmi ouvrière.
     * Et tire de facon aléatoire en prenant toutes les précautions possibles qui sont les obstacles
     *
     * @param g le graphe où se trouve la fourmi.
     */
    public void randomDirection(Graphe g)
    {
        // On cherche le nouveau Noeud de la fourmis
        this.positionActuel = Aretes.rechercheAretes(g, this.x, this.y);

        // Grace au noeud trouvé, on cherche sa coordonnée X et Y, qui permettra de chercher son prochain Noeud, etc...
        ArrayList<Integer> coord = g.rechercherCoord(this.positionActuel);
        setX(coord.get(0)); // La coordonnée X se situe à l'indice 0 de la liste renvoyé
        setY(coord.get(1)); // La coordonnée Y se situe à l'indice 1 de la liste renvoyé
    }

    /**
     * Recevoir la coordonnée X (ligne).
     *
     * @return the x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Recevoir la coordonnée Y (colonne).
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
}
