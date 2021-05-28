package V2.javaClass;

import java.util.ArrayList;
import java.util.Random;

/**
 * The type Move soldat qui s'occupe de faire déplacer la fourmi
 */
public abstract class MoveSoldat extends FourmisMove {

    /**
     * La position X de la fourmi sur la colonie
     */
    private int x;
    /**
     * La position Y de la fourmi sur la colonie
     */
    private int y;
    /**
     * Le graphe g de la fourmis
     */
    private final Graphe g;
    /**
     * Le noeud où se trouve la fourmis
     */
    private Noeud positionActuel;

    /**
     * The List noeud.
     */
    private final ArrayList<Noeud> listNoeud = new ArrayList<>(); // On crée une list de Noeud adjacents susceptibles, où un Noeud sera tiré aléatoirement
    private boolean record; // Record sert a mettre dans la liste de Noeud le noeud qui est un obstacle, il sert pour le fichier de rapport

    /**
     * Qui permet de choisir aléatoirement un noeud dans une liste de Noeuds adjacents à la fourmi.
     */
    private final Random random = new Random();

    /**
     * Instantiates a new Move soldat.
     *
     * @param x the x
     * @param y the y
     * @param g the g
     */
    public MoveSoldat(int x, int y, Graphe g) {
        setX(x);
        setY(y);
        this.g = g;
    }

    public void randomDirection() {

        // On cherche le nouveau Noeud de la fourmis
        rechercheAretes(this.x, this.y, this.g);
        this.positionActuel = randomNoeud();

        // Grace au noeud trouvé, on cherche sa coordonnée X et Y, qui permettra de chercher son prochain Noeud, etc...
        ArrayList<Integer> coord = g.rechercherCoord(this.positionActuel);
        setX(coord.get(0)); // La coordonnée X se situe à l'indice 0 de la liste renvoyé
        setY(coord.get(1)); // La coordonnée Y se situe à l'indice 1 de la liste renvoyé
    }

    public Noeud getPositionActuel() {
        return positionActuel;
    }

    public void setPositionActuel(Noeud positionActuel) {
        this.positionActuel = positionActuel;
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
