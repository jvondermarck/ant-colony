package V1.javaClass;

/**
 * La classe modifiable {@code Colonie}.
 * <p>
 * Une colonie a un numéro de colonie
 * On peut savoir le nombre total de colonie
 */
public class Colonie {

    private static int nombreColonie = 0;
    private final int numColonie;

    /**
     * Instancie une nouvelle Colonie, et lui attribu un numéro.
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
