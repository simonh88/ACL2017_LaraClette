package factory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TileFactory {

    public static TileFactory instance;

    private BufferedImage grassTile;
    private ImageIcon grassTile2;


    private TileFactory() {
        try {
            grassTile = ImageIO.read(new File("res/grass_tile.png"));
            grassTile2 = new ImageIcon("/res/grass_tile.png");
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/grass_tile.png");
            System.exit(-1);
        }
    }

    public static TileFactory instance() {
        if (instance == null) {
            instance = new TileFactory();
        }

        return instance;
    }

    public Image getGrassTile() {
        return grassTile;
    }
}
