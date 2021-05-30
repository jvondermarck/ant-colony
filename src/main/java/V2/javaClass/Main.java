package V2.javaClass;

public class Main {
    public static void main(String[] args)
    {
        MyOwnTest(new AntFacade());
    }

    public static void MyOwnTest(AntFacadeController controller)
    {
        controller.createGrid( 6,6 );
        controller.createColony( 2,2 );

        controller.putObstacle( 1,1 );
        controller.putObstacle( 3,0 );
        controller.putObstacle( 3,1 );
        controller.putObstacle( 3,2 );
//        controller.putObstacle( 2,3 );
//        controller.putObstacle( 3,4 );
//        controller.putObstacle( 3,5 );

        controller.putFood(0,0,15);
        controller.putFood(1,4,15);
        controller.putFood(2,5,15);
        controller.putFood(5,3,15);
        controller.setParameters(1,20,10);

        controller.createWorkers(1);
        controller.createSoldiers( 2);

        Display w = new Display( 6, 6, 40 );
        controller.setAntFile("TraceFourmis.csv");
        for( int i = 0; i < 100; i++)
            w.update( controller.play( 1, true ) );
    }
}
