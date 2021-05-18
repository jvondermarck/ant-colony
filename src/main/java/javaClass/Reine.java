package javaClass;
import java.util.ArrayList;

/**
 * The type Reine.
 */
public class Reine implements Fourmis {
    private final Colonie colonie;
    private final Graphe graphe;
    private int x;
    private int y;
    private final int duration; // Uniquement pour un test

    private final ArrayList<Soldat> theSoldiers = new ArrayList<>();
    private final ArrayList<Ouvrier> theWorkers = new ArrayList<>();

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
        this.duration = 10;
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
     * Give birth for workers.
     *
     * @param amount the amount
     */
    public void giveBirthWorker(int amount)
    {
        for(int i=0; i<amount; i++)
        {
            Ouvrier ouvrier = new Ouvrier(this.x, this.y, colonie, graphe);
            theWorkers.add(ouvrier);
            System.out.println("-NAISSANCE OUVRIER n°" + (i+1) + "-");
        }
    }

    public void setParametreColonie(int evaporationParam, int foodParam, int pheromoneParam)
    {
        this.colonie.setFoodParam(foodParam);
        this.colonie.setEvaporationParam(evaporationParam);
        this.colonie.setPheromoneParam(pheromoneParam);
    }


    // Méthode simplement utiliser pour des tests ...
    public void deplacementSoldat() {
        for(int i=0; i<duration; i++)
        {
            for(Soldat s : this.theSoldiers){
                s.randomDirection(this.graphe, s, Reine.this);
                s.recherchePositionActuel(s.getX(), s.getY());
                System.out.println(s);
            }
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
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

    public ArrayList<Ouvrier> getTheWorkers() {
        return theWorkers;
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
