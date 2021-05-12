public class Soldat extends Fourmis{
    private final Colonie colonie;
    private Graphe graphe;
    private static int nombreSoldat = 1;
    private int numeroSoldat;
    private Noeud positionActuel;
    private final int x;
    private final int y;

    public Soldat(int x, int y, Colonie colonie, Graphe graphe) {
        super(x,y);
        this.x = x;
        this.y = y;
        this.colonie = colonie;
        this.graphe = graphe;
        this.numeroSoldat = nombreSoldat;
        nombreSoldat++;
        recherchePositionActuel(this.x, this.y);
    }

    public void recherchePositionActuel(int ligne, int colonne)
    {
        positionActuel = graphe.rechercherNoeud(ligne, colonne);
    }

    @Override
    public String toString() {
        return "Soldat n°" + numeroSoldat + " - " + this.colonie.toString() + " --> se trouve au " + this.positionActuel;
    }
}