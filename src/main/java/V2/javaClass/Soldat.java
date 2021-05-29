package V2.javaClass;

import java.util.ArrayList;

/**
 * La classe Soldat qui se promène dans le Graphe en évitant des obstacles.
 */
public class Soldat extends FourmisMove {
    private final Colonie colonie; // La colonie de la fourmi
    private static int nombreSoldat = 0; // Le nombre de soldat
    private int x; // La position X de la fourmi sur la colonie
    private int y; // La position Y de la fourmi sur la colonie
    private final Graphe g; // Le graphe g de la fourmis
    private Noeud positionActuel; // Le noeud où se trouve la fourmis
    private final int numeroSoldat; // The Numero soldat.

    /**
     * Crée un nouveau Soldat.
     *
     * @param x       sa coordonnée X de naissance
     * @param y       sa coordonnée Y de naissance
     * @param colonie sa colonie où il est né
     * @param graphe  le graphe où le soldat réside
     */
    public Soldat(int x, int y, Colonie colonie, Graphe graphe) {
        this.colonie = colonie;
        nombreSoldat = nombreSoldat+1;
        this.numeroSoldat = nombreSoldat;
        this.g = graphe;
        recherchePositionActuel(x, y);
    }

    /**
     * On recherche le numéro du Noeud où se trouve le soldat
     *
     * @param ligne   numéro de la ligne où se trouve le noeud
     * @param colonne numéro de la colonne où se trouve le noeud
     */
    public void recherchePositionActuel(int ligne, int colonne)
    {
        this.positionActuel = (this.g.rechercherNoeud(ligne, colonne));
        // Grace au noeud trouvé, on cherche sa coordonnée X et Y, qui permettra de chercher son prochain Noeud, etc...
        ArrayList<Integer> coord = g.rechercherCoord(this.positionActuel);
        setX(coord.get(0)); // La coordonnée X se situe à l'indice 0 de la liste renvoyé
        setY(coord.get(1)); // La coordonnée Y se situe à l'indice 1 de la liste renvoyé
    }

    /**
     * Permet grace à une liste de Noeud adjcacents à la fourmis, de trouver un nouvelle emplacement
     */
    public void randomDirection()
    {
        // On cherche le nouveau Noeud de la fourmis
        rechercheAretes(this.x, this.y, this.g);
        this.positionActuel = randomNoeud();

        // Grace au noeud trouvé, on cherche sa coordonnée X et Y, qui permettra de chercher son prochain Noeud, etc...
        ArrayList<Integer> coord = g.rechercherCoord(this.positionActuel);
        setX(coord.get(0)); // La coordonnée X se situe à l'indice 0 de la liste renvoyé
        setY(coord.get(1)); // La coordonnée Y se situe à l'indice 1 de la liste renvoyé
    }

    /**
     * Redéfinition de la méthode toString(), on affiche la position d'un soldat avec le nom de sa colonie
     * @return la chaine de caracteres
     */
    @Override
    public String toString() {
        return "Soldat n°" + numeroSoldat + "  | " + this.colonie.toString() + " | " + getPositionActuel();
    }

    /**
     * Gets numero soldat.
     *
     * @return the numero soldat
     */
    public int getNumeroSoldat() {
        return numeroSoldat;
    }

    /**
     * Gets position actuel.
     *
     * @return the position actuel
     */
    public Noeud getPositionActuel() {
        return positionActuel;
    }

    /**
     * Sets position actuel.
     *
     * @param positionActuel the position actuel
     */
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