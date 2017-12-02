package environement;

public class Vase implements Decor {

    // Utile pour savoir si le pot a été cassé
    private boolean used;

    public Vase() {
        used = false;
    }

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

    @Override
    public boolean hasBeenUsed() {
        return used;
    }

    @Override
    public void setUsed() {
        used = true;
    }
}
