import java.util.*;

public class Aretes {

    private final int[][] tab;
    private final Boolean[][] estObstacle;
    private final int x;
    private final int y;
    private final ArrayList<Integer> listX = new ArrayList<>();
    private final ArrayList<Integer> listY = new ArrayList<>();

    public Aretes(Graphe g, int x, int y)
    {
        this.tab = g.getTaille();
        this.estObstacle = g.getEstObstacle();
        this.x = x;
        this.y = y;
        rechercheAretes();
    }

    public void rechercheAretes()
    {
        int xTab = 0;
        int yTab = 0;

        for (int i = 0; i < tab.length; ++i)
        {
            for(int j = 0; j < tab[i].length; ++j)
            {
                if(tab[i][j] == tab[this.x][this.y]) // On va mettre dans 2 variables les coordonnées de X et Y
                {
                    xTab = i;
                    yTab = j;
                }
            }
        }

        for(int y=yTab-1; y<=yTab+1; y++) // On parcous la colonne cellule-1 jusqu'a la cellule+1
            for(int x=xTab-1; x<=xTab+1; x++) // On parcous la ligne cellule-1 jusqu'a la cellule+1
                if(verficationNoeud(x, y)) // On vérfie qu'il n'est pas OutOfBands, qu'il est hors du graphe
                    // On vérifie que la case n'est pas un obstacle
                    if((x!=xTab || y!=yTab) && !estObstacle[x][y]) // On bloque la possibilité de se déplacer a la cellule de départ
                        ajouterAretes(x, y);

    }

    // Verifier qu'on ne depasse pas le tableau
    private boolean verficationNoeud(int xTab, int yTab) {
        if(xTab<0 || xTab>=tab.length)
            return false;
        return !(yTab<0 || yTab >= tab[0].length); // Si (false) = renvoie true et si (true) = renvoie false
        // tab[0] = car on regarde les colonnes, non pas les lignes
    }

    // On ajoute les deux coordonnées dans deux listes différentes
    private void ajouterAretes(int xTab, int yTab) {
        listX.add(xTab); // On ajoute la coordonnée de la ligne dans la 1er liste
        listY.add(yTab); // On ajoute la coordonnée de la colonne dans la 2e liste
    }




    public ArrayList<Integer> getListX() {
        return listX;
    }

    public ArrayList<Integer> getListY() {
        return listY;
    }
}
