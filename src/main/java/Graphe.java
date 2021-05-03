import java.util.Random;

public class Graphe
{
    private int[][] taille; // Tableau où se trouve tous les emplacements des cellules du graphe (vide)
    private Boolean[][] caseDejaPrise; // Tableau où se trouve les emplacements des cellules qui contient des noeuds

    private int longueur;
    private int largueur;
    private int size; // Air du rectangle

    private int nbrNoeudDansGraphe; // Pour savoir le nbr de Noeud total que présentent le graphe

    public Graphe(int longueur, int largueur, int nbrColonie)
    {
        // Si longueur ou largeur est négative, alors on lance un message d'erreur
        if(longueur<0 || largueur <0)
            throw new NumberFormatException("Votre valeur est négative, veuillez inscrire que des nombres positifs");

        try {
            this.taille = new int[longueur][largueur];
            this.caseDejaPrise = new Boolean[longueur][longueur];
            this.longueur = longueur;
            this.largueur = largueur;
            this.size = longueur*largueur;
            CreationColonie(nbrColonie);
            CreationEmplacement(); // On crée les emplacements de cellules sur le graphes (créer des cellules)
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
                caseDejaPrise[i][j] = false; // On dit que cette cellule est pas encore prise par un noeud (vu qu'on est à l'étape de création seulement)
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
                    return taille[i][j]; // On retourne l'emplacement du noeud (le numéro de cellule où se trouve le noeud)
                }
            }
        }
        return emplacementNoeud;
    }

    public void CreationColonie(int nbrColonie)
    {
        for(int i=0; i<nbrColonie; i++)
        {
            Colonie c = new Colonie();
        }
    }

    // Pour afficher le graphe
    public void afficherGraphe()
    {
        for (int[] innerArray: taille) {
            for(int data: innerArray) {
                System.out.println(data);
            }
        }
    }

    public void obstacle()
    {


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
