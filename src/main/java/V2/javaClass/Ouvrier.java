package V2.javaClass;


/**
 * The type Ouvrier.
 */
public class Ouvrier extends MoveOuvrier {
    /**
     * The constant nombreOuvrier.
     */
    private static int nombreOuvrier = 0;
    /**
     * Le numéro de l'ouvrier
     */
    private final int numeroWorker;
    private final Colonie colonie;
    private final Graphe g;

    /**
     * Instantiates a new Ouvrier.
     *
     * @param x       the x
     * @param y       the y
     * @param colonie the colonie
     * @param graphe  the graphe
     */
    public Ouvrier(int x, int y, Colonie colonie, Graphe graphe)
    {
        super(x,y,colonie, graphe);
        nombreOuvrier = nombreOuvrier+1;
        this.numeroWorker = nombreOuvrier;
        this.colonie = colonie;
        this.g = graphe;
        recherchePositionActuel(x, y);
        setaVisite(x, y, true); // Apres son premier déplacement, il ne pourra pas retourner à sa colonie vu qu'il peut pas aller sur les noeuds deja visite
        setListNoeudRetour(g.rechercherNoeud(x,y)); // Et on ajoute directement dans une liste, la liste de noeud qui servira à l'ouvriere de connaitre son chemin
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
     * Si la liste est vide, ca veut dire qu'elle est a la fourmiliere, donc elle doit repartir chercher a manger
     *
     * @return the boolean
     */
    public boolean doitRetravailler()
    {
        if(getListNoeudRetour().isEmpty()) // Si liste est vide
        {
            setEtatRetour(false); // Elle va denouveau travailler et rechercher à manger
            this.colonie.setQuantityFood(getQuantityFoodTaken());
            for (int i = 0; i < g.getRow(); ++i)
            {
                for(int j = 0; j < g.getColumn(); ++j)
                {
                    setaVisite(i, j, false); // On dit au début qu'il a rien visité
                }
            }
            // Comme on recommence tout a zero, on re-initialise tout
            setaVisite(getX(), getY(), true);
            setListNoeudRetour(g.rechercherNoeud(getX(),getY()));
            setQuantityFoodTaken(0);
        }
        return !isEtatRetour();
    }

    @Override
    public String toString() {
        return "Ouvrier n°" + numeroWorker + " | " + this.colonie.toString() + " | " + getPositionActuel() + " | Transporte : " + getQuantityFoodTaken() + " kg de nourriture | Retourne à la colonie : " + isEtatRetour();
    }

    public int getNumeroWorker() {
        return numeroWorker;
    }
}
