package V2.javaClass;

/**
 * La classe Noeud qui sert à une cellule particulière d'un graphe.
 */
public class Noeud {
    private int coordonneNoeud;
    /**
     * Le nombres de noeuds qu'on a crée.
     */
    static int nombreNoeud = 0;

    /**
     * Constructeur Noeud qui cherche un emplacement pour créer le noeud et lui ai attribué un numero
     *
     * @param g : un graphe
     */
    public Noeud(Graphe g)
    {
        if(g.getNbrNoeudDansGraphe() >= g.getAirGraphe()) // Si le nbr de noeud déjà crée est >= au nbr max de cellule possibles, on lance une erreur
            throw new IndexOutOfBoundsException("Vous avez crée trop de noeud");
        else {
            this.coordonneNoeud = g.getEmplacementNoeud(); //Noeud.this
            nombreNoeud++;
        }
    }

    /**
     * getCoordonneeNoeud() : méthode qui renvoie le numéro de l'objet Noeud
     *
     * @return coordonneNoeud : un entier du numero du noeud
     */
    public int getCoordonneNoeud() {
        return coordonneNoeud;
    }

    /** toString() : redéfinition de la méthode de même nom, affiche le numéro de l'objet Noeud
     @return chaine de caractère contenant le numéro du noeud
     */
    @Override
    public String toString() {
        return "Noeud n°" + this.coordonneNoeud;
    }

    /**
     * Mettre une coordonnée de noeud
     *
     * @param coordonneNoeud l'entier où se trouve le noeud
     */
    public void setCoordonneNoeud(int coordonneNoeud) {
        this.coordonneNoeud = coordonneNoeud;
    }
}
