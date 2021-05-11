import java.util.*;

public class Aretes {

    private final BitSet[][] grid;
    private int x;
    private int y;
    //private final ArrayList<Integer> listNoeud = new ArrayList<>();
    private Map<Integer, Integer> mapNoeud = new Hashtable<Integer,Integer>();


    public Aretes(BitSet[][] grid, int x, int y)
    {
        this.grid = grid;
        this.x = x;
        this.y = y;
        rechercheAretes();
    }

    public void rechercheAretes()
    {
        int xTab = 0;
        int yTab = 0;

        for (int i = 0; i < this.grid.length; ++i)
        {
            for(int j = 0; j < grid[i].length; ++j)
            {
                if(grid[i][j] == grid[this.x][this.y] && !grid[i][j].get(1)) // si ya pas un obstacle, alors
                {
                    xTab = i;
                    yTab = j;
                }
            }
        }

        //System.out.print("Les aretes adjacentes du Noeud (" + coordonne + ") sont : ");
        for(int y=yTab-1; y<=yTab+1; y++)
            for(int x=xTab-1; x<=xTab+1; x++)
                if(x!=xTab || y!=yTab) // On veut pas une aretes qui a fasse 1-1 par exemple, donc on check si c'est pas égale
                    ajouterAretes(x, y);
    }

    private void ajouterAretes(int xTab, int yTab) {
        if(verficationNoeud(xTab, yTab))
        {
            //listNoeud.add(this.tab[xTab][yTab]);
            mapNoeud.put(xTab,yTab);
        }
    }

    // Verifier qu'on ne depasse pas le tableau (a ne pas oublier).
    private boolean verficationNoeud(int xTab, int yTab) {
        if(xTab<0 || xTab>=grid.length)
            return false;
        return !(yTab<0 || yTab >= grid[0].length); // Si (false) = renvoie true et si (true) = renvoie false
        // tab[0] = car on regarde les colonnes, non pas les lignes
    }

    // On va chosir aléatoirement en fonction des aretes adjacentes du noeud, un nouveau noeud à aller
//    public int deplacementNoeud()
//    {
//        Random rand = new Random();
//        int noeudSuivant = listNoeud.get(rand.nextInt(listNoeud.size()));
//        return listNoeud.get(noeudSuivant);
//    }

    public Map<Integer, Integer> getMapNoeud() {
        return mapNoeud;
    }
}
