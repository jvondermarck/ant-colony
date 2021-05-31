package V2.javaClass;

import java.util.ArrayList;

/**
 * Un Graphe où se trouvera une colonie, des fourmis soldats, ouvrières, reine, avec des obstales, nourritures et phéromones.
 */
public class Graphe
{
    private boolean[][] estObstacle; // Tableau où se trouve les emplacements des cellules qui contient des noeuds
    private boolean[][] estNourriture; // Tableau où se trouve les emplacements des cellules qui contient de la nourriture
    private int[][] quantityFood; // Quandtite de nourriture dans chaque cellule
    private int [][] quantityPheromone; // Quantité de pheromone dans chaque cellule
    private final int row; // Le nombre de lignes du graphe
    private final int column; // Le nombre de colonnes du graphe
    private final Noeud[][] theNoeud; // Un tableau de Noeud qui permet de savoir a une coordonné X et Y, le numéro du noeud

    /**
     * Crée un Graphe en fonction de sa longueur et largueur.
     * Il créera automatiquement les cellules et les noeuds en leur attribuant des numéros
     *
     * @param longueur la longueur du Graphe
     * @param largueur la largueur du Graphe
     */
    public Graphe(int longueur, int largueur)
    {
        // Si longueur ou largeur est négative, alors on lance un message d'erreur
        if(longueur<0 || largueur <0)
            throw new NumberFormatException("Votre valeur est négative, veuillez inscrire que des nombres positifs");

        this.theNoeud = new Noeud[longueur][largueur];
        this.estObstacle = new boolean[longueur][largueur];
        this.estNourriture = new boolean[longueur][largueur];
        this.quantityFood = new int[longueur][largueur];
        this.quantityPheromone = new int[longueur][largueur];
        this.column = largueur;
        this.row = longueur;
        creationEmplacementNoeud(); // On crée les emplacements de cellules sur le graphes (créer des cellules)
    }

    /**
     * Creation emplacement noeud. Pour chaque cellule du graphe on va ajouter des numéros pour les repérer - On va créer les noeuds.
     */
    public void creationEmplacementNoeud()
    {
        int numeroCellule;
        for (int i = 0; i < this.row; ++i)
        {
            for(int j = 0; j < this.column; ++j)
            {
                estNourriture[i][j] = false; // Au début on a aucune cellule qui contient de la nourriture
                quantityFood[i][j] = 0; // On dit que au début on a une quantité de 0 comme nourriture
                quantityPheromone[i][j] = 0; // On dit que au début on a une quantité de 0 comme pheromone

                // On va créer le Noeud
                numeroCellule =  i*this.column + j + 1; // Le numéro du noeud qu'on va attribuer au noeud
                Noeud noeud = new Noeud(numeroCellule); // On crée une instance du noeud a la cellule en question, avec son numéro
                this.theNoeud[i][j] =  noeud; // On stock ce noeud dans un tableau de Noeud
            }
        }
    }

    /**
     * On recherche le numéro de la cellule où se trouve la fourmis fonction de sa coordonnée X et Y.
     *
     * @param x sa coordonée X (ligne)
     * @param y sa coordonée Y (colonne)
     * @return le noeud en question qu'on cherche
     */
    public Noeud rechercherNoeud(int x, int y)
    {
        return this.theNoeud[x][y];
    }

    /**
     * Rechercher la coordonnée X et Y en ayant seulement comme information son Noeud
     *
     * @param n le noeud où se trouve la fourmis
     * @return une liste d'entier avec en indice 0 sa coordonné X et sa coordonné Y en indice 1
     */
    public ArrayList<Integer> rechercherCoord(Noeud n)
    {
        ArrayList<Integer> coord = new ArrayList<>();
        for (int i = 0; i < this.row; i++)
        {
            for(int j = 0; j < this.column; j++)
            {
                if(n == rechercherNoeud(i,j)) // On va mettre dans 2 variables les coordonnées de X et Y
                {
                    coord.add(i);
                    coord.add(j);
                }
            }
        }
        return coord;
    }

