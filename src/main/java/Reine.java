public class Reine implements Fourmis {

    private Noeud residenceDeBase;
    private Colonie colonie;
    private Noeud coordonnee;

    public Reine(Noeud n, Graphe g)
    {
        residenceDeBase(n);
        colonie(this.residenceDeBase.getColonie());
        coordonnee(this.residenceDeBase);
        donnerNaissance(3, this.residenceDeBase, this.colonie, g);
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
