package V2.javaClass;
import java.util.ArrayList;
import java.util.Random;

/**
 * La classe abstraite modifiable {@code Figure}.
 * <p>
 *
 */

public abstract class FourmisMove implements Fourmis {
    private int x;
    private int y;

    public FourmisMove(int x, int y) {
        setX(x);
        setY(y);
    }

    public void randomDirection(Graphe g, Object o, Reine r)
    {
        Aretes aretes = new Aretes(g, this.x, this.y);

        if(o instanceof Ouvrier) // Si c'est un ouvrier, on va chercher sa future cellule différement
        {
            Ouvrier ouvrier = (Ouvrier)o; // Comme on sait que o est une instance de Ouvrier, on le cast
            aretes.paramOuvrier(ouvrier); // On cherche le ou les cellules adjacentes en fonction des cellules deja visite et des pheromones
        }

        Random rand = new Random();
        ArrayList<Integer> listX;
        ArrayList<Integer> listY;

        listX = aretes.getListX();
        listY = aretes.getListY();

        int alea = rand.nextInt(listX.size());
        int nextX = listX.get(alea);
        int nextY = listY.get(alea);

        this.x = nextX;
        this.y = nextY;

        if(o instanceof Ouvrier) // Si c'est un ouvrier, on va lui mettre ses coordonnées qui lui servira pour le retour
        {
            Ouvrier ouvrier = (Ouvrier)o; // Comme on sait que o est une instance de Ouvrier, on le cast
            ouvrier.getaVisite()[this.x][this.y] = true;
            if(g.getEstNourriture()[this.x][this.y]) // Si au nouvelle emplacement de la fourmis il y a de la nourriture
            {
                ouvrier.setEtatRetour(true); // Elle va devoir retourner sur la fourmilière ramener la nourriture
                if(g.getQuantityFood()[this.x][this.y] >= r.getColonie().getFoodParam()) // Si ya + a manger que la fourmis va prendre
                {
                    ouvrier.setQuantityFoodTaken(r.getColonie().getFoodParam()); // On prend que la quantite de nourriture qu'on lui a indiqué
                    g.getQuantityFood()[this.x][this.y] -= r.getColonie().getFoodParam();
                } else {
                    ouvrier.setQuantityFoodTaken(g.getQuantityFood()[this.x][this.y]); // On prend la nourriture qu'il reste, donc sa sera inferieur a ce qu'elle doit prendre de base
                    g.getQuantityFood()[this.x][this.y] = 0;
                }
            } else {
                ouvrier.getListXRetour().add(this.x); // Si ouvrier est pas sur la cellule où se trouve de la nourriture, on ajoute ses coordonnés dans la liste
                ouvrier.getListYRetour().add(this.y);

            }
        }
    }

    public void cheminRetour(Ouvrier ouvrier)
    {
        if(ouvrier.isEtatRetour()) // Si il retourne bien à la base
        {
            this.x = ouvrier.getListXRetour().get(ouvrier.getListXRetour().size()-1); // on prend sa derniere coordonnée enrengistré
            this.y = ouvrier.getListYRetour().get(ouvrier.getListYRetour().size()-1);

            // On va supprimer le dernier élement de la liste des coordonnées X et Y
            ouvrier.getListXRetour().remove(ouvrier.getListXRetour().size()-1);
            ouvrier.getListYRetour().remove(ouvrier.getListYRetour().size()-1);
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
}
