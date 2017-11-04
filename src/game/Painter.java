package game;

import characters.Hero;
import engine.GamePainter;
import environement.Room;

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

        Room currentRoom = game.currentRoom();

        for (int j = 0; j < currentRoom.getHeight(); j++) {
            for (int i = 0; i < currentRoom.getWidth(); i++) {
                switch (currentRoom.get(i, j).getType()) {
                    case WALL:
                        crayon.setColor(Color.DARK_GRAY);
                        crayon.fillRect(i * Room.TILE_WIDTH, j * Room.TILE_HEIGHT, Room.TILE_WIDTH, Room.TILE_HEIGHT);
                        break;
                    case EMPTY:
                        crayon.setColor(Color.WHITE);
                        crayon.fillRect(i * Room.TILE_WIDTH, j * Room.TILE_HEIGHT, Room.TILE_WIDTH, Room.TILE_HEIGHT);
                        break;
                }
            }
        }

        Hero hero = game.getHero();
        crayon.setColor(Color.BLUE);
        crayon.fillRect(hero.getPosX() * Room.TILE_WIDTH, hero.getPosY() * Room.TILE_HEIGHT, Room.TILE_WIDTH, Room.TILE_HEIGHT);
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
