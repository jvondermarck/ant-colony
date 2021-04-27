public class Noeud {
    int coordonneNoeud;

    public Noeud(Graphe g)
    {
        if(g.getNbrNoeudDansGraphe() >= g.getSize()) // Si le nbr de noeud déjà crée est >= au nbr max de cellule possibles, on lance une erreur
            throw new IndexOutOfBoundsException("Vous avez crée trop de noeud");
        else {
            this.coordonneNoeud = g.getEmplacementNoeud(Noeud.this);
        }
    }

    public int getCoordonneNoeud() {
        return coordonneNoeud;
    }
}
