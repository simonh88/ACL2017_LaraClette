package game;


import characters.Character;
import engine.Cmd;
import environement.Room;
import factory.SoundFactory;

import java.util.List;
import java.util.Random;

public class Game implements engine.Game {

    /**
     * Pas final car pour la réinitialisation -> new
     */
    private GameSpace gameSpace;
    private GameState gameState;

    private long deltaTime;
    private long timeSinceStart;

    private boolean isFinished;

    public Game() {
        this.gameSpace = new GameSpace();
        this.gameState = new GameState();
        isFinished = false;
        generateHero();
        deltaTime = 1000;
        timeSinceStart = System.currentTimeMillis();
        SoundFactory.instance().playBackground();
    }

    public GameState getGameState() {
        return gameState;
    }

    /**
     * faire evoluer le jeu suite a une commande
     *
     * @param commande
     */
    @Override
    public void evolve(Cmd commande) {

        Character hero = gameState.getHero();

        switch (commande) {
            case LEFT://Gauche
                if (isValidPosition(hero.getPosX() - 1, hero.getPosY())) {
                    hero.setPosX(hero.getPosX() - 1);
                    hero.setPosY(hero.getPosY());
                }
                break;
            case DOWN:
                if (isValidPosition(hero.getPosX(), hero.getPosY() + 1)) {
                    hero.setPosX(hero.getPosX());
                    hero.setPosY(hero.getPosY() + 1);
                }

                break;
            case RIGHT:
                if (isValidPosition(hero.getPosX() + 1, hero.getPosY())) {
                    hero.setPosX(hero.getPosX() + 1);
                    hero.setPosY(hero.getPosY());
                }

                break;
            case UP:
                if (isValidPosition(hero.getPosX(), hero.getPosY() - 1)) {
                    hero.setPosX(hero.getPosX());
                    hero.setPosY(hero.getPosY() - 1);
                }

                break;
            case ACTION:
                SoundFactory.instance().playSound("res/sound/Sword_Swing.wav");

                if (gameSpace.isChest(hero.getPosX(), hero.getPosY())) {
                    // Alors on win
                    //isFinished = true;
                    gameState.setVictory();
                }
                break;
            case RESTART:
                if (gameState.isVictory() || gameState.isLoss()) {
                    restart();
                }
                break;
        }

        //Fait bouger tous les monstres aléatoirement d'une case toutes les 1sec

        if (System.currentTimeMillis() - timeSinceStart > deltaTime ) {
            mooveMonsters();
            timeSinceStart = System.currentTimeMillis();
        }
    }


    private void restart() {
        gameState = new GameState();
        gameSpace = new GameSpace();
        generateHero();

    }

    /**
     * Fait bouger tous les monstres vers le héro.
     */
    public void mooveMonsters() {
        Character monster;
        Character h = gameState.getHero();
        int posX;
        int posY;
        for (int i = 0; i < gameState.sizeMonsters(); i++) {
            monster = gameState.getMonster(i);
            posX = h.getPosX() - monster.getPosX();
            //Ne peut pas aller sur la même case que le héro
            if (posX > 1) {
                //Droite
                if (isValidPosition(monster.getPosX() + 1, monster.getPosY())) {
                    monster.setPosX(monster.getPosX() + 1);
                }
            } else if (posX < 1) {
                //Gauche
                if (isValidPosition(monster.getPosX() - 1, monster.getPosY())) {
                    monster.setPosX(monster.getPosX() - 1);
                }
            }

            posY = h.getPosY() - monster.getPosY();
            if (posY > 1) {
                //Bas
                if (isValidPosition(monster.getPosX(), monster.getPosY() + 1)) {
                    monster.setPosY(monster.getPosY() + 1);
                }
            } else if (posY < 1) {
                //Haut
                if (isValidPosition(monster.getPosX(), monster.getPosY() - 1)) {
                    monster.setPosY(monster.getPosY() - 1);
                }
            }
        }

    }

    private void generateHero() {
        Random rand = new Random();
        int x = 0;
        int y = 0;
        while (!(isValidPosition(x, y))) {
            x = Math.abs(rand.nextInt()) % (10) + 1;
            y = Math.abs(rand.nextInt()) % (10) + 1;
        }
        gameState.setHero(x, y);

    }


    public boolean isValidPosition(int x, int y) {
        // Check monstres
        for (Character monster : monsters()) {
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

    public Character getHero() {
        return gameState.getHero();
    }


    public List<Character> monsters() {
        return gameState.monsters();
    }

    private boolean isValidposition(int x, int y) {

        return gameSpace.isValidPosition(x, y);
    }


}
