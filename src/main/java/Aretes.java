import java.util.*;

public class Aretes {

    private final int[][] tab;
    private final Boolean[][] estObstacle;
    private final int x;
    private final int y;
    private final ArrayList<Integer> listX = new ArrayList<>();
    private final ArrayList<Integer> listY = new ArrayList<>();
    private final Graphe graphe;

    public Aretes(Graphe g, int x, int y)
    {
        this.tab = g.getTaille();
        this.estObstacle = g.getEstObstacle();
        this.x = x;
        this.y = y;
        this.graphe = g;
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
        for(int x=xTab-1; x<=xTab+1; x++) // On parcous la ligne cellule-1 jusqu'a la cellule+1
            for(int y=yTab-1; y<=yTab+1; y++) // On parcous la colonne cellule-1 jusqu'a la cellule+1
                if(x == xTab || y == yTab) // Ce qui fait un +, on cherche pas les cellules adjacentes sur la diagonales
                {
                    if(verficationNoeud(x, y)) // On vérfie qu'il n'est pas OutOfBands, qu'il est hors du graphe
                        // On vérifie que la case n'est pas un obstacle
                        if((tab[x][y] != tab[xTab][yTab]) && !estObstacle[x][y]) // On bloque la possibilité de se déplacer a la cellule de départ
                            ajouterAretes(x, y);
                }


        if(listX.isEmpty() && listY.isEmpty())
        {
            listX.add(xTab);
            listY.add(yTab);
        }
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

    public void paramSoldat(Reine reine, Ouvrier ouvrier)
    {
        Colonie colonie = reine.getColonie();
        int evaporationParam = colonie.getEvaporationParam();
        int foodParam = colonie.getFoodParam();
        int pheromoneParam = colonie.getPheromoneParam();

        Boolean[][] aVisite = ouvrier.getaVisite();

        for(int i=0; i<listX.size(); i++)
        {
            for(int j=0; j<listY.size(); j++)
            {
                int xCoord = listX.get(i);
                int yCoord = listY.get(j);

                if(!aVisite[xCoord][yCoord]) // Si il a pas visité cette cellule
                {
                    if(rechercheBestPheromone(xCoord,yCoord)) // Si la cellule a la meilleure quantite de pheromone
                    {
                        aVisite[xCoord][yCoord] = true;
                        ouvrier.setaVisite(aVisite);

                        listX.clear(); // On supprime toutes les aretes adjacentes de depart
                        listX.add(xCoord); // On ajoute juste la coordonnée de x
                        listY.clear();
                        listY.add(yCoord); // On ajoute juste la coordonnée de y
                    }
                }
                i++;
            }
        }
    }

    private boolean rechercheBestPheromone(int x, int y)
    {
        int xBest = 0;
        int yBest = 0;

        int bestQuantity = 0;

        int [][] quantityPheromone = graphe.getQuantityPheromone();

        for(int i=0; i<listX.size(); i++)
        {
            for(int j=0; j<listY.size(); j++)
            {
                int xCoord = listX.get(i);
                int yCoord = listY.get(j);
                if(quantityPheromone[xCoord][yCoord] > bestQuantity)
                {
                    xBest = xCoord;
                    yBest = yCoord;
                    bestQuantity = quantityPheromone[xCoord][yCoord];
                }

                i++;
            }
        }

        return x == xBest && y == yBest; // Si les parametres correspondent aux best coord où se trouve les pheromones
    }

    public ArrayList<Integer> getListX() {
        return listX;
    }

    public ArrayList<Integer> getListY() {
        return listY;
    }
}
