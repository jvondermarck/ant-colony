package V2.javaClass;

import java.util.ArrayList;

/**
 * Un Graphe où se trouvera une colonie, des fourmis soldats, ouvrières, reine, avec des obstales, nourritures et phéromones.
 */
public class Graphe
{
    private int[][] taille; // Tableau où se trouve tous les emplacements des cellules du graphe (vide)
    private Boolean[][] estObstacle; // Tableau où se trouve les emplacements des cellules qui contient des noeuds
    private Boolean[][] estNourriture; // Tableau où se trouve les emplacements des cellules qui contient de la nourriture
    private int[][] quantityFood; // Quandtite de nourriture dans chaque cellule
    private int [][] quantityPheromone; // Quantité de pheromone dans chaque cellule

    private int airGraphe; // Air du rectangle
    private int longueur;
    private int largueur;
    private int nbrNoeudDansGraphe; // Pour savoir le nbr de Noeud total que présentent le graphe
    private final ArrayList<Noeud> theNoeud = new ArrayList<>();

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

        try {
            this.longueur = longueur;
            this.largueur = largueur;

            this.taille = new int[longueur][largueur];
            this.estObstacle = new Boolean[longueur][largueur];
            this.estNourriture = new Boolean[longueur][largueur];
            this.quantityFood = new int[longueur][largueur];
            this.quantityPheromone = new int[longueur][largueur];
            this.airGraphe = longueur*largueur;
            creationEmplacementNoeud(); // On crée les emplacements de cellules sur le graphes (créer des cellules)
        } catch(RuntimeException ex)
        {
            System.out.println("Le format entrée est incorect, veuillez réssayer.");
        }
    }

    /**
     * Creation emplacement noeud. Pour chaque cellule du graphe on va ajouter des numéros pour les repérer - On va créer les noeuds.
     */
    public void creationEmplacementNoeud()
    {
        int numeroCellule = 1;
        for (int i = 0; i < taille.length; ++i)
        {
            for(int j = 0; j < taille[i].length; ++j)
            {
                taille[i][j] = numeroCellule; // On ajoute sur cette cellule son numéro
                estObstacle[i][j] = true; // On dit que cette cellule est pas encore prise par un noeud (vu qu'on est à l'étape de création seulement) = donc que c'est un obstacle
                estNourriture[i][j] = false; // Au début on a aucune cellule qui contient de la nourriture
                quantityFood[i][j] = 0; // On dit que au début on a une quantité de 0 comme nourriture
                quantityPheromone[i][j] = 0; // On dit que au début on a une quantité de 0 comme pheromone
                numeroCellule++; // On incrémente de 1 pour que la prochaine cellule aie un nbr différent de celle d'avant

                // On va créer le Noeud
                Noeud noeud = new Noeud(Graphe.this);
                theNoeud.add(noeud);
            }
        }
        System.out.println("-NOEUD CRÉES dans Graphe: " + Noeud.nombreNoeud);
    }

    /**
     * On s'occupe d'attribuer à un noued, une cellule non prise sur le Graphe
     * Cette méthode sera appelée dans la classe Noeud.
     *
     * @return l 'emplacement du noeud
     */
    public int getEmplacementNoeud()
    {
        for (int i = 0; i < taille.length; ++i)
        {
            for(int j = 0; j < taille[i].length; ++j)
            {
                if(estObstacle[i][j]) // Si faux (si cellule ne contient deja pas un noeud, alors on cree un nouveau emplacement)
                {
                    estObstacle[i][j] = false; // On modifie l'état de la cellule en disant qu'elle est occupé par un noeud, donc que c'est plus un obstacle
                    this.nbrNoeudDansGraphe++; // On compte le nombre de case qu'on a dans notre graphe pour éviter qu'on créer + de noeud que de cellules
                    return taille[i][j]; // On retourne l'emplacement du noeud (le numéro de cellule où se trouve le noeud)
                }
            }
        }
        return 0;
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
        for (int[] ints : this.taille) {
            for (int anInt : ints) {
                if (anInt == taille[x][y]) {
                    for (Noeud n : this.theNoeud) {
                        int numero = n.getCoordonneNoeud();
                        if (numero == taille[x][y]) {
                            return n;
                        }
                    }
                }
            }
        }
        return null;
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
     * Récuperer le tableau d'entiers à deux dimensions qui est le Graphe en lui-meme
     *
     * @return un tableau d'entiers à deux dimensions
     */
    public int[][] getTaille() {
        return taille;
    }

    /**
     * Récuperer le tableau de boolean à deux dimensions qui indique où se trouve les obstacles
     *
     * @return the boolean [ ] [ ]
     */
    public Boolean[][] getEstObstacle() {
        return estObstacle;
    }

    /**
     * Recuperer true/false si la cellule contient de la nourriture
     *
     * @return the boolean [ ] [ ]
     */
    public Boolean[][] getEstNourriture() {
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
     * Récuperer les dimensions du graphe
     *
     * @return l 'air du graphe
     */
    public int getAirGraphe() {
        return airGraphe;
    }

    /**
     * Récuperer le nombre de noeuds que le graphe à crée.
     *
     * @return le nombre de noeuds
     */
    public int getNbrNoeudDansGraphe() {
        return nbrNoeudDansGraphe;
    }

    /**
     * Gets longueur du graphe.
     *
     * @return the longueur
     */
    public int getLongueur() {
        return longueur;
    }

    /**
     * Gets largueur du graphe.
     *
     * @return the largueur du graphe
     */
    public int getLargueur() {
        return largueur;
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
     * Mettre de ka nourriture a une cellule donné
     *
     * @param estNourriture le tableau boolean à deux dimensions concernant la nourriture
     */
    public void setEstNourriture(Boolean[][] estNourriture) {
        this.estNourriture = estNourriture;
    }

    /**
     * Quand on veut modifier le tableau 2D sur les obstacles, on appelle cette méthode
     *
     * @param estObstacle le tableau boolean à deux dimensions concernant les obstacles
     */
    public void setEstObstacle(Boolean[][] estObstacle) {
        this.estObstacle = estObstacle;
    }
}
