package javaClass;

import java.util.ArrayList;

public class Ouvrier extends FourmisMove {
    private final Colonie colonie;
    private final Graphe graphe;
    private static int nombreSoldat = 0;
    private final int numeroWorker;
    private Noeud positionActuel;
    private final int xColonie;
    private final int yColonie;

    private Boolean[][] aVisite; // True : si il a deja visité une cellule
    private final ArrayList<Integer> listXRetour = new ArrayList<>(); // Liste coordonné de x : servira pour le retour de la fourmis ouvriere
    private final ArrayList<Integer> listYRetour = new ArrayList<>(); // Liste coordonné de y :  servira pour le retour de la fourmis ouvriere

    private boolean etatRetour; // Savoir si elle est a l'aller ou retour
    private int quantityFoodTaken; // Quantité de nourriture prise par la fourmis

    public Ouvrier(int x, int y, Colonie colonie, Graphe graphe)
    {
        super(x,y);
        this.colonie = colonie;
        this.graphe = graphe;
        nombreSoldat = nombreSoldat+1;
        this.numeroWorker = nombreSoldat;
        this.quantityFoodTaken = 0;
        recherchePositionActuel(x, y);

        this.etatRetour = false;

        this.aVisite = new Boolean[graphe.getLongueur()][graphe.getLargueur()];
        for (int i = 0; i < graphe.getTaille().length; ++i)
        {
            for(int j = 0; j < graphe.getTaille()[i].length; ++j)
            {
                aVisite[i][j] = false; // On dit au début qu'il a rien visité
            }
        }

        xColonie = x;
        yColonie = y;
        aVisite[xColonie][yColonie] = true;
        listXRetour.add(xColonie);
        listYRetour.add(yColonie);

        //this.aVisite[2][3] = true;
        this.aVisite[3][2] = true;
        this.aVisite[3][3] = true;
        this.aVisite[3][4] = true;
        this.aVisite[4][3] = true;
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

    // Pour savoir si sa liste est vide ca veut dire qu'elle est a la fourmiliere, donc elle doit repartir chercher a manger
    public boolean doitRetravailler()
    {
        if(listXRetour.isEmpty() || listYRetour.isEmpty()) // Si liste est vide
        {
            etatRetour = false; // Elle va denouveau travailler et rechercher à manger
            this.colonie.setQuantityFood(quantityFoodTaken);
            for (int i = 0; i < graphe.getTaille().length; ++i)
            {
                for(int j = 0; j < graphe.getTaille()[i].length; ++j)
                {
                    aVisite[i][j] = false; // On dit au début qu'il a rien visité
                }
            }
            // Comme on recommence tout a zero, on re-initialise tout
            aVisite[xColonie][yColonie] = true;
            listXRetour.add(xColonie);
            listYRetour.add(yColonie);
            quantityFoodTaken = 0;
            System.out.println(colonie); // On affiche l'état de la colonie
        }
        return !etatRetour;
    }

    @Override
    public String toString() {
        return "Ouvrier n°" + numeroWorker + " | " + this.colonie.toString() + " | " + this.positionActuel + " | Transporte : " + quantityFoodTaken + " kg de nourriture | Retourne à la colonie : " + etatRetour;
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

    public boolean isEtatRetour() {
        return etatRetour;
    }

    public void setEtatRetour(boolean etatRetour) {
        this.etatRetour = etatRetour;
    }

    public void setQuantityFoodTaken(int quantityFoodTaken) {
        this.quantityFoodTaken = quantityFoodTaken;
    }
}
