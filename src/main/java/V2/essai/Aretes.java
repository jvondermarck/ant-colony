//package V2.essai;
//import V1.javaClass.Graphe;
//import V1.javaClass.Noeud;
//
//import java.util.*;
//
///**
// * La classe Aretes qui s'occupe de cherche les aretes adjacentes pour changer de noeud.
// */
//public class Aretes {
//
//    /**
//     * Qui permet de choisir aléatoirement un noeud dans une liste de Noeuds adjacents à la fourmi.
//     */
//    private static final Random random = new Random();
//
//    /**
//     * Recherche aretes adjacentes en effuctant des tests de validités.
//     *
//     * @param g         le Graphe où se trouve la fourmis
//     * @param xPosition la position X où se trouve actuellement la fourmis
//     * @param yPosition la position Y où se trouve actuellement la fourmis
//     * @return le nouveau Noeud où la fourmis se déplacera
//     */
//    public static Noeud rechercheAretes(Graphe g, int xPosition, int yPosition)
//    {
//        ArrayList<Noeud> listNoeud = new ArrayList<>(); // On crée une list de Noeud adjacents susceptibles, où un Noeud sera tiré aléatoirement
//        Noeud positionActuel = g.rechercherNoeud(xPosition,yPosition); // Le Noeud où se trouve actuellement la fourmi.
//
//        for(int x=xPosition-1; x<=xPosition+1; x++) // On parcous la ligne cellule-1 jusqu'a la cellule+1
//            for(int y=yPosition-1; y<=yPosition+1; y++) // On parcous la colonne cellule-1 jusqu'a la cellule+1
//                if(x == xPosition || y == yPosition) // On ne cherche pas des cellules qui se trouve à la diagonale de la position de la fourmis
//                {
//                    if(verficationNoeud(x, y, g)) // On vérfie qu'il n'est pas OutOfBands, qu'il est hors du graphe
//                    {
//                        Noeud positionAdjacente = g.rechercherNoeud(x,y); // Le Noeud adjacent à la fourmis
//                        // On vérifie que la case n'est pas un obstacle ET on bloque la possibilité de se déplacer a la cellule de départ
//                        if((positionAdjacente != positionActuel) && !g.getEstObstacle()[x][y])
//                            listNoeud.add(positionAdjacente); // On cherche les coordonnés X et Y pour avoir le Noeud en question, et on l'ajoute dans la liste
//                    }
//                }
//
//        if(listNoeud.isEmpty()) // Si la liste est vide, on ajoute directement la coordonnée X et Y de la fourmi où elle se trouve actuellement.
//        {
//            return positionActuel;
//        } else // Si la liste est pas vide, on va tirer aléatoirement dans la liste de Noeud --> un noeud bien précis
//        {
//            int indexRand = random.nextInt(listNoeud.size()); // indexRand qui est l'index auquel on va prendre le nouveau noeud de la liste
//            return listNoeud.get(indexRand); // On retourne la nouvelle position du Nooeud à l'index(-->indexRand) de la liste de Noeuds adjacents
//        }
//    }
//
//    /**
//     * verficationNoeud vérifie que le noeud est bien sur le graphe, qu'on ne le dépasse pas
//     * Cette méthode est seulement utilisé par la méthode rechercheAretes() de la classe Aretes.
//     * @param xTab la coordonnée X de la ligne a vérifier
//     * @param yTab la coordonnée Y de la ligne a vérifier
//     * @return boolean retourne true si le noeud ne dépasse pas le graphe et que la coordonnée X et Y est bien sur le graphe
//     */
//
//    private static boolean verficationNoeud(int xTab, int yTab, Graphe g) {
//        if(xTab<0 || xTab>=g.getRow()) // Si la position de la fourmis est inférieur à 0 OU supérieur au nombre de ligne == impossible = renvoie false
//            return false;
//        return !(yTab<0 || yTab >= g.getColumn()); // Si la position de la fourmis est inférieur à 0 OU supérieur au nombre de colonne == impossible = renvoie false
//    }
//}
