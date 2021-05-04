import java.util.ArrayList;

public class Soldat implements Fourmis {

    private Noeud residenceDeBase;
    private Colonie colonie;
    private Noeud coordonnee;
    private Graphe graphe;
    private int numeroSoldat;

    private final ArrayList<Noeud> ListeNoeud = new ArrayList<Noeud>();

    public Soldat(Noeud n, Colonie c, Graphe g, int numSoldat)
    {
        this.graphe = g;
        this.numeroSoldat = numSoldat;
        this.ListeNoeud.add(n);
        coordonnee(n);
        residenceDeBase(n);
        colonie(c);
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
        Noeud nouveauNoeud = arr.deplacementNoeud();

        while(ListeNoeud.contains(nouveauNoeud))
        {
            nouveauNoeud = arr.deplacementNoeud();
        }

        ListeNoeud.add(nouveauNoeud);
        coordonnee(nouveauNoeud);
    }

    public void enMouvement()
    {
        for(int i=0; i<this.graphe.getNbrNoeudDansGraphe(); i++)
        {
            patrouille(this.coordonnee);
            System.out.println("Soldat (" + this.numeroSoldat + "- né au Noeud : " + this.residenceDeBase + "), dans le noeud : " + this.coordonnee);
        }
    }
}
