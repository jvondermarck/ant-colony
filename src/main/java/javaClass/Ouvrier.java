package javaClass;

import java.util.ArrayList;

public class Ouvrier extends FourmisMove {
    private final Colonie colonie;
    private final Graphe graphe;
    private static int nombreSoldat = 0;
    private final int numeroWorker;
    private Noeud positionActuel;

    private Boolean[][] aVisite; // True : si il a deja visité une cellule
    private final ArrayList<Integer> listXRetour = new ArrayList<>(); // Liste coordonné de x : servira pour le retour de la fourmis ouvriere
    private final ArrayList<Integer> listYRetour = new ArrayList<>(); // Liste coordonné de y :  servira pour le retour de la fourmis ouvriere

    // Paramètre de la fourmis
    private int evaporationParam;
    private int foodParam;
    private int pheromoneParam;

    public Ouvrier(int x, int y, Colonie colonie, Graphe graphe)
    {
        super(x,y);
        this.colonie = colonie;
        this.graphe = graphe;
        nombreSoldat = nombreSoldat+1;
        this.numeroWorker = nombreSoldat;
        recherchePositionActuel(x, y);

        this.evaporationParam = colonie.getEvaporationParam();
        this.foodParam = colonie.getFoodParam();
        this.pheromoneParam = colonie.getPheromoneParam();

        this.aVisite = new Boolean[graphe.getLongueur()][graphe.getLargueur()];
        for (int i = 0; i < graphe.getTaille().length; ++i)
        {
            for(int j = 0; j < graphe.getTaille()[i].length; ++j)
            {
                aVisite[i][j] = false; // On dit au début qu'il a rien visité
            }
        }

        this.aVisite[8][9] = true;
        this.aVisite[8][7] = true;
        this.aVisite[7][8] = true;
        this.aVisite[9][8] = true;
    }

    /**
     * On recherche le numéro du Noeud où se trouve le soldat
     * @param ligne numéro de la ligne où se trouve le noeud
     * @param colonne numéro de la colonne où se trouve le noeud
     */
    public void recherchePositionActuel(int ligne, int colonne)
    {
        positionActuel = graphe.rechercherNoeud(ligne, colonne);
    }

    @Override
    public String toString() {
        return "Ouvrier n°" + numeroWorker + " - " + this.colonie.toString() + " --> se trouve au " + this.positionActuel;
    }

    public Boolean[][] getaVisite() {
        return aVisite;
    }

    public void setaVisite(Boolean[][] aVisite) {
        this.aVisite = aVisite;
    }

    public ArrayList<Integer> getListXRetour() {
        return listXRetour;
    }

    public ArrayList<Integer> getListYRetour() {
        return listYRetour;
    }


}
