package test;

import engine.Cmd;
import environement.Room;
import game.Game;
import org.junit.Assert;
import org.junit.Test;



public class GameTest {

    @Test
    public void wallOutRight() {
        Game g = new Game();

        Cmd cmd = Cmd.RIGHT;
        for (int i = 0; i < 100; i++) {
            g.evolve(cmd);
        }

        //System.out.println("wallOutRight : Pos X Hero : " + g.getHero().getPosX());

        Assert.assertTrue(g.getHero().getPosX() < (Room.SIZE-1) );
    }



    @Test
    public void wallOutLeft() {
        Game g = new Game();

        Cmd cmd = Cmd.LEFT;
        for (int i = 0; i < 100; i++) {
            g.evolve(cmd);
        }

        //System.out.println("wallOutLeft : Pos X Hero : " + g.getHero().getPosX());


        Assert.assertTrue(g.getHero().getPosX() > 0);
    }

    @Test
    public void wallOutTop() {
        Game g = new Game();

        Cmd cmd = Cmd.UP;
        for (int i = 0; i < 100; i++) {
            g.evolve(cmd);
        }

        //System.out.println("wallOutTop : Pos Y Hero : " + g.getHero().getPosY());

        Assert.assertTrue(g.getHero().getPosY() > 0);
    }

    @Test
    public void wallOutBottom() {
        Game g = new Game();

        Cmd cmd = Cmd.DOWN;
        for (int i = 0; i < 100; i++) {
            g.evolve(cmd);
        }

        //System.out.println("wallOutBottom : Pos Y Hero : " + g.getHero().getPosY());

        Assert.assertTrue(g.getHero().getPosY() < (Room.SIZE-1));
    }



}