public class Soldat extends FourmisMove {
    private final Colonie colonie;
    private final Graphe graphe;
    private static int nombreSoldat = 0;
    private final int numeroSoldat;
    private Noeud positionActuel;

    public Soldat(int x, int y, Colonie colonie, Graphe graphe) {
        super(x,y);
        this.colonie = colonie;
        this.graphe = graphe;
        nombreSoldat = nombreSoldat+1;
        this.numeroSoldat = nombreSoldat;
        recherchePositionActuel(x, y);
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