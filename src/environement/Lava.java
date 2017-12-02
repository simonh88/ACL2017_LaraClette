package environement;

public class Lava implements Decor {

    @Override
    public DecorType getType() {
        return DecorType.LAVA;
    }

    @Override
    public String toString() {
        return "LL";
    }

    @Override
    public boolean isTraversable() {
        return false;
    }

    @Override
    public boolean hasBeenUsed() {
        return false;
    }

    @Override
    public void setUsed() {

    }
}
