package V2.javaClass;

import java.util.ArrayList;
import java.util.Random;

public abstract class MoveOuvrier implements FourmisMove {

    protected int x;
    protected int y;
    protected boolean[][] estObstacle;
    protected Graphe g;
    protected ArrayList<Noeud> listNoeud = new ArrayList<>(); // On crée une list de Noeud adjacents susceptibles, où un Noeud sera tiré aléatoirement
    protected ArrayList<Noeud> listNoeudRetour = new ArrayList<>();
    protected boolean[][] aVisite; // True : si il a deja visité une cellule
    protected boolean etatRetour; // Savoir si elle est a l'aller ou retour
    protected int quantityFoodTaken; // Quantité de nourriture prise par la fourmis
    private final Colonie colonie;
    protected int numeroWorker;
    private boolean record; // Record sert a mettre dans la liste de Noeud le noeud qui est un obstacle, il sert pour le fichier de rapport


    /**
     * Le noeud où se trouve la fourmis
     */
    protected Noeud positionActuel;

    /**
     * Qui permet de choisir aléatoirement un noeud dans une liste de Noeuds adjacents à la fourmi.
     */
    private static final Random random = new Random();

    public MoveOuvrier(int x, int y, Colonie colonie) {
        setX(x);
        setY(y);
        this.colonie = colonie;
    }

    @Override
    public void randomDirection(Graphe g) {
        this.g = g;
        this.estObstacle = g.getEstObstacle();

        // On cherche le nouveau Noeud de la fourmis
        rechercheAretes(g, this.x, this.y);
        this.positionActuel = paramOuvrier(); // On cherche le ou les cellules adjacentes en fonction des cellules deja visite et des pheromones

        // Grace au noeud trouvé, on cherche sa coordonnée X et Y, qui permettra de chercher son prochain Noeud, etc...
        ArrayList<Integer> coord = g.rechercherCoord(this.positionActuel);
        setX(coord.get(0)); // La coordonnée X se situe à l'indice 0 de la liste renvoyé
        setY(coord.get(1)); // La coordonnée Y se situe à l'indice 1 de la liste renvoyé

        aVisite[this.x][this.y] = true;
        if (g.getEstNourriture()[this.x][this.y]) // Si au nouvelle emplacement de la fourmis il y a de la nourriture
        {
            etatRetour = true; // Elle va devoir retourner sur la fourmilière ramener la nourriture

            if (g.getQuantityFood()[this.x][this.y] >= this.colonie.getFoodParam()) // Si ya + a manger que la fourmis va prendre
            {
                quantityFoodTaken = this.colonie.getFoodParam(); // On prend que la quantite de nourriture qu'on lui a indiqué
                g.getQuantityFood()[this.x][this.y] -= this.colonie.getFoodParam();
            } else {
                quantityFoodTaken = g.getQuantityFood()[this.x][this.y]; // On prend la nourriture qu'il reste, donc sa sera inferieur a ce qu'elle doit prendre de base
                g.getQuantityFood()[this.x][this.y] = 0; // On met a 0 le nbr de nourriture vu que la fourmis a tout pris
                //g.getEstNourriture()[this.x][this.y] = false; // Cette cellule de nourriture est plus disponible
                boolean[][] estNourriture = g.getEstNourriture();
                estNourriture[this.x][this.y] = false;
                g.setEstNourriture(estNourriture); // On dit que cette case n'est plus de la nourriture
            }
            System.out.println("Nourriture trouvé ! (" + this.x + "," + this.y + ")");
        } else {
            listNoeudRetour.add(positionActuel); // Si ouvrier est pas sur la cellule où se trouve de la nourriture, on ajoute ses coordonnés dans la liste
        }
    }

