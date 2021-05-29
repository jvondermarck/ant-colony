package V2.javaClass;

import java.util.ArrayList;
import java.util.Random;

/**
 * The type Move ouvrier qui s'occupe de faire déplacer la fourmi ouvriere avec des criteres bien spéciaux (phéromones et noeud deja visite)
 * Elle reprend les noeuds adjacents trouvés grace à la classe FourmisMove, et MoveOuvrier applique ses propres critères de déplacement pour la fourmi ouvrière
 */
public abstract class MoveOuvrier extends FourmisMove {

    private int x; // La coordonné X (ligne)
    private int y; // La coordonné Y (colonne)
    private final boolean[][] estObstacle; //Tableau boolean 2D pour savoir si une cellule est un obstacle
    private final Graphe g; // Le graphe g.
    private ArrayList<Noeud> listNoeud = new ArrayList<>(); // On crée une list de Noeud adjacents susceptibles, où un Noeud sera tiré aléatoirement
    private final ArrayList<Noeud> listNoeudRetour = new ArrayList<>(); // La liste de noeuds que la fourmi devra utiliser pour retrouver son chemin
    private final boolean[][] aVisite; // True : si il a deja visité une cellule
    private boolean etatRetour; // Savoir si elle est a l'aller ou retour
    private int quantityFoodTaken; // Quantité de nourriture prise par la fourmis
    private final Colonie colonie; // La colonie
    private Noeud positionActuel; // Le noeud où se trouve la fourmis
    private final Random random = new Random(); //Qui permet de choisir aléatoirement un noeud dans une liste de Noeuds adjacents à la fourmi.
    private boolean record; // Record sert a mettre dans rechercheAretes() le noeud qui est un obstacle, on l'ajoutera exeptionnelement dans la liste de Noeud pour le fichier en .CSV

    /**
     * Instantiates a new Move ouvrier.
     *
     * @param x       the x
     * @param y       the y
     * @param colonie the colonie
     * @param g       the graphe
     */
    public MoveOuvrier(int x, int y, Colonie colonie, Graphe g) {
        setX(x);
        setY(y);
        this.colonie = colonie;
        this.g = g;
        this.estObstacle = g.getEstObstacle();
        this.aVisite = new boolean[g.getRow()][g.getColumn()];
    }

    public void randomDirection()
    {
        // On cherche le nouveau Noeud de la fourmis
        rechercheAretes(this.x, this.y, g); // On recherche toutes les cellules adjacentes à la fourmi.
        this.listNoeud = getListNoeud(); // On récupère la liste de Noeud adjacents trouvés dans la classe FourmisMove
        this.positionActuel = paramOuvrier(); // On cherche le ou les cellules adjacentes en fonction des cellules deja visites et des pheromones

        // Grace au noeud trouvé, on cherche sa coordonnée X et Y, qui permettra de chercher son prochain Noeud, etc...
        ArrayList<Integer> coord = g.rechercherCoord(this.positionActuel);
        setX(coord.get(0)); // La coordonnée X se situe à l'indice 0 de la liste renvoyé
        setY(coord.get(1)); // La coordonnée Y se situe à l'indice 1 de la liste renvoyé

        aVisite[this.x][this.y] = true;
        if (g.getEstNourriture()[this.x][this.y]) // Si au nouvelle emplacement de la fourmis il y a de la nourriture
        {
            etatRetour = true; // Elle va devoir retourner sur la fourmilière ramener la nourriture
            quantityFoodTaken = g.gestionNourriture(this.x, this.y, this.colonie); // Cette méthode va renvoyer la quantité de nourriture de la fourmi qu'elle va transporter.
        } else {
            listNoeudRetour.add(positionActuel); // Si l'ouvrier est pas sur la cellule où se trouve de la nourriture, on ajoute ses coordonnés dans la liste pour le chemin retour
        }
    }

