package factory;

import environement.GroundType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import characters.Character;


public class TileFactory {

    public static TileFactory instance;

    private BufferedImage menuTile;

    private BufferedImage grassTileSimple1;
    private BufferedImage grassTileSimple2;
    private BufferedImage grassTileSimple3;

    private BufferedImage treeTile;
    private BufferedImage vaseTile;
    private BufferedImage brokenVaseTile;

    private BufferedImage bonesTile1;
    private BufferedImage bonesTile2;

    private BufferedImage grassTileGrass;
    private BufferedImage grassTileDirt;
    private BufferedImage grassTileFullDirt;

    private BufferedImage wallTile;
    private BufferedImage chestTile;
    private BufferedImage[] hero;
    private BufferedImage[] attack;
    private BufferedImage[] monster;
    private BufferedImage monsterDead;
    private BufferedImage monsterAttack;
    private BufferedImage heart;
    private BufferedImage hp_1;
    private BufferedImage hp_2;

    private TileFactory() {

        //**********************************************************************
        //**                       PARTIE MENU                                **
        //**********************************************************************

        try {
            menuTile = ImageIO.read(new File("res/img/menu.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/menu.png");
            System.exit(-1);
        }

        //**********************************************************************
        //**                       PARTIE HERBE                               **
        //**********************************************************************

        try {
            grassTileGrass = ImageIO.read(new File("res/img/grass_tile_grass.png"));
            grassTileDirt = ImageIO.read(new File("res/img/grass_tile_dirt.png"));
            grassTileFullDirt = ImageIO.read(new File("res/img/grass_tile_full_dirt.png"));

            grassTileSimple1 = ImageIO.read(new File("res/img/grass_tile_simple_1.png"));
            grassTileSimple2 = ImageIO.read(new File("res/img/grass_tile_simple_2.png"));
            grassTileSimple3 = ImageIO.read(new File("res/img/grass_tile_simple_3.png"));

            bonesTile1 = ImageIO.read(new File("res/img/bones_tile_1.png"));
            bonesTile2 = ImageIO.read(new File("res/img/bones_tile_2.png"));

            treeTile = ImageIO.read(new File("res/img/tree_tile_1.png"));
            vaseTile = ImageIO.read(new File("res/img/vase_tile.png"));
            brokenVaseTile = ImageIO.read(new File("res/img/broken_vase_tile.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger une des tile : ");
            System.out.println(ioe.getMessage());
            System.exit(-1);
        }

        //**********************************************************************
        //**                       PARTIE MONSTRES                            **
        //**********************************************************************

        monster = new BufferedImage[8];

        try {
            monster[0] = ImageIO.read(new File("res/img/monster_0.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/monster_0.png");
            System.exit(-1);
        }

        try {
            monster[1] = ImageIO.read(new File("res/img/monster_1.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/monster_1.png");
            System.exit(-1);
        }

        try {
            monster[2] = ImageIO.read(new File("res/img/monster_2.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/monster_2.png");
            System.exit(-1);
        }

        try {
            monster[3] = ImageIO.read(new File("res/img/monster_3.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/monster_3.png");
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

        //**********************************************************************
        //**                       PARTIE MUR                                 **
        //**********************************************************************

        try {
            wallTile = ImageIO.read(new File("res/img/wall_tile_v2.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/wall_tile.png");
            System.exit(-1);
        }

        //**********************************************************************
        //**                       PARTIE COFFRE                              **
        //**********************************************************************

        try {
            chestTile = ImageIO.read(new File("res/img/chest_tile_v2.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/chest_tile.png");
            System.exit(-1);
        }

        //**********************************************************************
        //**                       PARTIE HERO                                **
        //**********************************************************************

        hero = new BufferedImage[8];

        try {
            hero[0] = ImageIO.read(new File("res/img/hero_0.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/hero_0.png");
            System.exit(-1);
        }

        try {
            hero[1] = ImageIO.read(new File("res/img/hero_1.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/hero_1.png");
            System.exit(-1);
        }

        try {
            hero[2] = ImageIO.read(new File("res/img/hero_2.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/hero_2.png");
            System.exit(-1);
        }

        try {
            hero[3] = ImageIO.read(new File("res/img/hero_3.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/hero_3.png");
            System.exit(-1);
        }


        //**********************************************************************
        //**                       PARTIE HERO                                **
        //**********************************************************************

        attack = new BufferedImage[4];

        try {
            attack[0] = ImageIO.read(new File("res/img/attack_S.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/attack_S.png");
            System.exit(-1);
        }

        try {
            attack[1] = ImageIO.read(new File("res/img/attack_Z.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/attack_Z.png");
            System.exit(-1);
        }

        try {
            attack[2] = ImageIO.read(new File("res/img/attack_Q.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/attack_Q.png");
            System.exit(-1);
        }

        try {
            attack[3] = ImageIO.read(new File("res/img/attack_D.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/attack_D.png");
            System.exit(-1);
        }

        //**********************************************************************
        //**                       PARTIE COEUR                               **
        //**********************************************************************

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


    public Image getGroundTile(GroundType groundType) {
        switch (groundType) {
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
            case BONES1:
                return bonesTile1;
            case BONES2:
                return bonesTile2;
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

    public Image getHero(Character h) {

        if(h.getLastMove() == "S") return hero[0];
        if(h.getLastMove() == "Z") return hero[1];
        if(h.getLastMove() == "Q") return hero[2];
        if(h.getLastMove() == "D") return hero[3];

        return hero[0];
    }

    public Image getAttack(Character c) {
        if(c.getLastMove() == "S") return attack[0];
        if(c.getLastMove() == "Z") return attack[1];
        if(c.getLastMove() == "Q") return attack[2];
        if(c.getLastMove() == "D") return attack[3];

        return attack[0];
    }

    public Image getMonster(Character m) {
        if(m.getLastMove() == "S") return monster[0];
        if(m.getLastMove() == "Z") return monster[1];
        if(m.getLastMove() == "Q") return monster[2];
        if(m.getLastMove() == "D") return monster[3];

        return monster[0];
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

    public Image getTreeTile() {
        return treeTile;
    }

    public Image getVaseTile() {
        return vaseTile;
    }

    public Image getBrokenVaseTile() {
        return brokenVaseTile;
    }


}
