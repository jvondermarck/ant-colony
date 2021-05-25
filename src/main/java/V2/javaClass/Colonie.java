package V2.javaClass;

/**
 * La classe modifiable {@code Colonie}.
 *
 * Une colonie est
 */
public class Colonie {


    private static int nombreColonie = 0;
    private final int numColonie;

    // Paramètre de la fourmis
    private int evaporationParam;
    private int foodParam;
    private int pheromoneParam;

    private int quantityFood; // Quantité de nourriture ramené par les fourmis ouvrieres

    /**
     * Instantiates a new Colonie.
     */
    public Colonie()
    {
        this.numColonie = nombreColonie + 1 ;
        nombreColonie++;
        this.quantityFood = 0;
    }

    /**
     * Gets evaporation param.
     *
     * @return the evaporation param
     */
    public int getEvaporationParam() {
        return evaporationParam;
    }

    /**
     * Gets food param.
     *
     * @return the food param
     */
    public int getFoodParam() {
        return foodParam;
    }

    /**
     * Renvoie la quantité de phéromones déposé par les fourmis d'une colonie
     *
     * @return pheromoneParam : entier contenant quantité de phéromone déposée par les fourmis lors de leur passage dans une cellule.
     */
    public int getPheromoneParam() {
        return pheromoneParam;
    }

    /**
     * Affecte le nombre d'itération avant de retirer les phéromones d'une colonie pour l'attribut en paramètre
     *
     * @param evaporationParam rythme d'évaporation des phéromones. La valeur par défaut est 1 par itération.
     */
    public void setEvaporationParam(int evaporationParam) {
        this.evaporationParam = evaporationParam;
    }

    /**
     * Mettre les parametres de nourritures
     *
     * @param foodParam quantité maximale de nourriture que peut transporter une fourmi ouvrière.
     */
    public void setFoodParam(int foodParam) {
        this.foodParam = foodParam;
    }

    /**
     * Mettre les parametres de pheromones
     *
     * @param pheromoneParam quantité de phéromone déposée par les fourmis lors de leur passage dans une cellule.
     */
    public void setPheromoneParam(int pheromoneParam) {
        this.pheromoneParam = pheromoneParam;
    }

    /**
     * Redéfinition de la méthode toString(), on affiche le numéro de colonie, et sa quantité de nourriture stockées
     */
    @Override
    public String toString() {
        return "Colonie n°" + numColonie + " -> à un stock de " + quantityFood +  " nourriture stockée";
    }

    public void setQuantityFood(int quantityFood) {
        this.quantityFood += quantityFood;
    }
}
