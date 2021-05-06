public class Noeud {
    private int coordonneNoeud;
    private final Colonie colonie;

    public Noeud(Graphe g)
    {
        if(g.getNbrNoeudDansGraphe() >= g.getAirGraphe()) // Si le nbr de noeud déjà crée est >= au nbr max de cellule possibles, on lance une erreur
            throw new IndexOutOfBoundsException("Vous avez crée trop de noeud");
        else {
            this.coordonneNoeud = g.getEmplacementNoeud(Noeud.this);
            this.colonie = new Colonie();
        }
    }

    public int getCoordonneNoeud() {
        return coordonneNoeud;
    }

    public void setCoordonneNoeud(int coordonneNoeud) {
        this.coordonneNoeud = coordonneNoeud;
    }

    public Colonie getColonie() {
        return colonie;
    }

    @Override
    public String toString() {
        return "n°" + this.coordonneNoeud;
    }
}
