package game;

import characters.Hero;
import characters.Monster;
import engine.GamePainter;
import environement.Room;
import factory.TileFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

import static environement.Decor.*;

public class Painter implements GamePainter {


    /* Taille de la fenetre */
    private static final int WIN_WIDTH = 600;
    private static final int WIN_HEIGHT = 600;

    private Game game;

    public Painter(Game game) {
        this.game = game;
    }

    @Override
    public void draw(BufferedImage image) {
        Graphics2D crayon = (Graphics2D) image.getGraphics();

        if (game.getGameState().isRunning()) {
        /* On dessine les mur */
            printwalls(crayon);

        /* On dessine le hero */
            printHero(crayon);

        /* On dessine les monstres */
            printMonsters(crayon);

            crayon.drawString("Commands : ", 10, 565);
            crayon.drawString("LEFT: q | RIGHT: d | UP: z | DOWN: s", 30, 580);
            crayon.drawString("ACTION: e", 30, 595);
        }else if(game.getGameState().isLoss()){
            crayon.setColor(Color.RED);
            crayon.setFont(new Font(" TimesRoman ",Font.BOLD,30));
            crayon.drawString("Loss ! ", 250, 300);
        }else if(game.getGameState().isVictory()){
            crayon.setColor(Color.RED);
            crayon.setFont(new Font(" TimesRoman ",Font.BOLD,30));
            crayon.drawString("Win ! ", 250, 300);
            crayon.drawString("Press R to restart", 140, 350);

        }

    }

    private void printwalls(Graphics2D crayon) {
        Room currentRoom = game.currentRoom();

        /* On dessine les mur */
        for (int j = 0; j < currentRoom.getHeight(); j++) {
            for (int i = 0; i < currentRoom.getWidth(); i++) {
                switch (currentRoom.get(i, j).getType()) {
                    case WALL:

                        crayon.drawImage(TileFactory.instance().getWallTile(),
                                i * Room.TILE_WIDTH, j * Room.TILE_HEIGHT, null);

                        break;
                    case EMPTY:

                        crayon.drawImage(TileFactory.instance().getGrassTile(),
                                i * Room.TILE_WIDTH, j * Room.TILE_HEIGHT, null);

                        break;
                    case CHEST:
                        crayon.setColor(Color.orange);
                        crayon.fillRect(i * Room.TILE_WIDTH, j * Room.TILE_HEIGHT, Room.TILE_WIDTH, Room.TILE_HEIGHT);
                        break;
                }
            }
        }
    }

    private void printHero(Graphics2D crayon) {
        Hero hero = game.getHero();
        crayon.setColor(Color.BLUE);
        crayon.fillRect(hero.getPosX() * Room.TILE_WIDTH, hero.getPosY() * Room.TILE_HEIGHT, Room.TILE_WIDTH, Room.TILE_HEIGHT);
    }

    private void printMonsters(Graphics2D crayon) {
        for (Monster monster : game.monsters()) {
            crayon.setColor(Color.RED);
            crayon.fillRect(monster.getPosX() * Room.TILE_WIDTH, monster.getPosY() * Room.TILE_HEIGHT, Room.TILE_WIDTH, Room.TILE_HEIGHT);
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
