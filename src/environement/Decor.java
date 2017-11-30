package environement;

public interface Decor {
    public static final int EMPTY = 0, CHEST = 1, WALL = 2, LAVA = 3, GRASS = 4;

    public int getType();

    public String toString();
}
