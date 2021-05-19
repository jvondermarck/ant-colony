package V1.javaClass;
import java.util.ArrayList;

/**
 * The type Reine.
 */
public class Reine implements Fourmis {
    private final Colonie colonie;
    private final Graphe graphe;
    private int x;
    private int y;

    private final ArrayList<Soldat> theSoldiers = new ArrayList<>();

    /**
     * Instantiates a new Reine.
     *
     * @param x the x
     * @param y the y
     * @param g the g
     */
    public Reine(int x, int y, Graphe g)
    {
        setX(x);
        setY(y);
        this.colonie = new Colonie();
        this.graphe = g;
        System.out.println("-NAISSANCE REINE-");
    }

    /**
     * Give birth for Soldiers.
     *
     * @param amount the amount
     */
    public void giveBirthSoldier(int amount)
    {
        for(int i=0; i<amount; i++)
        {
            Soldat soldat = new Soldat(this.x, this.y, colonie, graphe);
            theSoldiers.add(soldat);
            System.out.println("-NAISSANCE SOLDAT n°" + (i+1) + "-");
        }
    }

    /**
     * Gets the soldiers.
     *
     * @return une liste de soldats
     */
    public ArrayList<Soldat> getTheSoldiers() {
        return theSoldiers;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    public Colonie getColonie() {
        return colonie;
    }
}
