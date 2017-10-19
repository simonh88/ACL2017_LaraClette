package environement;

public class Empty implements Decor {

    public Empty(){

    }

    @Override
    public int getType() {
        return Decor.EMPTY;
    }

    @Override
    public String toString() {
        return "__";
    }
}
