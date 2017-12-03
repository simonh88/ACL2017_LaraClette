package environement;

public class Bridge implements Decor{
    @Override
    public DecorType getType() {
        return DecorType.BRIDGE;
    }

    @Override
    public String toString() {
        return "~~";
    }

    @Override
    public boolean isTraversable() {
        return true;
    }

    @Override
    public boolean hasBeenUsed() {
        return false;
    }

    @Override
    public void setUsed() {

    }
}
