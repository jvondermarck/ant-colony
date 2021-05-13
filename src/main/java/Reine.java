import java.util.ArrayList;

public class Reine extends Fourmis {
    private final Colonie colonie;
    private final Graphe graphe;
    private final int x;
    private final int y;

    private final ArrayList<Soldat> theSoldiers = new ArrayList<>();

    public Reine(int x, int y, Graphe g)
    {
        super(x, y);
        this.x = x;
        this.y = y;
        this.colonie = new Colonie();
        this.graphe = g;
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

    public ArrayList<Soldat> getTheSoldiers() {
        return theSoldiers;
    }
}
