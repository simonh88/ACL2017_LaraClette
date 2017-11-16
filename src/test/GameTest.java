package test;

import engine.Cmd;
import game.Game;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private static int TEST_DURATION = 10;

    /**
     * Evolve aléatoirement pendant TEST_DURATION (seulement mouvement)
     */
    @Test
    void evolve() {

        Game g = new Game();

        long start_time = System.currentTimeMillis();
        long duration_millis = TEST_DURATION * 1000;
        long end_time = start_time + duration_millis;

        long current_time = System.currentTimeMillis();

        Random rand = new Random();

        while (current_time < end_time) {

            Cmd cmd;
            int r = rand.nextInt() % 4;
            switch (r) {
                case 0:
                    cmd = Cmd.LEFT;
                    break;
                case 1:
                    cmd = Cmd.RIGHT;
                    break;
                case 2:
                    cmd = Cmd.DOWN;
                    break;
                case 3:
                    cmd = Cmd.UP;
                    break;
                default:
                    cmd = Cmd.UP;
            }

            g.evolve(cmd);
            current_time = System.currentTimeMillis();
        }
    }

}