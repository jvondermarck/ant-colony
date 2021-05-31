package V1.javaClass;

/**
 * La classe Noeud qui sert à une cellule particulière d'un graphe.
 */
public class Noeud {
    private int coordonneNoeud;

    /**
     * Constructeur Noeud qui cherche un emplacement pour créer le noeud et lui ai attribué un numero
     */
    public Noeud(int numeroNoeud)
    {
        this.coordonneNoeud = numeroNoeud;
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

    /** toString() : redéfinition de la méthode, affiche le numéro de l'objet Noeud
     @return chaine de caractère contenant le numéro du noeud
     */
    @Override
    public String toString() {
        return "Noeud n°" + this.coordonneNoeud;
    }



}
