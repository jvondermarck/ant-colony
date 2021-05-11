public enum  Direction {
    NORTH(0,-1),
    WEST(1,0),
    EAST(-1,0),
    SOUTH(0,1);

    private int x,y;

    Direction(int x,int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }
}
