package V2.javaClass;

import V2.javaClass.Graphe;

import java.util.*;

public class Aretes {

    protected int[][] tab;
    protected Boolean[][] estObstacle;
    protected int x;
    protected int y;
    protected ArrayList<Integer> listX = new ArrayList<>();
    protected ArrayList<Integer> listY = new ArrayList<>();
    protected Graphe graphe;
    private Boolean passeDroitVoisin = false;
    private String noeudVoisin;

    public Aretes(Graphe g, int x, int y)
    {
        this.tab = g.getTaille();
        this.estObstacle = g.getEstObstacle();
        this.x = x;
        this.y = y;
        this.graphe = g;
        rechercheAretes();
    }

    public void rechercheAretes()
    {
        int xTab = 0;
        int yTab = 0;

        for (int i = 0; i < tab.length; ++i)
        {
            for(int j = 0; j < tab[i].length; ++j)
            {
                if(tab[i][j] == tab[this.x][this.y]) // On va mettre dans 2 variables les coordonnées de X et Y
                {
                    xTab = i;
                    yTab = j;
                }
            }
        }
        for(int x=xTab-1; x<=xTab+1; x++) // On parcous la ligne cellule-1 jusqu'a la cellule+1
            for(int y=yTab-1; y<=yTab+1; y++) // On parcous la colonne cellule-1 jusqu'a la cellule+1
                if(x == xTab || y == yTab) // Ce qui fait un +, on cherche pas les cellules adjacentes sur la diagonales
                {
                    if(verficationNoeud(x, y)) // On vérfie qu'il n'est pas OutOfBands, qu'il est hors du graphe
                        // On vérifie que la case n'est pas un obstacle
                        if((tab[x][y] != tab[xTab][yTab]) && ((!estObstacle[x][y]) || passeDroitVoisin)) // On bloque la possibilité de se déplacer a la cellule de départ
                            ajouterAretes(x, y);
                }


        if(listX.isEmpty() && listY.isEmpty())
        {
            listX.add(xTab);
            listY.add(yTab);
        }
    }

    // Verifier qu'on ne depasse pas le tableau
    private boolean verficationNoeud(int xTab, int yTab) {
        if(xTab<0 || xTab>=tab.length)
            return false;
        return !(yTab<0 || yTab >= tab[0].length); // Si (false) = renvoie true et si (true) = renvoie false
        // tab[0] = car on regarde les colonnes, non pas les lignes
    }

    // On ajoute les deux coordonnées dans deux listes différentes
    private void ajouterAretes(int xTab, int yTab) {
        listX.add(xTab); // On ajoute la coordonnée de la ligne dans la 1er liste
        listY.add(yTab); // On ajoute la coordonnée de la colonne dans la 2e liste
    }

    public String noeudVoisin(Object o)
    {
        if(o instanceof Ouvrier)
        {
            Ouvrier ouvrier = (Ouvrier)o;
            passeDroitVoisin = true;
            int numeroOuvrier = ouvrier.getNumeroWorker();
            noeudVoisin = "\t --> Ouvrier n°" + numeroOuvrier + " | ";
//            rechercheAretes();
            int nbrNoeudVoisin = 1;
            for(int i=0; i<listX.size(); i++)
            {
                for(int j=0; j<listY.size(); j++)
                {
                    int numNoeud = graphe.rechercherNoeud(listX.get(i),listY.get(j)).getCoordonneNoeud();
                    noeudVoisin += " Voisin n°" + nbrNoeudVoisin + " se trouve au Noeud n°" + numNoeud + " ";
                    if(estObstacle[listX.get(i)][listY.get(j)])
                    {
                        noeudVoisin += " est un obstacle.";
                    } else{
                        noeudVoisin += " est pas un obstacle - ";
                    }
                    if(graphe.getEstNourriture()[listX.get(i)][listY.get(j)])
                    {
                        noeudVoisin += " contient : " + graphe.getQuantityFood()[listX.get(i)][listY.get(j)] + "kg de nourriture.";
                    } else {
                        noeudVoisin += " n'a pas de nourriture - ";
                    }
                    if(graphe.getQuantityPheromone()[listX.get(i)][listY.get(j)] >= 1)
                    {
                        noeudVoisin += " contient : " + graphe.getQuantityPheromone()[listX.get(i)][listY.get(j)]+ " phéromones.";
                    } else{
                        noeudVoisin += " et n'a pas de phéromones.";
                    }
                    noeudVoisin += " |\n\t|                      ";
                    i++;
                    nbrNoeudVoisin++;
                }
            }
        }
        if(o instanceof Soldat)
        {

        }
        return noeudVoisin;
    }

    public ArrayList<Integer> getListX() {
        return listX;
    }

    public ArrayList<Integer> getListY() {
        return listY;
    }
}
