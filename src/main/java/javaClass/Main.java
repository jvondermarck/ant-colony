package javaClass;
public class Main {
    public static void main(String[] args)
    {
        //utilisationInterface();
        //utilisationSansInterface();
        MyOwnTest(new AntFacade(500));
    }

    /*
          METTRE DANS LA CLASSE  public AntFacade() { this.sleepingTime = votreValeur};
          pour pouvoir voir l'interface graphique OU utiliser la surchage constructeur
     */

    public static void MyOwnTest(AntFacadeController controller)
    {
        controller.createGrid( 15,15 );
        controller.createColony( 8,8 );

        controller.putObstacle( 12,10 );
        controller.putObstacle( 10,11 );
        controller.putObstacle( 10,9 );
        controller.putObstacle( 12,14 );

        controller.putFood(7,6,15);
        controller.setParameters(2,10,3);

        controller.createWorkers(1);
        controller.createSoldiers( 0 );

        Display w = new Display( 15, 15, 40 );
        for( int i = 0; i < 30; i++)
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
