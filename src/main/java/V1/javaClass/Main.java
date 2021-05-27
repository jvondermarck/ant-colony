package V1.javaClass;
public class Main {
    public static void main(String[] args)
    {
        MyOwnTest(new AntFacade()); // Surcharger le constructeur pour augmenter le temps ou le diminuer
    }

    public static void MyOwnTest(AntFacadeController controller)
    {
        controller.createGrid( 20,20 );
        controller.createColony( 10,10 );

        controller.putObstacle( 12,10 );
        controller.putObstacle( 10,11 );
        controller.putObstacle( 10,9 );
        controller.putObstacle( 12,14 );

        controller.createSoldiers( 5 );

        Display w = new Display( 20, 20, 50 );
        for( int i = 0; i < 50; i++)
            w.update( controller.play( 1, false ) );

    }
}
