package environement;

public class Chest implements Decor {

    // Utile pour faire la différence entre un coffre fermé et un coffre qui a été ouvert
    // (et pour changer de sprite en conséquence)
    private boolean used;

    @Override
    public DecorType getType() {
        return DecorType.CHEST;
    }

    @Override
    public String toString() {
        return "_C";
    }

    @Override
    public boolean isTraversable() {
        return true;
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
