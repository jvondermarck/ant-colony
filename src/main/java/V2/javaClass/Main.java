package V2.javaClass;

public class Main {
    public static void main(String[] args)
    {
        //utilisationInterface();
        //utilisationSansInterface();
        MyOwnTest(new AntFacade(1000));
    }

    /*
          METTRE DANS LA CLASSE  public AntFacade() { this.sleepingTime = votreValeur};
          pour pouvoir voir l'interface graphique OU utiliser la surchage constructeur
     */

    public static void MyOwnTest(AntFacadeController controller)
    {
        controller.createGrid( 6,6 );
        controller.createColony( 3,3 );

//        controller.putObstacle( 1,1 );
//        controller.putObstacle( 3,0 );
//        controller.putObstacle( 3,1 );
//        controller.putObstacle( 3,2 );
//        controller.putObstacle( 2,3 );
//        controller.putObstacle( 3,4 );
//        controller.putObstacle( 3,5 );

        controller.putFood(0,0,15);
        controller.putFood(1,4,15);
        controller.putFood(2,5,15);
        controller.putFood(5,3,15);
        controller.setParameters(5,20,10);

        controller.createWorkers(3);
        controller.createSoldiers( 3);

        Display w = new Display( 6, 6, 40 );
        controller.setAntFile("TraceFourmis.csv");
        for( int i = 0; i < 30; i++)
            w.update( controller.play( 1, true ) );
    }

    public static void utilisationInterface()
    {
        AntFacade ant = new AntFacade();
        ant.createGrid(20,20);

        ant.createColony(9,9);
        ant.createSoldiers(3);

        ant.putObstacle(8,8);
        ant.putObstacle(9,10);
        ant.putObstacle(9,8);
//        ant.putObstacle(8,9);
//        ant.putObstacle(10,10);
        ant.putObstacle(10,11);

        ant.play(10,false);
    }

    public static void utilisationSansInterface()
    {
        Graphe graphe = new Graphe(3,3);
        Reine reine = new Reine(1,1,graphe);
        graphe.mettreObstacle(1,0);
        graphe.mettreObstacle(1,2);
        reine.giveBirthSoldier(1);
        //reine.deplacementSoldat();
    }

}
