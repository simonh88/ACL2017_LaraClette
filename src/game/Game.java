package game;

import characters.Hero;
import engine.Cmd;
import environement.Room;

import java.util.Iterator;
import java.util.Scanner;

public class Game implements engine.Game{

    private final GameSpace gameSpace;
    private final GameState gameState;

    public Game() {
        this.gameSpace = new GameSpace();
        this.gameState = new GameState();
    }

    /**
     * faire evoluer le jeu suite a une commande
     *
     * @param commande
     */
    @Override
    public void evolve(Cmd commande) {

        Hero h = gameState.getHero();

        switch (commande) {
            case LEFT://Gauche
                if(gameSpace.isValidPosition(gameState.getHero().getPosX(), gameState.getHero().getPosY()-1)){
                    h.setPosX(gameState.getHero().getPosX()-1);
                    h.setPosY(gameState.getHero().getPosY());
                }
                break;
            case DOWN:
                if(gameSpace.isValidPosition(gameState.getHero().getPosX(), gameState.getHero().getPosY()+1)){
                    h.setPosX(gameState.getHero().getPosX());
                    h.setPosY(gameState.getHero().getPosY()+1);
                }

                break;
            case RIGHT:
                if(gameSpace.isValidPosition(gameState.getHero().getPosX()+1, gameState.getHero().getPosY())){
                    h.setPosX(gameState.getHero().getPosX()+1);
                    h.setPosY(gameState.getHero().getPosY());
                }

                break;
            case UP:
                if(gameSpace.isValidPosition(gameState.getHero().getPosX()-1, gameState.getHero().getPosY())){
                    h.setPosX(gameState.getHero().getPosX());
                    h.setPosY(gameState.getHero().getPosY()-1);
                }

                break;

        }
    }


    public boolean isValidPosition(int x, int y) {
        return gameSpace.isValidPosition(x, y);
    }

    public String toString(){
        return gameSpace.toString(gameState.getHero().getPosX(), gameState.getHero().getPosY());

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    public Room currentRoom() {
        return gameSpace.currentRoom();
    }

    public Hero getHero() {
        return gameState.getHero();
    }
}
