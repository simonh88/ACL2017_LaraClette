package test;

import engine.Cmd;
import game.Game;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void wallOutRight() {
        Game g = new Game();

        Cmd cmd = Cmd.RIGHT;
        for (int i = 0; i < 100; i++) {
            g.evolve(cmd);
        }

        assertTrue(g.getHero().getPosX() < 20);
    }

    @Test
    public void wallOutLeft() {
        Game g = new Game();

        Cmd cmd = Cmd.LEFT;
        for (int i = 0; i < 100; i++) {
            g.evolve(cmd);
        }

        assertTrue(g.getHero().getPosX() >= 0);
    }

    @Test
    public void wallOutTop() {
        Game g = new Game();

        Cmd cmd = Cmd.UP;
        for (int i = 0; i < 100; i++) {
            g.evolve(cmd);
        }

        assertTrue(g.getHero().getPosY() >= 0);
    }

    @Test
    public void wallOutBottom() {
        Game g = new Game();

        Cmd cmd = Cmd.DOWN;
        for (int i = 0; i < 100; i++) {
            g.evolve(cmd);
        }

        assertTrue(g.getHero().getPosY() < 20);
    }
}