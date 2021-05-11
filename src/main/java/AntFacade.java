import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;

public class AntFacade implements AntFacadeController {
    private BitSet[][] grid;

    ArrayList<Reine> theColonies = new ArrayList<Reine>();
    ArrayList<Soldat> theSoldiers = new ArrayList<Soldat>();

    private int width; // Largueur
    private int height; // Hauteur
    private int cellSize; // Taille de la cellule
    private long sleepingTime; // Vitesse de repos entre chaque seconde de durée

    public AntFacade(int cellSize, long sleepingTime)
    {
        this.cellSize = cellSize;
        this.sleepingTime = sleepingTime;
    }

    @Override
    public void createGrid(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new BitSet[this.height][this.width];

        for(int x=0 ; x<this.width; x++){
            for(int y=0 ; y<this.height; y++){
                this.grid[y][x] = new BitSet(7);
            }
        }
    }

    @Override
    public void putObstacle(int row, int column) {
        this.grid[row][column].set(1); // car : cells[i][j].get(1) --> obstacle "O"
    }

    @Override
    public void createColony(int row, int column) {
        this.grid[row][column].set(0); // car : cells[i][j].get(0) --> fourmilière "F"
        this.theColonies.add(new Reine(row,column));
    }

    @Override
    public void createSoldiers(int amount) {
        for(int i=0; i<amount; i++)
        {
            for (Reine r:this.theColonies) {
                this.theSoldiers.add(new Soldat(r));
            }
        }
    }

    @Override
    public BitSet[][] play(int duration, boolean record)
    {
        Display display = new Display(this.height, this.width,this.cellSize);
        try{
            for(int i = 0 ;i < duration; i++)
            {
                for(int y=0;y<height;y++){
                    for(int x=0;x<width;x++){
                        this.grid[y][x].clear(2); // Effacer emplacement Soldat de duration-1
                    }
                }

                for(Soldat s : this.theSoldiers){
                    s.randomDirection(this.grid);
                    this.grid[s.getX()][s.getY()].set(2);
                }

                display.update(this.grid);

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

    }

    @Override
    public void setParameters(int evaporationParam, int foodParam, int pheromoneParam) {
        // Version 2
    }


    @Override
    public void createWorkers(int amount) {

    }

    @Override
    public void setAntFile(String antLogFile) {

    }
}
