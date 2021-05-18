package javaClass;
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
            aretes.paramOuvrier(r, ouvrier); // On cherche le ou les cellules adjacentes en fonction des cellules deja visite et des pheromones
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
            ouvrier.getListXRetour().add(this.x);
            ouvrier.getListYRetour().add(this.y);
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
