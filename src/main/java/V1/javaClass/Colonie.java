package V1.javaClass;

/**
 * La classe modifiable {@code Colonie}.
 *
 * Une colonie est
 */
public class Colonie {

    private static int nombreColonie = 0;
    private final int numColonie;


    /**
     * Instantiates a new Colonie.
     */
    public Colonie()
    {
        this.numColonie = nombreColonie + 1 ;
        nombreColonie++;
    }

    @Override
    public String toString() {
        return "Colonie n°" + numColonie;
    }
}
