package game;

import characters.Character;
import engine.GamePainter;
import environement.Grass;
import environement.Room;
import factory.SoundFactory;
import factory.TileFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

import static environement.Decor.*;

public class Painter implements GamePainter {


    /* Taille de la fenetre */
    private static final int WIN_WIDTH = Room.SIZE * Room.TILE_WIDTH;
    private static final int WIN_HEIGHT = Room.SIZE * Room.TILE_HEIGHT;

    private Game game;

    public Painter(Game game) {
        this.game = game;
    }

    @Override
    public void draw(BufferedImage image) {
        Graphics2D crayon = (Graphics2D) image.getGraphics();


        if (game.getGameState().isMenu()){
        /* On dessine le menu */
            printMenu(crayon);
        } else if (game.getGameState().isRunning()) {
        /* On dessine les mur */
            printWalls(crayon);

        /* On dessine les monstres */
            printMonsters(crayon);

        /* On dessine le hero */
            printHero(crayon);

        /* On dessine les infos du hero */
            printInfo(crayon);

            crayon.setColor(Color.WHITE);
            crayon.setFont(new Font(" TimesRoman ",Font.BOLD,15));
            crayon.drawString("Commands : ", 10, 565);
            crayon.drawString("LEFT: q | RIGHT: d | UP: z | DOWN: s", 30, 580);
            crayon.drawString("ACTION: e", 30, 595);
        }else if(game.getGameState().isLoss()){
            SoundFactory.instance().stopBackground();
            crayon.setColor(Color.RED);
            crayon.setFont(new Font(" TimesRoman ",Font.BOLD,30));
            crayon.drawString("Loss ! ", 250, 300);
            crayon.drawString("Press R to restart", 140, 350);
        }else if(game.getGameState().isVictory()){
            crayon.setColor(Color.RED);
            crayon.setFont(new Font(" TimesRoman ",Font.BOLD,30));
            crayon.drawString("Win ! ", 250, 300);
            crayon.drawString("Press R to restart", 140, 350);

        }

    }

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
                    case GRASS:
                        Grass grass = (Grass) currentRoom.get(i, j);
                        Image grassTile = TileFactory.instance().getGroundTile(grass.getGroundType());
                        crayon.drawImage(grassTile,
                                i * Room.TILE_WIDTH, j * Room.TILE_HEIGHT, null);

                        break;
                    case CHEST:

                        crayon.drawImage(TileFactory.instance().getChestTile(),
                                i * Room.TILE_WIDTH, j * Room.TILE_HEIGHT, null);
                        break;
                }
            }
        }
    }

    private void printHero(Graphics2D crayon) {
        Character hero = game.getHero();

        if(!hero.isOnAttack()){
            crayon.drawImage(TileFactory.instance().getGirl(),
                    hero.getPosX() * Room.TILE_WIDTH,
                    hero.getPosY() * Room.TILE_HEIGHT, null);
        }

        else{
            crayon.drawImage(TileFactory.instance().getGirlAttack(),
                    hero.getPosX() * Room.TILE_WIDTH,
                    hero.getPosY() * Room.TILE_HEIGHT, null);
        }
    }

    private void printMonsters(Graphics2D crayon) {
        for (Character monster : game.monsters()) {
            if(monster.getCurrentRoom() == game.indexCurrentRoom()) {
                if (monster.isAlive()) {

                    if(monster.isOnAttack()){
                        crayon.drawImage(TileFactory.instance().getMonsterAttack(),
                                monster.getPosX() * Room.TILE_WIDTH,
                                monster.getPosY() * Room.TILE_HEIGHT, null);
                    }

                    else {
                        crayon.drawImage(TileFactory.instance().getMonster(),
                                monster.getPosX() * Room.TILE_WIDTH,
                                monster.getPosY() * Room.TILE_HEIGHT, null);
                    }

                    if (monster.getHP() > 5){
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

    private void printInfo(Graphics2D crayon) {
        Room currentRoom = game.currentRoom();
        Character hero = game.getHero();

        int lastCaseX = currentRoom.getWidth() - 1;
        int lastCaseY = currentRoom.getHeight() - 1;

        crayon.drawImage(TileFactory.instance().getHeart(),
                lastCaseX * Room.TILE_WIDTH, lastCaseY * Room.TILE_HEIGHT, null);

        String strHP = Integer.toString(hero.getHP());

        if(hero.getHP() < 10) strHP = "0" + Integer.toString(hero.getHP());

        crayon.setColor(Color.BLACK);
        crayon.setFont(new Font(" TimesRoman ",Font.BOLD,18));
        crayon.drawString(strHP, lastCaseX * Room.TILE_WIDTH + 12, lastCaseY * Room.TILE_HEIGHT + 30);
    }


    private void printMenu(Graphics2D crayon){
        crayon.drawImage(TileFactory.instance().getMenuTile(), 0, 0, null);
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
