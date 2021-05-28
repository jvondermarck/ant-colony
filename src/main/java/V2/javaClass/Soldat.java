package V2.javaClass;

/**
 * La classe Soldat qui se promène dans le Graphe en évitant des obstacles.
 */
public class Soldat extends MoveSoldat {
    private final Colonie colonie;
    private static int nombreSoldat = 0;
    private final Graphe g;
    /**
     * The Numero soldat.
     */
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
        super(x,y, graphe);
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
        setPositionActuel(this.g.rechercherNoeud(ligne, colonne));
    }

    /**
     * Redéfinition de la méthode toString(), on affiche la position d'un soldat avec le nom de sa colonie
     * @return la chaine de caracteres
     */
    @Override
    public String toString() {
        return "Soldat n°" + numeroSoldat + "  | " + this.colonie.toString() + " | " + getPositionActuel();
    }

    public int getNumeroSoldat() {
        return numeroSoldat;
    }
}