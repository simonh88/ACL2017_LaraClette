package game;

public class Game {

    private final GameSpace gameSpace;
    private final GameState gameState;

    //O


    public Game() {
        this.gameSpace = new GameSpace();
        this.gameState = new GameState();
    }

    public void show() {
        System.out.println(gameSpace);
    }

    public void run(){
        while(gameState.isRunning()){
            //PRINT DE LETAT DU JEU
            System.out.println(toString());
            //DEMANDE DE COMMANDE DE MOUVEMENT
            
        }
    }


    public String toString(){
        return gameSpace.toString();
    }
}
