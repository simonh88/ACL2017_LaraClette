package environement;

public class Tree implements Decor {

    @Override
    public DecorType getType() {
        return DecorType.TREE;
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