    /**
     * Chemin retour qui fait le chemin inverse que la fourmi a emprunté au cours de sa recherche de nourriture.
     * Cette méthode est utilisé quand la fourmi a trouvé de la nourriture, et qu'elle doit retrouver les emplacements des Noeuds qu'elle aura parcouru
     */
    public void cheminRetour() {
        ArrayList<Integer> coord = g.rechercherCoord(listNoeudRetour.get(listNoeudRetour.size() - 1)); // Vu qu'on retourne en arrière, on prend le Noeud antérieur
        int coordListXRetour = coord.get(0);
        int coordListYRetour = coord.get(1);

        // On vérifie que la future coordonnée de retour n'est pas un obstacle
        if (etatRetour && !this.estObstacle[coordListXRetour][coordListYRetour]) // Si il retourne bien à la colonie
        {
            this.x = coordListXRetour; // on prend sa derniere coordonnée enrengistré
            this.y = coordListYRetour;
            this.positionActuel = listNoeudRetour.get(listNoeudRetour.size() - 1); // On enrengistre le Noeud où elle se trouve

            // On va supprimer le dernier élement de la liste de Noeud
            listNoeudRetour.remove(listNoeudRetour.size() - 1);
        }
    }

    /**
     * Methode qui sert uniquement a la fourmis Ouvriere en fonction des noeuds visités, et des phéromones
     *
     * @return le Noeud où elle va se déplacer
     */
    public Noeud paramOuvrier()
    {
        boolean droitDePasssage = rechercheToutVisite(); // si true (à déja visités toutes les cellules adjacentes), on l'autorise a se deplacer aléatoirement à nouveau

        // Si rechercheAucunPheromone = false = ca veut dire que ya au moins une cellule qui contient au minimum 1 pheromone, donc on execute cette condition
        if (!rechercheAucunPheromone(droitDePasssage))
        {
            rechercheBestPheromone(droitDePasssage); // On recherche le Noeud en question qui contient le meilleure taux de phéromones
        }
        else { // Si il n'y a aucun phéromone ou que c'est que des cellules déja visités :
            for (int i = 0; i < listNoeud.size(); i++)
            {
                ArrayList<Integer> coord = g.rechercherCoord(listNoeud.get(i));
                int xCoord = coord.get(0);
                int yCoord = coord.get(1);
                if (aVisite[xCoord][yCoord] && !droitDePasssage) // Si on a visité la cellule et Si droitPassage = true --> on veut pas enlever tous les noeuds avec list.Remove, vu qu'il a tout visite et qu'il a le droit de se déplacer dans tte les cellules adjacentes
                {
                    listNoeud.remove(i); // On supprime la coordonnée de X du tableau pour pas que la fourmis se deplace sur cette cellule
                    i = 0; // On recomence la boucle vu qu'on supprime un élement de la liste avec la variable i
                }
            }
        }

        if(listNoeud.isEmpty()) // Si la liste est vide, on ajoute directement la coordonnée X et Y de la fourmi où elle se trouve actuellement.
        {
            return this.positionActuel;
        } else // Si la liste est pas vide, on va tirer aléatoirement dans la liste de Noeud --> un noeud bien précis
        {
            int indexRand = random.nextInt(listNoeud.size()); // indexRand qui est l'index auquel on va prendre le nouveau noeud de la liste
            return listNoeud.get(indexRand); // On retourne la nouvelle position du Nooeud à l'index(-->indexRand) de la liste de Noeuds adjacents
        }
    }

    /**
     * Sur les cellules adjacentes de la fourmis, on cherche la cellule qui a le + de pheromone
     */
    private void rechercheBestPheromone(boolean droitDePasssage)
    {
        Noeud bestPheromone = null; // Le noeud qui va contenir le + de phéromone
        int bestQuantity = 0; // Cette variable servira pour savoir si un noeud à une + grande quantité de phéromone qu'un autre
        int[][] quantityPheromone = g.getQuantityPheromone();

        for(int i=0; i<listNoeud.size(); i++)
        {
            ArrayList<Integer> coord = g.rechercherCoord(listNoeud.get(i));
            int xCoord = coord.get(0);
            int yCoord = coord.get(1);
            if(quantityPheromone[xCoord][yCoord] > bestQuantity && (!aVisite[xCoord][yCoord] || droitDePasssage))
            {
                bestPheromone = listNoeud.get(i);
                bestQuantity = quantityPheromone[xCoord][yCoord];
            }
        }

        listNoeud.clear(); // On supprime toute la liste de noeud, pour mettre seulement le meilleure Noeud
        listNoeud.add(bestPheromone); // On ajoute seulement le noeud qui a le plus de phéromones.
    }

