import java.util.*;

public abstract class Fourmis {
    private int x;
    private int y;

    public Fourmis(int x,int y){
        this.x = x;
        this.y = y;
    }

    public void randomDirection(BitSet[][] grid)
    {
        Aretes aretes = new Aretes(grid, this.x, this.y);

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
        return x;
    }

    public int getY() {
        return y;
    }
}
