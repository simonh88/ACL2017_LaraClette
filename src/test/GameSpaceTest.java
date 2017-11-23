package test;

import game.GameSpace;

import org.junit.Test;
import static org.junit.Assert.*;


public class GameSpaceTest {

    @Test
    public void isValidPositionTopLeft() throws Exception {
        GameSpace gs = new GameSpace();
        assertFalse(gs.isValidPosition(-1, -1));
    }

    @Test
    public void isValidPositionLeft() throws Exception {
        GameSpace gs = new GameSpace();
        assertFalse(gs.isValidPosition(-1, 1));
    }

    @Test
    public void isValidPositionRight() throws Exception {
        GameSpace gs = new GameSpace();
        assertFalse(gs.isValidPosition(100, 1));
    }

    @Test
    public void isValidPositionTop() throws Exception {
        GameSpace gs = new GameSpace();
        assertFalse(gs.isValidPosition(1, -1));
    }

    @Test
    public void isValidPositionDown() throws Exception {
        GameSpace gs = new GameSpace();
        assertFalse(gs.isValidPosition(1, 100));
    }

    @Test
    public void isValidPositionBottomRight() throws Exception {
        GameSpace gs = new GameSpace();
        assertFalse(gs.isValidPosition(100, 100));
    }

    @Test
    public void isValidPositionMiddle() throws Exception {
        GameSpace gs = new GameSpace();
        assertTrue(gs.isValidPosition(4, 4));
    }
}