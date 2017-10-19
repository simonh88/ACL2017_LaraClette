package game;

public class Game {

    private final GameSpace gameSpace;

    public Game() {
        this.gameSpace = new GameSpace();
    }

    public void show() {
        System.out.println(gameSpace);
    }
}
