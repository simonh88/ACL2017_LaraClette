package environement;

public class Wall implements Decor {

    public Wall(){

    }

    @Override
    public int getType() {
        return Decor.WALL;
    }

    @Override
    public String toString() {
        return "WW";
    }
}
