package V1.javaClass;
import java.util.*;

/**
 * La classe Aretes qui s'occupe de cherche les aretes adjacentes pour changer de noeud.
 */
public class Aretes {

    /**
     * Qui permet de choisir aléatoirement un noeud
     */
    static Random random = new Random();

    /**
     * Recherche aretes adjacentes en effuctant des tests de validités.
     *
     * @param g         le Graphe où se trouve la fourmis
     * @param xPosition la position X où se trouve la fourmis
     * @param yPosition la position Y où se trouve la fourmis
     * @return le nouveau noeud où la fourmis se déplacera
     */
    public static Noeud rechercheAretes(Graphe g, int xPosition, int yPosition)
    {
        ArrayList<Noeud> listNoeud = new ArrayList<>(); // On crée une list de Noeud adjacents susceptibles, où un Noeud sera tiré aléatoirement

        for(int x=xPosition-1; x<=xPosition+1; x++) // On parcous la ligne cellule-1 jusqu'a la cellule+1
            for(int y=yPosition-1; y<=yPosition+1; y++) // On parcous la colonne cellule-1 jusqu'a la cellule+1
                if(x == xPosition || y == yPosition) // On ne cherche pas des cellules qui se trouve à la diagonale de la position de la fourmis
                {
                    if(verficationNoeud(x, y, g.getRow(), g.getColumn())) // On vérfie qu'il n'est pas OutOfBands, qu'il est hors du graphe
                    {
                        // On vérifie que la case n'est pas un obstacle ET On bloque la possibilité de se déplacer a la cellule de départ
                        if((g.rechercherNoeud(x,y) != g.rechercherNoeud(xPosition,yPosition)) && !g.getEstObstacle()[x][y])
                            listNoeud.add(g.rechercherNoeud(x,y)); // On cherche les coordonnés X et Y pour avoir le noeud en question, et on l'ajoute dans la liste
                    }

                }

        if(listNoeud.isEmpty()) // Si la liste est vide, on ajoute directement la coordonnée X et Y de la fourmi où elle se trouve actuellement.
        {
            return g.rechercherNoeud(xPosition,yPosition);
        } else // Si la liste est pas vide, on va tirer aléatoirement dans la liste de Noeud, un noeud au pif
        {
            int indexRand = random.nextInt(listNoeud.size()); // indexRand qui est l'index auquel on va prendre le nouveau noeud de la liste
            return listNoeud.get(indexRand); // On retourne le noeud à l'index(indexRand) de la liste de Noeuds adjacents
        }
    }

    /**
     * verficationNoeud vérifie que le noeud est bien sur le graphe, qu'on ne le dépasse pas
     * @param xTab la coordonnée X de la ligne a vérifier
     * @param yTab la coordonnée Y de la ligne a vérifier
     * @return boolean retourne true si le noeud ne dépasse pas le graphe et qu'il est conforme
     */

    private static boolean verficationNoeud(int xTab, int yTab, int row, int column) {
        if(xTab<0 || xTab>=row)
            return false;
        return !(yTab<0 || yTab >= column); // Si (false) = renvoie true et si (true) = renvoie false
    }
}
