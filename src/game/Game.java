package game;

import characters.Hero;
import characters.Monster;
import engine.Cmd;
import environement.Chest;
import environement.Room;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Game implements engine.Game {

    private final GameSpace gameSpace;
    private final GameState gameState;

    private boolean isFinished;

    public Game() {
        this.gameSpace = new GameSpace();
        this.gameState = new GameState();
        isFinished = false;
    }

    public GameState getGameState(){
        return gameState;
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
                if (isValidPosition(gameState.getHero().getPosX() - 1, gameState.getHero().getPosY())) {
                    h.setPosX(gameState.getHero().getPosX() - 1);
                    h.setPosY(gameState.getHero().getPosY());
                }
                break;
            case DOWN:
                if (isValidPosition(gameState.getHero().getPosX(), gameState.getHero().getPosY() + 1)) {
                    h.setPosX(gameState.getHero().getPosX());
                    h.setPosY(gameState.getHero().getPosY() + 1);
                }

                break;
            case RIGHT:
                if (isValidPosition(gameState.getHero().getPosX() + 1, gameState.getHero().getPosY())) {
                    h.setPosX(gameState.getHero().getPosX() + 1);
                    h.setPosY(gameState.getHero().getPosY());
                }

                break;
            case UP:
                if (isValidPosition(gameState.getHero().getPosX(), gameState.getHero().getPosY() - 1)) {
                    h.setPosX(gameState.getHero().getPosX());
                    h.setPosY(gameState.getHero().getPosY() - 1);
                }

                break;
            case ACTION:
                if (gameSpace.isChest(h.getPosX(), h.getPosY())) {
                    // Alors on win
                    isFinished = true;
                    gameState.setVictory();
                    System.out.println("Vous avez gagné");
                }
                break;

        }

        //Fait bouger tous les monstres aléatoirement d'une case
        if (!(commande == Cmd.IDLE)) mooveMonsters();
    }

    /**
     * Fait bouger tous les monstres vers le héro.
     */
    public void mooveMonsters() {
        Monster m;
        Hero h = gameState.getHero();
        int posX;
        int posY;
        for (int i = 0; i < gameState.sizeMonsters(); i++) {
            m = gameState.getMonster(i);
            posX = h.getPosX() - m.getPosX();
            //Ne peut pas aller sur la même case que le héro
            if (posX > 1) {
                //Droite
                if (isValidPosition(m.getPosX() + 1, m.getPosY())) {
                    m.setPosX(m.getPosX() + 1);
                }
            } else if (posX < 1) {
                //Gauche
                if (isValidPosition(m.getPosX() - 1, m.getPosY())) {
                    m.setPosX(m.getPosX() - 1);
                }
            }


            posY = h.getPosY() - m.getPosY();
            if (posY > 1) {
                //Bas
                if (isValidPosition(m.getPosX(), m.getPosY() + 1)) {
                    m.setPosY(m.getPosY() + 1);
                }
            } else if (posY < 1) {
                //Haut
                if (isValidPosition(m.getPosX(), m.getPosY() - 1)) {
                    m.setPosY(m.getPosY() - 1);
                }
            }

        }
    }


    public boolean isValidPosition(int x, int y) {
        // Check monstres
        for (Monster monster : monsters()) {
            if (x == monster.getPosX() && y == monster.getPosY()) return false;
        }

        // Check murs
        return gameSpace.isValidPosition(x, y);
    }

    public String toString() {
        return gameSpace.toString(gameState.getHero().getPosX(), gameState.getHero().getPosY());

    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    public Room currentRoom() {
        return gameSpace.currentRoom();
    }

    public Hero getHero() {
        return gameState.getHero();
    }


    public List<Monster> monsters() {
        return gameState.monsters();
    }

    private boolean isValidposition(int x, int y) {

        return gameSpace.isValidPosition(x, y);
    }


}
