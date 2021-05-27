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
        // Si l'emplacement de la reine est négative, alors on lance un message d'erreur
        if(x<0 || y<0)
            throw new NumberFormatException("Votre valeur est négative, veuillez inscrire que des nombres positifs");

        setX(x);
        setY(y);
        this.colonie = new Colonie();
        this.graphe = g;
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
