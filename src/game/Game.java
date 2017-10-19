package game;

import characters.Hero;

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
            Hero h = gameState.getHero();
            switch (key){
                case 'q'://Gauche
                    if(gameSpace.isValidPosition(gameState.getHero().getPosX(), gameState.getHero().getPosY()-1)){
                    h.setPosX(gameState.getHero().getPosX());
                    h.setPosY(gameState.getHero().getPosY()-1);
                }
                    break;
                case 's':
                    if(gameSpace.isValidPosition(gameState.getHero().getPosX()+1, gameState.getHero().getPosY()){
                    h.setPosX(gameState.getHero().getPosX()+1);
                    h.setPosY(gameState.getHero().getPosY());
                }

                    break;
                case 'd':
                    if(gameSpace.isValidPosition(gameState.getHero().getPosX(), gameState.getHero().getPosY()+1){
                    h.setPosX(gameState.getHero().getPosX());
                    h.setPosY(gameState.getHero().getPosY()+1);
                }

                    break;
                case 'z':
                    if(gameSpace.isValidPosition(gameState.getHero().getPosX()-1, gameState.getHero().getPosY())){
                        h.setPosX(gameState.getHero().getPosX()-1);
                        h.setPosY(gameState.getHero().getPosY());
                    }

                    break;
                case 'e':
                    break;
            }

            if(gameSpace.isChest(h.getPosX(), h.getPosY())){
                gameState.setVictory();
            }
        }
        System.out.println("\n\n====== VICTOIRE ==== ");
    }


    public boolean isValidPosition(int x, int y) {
        return gameSpace.isValidPosition(x, y);
    }

    public String toString(){
        return gameSpace.toString();

    }
}
