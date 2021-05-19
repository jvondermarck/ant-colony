package V1.javaClass;
import java.util.ArrayList;

/**
 * Une Reine qui est unique à une seule colonie.
 */
public class Reine implements Fourmis {
    private final Colonie colonie;
    private final Graphe graphe;
    private int x;
    private int y;

    private final ArrayList<Soldat> theSoldiers = new ArrayList<>();

    /**
     * Crée une nouvelle Reine. Cette reine créera sa propre colonie.
     *
     * @param x sa coordonnée X (ligne) où elle sera affecté sur le graphe
     * @param y sa coordonnée Y (colonne) où elle sera affecté sur le graphe
     * @param g le graphe où réside la reine
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
     * La reine donne naissance a un ou des soldats.
     *
     * @param amount le nombre de soldat que l'on veut créer
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
     * Recevoir la liste de tous les soldats que la reine aura fait naitre
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

    /**
     * Recevoir sa colonie où elle réside.
     *
     * @return le numéro de sa colonie
     */
    public Colonie getColonie() {
        return colonie;
    }
}
