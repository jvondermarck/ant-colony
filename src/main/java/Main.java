public class Main {
    public static void main(String[] args)
    {
        utilisationInterface();
        //utilisationSansInterface();
    }

    public static void utilisationInterface()
    {
        AntFacade ant = new AntFacade(50,750);
        ant.createGrid(20,20);

        ant.createColony(9,9);
        ant.createSoldiers(3);

        ant.putObstacle(8,8);
        ant.putObstacle(9,10);
//        ant.putObstacle(9,8);
//        ant.putObstacle(8,9);
//        ant.putObstacle(10,10);
//        ant.putObstacle(10,11);

        ant.play(10,false);
    }

    public static void utilisationSansInterface()
    {
        Graphe graphe = new Graphe(3,3);
        Reine reine = new Reine(1,1,graphe);
        graphe.mettreObstacle(1,0);
        graphe.mettreObstacle(1,2);
        reine.giveBirth(1);
        reine.deplacementSoldat();
    }
}
