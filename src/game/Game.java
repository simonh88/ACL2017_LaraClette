package game;

import characters.Hero;
import characters.Monster;
import engine.Cmd;
import environement.Chest;
import environement.Empty;
import environement.Room;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game implements engine.Game {

    /**
     * Pas final car pour la réinitialisation -> new
     */
    private GameSpace gameSpace;
    private GameState gameState;

    private boolean isFinished;

    public Game() {
        this.gameSpace = new GameSpace();
        this.gameState = new GameState();
        isFinished = false;
        generateHero();
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
                    //isFinished = true;
                    gameState.setVictory();
                }
                break;
            case RESTART:
                if(gameState.isVictory() || gameState.isLoss()){
                    restart();
                }
                break;
        }

        //Fait bouger tous les monstres aléatoirement d'une case
        if (!(commande == Cmd.IDLE)) mooveMonsters();
    }

    private void restart(){
        gameState = new GameState();
        gameSpace = new GameSpace();
        generateHero();

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

    private void generateHero(){
        Random rand = new Random();
        int x = 0;
        int y = 0;
        while (! (isValidPosition(x,y))) {
            x = Math.abs(rand.nextInt()) % (10) + 1;
            y = Math.abs(rand.nextInt()) % (10) + 1;
        }
        gameState.setHero(x,y);

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
