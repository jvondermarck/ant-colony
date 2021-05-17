public class Colonie {
    
    private static int nombreColonie = 0;
    private final int numColonie;

    // Paramètre de la fourmis
    private int evaporationParam;
    private int foodParam;
    private int pheromoneParam;

    public Colonie()
    {
        this.numColonie = nombreColonie + 1 ;
        nombreColonie++;
    }

    public int getEvaporationParam() {
        return evaporationParam;
    }

    public int getFoodParam() {
        return foodParam;
    }

    public int getPheromoneParam() {
        return pheromoneParam;
    }

    public void setEvaporationParam(int evaporationParam) {
        this.evaporationParam = evaporationParam;
    }

    public void setFoodParam(int foodParam) {
        this.foodParam = foodParam;
    }

    public void setPheromoneParam(int pheromoneParam) {
        this.pheromoneParam = pheromoneParam;
    }

    @Override
    public String toString() {
        return "Colonie n°" + numColonie;
    }
}
