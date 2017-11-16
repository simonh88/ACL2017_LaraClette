package environement;

import java.util.Random;

public class Room {

    public static final int TILE_HEIGHT = 50;
    public static final int TILE_WIDTH = 50;
    public static final int SIZE = 12;

    private final Decor[][] room;

    public Room() {
        room = new Decor[SIZE][SIZE];
        setupRoomAndBorder(room);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n");
        for (int j = 0; j < room.length; j++) {
            sb.append("|");
            for (int i = 0; i < room[0].length; i++) {


                sb.append(room[j][i]);
                sb.append("|");


            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String toString(int posx, int posy) {
        StringBuilder sb = new StringBuilder();

        sb.append("\n");
        for (int j = 0; j < room.length; j++) {
            sb.append("|");
            for (int i = 0; i < room[0].length; i++) {

                if (j == posy && i == posx) {
                    sb.append("H_");
                } else {
                    sb.append(room[j][i]);
                }
                sb.append("|");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Place les murs sur le bord de la room
     * @param room
     */
    private void setupRoomAndBorder(Decor[][] room) {

        // Le vide central
        for (int j = 0; j < room.length; j++) {
            for (int i = 0; i < room[0].length; i++) {
                room[j][i] = new Empty();
            }
        }

        // Mur à gauche
        for (int j = 0; j < room.length; j++) {
            room[j][0] = new Wall();
        }

        // Mur à droite
        for (int j = 0; j < room.length; j++) {
            room[j][room[0].length-1] = new Wall();
        }

        // Mur en haut
        for (int i = 0; i < room[0].length; i++) {
            room[0][i] = new Wall();
        }

        // Mur à droite
        for (int i = 0; i < room.length; i++) {
            room[room.length - 1][i] = new Wall();
        }
    }

    /**
     * Place aléatoirement un coffre dans cette room
     */
    public void placeChestInRoom() {
        int x = 0;
        int y = 0;

        Random rand = new Random();

        while (! (room[y][x] instanceof Empty)) {
            x = Math.abs(rand.nextInt()) % (room[0].length - 2) + 1;
            y = Math.abs(rand.nextInt()) % (room.length - 2) + 1;
        }

        room[y][x] = new Chest();
    }


    /**
     *
     * @param x position abscisse
     * @param y position ordonnee
     * @return si la case a un chest
     */
    public boolean hasChest(int x, int y){
        boolean chest = false;

        if ( room[y][x].getType() == Decor.CHEST ){
            chest = true;
        }

        return chest;
    }

    public boolean isValidPosition(int x, int y) {
        if (x < 0 || y < 0) return false;
        if (x >= SIZE || y >= SIZE) return false;
        return ! (room[y][x] instanceof Wall);
    }

    public int getWidth() {
        return room[0].length;
    }

    public int getHeight() {
        return room.length;
    }

    public Decor get(int i, int j) {
        return room[j][i];
    }
}
