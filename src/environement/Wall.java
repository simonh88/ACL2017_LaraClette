package environement;

public class Wall implements Decor {

    @Override
    public DecorType getType() {
        return DecorType.WALL;
    }

    @Override
    public String toString() {
        return "WW";
    }

    @Override
    public boolean isTraversable() {
        return false;
    }
}