    /**
     * Si les cellules adjacentes ne contiennent pas de pheromone, la fourmi devra se déplacer de facon aléatoire et non pas chercher des phéromones
     */
    private boolean rechercheAucunPheromone(boolean droitDePassage)
    {
        int quantityNull = 0;
        int nbFois = 0;
        int [][] quantityPheromone = g.getQuantityPheromone();

        for(int i=0; i<listNoeud.size(); i++)
        {
            ArrayList<Integer> coord = g.rechercherCoord(listNoeud.get(i));
            if(!aVisite[coord.get(0)][coord.get(1)] || droitDePassage) // Si il n'a pas visité le noeud OU qu'il a un droit de passage, alors on peut regarder combien il y a de pheromones ou non
            {
                if(quantityPheromone[coord.get(0)][coord.get(1)] == quantityNull) // Si la cellule a une quantité de nourriture = 0, on incremente de 1 le nb de fois où ya pas a manger dans les cellules adjacentes
                {
                    nbFois++;
                }
            } else {
                nbFois++; // Si la cellule a deja etait visité, on incremente quand meme de 1, car on dit que comme on peut pas la visiter, ya plus de pheromone
            }
        }

        return listNoeud.size() == nbFois;
        // return true si on a toutes les cellules de X,Y adjacentes a la fourmis qui contient 0 en pheromone
        // si 4 cellules adjacentes sans phéromones = true, sinon false
    }

    /**
     * Recherche si la fourmis soldat a deja visite tous les noeuds adjacents
     */
    private boolean rechercheToutVisite()
    {
        int nbFois = 0;

        for(int i=0; i<listNoeud.size(); i++)
        {
            ArrayList<Integer> coord = g.rechercherCoord(listNoeud.get(i));
            if(this.aVisite[coord.get(0)][coord.get(1)]) // Si il a deja visité la cellule, alors on incremente de 1
            {
                nbFois++;
            }
        }
        return listNoeud.size() == nbFois;
        // return true si on a toutes les cellules de X,Y adjacentes a la fourmis qui on deja etait visité
        // si on a 4 cellules adjacentes, et que les 4 ont deja étaient visités, on renvoie true
    }

    /**
     * Is etat retour boolean.
     *
     * @return the boolean
     */
    public boolean isEtatRetour() {
        return etatRetour;
    }

    /**
     * Gets list noeud retour.
     *
     * @return the list noeud retour
     */
    public ArrayList<Noeud> getListNoeudRetour() {
        return listNoeudRetour;
    }

    /**
     * Gets quantity food taken.
     *
     * @return the quantity food taken
     */
    public int getQuantityFoodTaken() {
        return quantityFoodTaken;
    }

    /**
     * Gets position actuel.
     *
     * @return the position actuel
     */
    public Noeud getPositionActuel() {
        return positionActuel;
    }

    /**
     * Sets list noeud retour.
     *
     * @param listNoeudRetour the list noeud retour
     */
    public void setListNoeudRetour(Noeud listNoeudRetour) {
        this.listNoeudRetour.add(positionActuel);
    }

    /**
     * Sets visite.
     *
     * @param x    the x
     * @param y    the y
     * @param bool the bool
     */
    public void setaVisite(int x, int y, boolean bool) {
        this.aVisite[x][y] = bool;
    }

    /**
     * Sets etat retour.
     *
     * @param etatRetour the etat retour
     */
    public void setEtatRetour(boolean etatRetour) {
        this.etatRetour = etatRetour;
    }

    /**
     * Sets quantity food taken.
     *
     * @param quantityFoodTaken the quantity food taken
     */
    public void setQuantityFoodTaken(int quantityFoodTaken) {
        this.quantityFoodTaken = quantityFoodTaken;
    }

    /**
     * Sets position actuel.
     *
     * @param positionActuel the position actuel
     */
    public void setPositionActuel(Noeud positionActuel) {
        this.positionActuel = positionActuel;
    }

    /**
     * Recevoir la coordonnée X (ligne).
     *
     * @return the x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Recevoir la coordonnée Y (colonne).
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
