package test;

import engine.Cmd;
import game.Game;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void wallOutRight() {
        Game g = new Game();

        Cmd cmd = Cmd.RIGHT;
        for (int i = 0; i < 100; i++) {
            g.evolve(cmd);
        }

        assertTrue(g.getHero().getPosX() < 20);
    }

    @Test
    void wallOutLeft() {
        Game g = new Game();

        Cmd cmd = Cmd.LEFT;
        for (int i = 0; i < 100; i++) {
            g.evolve(cmd);
        }

        assertTrue(g.getHero().getPosX() >= 0);
    }

    @Test
    void wallOutTop() {
        Game g = new Game();

        Cmd cmd = Cmd.UP;
        for (int i = 0; i < 100; i++) {
            g.evolve(cmd);
        }

        assertTrue(g.getHero().getPosY() >= 0);
    }

    @Test
    void wallOutBottom() {
        Game g = new Game();

        Cmd cmd = Cmd.DOWN;
        for (int i = 0; i < 100; i++) {
            g.evolve(cmd);
        }

        assertTrue(g.getHero().getPosY() < 20);
    }
}