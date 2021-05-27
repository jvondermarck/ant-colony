package V2.javaClass;


public class Ouvrier extends MoveOuvrier {
    protected final Colonie colonie;
    protected static int nombreOuvrier = 0;

    private Graphe g;

    public Ouvrier(int x, int y, Colonie colonie, Graphe graphe)
    {
        super(x,y,colonie);
        this.colonie = colonie;
        nombreOuvrier = nombreOuvrier+1;
        this.numeroWorker = nombreOuvrier;
        this.quantityFoodTaken = 0;
        this.g = graphe;
        recherchePositionActuel(x, y);


        this.etatRetour = false;

        this.aVisite = new boolean[graphe.getRow()][graphe.getColumn()];
        for (int i = 0; i < graphe.getRow(); ++i)
        {
            for(int j = 0; j < graphe.getColumn(); ++j)
            {
                aVisite[i][j] = false; // On dit au début qu'il a rien visité
            }
        }

        this.aVisite[x][y] = true;
        this.listNoeudRetour.add(g.rechercherNoeud(x,y));

        //this.aVisite[2][3] = true;
    }

    /**
     * On recherche le numéro du Noeud où se trouve le soldat
     * @param ligne numéro de la ligne où se trouve le noeud
     * @param colonne numéro de la colonne où se trouve le noeud
     */
    public void recherchePositionActuel(int ligne, int colonne)
    {
        positionActuel = this.g.rechercherNoeud(ligne, colonne);
    }

    // Pour savoir si sa liste est vide ca veut dire qu'elle est a la fourmiliere, donc elle doit repartir chercher a manger
    public boolean doitRetravailler()
    {
        if(listNoeudRetour.isEmpty()) // Si liste est vide
        {
            etatRetour = false; // Elle va denouveau travailler et rechercher à manger
            this.colonie.setQuantityFood(quantityFoodTaken);
            for (int i = 0; i < g.getRow(); ++i)
            {
                for(int j = 0; j < g.getColumn(); ++j)
                {
                    aVisite[i][j] = false; // On dit au début qu'il a rien visité
                }
            }
            // Comme on recommence tout a zero, on re-initialise tout
            aVisite[x][y] = true;
            this.listNoeudRetour.add(g.rechercherNoeud(x,y));
            quantityFoodTaken = 0;
            System.out.println(colonie); // On affiche l'état de la colonie
        }
        return !etatRetour;
    }

    @Override
    public String toString() {
        return "Ouvrier n°" + numeroWorker + " | " + this.colonie.toString() + " | " + this.positionActuel + " | Transporte : " + quantityFoodTaken + " kg de nourriture | Retourne à la colonie : " + etatRetour;
    }

    public void setaVisite(boolean[][] aVisite) {
        this.aVisite = aVisite;
    }

    public boolean isEtatRetour() {
        return etatRetour;
    }

    public void setEtatRetour(boolean etatRetour) {
        this.etatRetour = etatRetour;
    }

    public void setQuantityFoodTaken(int quantityFoodTaken) {
        this.quantityFoodTaken = quantityFoodTaken;
    }

    public Noeud getPositionActuel() {
        return positionActuel;
    }

    public int getNumeroWorker() {
        return numeroWorker;
    }
}
