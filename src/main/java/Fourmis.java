import java.util.*;

public class Fourmis {
    private int x;
    private int y;

    public Fourmis(int x,int y){
        this.x = x;
        this.y = y;
    }

    public void randomDirection(BitSet[][] grid)
    {
        Aretes aretes = new Aretes(grid, this.x, this.y);
        Map<Integer, Integer> mapNoeud;
        mapNoeud = aretes.getMapNoeud();

        if(mapNoeud.size() == 0){ // si aucune direction possible : ne rien faire
            return;
        }

        Set<Integer> keySet = mapNoeud.keySet();
        List<Integer> keyList = new ArrayList<>(keySet);

        int size = keyList.size();
        int randIdx = new Random().nextInt(size);

        int randomKey = keyList.get(randIdx);
        int randomValue = mapNoeud.get(randomKey);

        this.x = randomKey;
        this.y = randomValue;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
