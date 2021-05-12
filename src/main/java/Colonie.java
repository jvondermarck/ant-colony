public class Colonie {
    
    private static int nombreColonie = 0;
    private final int numColonie;

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
