public class Soldat extends Fourmis{
    private Reine colonie;
    public Soldat(Reine colonie) {
        super(colonie.getX(),colonie.getY());
        this.colonie = colonie;
    }
}