    /**
     * Mettre obstacle a une coordonnée bien précise.
     *
     * @param row    la ligne
     * @param column la colonne
     */
    public void mettreObstacle(int row, int column)
    {
        this.estObstacle[row][column] = true;
    }

    /**
     * Mettre nourriture.
     *
     * @param row      the row
     * @param column   the column
     * @param quantity the quantity
     */
    public void mettreNourriture(int row, int column, int quantity)
    {
        if(row<0 || column <0 || quantity<0)
            throw new NumberFormatException("Votre valeur est négative, veuillez inscrire que des nombres positifs");

        this.estNourriture[row][column] = true;
        this.quantityFood[row][column] += quantity;
    }

    /**
     * Gestion nourriture int.
     *
     * @param x       la coordonné X ligne
     * @param y       la coordonné Y ligne
     * @param colonie La colonie de la fourmis
     * @return un entier qui détient la quantité de nourriture que la fourmi va prendre sur elle
     */
    public int gestionNourriture(int x, int y, Colonie colonie)
    {
        int quantityFoodTaken; // Quantité de nourriture que la fourmi va prendre à une coordonnée X et Y
        if (quantityFood[x][y] >= colonie.getFoodParam()) // Si ya + a manger que la fourmis va prendre
        {
            quantityFoodTaken = colonie.getFoodParam(); // On prend que la quantite de nourriture qu'on lui a indiqué
            quantityFood[x][y] -= colonie.getFoodParam();
        } else {
            quantityFoodTaken = quantityFood[x][y]; // On prend la nourriture qu'il reste, donc sa sera inferieur a ce qu'elle doit prendre de base
            quantityFood[x][y] = 0; // On met a 0 le nbr de nourriture vu que la fourmis a tout pris
            estNourriture[x][y] = false; // Vu que la quantité de noruriture est null, on dit que la case ne contient plus de nourriture
        }
        return quantityFoodTaken;
    }

    /**
     * Récuperer le tableau de boolean à deux dimensions qui indique où se trouve les obstacles
     *
     * @return the boolean [ ] [ ]
     */
    public boolean[][] getEstObstacle() {
        return estObstacle;
    }

    /**
     * Recuperer true/false si la cellule contient de la nourriture
     *
     * @return the boolean [ ] [ ]
     */
    public boolean[][] getEstNourriture() {
        return estNourriture;
    }

    /**
     * recuperer la quantite a manger de la cellule
     *
     * @return le tableau d'entier à deux dimensions concernant la quantité à manger
     */
    public int[][] getQuantityFood() {
        return quantityFood;
    }

    /**
     * Recuperer la quantite de pheromone a une cellule en question
     *
     * @return le tableau d'entier à deux dimensions concernant la quantité de phéromones
     */
    public int[][] getQuantityPheromone() {
        return quantityPheromone;
    }

    /**
     * Un tableau 2D pour la quantité de nourriture
     * @param quantityFood le tableau 2D quantité de nourriture
     */
    public void setQuantityFood(int[][] quantityFood) {
        this.quantityFood = quantityFood;
    }

    /**
     * Retourne le nombre de ligne du graphe
     *
     * @return le nombre de ligne du graphe
     */
    public int getRow() {
        return row;
    }

    /**
     * Retourne le nombre de colonne du graphe
     *
     * @return le nombre de colonne du graphe
     */
    public int getColumn() {
        return column;
    }

    /**
     * Sets quantity pheromone.
     *
     * @param quantityPheromone le tableau d'entier à deux dimensions concernant la quantité de phéromones
     */
    public void setQuantityPheromone(int[][] quantityPheromone) {
        this.quantityPheromone = quantityPheromone;
    }

    /**
     * Mettre de la nourriture a une cellule donné
     *
     * @param estNourriture le tableau boolean à deux dimensions concernant la nourriture
     */
    public void setEstNourriture(boolean[][] estNourriture) {
        this.estNourriture = estNourriture;
    }

    /**
     * Quand on veut modifier le tableau 2D sur les obstacles, on appelle cette méthode
     *
     * @param estObstacle le tableau boolean à deux dimensions concernant les obstacles
     */
    public void setEstObstacle(boolean[][] estObstacle) {
        this.estObstacle = estObstacle;
    }
}
