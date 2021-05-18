package javaClass;

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
        } else {
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
        for(Soldat s : this.theSoldiers){
            this.grid[this.xColonie][this.yColonie].set(2); // On met en jaune la case
        }
    }

    @Override
    public void createWorkers(int amount) {
        reine.giveBirthWorker(amount);
        theWorkers = reine.getTheWorkers(); // On récupere tous les workers crées
        for(Ouvrier o : this.theWorkers){
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
                        //this.grid[y][x].clear(3); // Effacer emplacement Soldat de duration-1

                        // On s'occupe de gérer les itérations de pheromones
                        int nbEvaporation = reine.getColonie().getEvaporationParam();
                        if(i % nbEvaporation == 0) // si 16%8=0 bah on suppr les pheromones
                        {
                            if(graphe.getTaille()[y][x] >= nbEvaporation)
                            {
                                graphe.getTaille()[y][x] -= nbEvaporation;
                            } else {
                                graphe.getTaille()[y][x] = 0;
                            }
                        }
                    }
                }

                for(Soldat s : this.theSoldiers){
                    s.randomDirection(this.graphe, s, reine); // On cherche toutes les aretes adjacentes
                    this.grid[s.getX()][s.getY()].set(2); // On met en jaune la case
                    s.recherchePositionActuel(s.getX(), s.getY()); // On cherche le numéro du noeud où il se trouve
                    System.out.println(s); // On affiche sa position, son numéro de soldat, et sa colonie
                }

                for(Ouvrier o : this.theWorkers){
                    o.randomDirection(this.graphe, o, reine); // On cherche toutes les aretes adjacentes
                    this.grid[o.getX()][o.getY()].set(3); // On met en jaune la case
                    o.recherchePositionActuel(o.getX(), o.getY()); // On cherche le numéro du noeud où il se trouve
                    System.out.println(o); // On affiche sa position, son numéro de soldat, et sa colonie
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
        this.grid[row][column].set(5); // car : cells[i][j].get(5) --> obstacle "N"
        graphe.mettreNourriture(row, column, quantity);
    }

    @Override
    public void setParameters(int evaporationParam, int foodParam, int pheromoneParam) {
        reine.setParametreColonie(evaporationParam,foodParam,pheromoneParam);
    }

    @Override
    public void setAntFile(String antLogFile) {

    }
}