    public void cheminRetour() {
        ArrayList<Integer> coord = g.rechercherCoord(listNoeudRetour.get(listNoeudRetour.size() - 1));
        int coordListXRetour = coord.get(0);
        int coordListYRetour = coord.get(1);

        // On vérifie que la future coordonnée de retour n'est pas un obstacle
        if (etatRetour && !this.estObstacle[coordListXRetour][coordListYRetour]) // Si il retourne bien à la base
        {
            this.x = coordListXRetour; // on prend sa derniere coordonnée enrengistré
            this.y = coordListYRetour;

            // On va supprimer le dernier élement de la liste de Noeud
            listNoeudRetour.remove(listNoeudRetour.size() - 1);
        }
    }

    /**
     * Recherche aretes adjacentes en effuctant des tests de validités.
     *
     * @param g         le Graphe où se trouve la fourmis
     * @param xPosition la position X où se trouve actuellement la fourmis
     * @param yPosition la position Y où se trouve actuellement la fourmis
     */
    private void rechercheAretes(Graphe g, int xPosition, int yPosition) {
        listNoeud.clear();
        Noeud positionActuel = g.rechercherNoeud(xPosition, yPosition); // Le Noeud où se trouve actuellement la fourmi.

        for (int x = xPosition - 1; x <= xPosition + 1; x++) // On parcous la ligne cellule-1 jusqu'a la cellule+1
            for (int y = yPosition - 1; y <= yPosition + 1; y++) // On parcous la colonne cellule-1 jusqu'a la cellule+1
                if (x == xPosition || y == yPosition) // On ne cherche pas des cellules qui se trouve à la diagonale de la position de la fourmis
                {
                    if (verficationNoeud(x, y, g)) // On vérfie qu'il n'est pas OutOfBands, qu'il est hors du graphe
                    {
                        Noeud positionAdjacente = g.rechercherNoeud(x, y); // Le Noeud adjacent à la fourmis
                        // On vérifie que la case n'est pas un obstacle ET on bloque la possibilité de se déplacer a la cellule de départ
                        if((positionAdjacente != positionActuel) && (!g.getEstObstacle()[x][y] || record))
                            listNoeud.add(positionAdjacente); // On cherche les coordonnés X et Y pour avoir le Noeud en question, et on l'ajoute dans la liste
                    }
                }

        if (listNoeud.isEmpty()) // Si la liste est vide, on ajoute directement la coordonnée X et Y de la fourmi où elle se trouve actuellement.
        {
            listNoeud.add(positionActuel);
        }
    }

    /**
     * verficationNoeud vérifie que le noeud est bien sur le graphe, qu'on ne le dépasse pas
     * Cette méthode est seulement utilisé par la méthode rechercheAretes() de la classe Aretes.
     *
     * @param xTab la coordonnée X de la ligne a vérifier
     * @param yTab la coordonnée Y de la ligne a vérifier
     * @return boolean retourne true si le noeud ne dépasse pas le graphe et que la coordonnée X et Y est bien sur le graphe
     */

    private static boolean verficationNoeud(int xTab, int yTab, Graphe g) {
        if (xTab < 0 || xTab >= g.getRow()) // Si la position de la fourmis est inférieur à 0 OU supérieur au nombre de ligne == impossible = renvoie false
            return false;
        return !(yTab < 0 || yTab >= g.getColumn()); // Si la position de la fourmis est inférieur à 0 OU supérieur au nombre de colonne == impossible = renvoie false
    }

