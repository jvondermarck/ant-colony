package V1.javaClass;

/**
 * La classe Soldat qui se promène dans le Graphe en évitant des obstacles.
 */
public class Soldat extends FourmisMove {
    private final Colonie colonie;
    private final Graphe graphe;
    private static int nombreSoldat = 0;
    private final int numeroSoldat;

    /**
     * Crée un nouveau Soldat.
     *
     * @param x       sa coordonnée X de naissance
     * @param y       sa coordonnée Y de naissance
     * @param colonie sa colonie où il est né
     * @param graphe  le graphe où le soldat réside
     */
    public Soldat(int x, int y, Colonie colonie, Graphe graphe) {
        super(x,y);
        this.colonie = colonie;
        this.graphe = graphe;
        nombreSoldat = nombreSoldat+1;
        this.numeroSoldat = nombreSoldat;
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
        positionActuel = graphe.rechercherNoeud(ligne, colonne);
    }

    /**
     * Redéfinition de la méthode toString(), on affiche la position d'un soldat avec le nom de sa colonie
     */
    @Override
    public String toString() {
        return "Soldat n°" + numeroSoldat + " - " + this.colonie.toString() + " --> se trouve au " + this.positionActuel;
    }

    /**
     * On retourne le Noeud où se trouve le soldat
     *
     * @return la position du noeud
     */
    public Noeud getPositionActuel() {
        return positionActuel;
    }
}