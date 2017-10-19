package environement;

public abstract class Decor {
    protected int x;
    protected int y;

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
}
