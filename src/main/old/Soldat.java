import java.util.ArrayList;
import java.util.List;

public class Soldat implements Fourmis {

    private Noeud residenceNaissance;
    private Colonie colonie;
    private Noeud positionActuel;
    private final Graphe graphe;
    private final int numeroSoldat;

    private final List<Integer> ListeNoeud = new ArrayList<>();

    public Soldat(Noeud n, Colonie c, Graphe g, int numSoldat)
    {
        this.graphe = g;
        this.numeroSoldat = numSoldat;
        this.ListeNoeud.add(n.getCoordonneNoeud());
        setPositionActuel(n);
        setResidenceNaissance(n);
        setColonie(c);
        enMouvement();
    }

    @Override
    public void setPositionActuel(Noeud n) {
        this.positionActuel = n;
    }

    @Override
    public void setResidenceNaissance(Noeud n) { this.residenceNaissance = n; }

    @Override
    public void setColonie(Colonie c) {
        this.colonie = c;
    }

    public void patrouille(Noeud n)
    {
        Aretes arr = new Aretes(n, this.graphe);
        arr.deplacementNoeud();
        this.ListeNoeud.add(n.getCoordonneNoeud());
        setPositionActuel(n);
    }

    public void enMouvement()
    {
        for(int i=0; i<this.graphe.getNbrNoeudDansGraphe(); i++)
        {
            patrouille(this.positionActuel);
            System.out.println("Soldat (" + this.numeroSoldat + ", habitation : " + this.residenceNaissance + "), se trouve au : " + this.positionActuel);
        }
    }
}