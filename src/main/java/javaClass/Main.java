package javaClass;
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
        controller.createGrid( 20,20 );
        controller.createColony( 10,10 );

//        controller.putObstacle( 1,1 );
//        controller.putObstacle( 3,0 );
//        controller.putObstacle( 3,1 );
//        controller.putObstacle( 3,2 );
//        controller.putObstacle( 2,3 );
//        controller.putObstacle( 3,4 );
//        controller.putObstacle( 3,5 );

        controller.putFood(0,0,15);
        controller.putFood(0,19,15);
        controller.putFood(19,0,15);
        controller.putFood(19,19,15);
        controller.setParameters(2,8,5);

        controller.createWorkers(5);
        controller.createSoldiers( 0 );

        Display w = new Display( 20, 20, 40 );
        for( int i = 0; i < 50; i++)
            w.update( controller.play( 1, false ) );
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
