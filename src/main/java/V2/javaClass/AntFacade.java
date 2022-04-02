package V2.javaClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;

/**
 * AntFacade qui implémente AntFacadeController
 */
public class AntFacade implements AntFacadeController {
    private BitSet[][] grid;
    private Graphe graphe;
    private final long sleepingTime; // Vitesse de repos entre chaque seconde de durée

    private ArrayList<Soldat> theSoldiers; // La liste de tous les soldats crées qui permettra de les faire déplacer
    private ArrayList<Ouvrier> theWorkers; // La liste de tous les ouvriers crées qui permettra de les faire déplacer

    private Reine reine; // La reine qui va s'occuper de gérer la colonie
    private int width; // Largueur
    private int height; // Hauteur

    private int xColonie, yColonie; // Permet d'avoir la coordonnée
    private Noeud colonieCoord; // Coordonnée de la colonie
    private AntFacadeHistorique antHisto; // La classe qui servira à creer le fichier en .CSV.

    /**
     * Instantiates a new Ant facades
     */
    public AntFacade()
    {
        this.sleepingTime = 500;
    }

    /**
     * Instantiates a new Ant facade.
     *
     * @param temps the temps
     */
    public AntFacade(int temps)
    {
        this.sleepingTime = temps;
    }


    @Override
    public void createGrid(int width, int height) {
        this.width = width;
        this.height = height;

        graphe = new Graphe(this.height, this.width);

        this.grid = new BitSet[this.height][this.width];
        for(int x=0 ; x<this.grid.length; x++){
            for(int y=0 ; y<this.grid[x].length; y++){
                this.grid[x][y] = new BitSet(7);
            }
        }
    }

    @Override
    public void putObstacle(int row, int column) {
        if(row == this.xColonie && column == this.yColonie)
        {
            throw new IllegalArgumentException("Obstacle placé sur la fourmilière");
        }
        else if(grid[row][column].get(5))
        {
            throw new IllegalArgumentException("Obstacle placé sur de la nourriture");
        }
        else {
            this.grid[row][column].set(1); // car : cells[i][j].get(1) --> obstacle "O"
            graphe.mettreObstacle(row, column);
        }
    }

    @Override
    public void createColony(int row, int column) {
        this.xColonie = row; // Coordonnée de la colonie
        this.yColonie = column;
        this.grid[row][column].set(0); // car : cells[i][j].get(0) --> fourmilière "F"
        reine = new Reine(row, column, graphe); // On a juste une colonie = une reine
    }

    @Override
    public void createSoldiers(int amount) {
        reine.giveBirthSoldier(amount);
        theSoldiers = reine.getTheSoldiers(); // On récupere tous les soldats crées
        for(Soldat ignored : this.theSoldiers){
            this.grid[this.xColonie][this.yColonie].set(2); // On met en jaune la case
        }
    }

    @Override
    public void createWorkers(int amount) {
        reine.giveBirthWorker(amount);
        theWorkers = reine.getTheWorkers(); // On récupere tous les workers crées
        for(Ouvrier ignored : this.theWorkers){
            this.grid[this.xColonie][this.yColonie].set(3);
        }
    }
     /*
        enregistrer dans un fichier au format .csv une trace des états successifs du système indiquant pour
        chaque fourmi le noeud occupé, l’état de ce noeud et de ses voisins (nourriture, phéromones) et la
        quantité de nourriture éventuellement transportée par la fourmi.
     */

