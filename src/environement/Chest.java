package environement;

public class Chest implements Decor {

    @Override
    public DecorType getType() {
        return DecorType.CHEST;
    }

    @Override
    public String toString() {
        return "_C";
    }

    @Override
    public boolean isTraversable() {
        return false;
    }
}
