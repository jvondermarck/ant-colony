public class Graphe
{
    private int[][] taille;
    private Boolean[][] caseDejaPrise;
    private Boolean[][] isSetMatrix;

    private int longueur;
    private int largueur;
    private int size;

    private int nbrNoeudDansGraphe;

    public Graphe(int longueur, int largueur)
    {
        // Si longueur ou largeur est négative, alors on lance un message d'erreur
        if(longueur<0 || largueur <0)
            throw new NumberFormatException("Votre valeur est négative, veuillez inscrire que des nombres positifs");

        try {
            this.taille = new int[longueur][largueur];
            this.caseDejaPrise = new Boolean[longueur][longueur];
            CreationEmplacement();
            this.longueur = longueur;
            this.largueur = largueur;
            this.size = longueur*largueur;
        } catch(RuntimeException ex)
        {
            System.out.println("Le format entrée est incorect, veuillez réssayer.");
        }
    }

    // Pour chaque cellule du graphe on va ajouter des numéros pour les repérer.
    public void CreationEmplacement()
    {
        int numeroCellule = 1;
        for (int i = 0; i < taille.length; ++i)
        {
            for(int j = 0; j < taille[i].length; ++j)
            {
                taille[i][j] = numeroCellule; // On ajoute sur cette cellule son numéro
                caseDejaPrise[i][j] = false; // On dit que cette cellule est pas encore prise par un noeud
                numeroCellule++; // On incrémente de 1 pour que la prochaine cellule aie un nbr différent de celle d'avant
            }
        }
    }

    // On s'occupe d'attribuer à un noued, une cellule non prise sur le Graphe
    public int getEmplacementNoeud(Noeud n)
    {
        int emplacementNoeud = 0; // L'emplacement du noeuf qu'on va retourner
        for (int i = 0; i < taille.length; ++i)
        {
            for(int j = 0; j < taille[i].length; ++j)
            {
                if(!caseDejaPrise[i][j]) // Si faux (si cellule ne contient deja pas un noeud, alors on cree un nouveau emplacement)
                {
                    caseDejaPrise[i][j] = true; // On modifie l'état de la cellule en disant qu'elle est occupé par un noeud
                    this.nbrNoeudDansGraphe++; // On compte le nombre de case qu'on a dans notre graphe pour éviter qu'on créer + de noeud que de cellules
                    return emplacementNoeud = taille[i][j];
                }
            }
        }
        return emplacementNoeud;
    }

    // Pour afficher le graphe
    public void afficherGraphe()
    {
        for (int[] innerArray: taille) {
            // second for...each loop access each element inside the row
            for(int data: innerArray) {
                System.out.println(data);
            }
        }
    }

    public int getLongueur() {
        return longueur;
    }

    public int getLargueur() {
        return largueur;
    }

    public int getSize() {
        return size;
    }

    public int getNbrNoeudDansGraphe() {
        return nbrNoeudDansGraphe;
    }

    public int[][] getTaille() {
        return taille;
    }

}
