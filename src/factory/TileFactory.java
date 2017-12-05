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

    public static final int HERO = 1;
    public static final int HERO_ATTACK = 2;
    public static final int MONSTER = 3;
    public static final int MONSTER_ATTACK = 4;
    public static final int BOSS = 5;
    private BufferedImage bossDead;

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

    private BufferedImage waterTile;
    private BufferedImage bridgeTile;

    private BufferedImage wallTile;
    private BufferedImage chestTile;
    private BufferedImage[] hero;
    private BufferedImage[] boss;
    private BufferedImage[] attack;
    private BufferedImage[] monster;
    private BufferedImage[] attackMonster;
    private BufferedImage monsterDead;
    private BufferedImage monsterAttack;
    private BufferedImage heart;
    private BufferedImage key;
    private BufferedImage little_heart;
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
            grassTileGrass = ImageIO.read(new File("res/img/decor/grass_tile_grass.png"));
            grassTileDirt = ImageIO.read(new File("res/img/decor/grass_tile_dirt.png"));
            grassTileFullDirt = ImageIO.read(new File("res/img/decor/grass_tile_full_dirt.png"));

            grassTileSimple1 = ImageIO.read(new File("res/img/decor/grass_tile_simple_1.png"));
            grassTileSimple2 = ImageIO.read(new File("res/img/decor/grass_tile_simple_2.png"));
            grassTileSimple3 = ImageIO.read(new File("res/img/decor/grass_tile_simple_3.png"));

            bonesTile1 = ImageIO.read(new File("res/img/decor/bones_tile_1.png"));
            bonesTile2 = ImageIO.read(new File("res/img/decor/bones_tile_2.png"));

            treeTile = ImageIO.read(new File("res/img/decor/tree_tile_1.png"));
            vaseTile = ImageIO.read(new File("res/img/decor/vase_tile.png"));
            brokenVaseTile = ImageIO.read(new File("res/img/decor/broken_vase_tile.png"));

            waterTile = ImageIO.read(new File("res/img/decor/water_tile.png"));
            bridgeTile = ImageIO.read(new File("res/img/decor/bridge_tile.png"));
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
            monster[0] = ImageIO.read(new File("res/img/monsters/zombie_0.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/monsters/zombie_0.png");
            System.exit(-1);
        }

        try {
            monster[1] = ImageIO.read(new File("res/img/monsters/zombie_1.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/monsters/zombie_1.png");
            System.exit(-1);
        }

        try {
            monster[2] = ImageIO.read(new File("res/img/monsters/zombie_2.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/monsters/zombie_2.png");
            System.exit(-1);
        }

        try {
            monster[3] = ImageIO.read(new File("res/img/monsters/zombie_3.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/monsters/zombie_3.png");
            System.exit(-1);
        }

        try {
            monsterDead = ImageIO.read(new File("res/img/monsters/zombie_dead.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/monsters/zombie_dead.png");
            System.exit(-1);
        }

        //**********************************************************************
        //**                       PARTIE MUR                                 **
        //**********************************************************************

        try {
            wallTile = ImageIO.read(new File("res/img/decor/wall_tile_v2.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/decor/wall_tile.png");
            System.exit(-1);
        }

        //**********************************************************************
        //**                       PARTIE COFFRE                              **
        //**********************************************************************

        try {
            chestTile = ImageIO.read(new File("res/img/decor/chest_tile_v2.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/decor/chest_tile.png");
            System.exit(-1);
        }

        //**********************************************************************
        //**                       PARTIE HERO                                **
        //**********************************************************************

        hero = new BufferedImage[8];

        try {
            hero[0] = ImageIO.read(new File("res/img/hero/hero_0.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/hero/hero_0.png");
            System.exit(-1);
        }

        try {
            hero[1] = ImageIO.read(new File("res/img/hero/hero_1.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/hero/hero_1.png");
            System.exit(-1);
        }

        try {
            hero[2] = ImageIO.read(new File("res/img/hero/hero_2.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/hero/hero_2.png");
            System.exit(-1);
        }

        try {
            hero[3] = ImageIO.read(new File("res/img/hero/hero_3.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/hero/hero_3.png");
            System.exit(-1);
        }

        //**********************************************************************
        //**                       PARTIE BOSS                                **
        //**********************************************************************

        boss = new BufferedImage[8];

        try {
            boss[0] = ImageIO.read(new File("res/img/boss/boss_0.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/boss/boss_0.png");
            System.exit(-1);
        }

        try {
            boss[1] = ImageIO.read(new File("res/img/boss/boss_1.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/boss/boss_1.png");
            System.exit(-1);
        }

        try {
            boss[2] = ImageIO.read(new File("res/img/boss/boss_2.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/boss/boss_2.png");
            System.exit(-1);
        }

        try {
            boss[3] = ImageIO.read(new File("res/img/boss/boss_3.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/boss/boss_3.png");
            System.exit(-1);
        }

        try {
            bossDead = ImageIO.read(new File("res/img/boss/boss_dead.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/boss/boss_dead.png");
            System.exit(-1);
        }

        //**********************************************************************
        //**                       PARTIE ATTAQUE                             **
        //**********************************************************************

        attack = new BufferedImage[4];

        try {
            attack[0] = ImageIO.read(new File("res/img/attack/attack_S.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/attack/attack_S.png");
            System.exit(-1);
        }

        try {
            attack[1] = ImageIO.read(new File("res/img/attack/attack_Z.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/attack/attack_Z.png");
            System.exit(-1);
        }

        try {
            attack[2] = ImageIO.read(new File("res/img/attack/attack_Q.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/attack/attack_Q.png");
            System.exit(-1);
        }

        try {
            attack[3] = ImageIO.read(new File("res/img/attack/attack_D.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/attack/attack_D.png");
            System.exit(-1);
        }



        attackMonster = new BufferedImage[4];

        try {
            attackMonster[0] = ImageIO.read(new File("res/img/attack/attackMonster_S.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/attack/attackMonster_S.png");
            System.exit(-1);
        }

        try {
            attackMonster[1] = ImageIO.read(new File("res/img/attack/attackMonster_Z.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/attack/attackMonster_Z.png");
            System.exit(-1);
        }

        try {
            attackMonster[2] = ImageIO.read(new File("res/img/attack/attackMonster_Q.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/attack/attackMonster_Q.png");
            System.exit(-1);
        }

        try {
            attackMonster[3] = ImageIO.read(new File("res/img/attack/attackMonster_D.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/attack/attackMonster_D.png");
            System.exit(-1);
        }

        //**********************************************************************
        //**                       PARTIE COEUR                               **
        //**********************************************************************

        try {
            heart = ImageIO.read(new File("res/img/heart/heart.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/heart/heart.png");
            System.exit(-1);
        }

        try {
            little_heart = ImageIO.read(new File("res/img/heart/little_heart.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/heart/little_heart.png");
            System.exit(-1);
        }

        try {
            hp_1 = ImageIO.read(new File("res/img/heart/hp_1.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/heart/hp_1.png");
            System.exit(-1);
        }

        try {
            hp_2 = ImageIO.read(new File("res/img/heart/hp_2.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/heart/hp_2.png");
            System.exit(-1);
        }

        //**********************************************************************
        //**                       PARTIE KEY                               **
        //**********************************************************************
        try {
            key = ImageIO.read(new File("res/img/ressources/key.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger res/img/ressources/key.png");
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

    public Image getSpriteCharacterByOrientation(Character c, int cas){

        if(c.getLastMove() == "S") return getSpriteCharacterByCas(cas, 0);
        if(c.getLastMove() == "Z") return getSpriteCharacterByCas(cas, 1);
        if(c.getLastMove() == "Q") return getSpriteCharacterByCas(cas, 2);
        if(c.getLastMove() == "D") return getSpriteCharacterByCas(cas, 3);

        return hero[0];

    }

    private Image getSpriteCharacterByCas(int cas, int orientation){

        switch (cas){
            case HERO:
                return hero[orientation];
            case MONSTER:
                return monster[orientation];
            case HERO_ATTACK:
                return attack[orientation];
            case MONSTER_ATTACK:
                return attackMonster[orientation];
            case BOSS:
                return boss[orientation];

        }
        return hero[0];
    }

    public Image getMonsterDead() {
        return monsterDead;
    }

    public Image getHeart() {
        return heart;
    }

    public Image getLittleHeart() {
        return little_heart;
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

    public Image getWaterTile() {
        return waterTile;
    }

    public Image getBridgeTile() {
        return bridgeTile;
    }

    public Image getBossDead(){
        return bossDead;
    }

    public Image getKey() {
        return key;
    }
}