    // Methode qui sert uniquement a la fourmis Ouvriere en fonction des noeuds visités, et des phéromones
    public Noeud paramOuvrier() {
        boolean droitDePasssage = rechercheToutVisite(); // si true (tout visités), on autorise a se deplacer aléatoirement

        // Si rechercheAucunPheromone = false = ca veut dire que ya au moins une cellule qui contient au min 1 pheromone, donc on execute cette condition
        if (!rechercheAucunPheromone(droitDePasssage)) {
            for (int i = 0; i < listNoeud.size(); i++)
            {
                ArrayList<Integer> coord = g.rechercherCoord(listNoeud.get(i));
                int xCoord = coord.get(0);
                int yCoord = coord.get(1);

                if (!aVisite[xCoord][yCoord] || droitDePasssage) // Si il a pas visité cette cellule ou qu'il a deja tout visité
                {
                    if (rechercheBestPheromone(xCoord, yCoord)) // Si la cellule a la meilleure quantite de pheromone
                    {
                        listNoeud.clear();
                        listNoeud.add(g.rechercherNoeud(xCoord, yCoord));
                    }
                }
            }
        } else { // Si il n'y a aucun phéromone ou que c'est que des cellules déja visités :
            if (!droitDePasssage) // Si on met pas cette condition et que droitPassage = true, on va supprimer toutes les coord adjacentes vu qu'on a tout visité
            {
                for (int i = 0; i < listNoeud.size(); i++) {
                    ArrayList<Integer> coord = g.rechercherCoord(listNoeud.get(i));
                    int xCoord = coord.get(0);
                    int yCoord = coord.get(1);
                    if (aVisite[xCoord][yCoord]) // Si on a visité la cellule
                    {
                        listNoeud.remove(i); // On supprime la coordonnée de X du tableau pour pas que la fourmis se deplace sur cette cellule
                        i = 0;
                    }
                }
            }
        }

        if(listNoeud.isEmpty()) // Si la liste est vide, on ajoute directement la coordonnée X et Y de la fourmi où elle se trouve actuellement.
        {
            return positionActuel;
        } else // Si la liste est pas vide, on va tirer aléatoirement dans la liste de Noeud --> un noeud bien précis
        {
            int indexRand = random.nextInt(listNoeud.size()); // indexRand qui est l'index auquel on va prendre le nouveau noeud de la liste
            return listNoeud.get(indexRand); // On retourne la nouvelle position du Nooeud à l'index(-->indexRand) de la liste de Noeuds adjacents
        }
    }

    // Sur les cellules adjacentes de la fourmis, on cherche la cellule qui a le + de pheromone
    public boolean rechercheBestPheromone(int x, int y)
    {
        int xBest = 0;
        int yBest = 0;
        int bestQuantity = 0;
        int [][] quantityPheromone = g.getQuantityPheromone();

        for(int i=0; i<listNoeud.size(); i++)
        {
            ArrayList<Integer> coord = g.rechercherCoord(listNoeud.get(i));
            int xCoord = coord.get(0);
            int yCoord = coord.get(1);
            if(quantityPheromone[xCoord][yCoord] > bestQuantity && !aVisite[xCoord][yCoord])
            {
                xBest = xCoord;
                yBest = yCoord;
                bestQuantity = quantityPheromone[xCoord][yCoord];
            }
        }

        return x == xBest && y == yBest; // Si les parametres correspondent aux best coord où se trouve les pheromones
    }

    // Si les cellules adjacentes ne contiennent pas de pheromone, il devra aller de facon aléatoire comme la fourmis soldat
    public boolean rechercheAucunPheromone(boolean droitDePassage)
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
        // si 4 cellules adjacentes sans nourriture = true, sinon false
    }

    // Recherche si la fourmis soldat a deja visite tous les noeuds adjacents
    public boolean rechercheToutVisite()
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

    public String noeudVoisin()
    {
        record = true;
        rechercheAretes(g, this.x, this.y);
        StringBuilder noeudVoisin = new StringBuilder("\t --> Ouvrier n°" + numeroWorker + " | ");

        int nbrNoeudVoisin = 1;
        for(int i=0; i<listNoeud.size(); i++)
        {
            ArrayList<Integer> coord = g.rechercherCoord(listNoeud.get(i));
            int xCoord = coord.get(0);
            int yCoord = coord.get(1);

            noeudVoisin.append(" Voisin n°").append(nbrNoeudVoisin).append(" se trouve au Noeud n°").append(listNoeud.get(i)).append(" ");

            if(estObstacle[xCoord][yCoord])
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

    public int getX() {
        return this.x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
}
