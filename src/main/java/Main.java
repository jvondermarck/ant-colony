public class Main {
    public static void main(String[] args) {
        AntFacade ant = new AntFacade(40,750);
        ant.createGrid(20,20);

        ant.createColony(9,9);
        ant.createSoldiers(1);

        ant.putObstacle(4,8);
        ant.putObstacle(7,12);
        ant.putObstacle(1,1);
        ant.putObstacle(1,2);

        ant.play(10,false);
    }
}
