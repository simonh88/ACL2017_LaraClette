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
    private long deltaTimeAttack;
    private long timeSinceStart;
    private long timeSinceStartAttack;

    private boolean isFinished;
    private boolean heroOnAttack;

    public Game() {
        this.gameSpace = new GameSpace();
        this.gameState = new GameState();
        isFinished = false;
        heroOnAttack = false;
        generateHero();
        deltaTime = 1000;
        deltaTimeAttack = 100;
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

        Room currentRoom = currentRoom();
        switch (commande) {
            case LEFT://Gauche
                int nextX = hero.getPosX() - 1;
                int nextY = hero.getPosY();
                if( nextY < 0){//Gauche
                    //TODO Changement map
                }
                if (isValidPosition(nextX, nextY)) {
                    hero.setPosX(hero.getPosX() - 1);
                    hero.setPosY(hero.getPosY());
                }
                break;
            case DOWN:
                if (hero.getPosX() >= Room.SIZE) { //Bas
                //TODO Changement map
                }
                if (isValidPosition(hero.getPosX(), hero.getPosY() + 1)) {
                    hero.setPosX(hero.getPosX());
                    hero.setPosY(hero.getPosY() + 1);
                }

                break;
            case RIGHT:
                if( hero.getPosY() >= Room.SIZE){//Droite
                    //TODO Changement map
                }
                if (isValidPosition(hero.getPosX() + 1, hero.getPosY())) {
                    hero.setPosX(hero.getPosX() + 1);
                    hero.setPosY(hero.getPosY());
                }

                break;
            case UP:
                if (hero.getPosX() < 0){//Haut
                    //TODO Changement map
                }
                if (isValidPosition(hero.getPosX(), hero.getPosY() - 1)) {
                    hero.setPosX(hero.getPosX());
                    hero.setPosY(hero.getPosY() - 1);
                }

                break;
            case ACTION:
                //SoundFactory.instance().playSound("res/sound/Sword_Swing.wav");

                if (gameSpace.isChest(hero.getPosX(), hero.getPosY())) {
                    // Alors on win
                    //isFinished = true;
                    SoundFactory.instance().stopBackground();
                    gameState.setVictory();
                }
                break;
            case ATTACK:
                SoundFactory.instance().playSound("res/sound/Sword_Swing.wav");
                attackHero();

                //TODO L'ATTAQUE
            case RESTART:
                if (gameState.isVictory() || gameState.isLoss()) {
                    restart();
                }
                break;
        }

        // Si le joeur est sur un bord il doit changer de map
        if (playerOnBorder()) {
            // TODO : implement
            System.out.println("Player on border !");
            changeRoom();
        }

        //Fait bouger tous les monstres aléatoirement d'une case toutes les 1sec

        if (System.currentTimeMillis() - timeSinceStart > deltaTime ) {
            mooveMonsters();
            attackMonster();
            timeSinceStart = System.currentTimeMillis();
        }


        //Fait l'animation de l'attaque du hero

        if (System.currentTimeMillis() - timeSinceStartAttack > deltaTimeAttack ) {
            heroOnAttack = false;
        }
    }


    private void restart() {
        gameState = new GameState();
        gameSpace = new GameSpace();
        generateHero();
        SoundFactory.instance().playBackground();

    }

    /**
     * Fait bouger tous les monstres vers le héro.
     */
    private void mooveMonsters() {
        Character monster;
        Character h = gameState.getHero();

        int posX;
        int posY;
        for (int i = 0; i < gameState.sizeMonsters(); i++) {
            monster = gameState.getMonster(i);

            if(monster.isAlive()) {

                //System.out.println("Monster : " + monster.getPosX() + "," + monster.getPosY());
                //System.out.println("Hero : " + h.getPosX() + "," + h.getPosY());


                if (monster.getPosX() > h.getPosX()) {
                    // DROITE
                    if (isValidPosition(monster.getPosX() - 1, monster.getPosY())) {
                        monster.setPosX(monster.getPosX() - 1);
                    }
                }

                if (monster.getPosX() < h.getPosX()) {
                    // GAUCHE
                    if (isValidPosition(monster.getPosX() + 1, monster.getPosY())) {
                        monster.setPosX(monster.getPosX() + 1);
                    }
                }

                if (monster.getPosY() > h.getPosY()) {
                    // BAS
                    if (isValidPosition(monster.getPosX(), monster.getPosY() - 1)) {
                        monster.setPosY(monster.getPosY() - 1);
                    }
                }

                if (monster.getPosY() < h.getPosY()) {
                    // HAUT
                    if (isValidPosition(monster.getPosX(), monster.getPosY() + 1)) {
                        monster.setPosY(monster.getPosY() + 1);
                    }
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

    private void attackHero() {
        Character hero = gameState.getHero();
        this.heroOnAttack = true;
        timeSinceStartAttack = System.currentTimeMillis();

        for (Character monster : monsters()) {

            int distanceX = monster.getPosX() - hero.getPosX();
            int distanceY = monster.getPosY() - hero.getPosY();

            //System.out.println("\n\ndistanceX  : " + distanceX);
            //System.out.println("distanceY  : " + distanceY);

            if (distanceX <= 1 && distanceX >= -1 && distanceY <= 1 && distanceY >= -1){
                monster.setHP(monster.getHP() - 5);
            }

        }
    }

    private void attackMonster() {
        Character hero = gameState.getHero();

        for (Character monster : monsters()) {

            if(monster.isAlive()) {

                int distanceX = monster.getPosX() - hero.getPosX();
                int distanceY = monster.getPosY() - hero.getPosY();

                if (distanceX <= 1 && distanceX >= -1 && distanceY <= 1 && distanceY >= -1) {
                    hero.setHP(hero.getHP() - 1);
                }

                if (!hero.isAlive()) gameState.setLoss();

            }
        }
    }


    public boolean isValidPosition(int x, int y) {
        // Check monstres
        for (Character monster : monsters()) {
            if (x == monster.getPosX() && y == monster.getPosY() && monster.isAlive()) return false;
        }

        // Check Hero
        Character hero = gameState.getHero();
        if (x == hero.getPosX() && y == hero.getPosY()) return false;

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
        int indexRoom = getHero().getCurrentRoom();
        return gameSpace.currentRoom(indexRoom);
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

    /**
     * Check si le joueur est sur le bord du plateau, utile pour savoir si on doit changer de map
     * @return boolean
     */
    private boolean playerOnBorder() {
        Character hero = getHero();
        return hero.getPosX() == 0 || hero.getPosY() == 0 || hero.getPosX() == Room.SIZE-1 || hero.getPosY() == Room.SIZE-1;
    }

    /**
     * On change de room
     */
    private void changeRoom() {
        System.out.println("Changing room !");
        Character hero = getHero();

        // On détermine vers quelle room le joueur va

        if (hero.getPosX() == 0) {
            // On va à gauche
            System.out.println("Loading room left");
            gameSpace.goRoomLeft();

        }

        if (hero.getPosX() == Room.SIZE-1) {
            // On va à droite
            System.out.println("Loading room right");
            gameSpace.goRoomRight();
        }

        if (hero.getPosY() == 0) {
            // On va en haut
            gameSpace.goRoomUp();
        }

        if (hero.getPosY() == Room.SIZE-1) {
            // On va en bas
            gameSpace.goRoomBottom();
        }

        // TODO Reset proprement la position
        hero.setPosX(5);
        hero.setPosY(5);

    }


    public boolean heroIsOnAttack(){
        return this.heroOnAttack;
    }
}
