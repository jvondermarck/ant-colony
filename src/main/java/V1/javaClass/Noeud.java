package V1.javaClass;

/**
 * La classe Noeud qui sert à une cellule particulière d'un graphe.
 */
public class Noeud {
    private int coordonneNoeud;
    /**
     * The nombre de noeuds qu'on a crée.
     */
    static int nombreNoeud = 0;

    /**
     * Constructeur Noeud qui cherche un emplacement pour créer le noeud et lui ai attribué un numero
     *
     * @param g : le graphe où se trouve le noeud
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

    /**
     * Pour changer la coordonné du noeud.
     *
     * @param coordonneNoeud la nouvelle coordonnée du noeud
     */
    public void setCoordonneNoeud(int coordonneNoeud) {
        this.coordonneNoeud = coordonneNoeud;
    }

    /** toString() : redéfinition de la méthode de même nom, affiche le numéro de l'objet Noeud
     @return chaine de caractère contenant le numéro du noeud
     */
    @Override
    public String toString() {
        return "Noeud n°" + this.coordonneNoeud;
    }
}
