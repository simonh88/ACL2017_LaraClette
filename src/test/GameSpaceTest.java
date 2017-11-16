package test;

import game.GameSpace;

import static org.junit.Assert.*;

public class GameSpaceTest {
    @org.junit.Test
    public void placeChest() throws Exception {
        GameSpace gs = new GameSpace();
        gs.placeChest();
        gs = new GameSpace();
        gs.placeChest();
        gs = new GameSpace();
        gs.placeChest();
        gs = new GameSpace();
        gs.placeChest();
    }

    @org.junit.Test
    public void isValidPosition() throws Exception {
        GameSpace gs = new GameSpace();
        assertFalse(gs.isValidPosition(-1, -1));
        assertFalse(gs.isValidPosition(-1, 1));
        assertFalse(gs.isValidPosition(1, -1));
        assertFalse(gs.isValidPosition(14, 3));
        assertTrue(gs.isValidPosition(3, 3));
    }
}