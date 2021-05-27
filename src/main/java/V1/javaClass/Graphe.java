package V1.javaClass;
import java.util.ArrayList;

/**
 * Un Graphe où se trouvera une colonie, des fourmis soldats, ouvrières, reine, avec des obstales, nourritures et phéromones.
 */
public class Graphe
{
    private boolean[][] estObstacle; // Tableau où se trouve les emplacements des cellules qui contient des noeuds
    private final int airGraphe; // Air du rectangle
    private int nbrNoeudDansGraphe; // Pour savoir le nbr de Noeud total que présentent le graphe
    private final Noeud[][] theNoeud; // Un tableau de Noeud qui permet de savoir a une coordonné X et Y, le numéro du noeud

    private final int row; // Le nombre de lignes du graphe
    private final int column; // Le nombre de colonnes du graphe

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

        this.estObstacle = new boolean[longueur][largueur];
        this.theNoeud = new Noeud[longueur][largueur];
        this.airGraphe = longueur*largueur;
        this.column = largueur;
        this.row = longueur;
        creationEmplacementNoeud(); // On crée les emplacements de cellules sur le graphes (créer des cellules)
    }

    /**
     * Creation emplacement noeud. Pour chaque cellule du graphe on va ajouter des numéros pour les repérer
     * Et nous allons créer les noeuds.
     */
    public void creationEmplacementNoeud()
    {
        int numeroCellule;
        for (int i = 0; i < this.row; ++i)
        {
            for(int j = 0; j < this.column; ++j)
            {
                // On va créer le Noeud
                numeroCellule =  i*this.column + j + 1; // Le numéro du noeud qu'on va attribuer au noeud
                Noeud noeud = new Noeud(Graphe.this, numeroCellule); // On crée une instance du noeud a la cellule en question, avec son numéro
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
        for (int i = 0; i < this.row; i++)
        {
            for(int j = 0; j < this.column; j++)
            {
                if (this.theNoeud[i][j] == this.theNoeud[x][y]) // On regarde si le numéro du Noeud correspond bien à celui qu'on cherche
                {
                    return this.theNoeud[x][y];
                }
            }
        }
        return null;
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
     * Récuperer le tableau de boolean à deux dimensions qui indique où se trouve les obstacles
     *
     * @return the boolean [ ] [ ]
     */
    public boolean[][] getEstObstacle() {
        return estObstacle;
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
     * Quand on veut modifier le tableau 2D sur les obstacles, on appelle cette méthode
     *
     * @param estObstacle le tableau boolean à deux dimensions concernant les obstacles
     */
    public void setEstObstacle(boolean[][] estObstacle) {
        this.estObstacle = estObstacle;
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
}