    @Override
    public BitSet[][] play(int duration, boolean record)
    {
        try{
            if(record) {
                  antHisto.startFile(); // Si on est au lancement du jeu, on va créer l'en-tête
            }

            for(int i = 1 ;i <= duration; i++)
            {
                for(int x=0;x<height;x++)
                {
                    for(int y=0;y<width;y++)
                    {
                        this.grid[x][y].clear(2); // Effacer emplacement Soldat de duration-1
                        this.grid[x][y].clear(3); // Effacer emplacement Ouvrier de duration-1
                        this.grid[x][y].clear(4);

                        if(this.grid[x][y].get(5) && !graphe.getEstNourriture()[x][y]) // Si la cellule était nourriture et que dans le graphe ya plus de nourriture
                        {
                            this.grid[x][y].clear(5); // On efface sur l'affichage la nourriture
                        }

                        if(graphe.getQuantityPheromone()[x][y] >= 1) // Si la cellule contient au moins 1 phéromone
                        {
                            this.grid[x][y].set(6); // On affiche les pheromones
                        } else {
                            this.grid[x][y].clear(6); // On efface sur l'affichage la nourriture
                        }

                        // On s'occupe de gérer les itérations de pheromones
                        int nbEvaporation = reine.getColonie().getEvaporationParam();
                        int[][] tabQuantityPheromone = graphe.getQuantityPheromone();
                        if(nbEvaporation!=0) // On fait ette condition pour pas avoir l'erreur de division par 0
                        {
                            if(i % nbEvaporation == 0) // si 16%8=0 bah on suppr les pheromones
                            {
                                if(tabQuantityPheromone[x][y] >= nbEvaporation)
                                {
                                    tabQuantityPheromone[x][y] -= nbEvaporation;
                                    graphe.setQuantityPheromone(tabQuantityPheromone);
                                } else { // Si la quantité est < a la quantité de stockage de la fourmi, elle prend ce qu'il reste
                                    tabQuantityPheromone[x][y] = 0; // Vu que la fourmi a tout pris la nourriture, il reste plus rien
                                    graphe.setQuantityPheromone(tabQuantityPheromone);
                                    this.grid[x][y].clear(6); // On efface sur l'affichage la nourriture
                                }
                            }
                        }
                    }
                }

                if(this.theSoldiers != null)
                {
                    for(Soldat s : this.theSoldiers){
                        s.randomDirection(); // On cherche toutes les aretes adjacentes de la fourmi soldat
                        this.grid[s.getX()][s.getY()].set(2);
                        //System.out.println(s); // On affiche sa position, son numéro de soldat, et sa colonie
                    }
                }

                if(this.theWorkers != null)
                {
                    for(Ouvrier o : this.theWorkers){
                        if(o.isEtatRetour() && !o.doitRetravailler()) // Si elle retourne a la fourmiliere
                        {
                            o.cheminRetour(); // On fait le chemin inverse de son parcours initial
                            this.grid[o.getX()][o.getY()].set(4); // On met son état en mode fourmis retour
                            // On ne doit pas poser des pheromones sur la fourmiliere et sur la nourriture
                            if(!this.grid[xColonie][yColonie].get(4) && !graphe.getEstNourriture()[o.getX()][o.getY()])
                            {
                                graphe.getQuantityPheromone()[o.getX()][o.getY()] = reine.getColonie().getPheromoneParam(); // On pose des phéromones
                            }
                        } else
                        { // Si elle cherche de la nourriture
                            o.randomDirection(); // On cherche toutes les aretes adjacentes
                            this.grid[o.getX()][o.getY()].set(3); // On met en jaune la case
                        }
                        //System.out.println(o); // On affiche sa position, son numéro de soldat, et sa colonie
                    }
                }

                if(record){
                    antHisto.iteration(this.grid, this.theWorkers, this.theSoldiers); // On va créer l'affichage de l'íteration qui vient de se passer
                }
                Thread.sleep(this.sleepingTime);
            }
            if (record){
                antHisto.closeFile(); // on ferme la connexion d'écriture
            }
        }
        catch (InterruptedException | IOException e)
        {
            e.printStackTrace();
        }
        return this.grid;
    }

    @Override
    public void putFood(int row, int column, int quantity) {
        if(row == this.xColonie && column == this.yColonie)
        {
            throw new IllegalArgumentException("Nourriture placé sur la fourmilière");
        }
        else if (grid[row][column].get(1)) {
            throw new IllegalArgumentException("Nourriture placé sur un obstacle");
        }
        else {
            this.grid[row][column].set(5); // car : cells[i][j].get(5) --> obstacle "N"
            graphe.mettreNourriture(row, column, quantity);
        }
    }

    @Override
    public void setParameters(int evaporationParam, int foodParam, int pheromoneParam) {
        reine.setParametreColonie(evaporationParam,foodParam,pheromoneParam);
    }

    @Override
    public void setAntFile(String antLogFile) {
        antHisto = new AntFacadeHistorique(width, height, grid, antLogFile, graphe); // On instancie la classe AntFacadeHistorique
    }
}
