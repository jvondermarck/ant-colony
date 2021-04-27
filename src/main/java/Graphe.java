public class Graphe {
    private int taille[][];
    private Boolean caseDejaPrise [][];

    public Graphe(int longueur, int largueur)
    {
        this.taille = new int[longueur][largueur];
        this.caseDejaPrise = new Boolean[longueur][largueur];
        CreationEmplacement();
    }

    public void CreationEmplacement()
    {
        int numeroNoeud = 1;
        for (int i = 0; i < taille.length; ++i)
        {
            for(int j = 0; j < taille[i].length; ++j)
            {
                taille[i][j] = numeroNoeud;
                caseDejaPrise[i][j] = false;
                numeroNoeud++;
            }
        }
    }

    public int getEmplacementNoeud(Noeud n)
    {
        int emplacement = 0;
        for (int i = 0; i < taille.length; ++i)
        {
            for(int j = 0; j < taille[i].length; ++j)
            {
                if(!caseDejaPrise[i][j]) // Si faux (si ne contient deja pas un noeud, alors on cree un nouveau emplacement)
                {
                    caseDejaPrise[i][j] = true;
                    return emplacement = taille[i][j];
                }
            }
        }
        return emplacement;
    }

    public void afficherGraphe()
    {
        for (int[] innerArray: taille) {
            // second for...each loop access each element inside the row
            for(int data: innerArray) {
                System.out.println(data);
            }
        }
    }
}
