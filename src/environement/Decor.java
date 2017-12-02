package environement;

public interface Decor {

    DecorType getType();

    String toString();

    boolean isTraversable();

    boolean hasBeenUsed();

    void setUsed();
}
