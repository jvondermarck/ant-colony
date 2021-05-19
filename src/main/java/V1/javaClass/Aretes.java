package V1.javaClass;
import java.util.*;

/**
 * La classe Aretes qui s'occupe de cherche les aretes adjacentes pour changer de noeud.
 */
public class Aretes {

    private final int[][] tab;
    private final Boolean[][] estObstacle;
    private final int x;
    private final int y;
    private final ArrayList<Integer> listX = new ArrayList<>();
    private final ArrayList<Integer> listY = new ArrayList<>();

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
     * verficationNoeud vérifie que le noeud est bien sur le graphe
     * @param xTab la coordonnée X de la ligne a vérifier
     * @param yTab la coordonnée Y de la ligne a vérifier
     * @return boolean retourne true si le noeud ne dépasse pas le graphe et qu'il est conforme
     */

    // Verifier qu'on ne depasse pas le tableau
    private boolean verficationNoeud(int xTab, int yTab) {
        if(xTab<0 || xTab>=tab.length)
            return false;
        return !(yTab<0 || yTab >= tab[0].length); // Si (false) = renvoie true et si (true) = renvoie false
        // tab[0] = car on regarde les colonnes, non pas les lignes
    }

    /**
     * ajouterAretes ajoute la cellule qui a validé tous les tests dans les deux listes
     * @param xTab la coordonnée X (ligne) à ajouter dans la listeX
     * @param yTab la coordonnée Y (colonne) à ajouter dans la listeY
     */

    // On ajoute les deux coordonnées dans deux listes différentes
    private void ajouterAretes(int xTab, int yTab) {
        listX.add(xTab); // On ajoute la coordonnée de la ligne dans la 1er liste
        listY.add(yTab); // On ajoute la coordonnée de la colonne dans la 2e liste
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
