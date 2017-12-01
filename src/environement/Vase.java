package environement;

public class Vase implements Decor {

    @Override
    public DecorType getType() {
        return DecorType.VASE;
    }

    @Override
    public String toString() {
        return "TT";
    }

    @Override
    public boolean isTraversable() {
        return false;
    }
}
