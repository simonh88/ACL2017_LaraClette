package utils;


public class Position {

    private final int x;
    private final  int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public boolean equals(Object o) {
        if (!(o instanceof Position)) return false;
        Position other = (Position) o;
        return x == other.x && y == other.y;
    }
}
