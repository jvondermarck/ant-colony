public class Main {
    public static void main(String[] args) {
        AntFacade ant = new AntFacade(40,750);
        ant.createGrid(20,20);

        ant.createColony(9,9);
        ant.createSoldiers(3);

//        ant.putObstacle(8,8);
//        ant.putObstacle(9,10);
//        ant.putObstacle(9,8);
//        ant.putObstacle(8,9);
//        ant.putObstacle(10,10);
//        ant.putObstacle(10,11);

        ant.play(10,false);
    }
}
