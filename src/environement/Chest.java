package environement;

public class Chest implements Decor {

    public Chest(){

    }

    @Override
    public int getType() {
        return Decor.CHEST;
    }

    @Override
    public String toString() {
        return "_C";
    }
}
