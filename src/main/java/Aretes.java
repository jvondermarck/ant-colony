import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Aretes {

    private final int[][] tab;
    private final int coordonne;
    private final Boolean[][] estObstacle;
    private final ArrayList<Integer> listNoeud = new ArrayList<>();
    private final Noeud n;

    public Aretes(Noeud n, Graphe g)
    {
        this.tab = g.getTaille();
        this.coordonne = n.getCoordonneNoeud();
        this.n = n;
        this.estObstacle = g.getEstObstacle();
        Affichage();
    }

    public void Affichage()
    {
        int xTab = 0;
        int yTab = 0;

        for (int i = 0; i < tab.length; ++i)
        {
            for(int j = 0; j < tab[i].length; ++j)
            {
                if(tab[i][j] == coordonne && !estObstacle[i][j]) // estObstacle = faux (que c'est un noeud)
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
                    afficheDonnee(x, y);
    }

    private void afficheDonnee(int xTab, int yTab) {
        if(valideDonnee(xTab, yTab))
        {
            //System.out.print("(" + this.coordonne + "-" + tab[xTab][yTab] + ") ");
            listNoeud.add(tab[xTab][yTab]);
        }
    }

    // Verifier qu'on ne depasse pas le tableau (a ne pas oublier).
    private boolean valideDonnee(int xTab, int yTab) {
        if(xTab<0 || xTab>=tab.length)
            return false;
        return !(yTab<0 || yTab >= tab[0].length); // Si (false) = renvoie true et si (true) = renvoie false
        // tab[0] = car on regarde les colonnes, non pas les lignes
    }

    // On va chosir aléatoirement en fonction des aretes adjacentes du noeud, un nouveau noeud à aller
    public void deplacementNoeud()
    {
        Random rand = new Random();
        int noeudSuivant = listNoeud.get(rand.nextInt(listNoeud.size()));
        this.n.setCoordonneNoeud(noeudSuivant);
    }


}
