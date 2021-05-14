import java.util.ArrayList;

public class Reine implements Fourmis {
    private final Colonie colonie;
    private final Graphe graphe;
    private int x;
    private int y;
    private final int duration;

    private final ArrayList<Soldat> theSoldiers = new ArrayList<>();

    public Reine(int x, int y, Graphe g)
    {
        setX(x);
        setY(y);
        this.colonie = new Colonie();
        this.graphe = g;
        this.duration = 10;
        System.out.println("-NAISSANCE REINE-");
    }

    public void giveBirth(int amount)
    {
        for(int i=0; i<amount; i++)
        {
            Soldat soldat = new Soldat(this.x, this.y, colonie, graphe);
            theSoldiers.add(soldat);
            System.out.println("-NAISSANCE SOLDAT n°" + (i+1) + "-");
        }
    }

    public void deplacementSoldat() {
        for(int i=0; i<duration; i++)
        {
            for(Soldat s : this.theSoldiers){
                s.randomDirection(this.graphe);
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
}
