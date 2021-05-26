package V2.javaClass;

import java.util.ArrayList;
import java.util.Random;

public abstract class MoveSoldat implements FourmisMove {

    private int x;
    private int y;
    private Graphe g;

    public MoveSoldat(int x, int y) {
        setX(x);
        setY(y);
    }

    @Override
    public void randomDirection(Graphe g, Object o, Reine r) {
        Aretes aretes = new Aretes(g, this.x, this.y);
        this.g = g;

        //Random rand = new Random();
        ArrayList<Integer> listX;
        ArrayList<Integer> listY;

        listX = aretes.getListX();
        listY = aretes.getListY();

        int alea = Aretes.rand.nextInt(listX.size());
        int nextX = listX.get(alea);
        int nextY = listY.get(alea);

        this.x = nextX;
        this.y = nextY;
    }

    public String noeudVoisin(Soldat soldat)
    {
        Aretes arr = new Aretes(this.g, soldat.getX(), soldat.getY());
        return arr.noeudVoisin(soldat);
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
