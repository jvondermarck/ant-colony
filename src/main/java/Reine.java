public class Reine implements Fourmis {

    private Noeud residenceDeBase;
    private Colonie colonie;
    private Noeud coordonnee;

    public Reine(Noeud n, Graphe g)
    {
        setResidenceNaissance(n);
        setColonie(this.residenceDeBase.getColonie());
        setPositionActuel(this.residenceDeBase);
        donnerNaissance(3, this.residenceDeBase, this.colonie, g);
    }

    @Override
    public void setPositionActuel(Noeud n) {
        this.coordonnee = n;
    }

    @Override
    public void setResidenceNaissance(Noeud n) {
        this.residenceDeBase = n;
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
