import java.util.ArrayList;
import java.util.List;

public class Soldat implements Fourmis {

    private Noeud residenceDeBase;
    private Colonie colonie;
    private Noeud coordonnee;
    private final Graphe graphe;
    private final int numeroSoldat;

    private final List<Integer> ListeNoeud = new ArrayList<>();

    public Soldat(Noeud n, Colonie c, Graphe g, int numSoldat)
    {
        this.graphe = g;
        this.numeroSoldat = numSoldat;
        this.ListeNoeud.add(n.getCoordonneNoeud());
        coordonnee(n);
        residenceDeBase(n);
        colonie(c);
        enMouvement();
    }

    @Override
    public void coordonnee(Noeud n) {
        this.coordonnee = n;
    }

    @Override
    public void residenceDeBase(Noeud n) {
        this.residenceDeBase = n;
    }

    @Override
    public void colonie(Colonie c) {
        this.colonie = c;
    }

    public void patrouille(Noeud n)
    {
        Aretes arr = new Aretes(n, this.graphe);
        arr.deplacementNoeud();

        while(ListeNoeud.contains(n.getCoordonneNoeud()))
        {
            arr.deplacementNoeud();
        }

        this.ListeNoeud.add(n.getCoordonneNoeud());
        coordonnee(n);
    }

    public void enMouvement()
    {
        for(int i=0; i<this.graphe.getNbrNoeudDansGraphe(); i++)
        {
            patrouille(this.coordonnee);
            System.out.println("Soldat (" + this.numeroSoldat + ", habitation : " + this.residenceDeBase + "), se trouve au : " + this.coordonnee);
        }
    }
}
