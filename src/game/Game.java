package game;

public class Game {

    private final GameSpace gameSpace;
    private final GameState gameState;

    public Game() {
        this.gameSpace = new GameSpace();
        this.gameState = new GameState();
    }

    public void show() {
        System.out.println(gameSpace);
    }

    public boolean isValidPosition(int x, int y) {
        return gameSpace.isValidPosition(x, y);
    }
}
