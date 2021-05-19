package V1.javaClass;
import java.util.ArrayList;
import java.util.Random;

/**
 * La classe abstraite FourmisMove utilisé uniquement par des fourmis qui on le droit de se déplacer {@code Figure}.
 * <p>
 */
public abstract class FourmisMove implements Fourmis {
    private int x;
    private int y;

    /**
     * Définit la fourmi comme fourmi qui peut se déplacer et prend ses coordonnées.
     *
     * @param x the x
     * @param y the y
     */
    public FourmisMove(int x, int y) {
        setX(x);
        setY(y);
    }

    /**
     * Cette méthode cherche la future cellule où se déplacera la fourmi ouvrière.
     * Et tire de facon aléatoire en prenant toutes les précautions possibles qui sont les obstacles, sa nouvelle destination
     *
     * @param g le graphe où se trouve la fourmi.
     */
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

    /**
     * Recevoir la coordonnée X (ligne).
     *
     * @return the x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Recevoir la coordonnée Y (colonne).
     *
     * @return the y
     */
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
