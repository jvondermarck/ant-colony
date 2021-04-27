public class Aretes {

    private final int[][] tab;
    private final int coordonne;

    public Aretes(Graphe g, Noeud n)
    {
        this.tab = g.getTaille();
        this.coordonne = n.getCoordonneNoeud();
    }

    public void Affichage()
    {
        int xTab = 0;
        int yTab = 0;

        for (int i = 0; i < tab.length; ++i)
        {
            for(int j = 0; j < tab[i].length; ++j)
            {
                if(tab[i][j] == coordonne)
                {
                    xTab = i;
                    yTab = j;
                }
            }
        }

        System.out.print("Les aretes adjacentes du Noeud (" + coordonne + ") sont : ");
        for(int y=yTab-1; y<=yTab+1; y++)
            for(int x=xTab-1; x<=xTab+1; x++)
                if(x!=xTab || y!=yTab) // On veut pas une aretes qui a fasse 1-1 par exemple, donc on check si c'est pas égale
                    afficheDonnee(x, y);
    }

    private void afficheDonnee(int xTab, int yTab) {
        if(valideDonnee(xTab, yTab))
            System.out.print("(" + this.coordonne + "-" + tab[xTab][yTab] + ") ");
    }

    // Verifier qu'on ne depasse pas le tableau (a ne pas oublier).
    private boolean valideDonnee(int xTab, int yTab) {
        if(xTab<0 || xTab>=tab.length)
            return false;
        return !(yTab<0 || yTab >= tab[0].length);
    }
}
