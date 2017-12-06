package game;

import characters.Character;
import engine.GamePainter;
import environement.Grass;
import environement.GroundLoot;
import environement.Room;
import factory.SoundFactory;
import factory.TileFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Painter implements GamePainter {


    /* Taille de la fenetre */
    public static final int WIN_WIDTH = Room.SIZE * Room.TILE_WIDTH;
    public static final int WIN_HEIGHT = Room.SIZE * Room.TILE_HEIGHT;

    private Game game;

    public Painter(Game game) {
        this.game = game;
    }

    @Override
    public void draw(BufferedImage image) {
        Graphics2D crayon = (Graphics2D) image.getGraphics();


        if (game.getGameState().isMenu()){
        /* On dessine le menu */
            if (!game.getMenu().isMenuCmd())
                game.getMenu().printMenu(crayon);
            else
                game.getMenu().printCommandes(crayon);
        } else if (game.getGameState().isRunning()) {
            /* On dessine les mur et le sol */
            printWalls(crayon);

            /* On dessine les monstres */
            printMonsters(crayon);

            /* On dessine les loots sur le sol */
            printGroundLoots(crayon);

            /* On dessine le hero */
            printHero(crayon);

            /* On dessine le boss Bien garder après le print du héro*/
            printBoss(crayon);


            /* On dessine les animations d'attaque */
            printAttack(crayon, game.getHero(), TileFactory.HERO_ATTACK);
            for (Character monster : game.monsters()) printAttack(crayon, monster, TileFactory.MONSTER_ATTACK);

            // On dessine les objets
            printObjects(crayon);

            /* On dessine les infos du hero */
            printInfo(crayon);

            crayon.setColor(Color.WHITE);
            crayon.setFont(new Font(" TimesRoman ",Font.BOLD,15));
            crayon.drawString("Commands : ", 10, 565);
            crayon.drawString("LEFT: q | RIGHT: d | UP: z | DOWN: s", 30, 580);
            crayon.drawString("ACTION: e", 30, 595);
        }else if(game.getGameState().isLoss()){
            SoundFactory.instance().stopBackground();
            printLoss(crayon);
        }else if(game.getGameState().isVictory()){
            printVictory(crayon);

        }

    }

    /**
     * Dessine les arbres, les pots, les coffres
     * @param crayon
     */
    private void printObjects(Graphics2D crayon) {
        Room currentRoom = game.currentRoom();

        for (int j = 0; j < currentRoom.getHeight(); j++) {
            for (int i = 0; i < currentRoom.getWidth(); i++) {
                switch (currentRoom.get(i, j).getType()) {
                    case VASE:
                        if (!currentRoom.get(i, j).hasBeenUsed()) {
                            crayon.drawImage(TileFactory.instance().getVaseTile(),
                                    i * Room.TILE_WIDTH, j * Room.TILE_HEIGHT, null);
                        }
                        break;

                    case TREE:
                        // Le -37 est là car la tile de l'abre est plus haute que large (87 de hauteur)
                        // La position d'affichage est donc légerement modifiée (87 - 37 = 50 = la taille des tile)
                        crayon.drawImage(TileFactory.instance().getTreeTile(),
                                i * Room.TILE_WIDTH, j * Room.TILE_HEIGHT - (87 - Room.TILE_HEIGHT), null);
                        break;
                    case CHEST:
                        crayon.drawImage(TileFactory.instance().getChestTile(),
                                i * Room.TILE_WIDTH, j * Room.TILE_HEIGHT, null);
                        break;
                }
            }
        }
    }


    /**
     * Dessine le sol et les murs
     * @param crayon
     */
    private void printWalls(Graphics2D crayon) {
        Room currentRoom = game.currentRoom();

        /* On dessine les mur */
        for (int j = 0; j < currentRoom.getHeight(); j++) {
            for (int i = 0; i < currentRoom.getWidth(); i++) {
                switch (currentRoom.get(i, j).getType()) {
                    case WALL:
                        crayon.drawImage(TileFactory.instance().getWallTile(),
                                i * Room.TILE_WIDTH, j * Room.TILE_HEIGHT, null);
                        break;

                    case WATER:
                        crayon.drawImage(TileFactory.instance().getWaterTile(),
                                i * Room.TILE_WIDTH, j * Room.TILE_HEIGHT, null);
                        break;

                    case BRIDGE:
                        crayon.drawImage(TileFactory.instance().getBridgeTile(),
                                i * Room.TILE_WIDTH, j * Room.TILE_HEIGHT, null);
                        break;

                    case GRASS:
                        Grass grass = (Grass) currentRoom.get(i, j);
                        Image grassTile = TileFactory.instance().getGroundTile(grass.getGroundType());
                        crayon.drawImage(grassTile,
                                i * Room.TILE_WIDTH, j * Room.TILE_HEIGHT, null);
                        break;

                    case VASE:
                        if (currentRoom.get(i, j).hasBeenUsed()) {
                            crayon.drawImage(TileFactory.instance().getBrokenVaseTile(),
                                    i * Room.TILE_WIDTH, j * Room.TILE_HEIGHT, null);
                        }

                }
            }
        }
    }

    private void printHero(Graphics2D crayon) {
        Character hero = game.getHero();

        crayon.drawImage(TileFactory.instance().getSpriteCharacterByOrientation(hero, TileFactory.HERO),
                hero.getPosX() * Room.TILE_WIDTH,
                hero.getPosY() * Room.TILE_HEIGHT, null);

    }

    private void printBoss(Graphics2D crayon){
        Character boss = game.getBoss();

        //Si le monstre est dans la bonne room
        if(boss.getCurrentRoom() == game.indexCurrentRoom()) {

            if (boss.isAlive()) {
                crayon.drawImage(TileFactory.instance().getSpriteCharacterByOrientation(boss, TileFactory.BOSS),
                        boss.getPosX() * Room.TILE_WIDTH,
                        boss.getPosY() * Room.TILE_HEIGHT, null);
                switch (boss.getHP()){
                    case 1:
                        crayon.drawImage(TileFactory.instance().getHP1(),
                                boss.getPosX() * Room.TILE_WIDTH,
                                boss.getPosY() * Room.TILE_HEIGHT, null);
                        break;
                    case 2:
                        crayon.drawImage(TileFactory.instance().getHP2(),
                                boss.getPosX() * Room.TILE_WIDTH,
                                boss.getPosY() * Room.TILE_HEIGHT, null);
                        break;
                    case 3:
                        crayon.drawImage(TileFactory.instance().getHP2(),
                                boss.getPosX() * Room.TILE_WIDTH,
                                boss.getPosY() * Room.TILE_HEIGHT, null);
                        crayon.drawImage(TileFactory.instance().getHP1(),
                                boss.getPosX() * Room.TILE_WIDTH,
                                boss.getPosY() * Room.TILE_HEIGHT+25, null);
                        break;
                    case 4:
                        crayon.drawImage(TileFactory.instance().getHP2(),
                                boss.getPosX() * Room.TILE_WIDTH,
                                boss.getPosY() * Room.TILE_HEIGHT, null);
                        crayon.drawImage(TileFactory.instance().getHP2(),
                                boss.getPosX() * Room.TILE_WIDTH,
                                boss.getPosY() * Room.TILE_HEIGHT+25, null);
                        break;
                    case 5:
                        crayon.drawImage(TileFactory.instance().getHP2(),
                                boss.getPosX() * Room.TILE_WIDTH,
                                boss.getPosY() * Room.TILE_HEIGHT, null);
                        crayon.drawImage(TileFactory.instance().getHP2(),
                                boss.getPosX() * Room.TILE_WIDTH,
                                boss.getPosY() * Room.TILE_HEIGHT+25, null);
                        crayon.drawImage(TileFactory.instance().getHP1(),
                                boss.getPosX() * Room.TILE_WIDTH-15,
                                boss.getPosY() * Room.TILE_HEIGHT, null);
                        break;
                }
                if (boss.getHP() > 5){
                    crayon.drawImage(TileFactory.instance().getHP2(),
                            boss.getPosX() * Room.TILE_WIDTH,
                            boss.getPosY() * Room.TILE_HEIGHT, null);
                }

                else{
                    crayon.drawImage(TileFactory.instance().getHP1(),
                            boss.getPosX() * Room.TILE_WIDTH,
                            boss.getPosY() * Room.TILE_HEIGHT, null);
                }
            }else{
                crayon.drawImage(TileFactory.instance().getBossDead(),
                        boss.getPosX() * Room.TILE_WIDTH,
                        boss.getPosY() * Room.TILE_HEIGHT, null);
            }
        }
    }

    private void printMonsters(Graphics2D crayon) {
        for (Character monster : game.monsters()) {
            if(monster.getCurrentRoom() == game.indexCurrentRoom()) {
                if (monster.isAlive()) {

                    crayon.drawImage(TileFactory.instance().getSpriteCharacterByOrientation(monster, TileFactory.MONSTER),
                            monster.getPosX() * Room.TILE_WIDTH,
                            monster.getPosY() * Room.TILE_HEIGHT, null);

                    if (monster.getHP() > 1){
                        crayon.drawImage(TileFactory.instance().getHP2(),
                                monster.getPosX() * Room.TILE_WIDTH,
                                monster.getPosY() * Room.TILE_HEIGHT, null);
                    }

                    else{
                        crayon.drawImage(TileFactory.instance().getHP1(),
                                monster.getPosX() * Room.TILE_WIDTH,
                                monster.getPosY() * Room.TILE_HEIGHT, null);
                    }
                } else {
                    crayon.drawImage(TileFactory.instance().getMonsterDead(),
                            monster.getPosX() * Room.TILE_WIDTH,
                            monster.getPosY() * Room.TILE_HEIGHT, null);
                }
            }
        }
    }
    
    private void printAttack(Graphics2D crayon, Character c, int cas){
        if(c.isOnAttack()){

            if(c.getLastMove() == "S"){
                crayon.drawImage(TileFactory.instance().getSpriteCharacterByOrientation(c, cas),
                        c.getPosX() * Room.TILE_WIDTH,
                        (c.getPosY()+1) * Room.TILE_HEIGHT, null);
            }

            if(c.getLastMove() == "Z"){
                crayon.drawImage(TileFactory.instance().getSpriteCharacterByOrientation(c, cas),
                        c.getPosX() * Room.TILE_WIDTH,
                        (c.getPosY()-1) * Room.TILE_HEIGHT, null);
            }

            if(c.getLastMove() == "Q"){
                crayon.drawImage(TileFactory.instance().getSpriteCharacterByOrientation(c, cas),
                        (c.getPosX()-1) * Room.TILE_WIDTH,
                        c.getPosY() * Room.TILE_HEIGHT, null);
            }

            if(c.getLastMove() == "D"){
                crayon.drawImage(TileFactory.instance().getSpriteCharacterByOrientation(c, cas),
                        (c.getPosX()+1) * Room.TILE_WIDTH,
                        c.getPosY() * Room.TILE_HEIGHT, null);
            }
        }
    }

    private void printInfo(Graphics2D crayon) {
        Room currentRoom = game.currentRoom();
        Character hero = game.getHero();




        int lastCaseX = currentRoom.getWidth() - 1;
        int lastCaseY = currentRoom.getHeight() - 1;
        //PRINT KEY DANS BARRE
        if(hero.hasKey())
        crayon.drawImage(TileFactory.instance().getKey(),
                (lastCaseX-1) * Room.TILE_WIDTH, lastCaseY * Room.TILE_HEIGHT, null);

        crayon.drawImage(TileFactory.instance().getHeart(),
                lastCaseX * Room.TILE_WIDTH, lastCaseY * Room.TILE_HEIGHT, null);

        String strHP = Integer.toString(hero.getHP());

        if(hero.getHP() < 10) strHP = "0" + Integer.toString(hero.getHP());

        crayon.setColor(Color.BLACK);
        crayon.setFont(new Font(" TimesRoman ",Font.BOLD,18));
        crayon.drawString(strHP, lastCaseX * Room.TILE_WIDTH + 12, lastCaseY * Room.TILE_HEIGHT + 30);
    }


    private void printLoss(Graphics2D crayon){
        crayon.drawImage(TileFactory.instance().getMenuTile(), 0, 0, null);
        crayon.drawImage(TileFactory.instance().getSkull(), getWidth() / 2 - 30, getHeight() / 2 - 20, null);
        crayon.setFont(new Font(" Serif ",Font.BOLD,18));
        crayon.setColor(new Color(102, 51, 0));

        crayon.drawString("Press R to restart", getWidth()/3 + 13, getHeight()/2 + 110);
        crayon.drawString("You have lost :(", getWidth()/3 + 18, getHeight()/2 + 70);
    }


    private void printVictory(Graphics2D crayon){
        crayon.drawImage(TileFactory.instance().getMenuTile(), 0, 0, null);
        crayon.drawImage(TileFactory.instance().getCrown(), getWidth() / 2 - 30, getHeight() / 2 - 20, null);
        crayon.setFont(new Font(" Serif ",Font.BOLD,18));
        crayon.setColor(new Color(102, 51, 0));

        crayon.drawString("Press R to restart", getWidth()/3 + 13, getHeight()/2 + 110);
        crayon.drawString("You win !", getWidth()/3 + 25, getHeight()/2 + 70);
    }

    private void printGroundLoots(Graphics2D crayon) {
        Room currentRoom = game.currentRoom();

        for (GroundLoot loot : currentRoom.getGroundLoots()) {
            switch (loot.getType()) {
                case HEART:
                    crayon.drawImage(TileFactory.instance().getLittleHeart(),
                            loot.getPosition().getX() * Room.TILE_WIDTH, loot.getPosition().getY() * Room.TILE_HEIGHT, null);
                    break;
                case KEY:
                    crayon.drawImage(TileFactory.instance().getKey(),
                            loot.getPosition().getX() * Room.TILE_WIDTH, loot.getPosition().getY() * Room.TILE_HEIGHT, null);
                    break;
            }
        }
    }

    @Override
    public int getWidth() {
        return WIN_WIDTH;
    }

    @Override
    public int getHeight() {
        return WIN_HEIGHT;
    }
}
