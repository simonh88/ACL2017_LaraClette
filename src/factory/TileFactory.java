package factory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TileFactory {

    public static TileFactory instance;

    private BufferedImage grassTile;
    private BufferedImage wallTile;
    private BufferedImage chestTile;
    private BufferedImage girl;
    private BufferedImage monster;
    private BufferedImage monsterDead;
    private BufferedImage heart;

    private TileFactory() {
        try {
            grassTile = ImageIO.read(new File("res/img/grass_tile.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/grass_tile.png");
            System.exit(-1);
        }

        try {
            monster = ImageIO.read(new File("res/img/monster.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/monster.png");
            System.exit(-1);
        }

        try {
            monsterDead = ImageIO.read(new File("res/img/monster_dead.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/monster_dead.png");
            System.exit(-1);
        }

        try {
            wallTile = ImageIO.read(new File("res/img/wall_tile.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/wall_tile.png");
            System.exit(-1);
        }

        try {
            chestTile = ImageIO.read(new File("res/img/chest_tile.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/chest_tile.png");
            System.exit(-1);
        }

        try {
            girl = ImageIO.read(new File("res/img/girl.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/girl.png");
            System.exit(-1);
        }

        try {
            heart = ImageIO.read(new File("res/img/heart.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/heart.png");
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

    public Image getWallTile() {
        return wallTile;
    }

    public Image getChestTile() {
        return chestTile;
    }

    public Image getGirl() {
        return girl;
    }

    public Image getMonster() {
        return monster;
    }

    public Image getMonsterDead() {
        return monsterDead;
    }

    public Image getHeart() {
        return heart;
    }
}
