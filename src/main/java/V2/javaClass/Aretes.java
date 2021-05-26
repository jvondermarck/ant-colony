package V2.javaClass;

import java.util.*;
/**
 * La classe Aretes qui s'occupe de cherche les aretes adjacentes pour changer de noeud.
 */
public class Aretes {

    protected int[][] tab;
    protected Boolean[][] estObstacle;
    protected int x;
    protected int y;
    protected ArrayList<Integer> listX = new ArrayList<>();
    protected ArrayList<Integer> listY = new ArrayList<>();
    protected Graphe graphe;
    protected static Random rand = new Random();

    /**
     * Instancie la classe Aretes. Elle permet de faire changer de noeud une fourmi.
     *
     * @param g le Graphe qui permet de prendre le tableau 2D du Graphe avec ses cellules
     * @param x la coordonnée X (ligne) où se trouve la fourmis à déplacer
     * @param y la coordonnée Y (colonne) où se trouve la fourmis à déplacer
     */
    public Aretes(Graphe g, int x, int y)
    {
        this.tab = g.getTaille();
        this.estObstacle = g.getEstObstacle();
        this.x = x;
        this.y = y;
        this.graphe = g;
        rechercheAretes();
    }

    /**
     * Recherche aretes adjacentes en effuctant des tests de validités.
     */
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
                        if((tab[x][y] != tab[xTab][yTab]) && !estObstacle[x][y]) // On bloque la possibilité de se déplacer a la cellule de départ
                            ajouterAretes(x, y);
                }


        if(listX.isEmpty() && listY.isEmpty())
        {
            listX.add(xTab);
            listY.add(yTab);
        }
    }

    /**
     * verficationNoeud vérifie que le noeud est bien sur le graphe, qu'on ne le dépasse pas
     * @param xTab la coordonnée X de la ligne a vérifier
     * @param yTab la coordonnée Y de la ligne a vérifier
     * @return boolean retourne true si le noeud ne dépasse pas le graphe et qu'il est conforme
     */

    private boolean verficationNoeud(int xTab, int yTab) {
        if(xTab<0 || xTab>=tab.length)
            return false;
        return !(yTab<0 || yTab >= tab[0].length); // Si (false) = renvoie true et si (true) = renvoie false
        // tab[0] = car on regarde les colonnes, non pas les lignes
    }

    /**
     * ajouterAretes ajoute la cellule qui a validé tous les tests dans les deux listes, on ajoute les deux coordonnées dans deux listes différentes
     * @param xTab la coordonnée X (ligne) à ajouter dans la listeX
     * @param yTab la coordonnée Y (colonne) à ajouter dans la listeY
     */

    private void ajouterAretes(int xTab, int yTab) {
        listX.add(xTab); // On ajoute la coordonnée de la ligne dans la 1er liste
        listY.add(yTab); // On ajoute la coordonnée de la colonne dans la 2e liste
    }

    /**
     * Cette méthode noeudVoisin() permet de prendre tous les noeuds adjacents de la fourmis
     * et de regarder si ce noeud est un obstacle, si il y a de la nourriture, si ya des phéromones, etc.
     * @param o : de type Object, possibilité de choisir soit un soldat ou un ouvrier
     * @return un type String contenant ligne par ligne chaque noeud voisin de la fourmis en question
     */

    public String noeudVoisin(Object o)
    {
        StringBuilder noeudVoisin = new StringBuilder();

        if(o instanceof Ouvrier)
        {
            Ouvrier ouvrier = (Ouvrier)o;
            int numeroOuvrier = ouvrier.getNumeroWorker();
            noeudVoisin = new StringBuilder("\t --> Ouvrier n°" + numeroOuvrier + " | ");
        }
        if(o instanceof Soldat)
        {
            Soldat soldat = (Soldat)o;
            int numeroSoldat = soldat.getNumeroSoldat();
            noeudVoisin = new StringBuilder("\t --> Soldat n°" + numeroSoldat + "  | ");
        }

        int nbrNoeudVoisin = 1;
        for(int i=0; i<listX.size(); i++)
        {
            for(int j=0; j<listY.size(); j++)
            {
                int numNoeud = graphe.rechercherNoeud(listX.get(i),listY.get(j)).getCoordonneNoeud();
                noeudVoisin.append(" Voisin n°").append(nbrNoeudVoisin).append(" se trouve au Noeud n°").append(numNoeud).append(" ");
                if(estObstacle[listX.get(i)][listY.get(j)])
                {
                    noeudVoisin.append("- est un obstacle - ");
                } else{
                    noeudVoisin.append("- est pas un obstacle - ");
                }
                if(graphe.getEstNourriture()[listX.get(i)][listY.get(j)])
                {
                    noeudVoisin.append(" contient : ").append(graphe.getQuantityFood()[listX.get(i)][listY.get(j)]).append("kg de nourriture - ");
                } else {
                    noeudVoisin.append(" n'a pas de nourriture - ");
                }
                if(graphe.getQuantityPheromone()[listX.get(i)][listY.get(j)] >= 1)
                {
                    noeudVoisin.append(" contient : ").append(graphe.getQuantityPheromone()[listX.get(i)][listY.get(j)]).append(" phéromones.");
                } else{
                    noeudVoisin.append(" et n'a pas de phéromones");
                }
                noeudVoisin.append("\n\t|                      ");
                i++;
                nbrNoeudVoisin++;
            }
        }
        noeudVoisin = new StringBuilder(noeudVoisin.substring(0, noeudVoisin.lastIndexOf("\n\t|                      ")));
        noeudVoisin.append("\n\t| -------------------------------------------------------------------------------------------------------------------------------------------------|");

        return noeudVoisin.toString();
    }

    /**
     * Recupère la listeX qui contient les coordonnées des lignes de toutes les cellules adjacentes.
     *
     * @return the list x
     */

    public ArrayList<Integer> getListX() {
        return listX;
    }

    /**
     * Recupère la listeX qui contient les coordonnées des colonnes de toutes les cellules adjacentes.
     *
     * @return the list y
     */
    public ArrayList<Integer> getListY() {
        return listY;
    }
}
