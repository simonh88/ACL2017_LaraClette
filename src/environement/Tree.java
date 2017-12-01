package environement;

public class Tree implements Decor {

    public Tree(){

    }

    @Override
    public int getType() {
        return Decor.TREE;
    }

    @Override
    public String toString() {
        return "TT";
    }
}
