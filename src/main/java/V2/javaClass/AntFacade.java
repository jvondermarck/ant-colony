package V2.javaClass;

import java.util.ArrayList;
import java.util.BitSet;

public class AntFacade implements AntFacadeController {
    private BitSet[][] grid;
    private Graphe graphe;

    private ArrayList<Soldat> theSoldiers;
    private ArrayList<Ouvrier> theWorkers;

    private Reine reine;
    private int width; // Largueur
    private int height; // Hauteur
    private final long sleepingTime; // Vitesse de repos entre chaque seconde de durée

    private int xColonie, yColonie;

    public AntFacade()
    {
        this.sleepingTime = 500;
    }
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
        this.xColonie = row;
        this.yColonie = column;
        this.grid[row][column].set(0); // car : cells[i][j].get(0) --> fourmilière "F"
        reine = new Reine(row, column, graphe); // On a juste une colonie = une reine
        //this.theColonies.add(new Reine(row,column));
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

    @Override
    public BitSet[][] play(int duration, boolean record)
    {
        try{
            for(int i = 0 ;i < duration; i++)
            {
                for(int y=0;y<height;y++){
                    for(int x=0;x<width;x++)
                    {
                        this.grid[y][x].clear(2); // Effacer emplacement Soldat de duration-1
                        this.grid[y][x].clear(3); // Effacer emplacement Soldat de duration-1
                        this.grid[y][x].clear(4);

                        if(this.grid[y][x].get(5) && !graphe.getEstNourriture()[y][x]) // Si la cellule était nourriture et que dans le graphe ya plus de nourriture
                        {
                            this.grid[y][x].clear(5); // On efface sur l'affichage la nourriture
                        }

                        // On s'occupe de gérer les itérations de pheromones
                        int nbEvaporation = reine.getColonie().getEvaporationParam();
                        if(nbEvaporation!=0) // On fait ette condition pour pas avoir l'erreur de division par 0
                        {
                            if(i % nbEvaporation == 0 && i != 0) // si 16%8=0 bah on suppr les pheromones
                            {
                                if(graphe.getQuantityPheromone()[y][x] >= nbEvaporation)
                                {
                                    graphe.getQuantityPheromone()[y][x] -= nbEvaporation;
                                } else {
                                    graphe.getQuantityPheromone()[y][x] = 0;
                                }
                            }
                        }
                    }
                }

                if(this.theSoldiers != null)
                {
                    for(Soldat s : this.theSoldiers){
                        s.randomDirection(this.graphe, s, reine); // On cherche toutes les aretes adjacentes
                        this.grid[s.getX()][s.getY()].set(2); // On met en jaune la case
                        s.recherchePositionActuel(s.getX(), s.getY()); // On cherche le numéro du noeud où il se trouve
                        System.out.println(s); // On affiche sa position, son numéro de soldat, et sa colonie
                    }
                }

                if(this.theWorkers != null)
                {
                    for(Ouvrier o : this.theWorkers){
                        if(o.isEtatRetour() && !o.doitRetravailler()) // Si elle retourne a la fourmiliere
                        {
                            o.cheminRetour(o); // On fait le chemin inverse
                            this.grid[o.getX()][o.getY()].set(4); // On met son état en mode fourmis retour
                            graphe.getQuantityPheromone()[o.getX()][o.getY()] = reine.getColonie().getPheromoneParam(); // On pose des phéromones
                        } else
                        { // Si elle cherche de la nourritire
                            o.randomDirection(this.graphe, o, reine); // On cherche toutes les aretes adjacentes
                            this.grid[o.getX()][o.getY()].set(3); // On met en jaune la case
                        }
                        o.recherchePositionActuel(o.getX(), o.getY()); // On cherche le numéro du noeud où il se trouve
                        System.out.println(o); // On affiche sa position, son numéro de soldat, et sa colonie
                    }
                }

                Thread.sleep(this.sleepingTime);
            }
        }
        catch (InterruptedException e)
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
        System.out.println();
    }
}
