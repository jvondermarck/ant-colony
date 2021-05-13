import java.util.ArrayList;

public class Reine extends Fourmis {
    private final Colonie colonie;
    private final Graphe graphe;
    private final int x;
    private final int y;
    private final int duration;

    private final ArrayList<Soldat> theSoldiers = new ArrayList<>();

    public Reine(int x, int y, Graphe g)
    {
        super(x, y);
        this.x = x;
        this.y = y;
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
}
