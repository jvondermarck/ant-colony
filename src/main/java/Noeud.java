public class Noeud {
    private final int coordonneNoeud;
    static int nombreNoeud = 0;

    public Noeud(Graphe g)
    {
        if(g.getNbrNoeudDansGraphe() >= g.getAirGraphe()) // Si le nbr de noeud déjà crée est >= au nbr max de cellule possibles, on lance une erreur
            throw new IndexOutOfBoundsException("Vous avez crée trop de noeud");
        else {
            this.coordonneNoeud = g.getEmplacementNoeud(); //Noeud.this
            nombreNoeud++;
        }
    }

    public int getCoordonneNoeud() {
        return coordonneNoeud;
    }

    @Override
    public String toString() {
        return "Noeud n°" + this.coordonneNoeud;
    }
}
