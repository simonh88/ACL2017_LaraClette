package environement;

import java.util.Random;

public class Room {

    public static final int TILE_HEIGHT = 50;
    public static final int TILE_WIDTH = 50;
    public static final int SIZE = 11;

    private final Decor[][] room;

    private int index_room_up;
    private int index_room_bottom;
    private int index_room_left;
    private int index_room_right;


    public Room() {

        index_room_bottom = -1;
        index_room_up = -1;
        index_room_left = -1;
        index_room_right = -1;

        room = new Decor[SIZE][SIZE];

        setupRoomAndBorder(room);
    }

    public Room(int index_room_up, int index_room_bottom, int index_room_left, int index_room_right) {

        this.index_room_bottom = index_room_bottom;
        this.index_room_up = index_room_up;
        this.index_room_left = index_room_left;
        this.index_room_right = index_room_right;

        room = new Decor[SIZE][SIZE];

        setupRoomAndBorder(room);
    }

    public void setRoomUp(int index_room_up) {
        this.index_room_up = index_room_up;
        setupRoomAndBorder(room);
    }

    public void setRoomBottom(int index_room_bottom) {
        this.index_room_bottom = index_room_bottom;
        setupRoomAndBorder(room);
    }

    public void setRoomLeft(int index_room_left) {
        this.index_room_left = index_room_left;
        setupRoomAndBorder(room);
    }

    public void setRoomRight(int index_room_right) {
        this.index_room_right = index_room_right;
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

        Random rand = new Random();



        // Le vide central
        for (int j = 0; j < room.length; j++) {
            for (int i = 0; i < room[0].length; i++) {

                // Fond aléatoire

                Decor randGrass;
                int randint = Math.abs(rand.nextInt() % 100);
                if (randint < 2) {
                    randGrass = new Grass(GrassType.DIRT);
                } else if (randint < 4) {
                    randGrass = new Grass(GrassType.FULL_DIRT);
                } else if (randint < 8) {
                    randGrass = new Grass(GrassType.GRASS);
                } else {
                    randGrass = new Grass(GrassType.SIMPLE);
                }

                room[j][i] = randGrass;
            }
        }

        // Mur à gauche
        for (int j = 0; j < room.length; j++) {
            if ( j != 5 || !hasLeftRoom())
                room[j][0] = new Wall();
        }

        // Mur à droite
        for (int j = 0; j < room.length; j++) {
            if ( j != 5 || !hasRightRoom())
                room[j][room[0].length-1] = new Wall();
        }

        // Mur en haut
        for (int i = 0; i < room[0].length; i++) {
            if ( i != 5 || !hasUpRoom())
                room[0][i] = new Wall();
        }

        // Mur en bas
        for (int i = 0; i < room.length; i++) {
            if ( i != 5 || !hasBottomRoom())
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

        while (room[y][x].getType() != Decor.GRASS) {
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
        // On peut aller en dehors du plateau
        // (Utile pour le changement de map, dès qu'on sors du plateau on change de map)
        if (x < 0 || y < 0) return true;
        if (x >= SIZE || y >= SIZE) return true;
        return ! (room[y][x].getType() == Decor.WALL);
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

    public boolean hasLeftRoom() {
        return index_room_left != -1;
    }

    public boolean hasRightRoom() {
        return index_room_right != -1;
    }

    public boolean hasUpRoom() {
        return index_room_up != -1;
    }

    public boolean hasBottomRoom() {
        return index_room_bottom != -1;
    }

    public int getIndexRoomUp() {
        return index_room_up;
    }

    public int getIndexRoomBottom() {
        return index_room_bottom;
    }

    public int getIndexRoomLeft() {
        return index_room_left;
    }

    public int getIndexRoomRight() {
        return index_room_right;
    }
}
