package V2.javaClass;

/**
 * La classe abstraite modifiable {@code Figure}.
 * <p>
 *
 */

public interface FourmisMove extends Fourmis {

    void randomDirection(Graphe g, Object o, Reine r);

    int getX();

    int getY();

    @Override
    void setX(int x);

    @Override
    void setY(int y);
}
