package game;

import java.util.Scanner;

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
            Scanner in = new Scanner(System.in);

            char key = in.next().charAt(0);
            switch (key){
                case 'q'://Gauche
                    gameSpace.isValidPosition(gameState.getHero().getPosX(), gameState.getHero().getPosY()-1);
                    break;
                case 's':
                    gameSpace.isValidPosition(gameState.getHero().getPosX()+1, gameState.getHero().getPosY());

                    break;
                case 'd':
                    gameSpace.isValidPosition(gameState.getHero().getPosX(), gameState.getHero().getPosY()+1);

                    break;
                case 'z':
                    gameSpace.isValidPosition(gameState.getHero().getPosX()-1, gameState.getHero().getPosY());

                    break;
                case 'e':
                    break;
            }
        }
    }


    public boolean isValidPosition(int x, int y) {
        return gameSpace.isValidPosition(x, y);
    }

    public String toString(){
        return gameSpace.toString(gameState.getHero().getPosX(), gameState.getHero().getPosY());

    }
}
