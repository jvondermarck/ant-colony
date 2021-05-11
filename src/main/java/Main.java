public class Main {
    public static void main(String[] args) {
        AntFacade ant = new AntFacade(30,1500);
        ant.createGrid(3,3);

        ant.createColony(1,1);
        ant.createSoldiers(1);

//        for(int i = 0 ; i < 9 ; i ++){
//            ant.putObstacle(i,i*2);
//            ant.putObstacle(9-i,10+i);
//        }

        ant.play(60,false);
    }
}
