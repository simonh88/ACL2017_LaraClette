package game;

import engine.GamePainter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Painter implements GamePainter {


    /**
     * la taille des cases
     */
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;

    @Override
    public void draw(BufferedImage image) {
        Graphics2D crayon = (Graphics2D) image.getGraphics();

    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }
}
