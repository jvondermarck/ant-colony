public class Reine implements Fourmis {

    private Noeud residenceNaissance;
    private Colonie colonie;
    private Noeud positionActuel;

    public Reine(Noeud n, Graphe g)
    {
        setResidenceNaissance(n);
        setColonie(this.residenceNaissance.getColonie());
        setPositionActuel(this.residenceNaissance);
        donnerNaissance(3, this.residenceNaissance, this.colonie, g);
    }

    @Override
    public void setPositionActuel(Noeud n) {
        this.positionActuel = n;
    }

    public void setResidenceNaissance(Noeud n) {
        this.residenceNaissance = n;
    }

    @Override
    public void setColonie(Colonie c) {
        this.colonie = c;
    }

    public void donnerNaissance(int nombreSoldat, Noeud n, Colonie c, Graphe g)
    {
        for(int i=0; i<nombreSoldat; i++)
        {
            Soldat s = new Soldat(n, c, g, i+1);
            System.out.println("Naissance du soldat : " + i);
            //s.enMouvement();
        }
    }
}
