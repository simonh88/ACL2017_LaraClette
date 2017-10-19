package environement;

public class Lava implements Decor {

    public Lava(){

    }


    @Override
    public int getType() {
        return Decor.LAVA;
    }

    @Override
    public String toString() {
        return "LL";
    }
}
