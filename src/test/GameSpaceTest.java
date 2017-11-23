package test;

import game.GameSpace;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;


public class GameSpaceTest {

    @Test
    void isValidPositionTopLeft() throws Exception {
        GameSpace gs = new GameSpace();
        assertFalse(gs.isValidPosition(-1, -1));
    }

    @Test
    void isValidPositionLeft() throws Exception {
        GameSpace gs = new GameSpace();
        assertFalse(gs.isValidPosition(-1, 1));
    }

    @Test
    void isValidPositionRight() throws Exception {
        GameSpace gs = new GameSpace();
        assertFalse(gs.isValidPosition(100, 1));
    }

    @Test
    void isValidPositionTop() throws Exception {
        GameSpace gs = new GameSpace();
        assertFalse(gs.isValidPosition(1, -1));
    }

    @Test
    void isValidPositionDown() throws Exception {
        GameSpace gs = new GameSpace();
        assertFalse(gs.isValidPosition(1, 100));
    }

    @Test
    void isValidPositionBottomRight() throws Exception {
        GameSpace gs = new GameSpace();
        assertFalse(gs.isValidPosition(100, 100));
    }

    @Test
    void isValidPositionMiddle() throws Exception {
        GameSpace gs = new GameSpace();
        assertTrue(gs.isValidPosition(4, 4));
    }
}