package environement;

public class Water implements Decor{
    @Override
    public DecorType getType() {
        return DecorType.WATER;
    }

    @Override
    public String toString() {
        return "~~";
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
