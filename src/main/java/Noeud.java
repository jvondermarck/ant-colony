public class Noeud {
    int coordonneNoeud;

    public Noeud(Graphe g)
    {
        this.coordonneNoeud = g.getEmplacementNoeud(Noeud.this);
    }

    public int getCoordonneNoeud() {
        return coordonneNoeud;
    }
}
