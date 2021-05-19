package V1.javaClass;
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

    public void randomDirection(Graphe g)
    {
        Aretes aretes = new Aretes(g, this.x, this.y);

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
