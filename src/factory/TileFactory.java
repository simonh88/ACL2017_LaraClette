package factory;

import environement.Grass;
import environement.GrassType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class TileFactory {

    public static TileFactory instance;

    private BufferedImage menuTile;

    private BufferedImage grassTileSimple1;
    private BufferedImage grassTileSimple2;
    private BufferedImage grassTileSimple3;

    private BufferedImage grassTileGrass;
    private BufferedImage grassTileDirt;
    private BufferedImage grassTileFullDirt;

    private BufferedImage wallTile;
    private BufferedImage chestTile;
    private BufferedImage girl;
    private BufferedImage girlAttack;
    private BufferedImage monster;
    private BufferedImage monsterDead;
    private BufferedImage monsterAttack;
    private BufferedImage heart;
    private BufferedImage hp_1;
    private BufferedImage hp_2;

    private TileFactory() {

        try {
            menuTile = ImageIO.read(new File("res/img/menu.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/menu.png");
            System.exit(-1);
        }

        try {
            grassTileGrass = ImageIO.read(new File("res/img/grass_tile_grass.png"));
            grassTileDirt = ImageIO.read(new File("res/img/grass_tile_dirt.png"));
            grassTileFullDirt = ImageIO.read(new File("res/img/grass_tile_full_dirt.png"));

            grassTileSimple1 = ImageIO.read(new File("res/img/grass_tile_simple_1.png"));
            grassTileSimple2 = ImageIO.read(new File("res/img/grass_tile_simple_2.png"));
            grassTileSimple3 = ImageIO.read(new File("res/img/grass_tile_simple_3.png"));
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
            monsterAttack = ImageIO.read(new File("res/img/monster_attack.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/monster_attack.png");
            System.exit(-1);
        }

        try {
            wallTile = ImageIO.read(new File("res/img/wall_tile.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/wall_tile.png");
            System.exit(-1);
        }

        try {
            chestTile = ImageIO.read(new File("res/img/chest_tile_v2.png"));
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
            girlAttack = ImageIO.read(new File("res/img/girl_attack.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/girl_attack.png");
            System.exit(-1);
        }

        try {
            heart = ImageIO.read(new File("res/img/heart.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/heart.png");
            System.exit(-1);
        }

        try {
            hp_1 = ImageIO.read(new File("res/img/hp_1.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/hp_1.png");
            System.exit(-1);
        }

        try {
            hp_2 = ImageIO.read(new File("res/img/hp_2.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/hp_2.png");
            System.exit(-1);
        }
    }

    public static TileFactory instance() {
        if (instance == null) {
            instance = new TileFactory();
        }

        return instance;
    }

    public Image getMenuTile(){ return  menuTile; }


    public Image getGrassTile(GrassType grassType) {
        switch (grassType) {
            case SIMPLE1:
                return grassTileSimple1;
            case SIMPLE2:
                return grassTileSimple2;
            case SIMPLE3:
                return grassTileSimple3;
            case GRASS:
                return grassTileGrass;
            case DIRT:
                return grassTileDirt;
            case FULL_DIRT:
                return grassTileFullDirt;
            default:
                return grassTileSimple1;
        }
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

    public Image getGirlAttack() {
        return girlAttack;
    }

    public Image getMonster() {
        return monster;
    }

    public Image getMonsterDead() {
        return monsterDead;
    }

    public Image getMonsterAttack() {
        return monsterAttack;
    }

    public Image getHeart() {
        return heart;
    }

    public Image getHP1() {
        return hp_1;
    }

    public Image getHP2() {
        return hp_2;
    }
}
