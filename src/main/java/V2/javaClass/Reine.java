package V2.javaClass;

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
    private final ArrayList<Ouvrier> theWorkers = new ArrayList<>();

    /**
     * Crée une nouvelle Reine. Cette reine créera sa propre colonie.
     *
     * @param x sa coordonnée X (ligne) où elle sera affecté sur le graphe
     * @param y sa coordonnée Y (colonne) où elle sera affecté sur le graphe
     * @param g le graphe où réside la reine
     */
    public Reine(int x, int y, Graphe g)
    {
        // Si l'emplacement de la reine est négative, alors on lance un message d'erreur
        if(x<0 || y<0)
            throw new NumberFormatException("Votre valeur est négative, veuillez inscrire que des nombres positifs");

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
        // Si le nbr de soldat est négative, alors on lance un message d'erreur
        if(amount<0)
            throw new NumberFormatException("Votre valeur est négative, veuillez inscrire que des nombres positifs");

        for(int i=0; i<amount; i++)
        {
            Soldat soldat = new Soldat(this.x, this.y, colonie, graphe);
            theSoldiers.add(soldat);
            System.out.println("-NAISSANCE SOLDAT n°" + (i+1) + "-");
        }
    }

    /**
     * La reine donne naissance a un ou des ouvriers.
     *
     * @param amount le nombre de soldat que l'on veut créer
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

    /**
     * Mettre des parametres à la colonie dde la fourmilière
     *
     * @param evaporationParam le temps d'evaporation
     * @param foodParam        la quantite de nourriture qu une fourmis peut stocker
     * @param pheromoneParam   la quantite de pheromones qu'elle libere sur les cellules
     */
    public void setParametreColonie(int evaporationParam, int foodParam, int pheromoneParam)
    {
        this.colonie.setFoodParam(foodParam);
        this.colonie.setEvaporationParam(evaporationParam);
        this.colonie.setPheromoneParam(pheromoneParam);
    }

    /**
     * Recevoir la liste de tous les soldats que la reine aura fait naitre
     *
     * @return une liste de soldats
     */
    public ArrayList<Soldat> getTheSoldiers() {
        return theSoldiers;
    }

    /**
     * Recevoir la liste de tous les ouvriers que la reine aura fait naitre
     *
     * @return une liste d'ouveiers
     */
    public ArrayList<Ouvrier> getTheWorkers() {
        return theWorkers;
    }

    /**
     * Mettre la coordonné x
     */
    @Override
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Mettre la coordonné y
     */
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
