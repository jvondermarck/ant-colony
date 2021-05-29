package V2.javaClass;

import java.util.ArrayList;
import java.util.Random;

/**
 * La classe Abstraite FourmisMove qui recherche tous les noeuds adjacents à la fourmis en faisant gaffe aux obstacles
 */

public abstract class FourmisMove implements IFourmisMove {

    private final ArrayList<Noeud> listNoeud = new ArrayList<>(); // On crée une list de Noeud adjacents susceptibles, où un Noeud sera tiré aléatoirement
    private boolean record; // Record sert a mettre dans la liste de Noeud le noeud qui est un obstacle, il sert pour le fichier de rapport

    /**
     * Qui permet de choisir aléatoirement un noeud dans une liste de Noeuds adjacents à la fourmi.
     */
    private final Random random = new Random();

    /**
     * Recherche aretes adjacentes en effuctant des tests de validités.
     *
     * @param xPosition la position X où se trouve actuellement la fourmis
     * @param yPosition la position Y où se trouve actuellement la fourmis
     */
    public void rechercheAretes(int xPosition, int yPosition, Graphe g)
    {
        listNoeud.clear();
        Noeud positionActuel = g.rechercherNoeud(xPosition,yPosition); // Le Noeud où se trouve actuellement la fourmi.

        for(int x=xPosition-1; x<=xPosition+1; x++) // On parcous la ligne cellule-1 jusqu'a la cellule+1
            for(int y=yPosition-1; y<=yPosition+1; y++) // On parcous la colonne cellule-1 jusqu'a la cellule+1
                if(x == xPosition || y == yPosition) // On ne cherche pas des cellules qui se trouve à la diagonale de la position de la fourmis
                {
                    if(verficationNoeud(x, y, g)) // On vérfie qu'il n'est pas OutOfBands, qu'il est hors du graphe
                    {
                        Noeud positionAdjacente = g.rechercherNoeud(x,y); // Le Noeud adjacent à la fourmis
                        // On vérifie que la case n'est pas un obstacle ET on bloque la possibilité de se déplacer a la cellule de départ
                        if((positionAdjacente != positionActuel) && (!g.getEstObstacle()[x][y] || record))
                            listNoeud.add(positionAdjacente); // On cherche les coordonnés X et Y pour avoir le Noeud en question, et on l'ajoute dans la liste
                    }
                }

        if(listNoeud.isEmpty()) // Si la liste est vide, on ajoute directement la coordonnée X et Y de la fourmi où elle se trouve actuellement.
        {
            listNoeud.add(positionActuel);
        }
    }

    public Noeud randomNoeud()
    {   // Si la liste est pas vide, on va tirer aléatoirement dans la liste de Noeud --> un noeud bien précis
        int indexRand = random.nextInt(listNoeud.size()); // indexRand qui est l'index auquel on va prendre le nouveau noeud de la liste
        return listNoeud.get(indexRand); // On retourne la nouvelle position du Nooeud à l'index(-->indexRand) de la liste de Noeuds adjacents
    }

    /**
     * verficationNoeud vérifie que le noeud est bien sur le graphe, qu'on ne le dépasse pas
     * Cette méthode est seulement utilisé par la méthode rechercheAretes() de la classe Aretes.
     * @param xTab la coordonnée X de la ligne a vérifier
     * @param yTab la coordonnée Y de la ligne a vérifier
     * @return boolean retourne true si le noeud ne dépasse pas le graphe et que la coordonnée X et Y est bien sur le graphe
     */

    private boolean verficationNoeud(int xTab, int yTab, Graphe g) {
        if(xTab<0 || xTab>=g.getRow()) // Si la position de la fourmis est inférieur à 0 OU supérieur au nombre de ligne == impossible = renvoie false
            return false;
        return !(yTab<0 || yTab >= g.getColumn()); // Si la position de la fourmis est inférieur à 0 OU supérieur au nombre de colonne == impossible = renvoie false
    }

    /**
     * Méthode pour la classe AntFacadeHistorique qui permet de savoir ses voisins adjacents et leur état
     *
     * @return une chaine de charactère qui renvoie l'état de son voisin adjacent
     */
    public String noeudVoisin(Object o, Graphe g)
    {
        record = true;
        StringBuilder noeudVoisin = new StringBuilder();

        if(o instanceof Ouvrier)
        {
            Ouvrier ouv = (Ouvrier)o;
            rechercheAretes(ouv.getX(), ouv.getY(), g);
            noeudVoisin = new StringBuilder("\t --> Ouvrier n°" + ouv.getNumeroWorker() + " | ");
        }
        if(o instanceof Soldat)
        {
            Soldat s = (Soldat)o;
            rechercheAretes(s.getX(), s.getY(), g);
            noeudVoisin = new StringBuilder("\t --> Soldat n°" + s.getNumeroSoldat() + " |  ");
        }

        int nbrNoeudVoisin = 1;
        for(int i=0; i<listNoeud.size(); i++)
        {
            ArrayList<Integer> coord = g.rechercherCoord(listNoeud.get(i));
            int xCoord = coord.get(0);
            int yCoord = coord.get(1);

            noeudVoisin.append("Voisin n°").append(nbrNoeudVoisin).append(" se trouve au Noeud n°").append(listNoeud.get(i)).append(" ");

            if(g.getEstObstacle()[xCoord][yCoord])
            {
                noeudVoisin.append("- est un obstacle - ");
            } else{
                noeudVoisin.append("- est pas un obstacle - ");
            }
            if(g.getEstNourriture()[xCoord][yCoord])
            {
                noeudVoisin.append(" contient : ").append(g.getQuantityFood()[xCoord][yCoord]).append("kg de nourriture - ");
            } else {
                noeudVoisin.append(" n'a pas de nourriture - ");
            }
            if(g.getQuantityPheromone()[xCoord][yCoord] >= 1)
            {
                noeudVoisin.append(" contient : ").append(g.getQuantityPheromone()[xCoord][yCoord]).append(" phéromones.");
            } else{
                noeudVoisin.append(" et n'a pas de phéromones");
            }
            noeudVoisin.append("\n\t|                      ");
            nbrNoeudVoisin++;
        }
        noeudVoisin = new StringBuilder(noeudVoisin.substring(0, noeudVoisin.lastIndexOf("\n\t|                      ")));
        noeudVoisin.append("\n\t| -------------------------------------------------------------------------------------------------------------------------------------------------|");

        record = false;
        return noeudVoisin.toString();
    }

    public ArrayList<Noeud> getListNoeud() {
        return listNoeud;
    }
}