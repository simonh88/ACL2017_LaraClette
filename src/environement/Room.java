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

                Decor randGrass = getRandomGround();
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

        // Place un (plusieurs?) arbre aléatoirement (il peut y en avoir 0
        placeObjectOrNot(DecorType.TREE);

        // Pareil pour les pots
        placeObjectOrNot(DecorType.VASE);
    }


    /**
     * Place aléatoirement un coffre dans cette room
     */
    public void placeChestInRoom() {
        int x = 0;
        int y = 0;

        Random rand = new Random();

        while (room[y][x].getType() != DecorType.GRASS) {
            x = Math.abs(rand.nextInt()) % (room[0].length - 2) + 1;
            y = Math.abs(rand.nextInt()) % (room.length - 2) + 1;
        }

        room[y][x] = new Chest();
    }





    public boolean isValidPosition(int x, int y) {
        // On peut aller en dehors du plateau
        // (Utile pour le changement de map, dès qu'on sors du plateau on change de map)
        if (x < 0 || y < 0) return true;
        if (x >= SIZE || y >= SIZE) return true;
        return room[y][x].isTraversable();
    }

    public DecorType getType (int x, int y){
        return room[x][y].getType();
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

    private Grass getRandomGround() {
        Random rand = new Random();
        Grass randGrass;

        int randint = Math.abs(rand.nextInt() % 100);
        if (randint < 2) {
            randGrass = new Grass(GroundType.DIRT);
        } else if (randint < 4) {
            randGrass = new Grass(GroundType.FULL_DIRT);
        } else if (randint < 8) {
            randGrass = new Grass(GroundType.GRASS);
        } else if (randint < 40) {
            randGrass = new Grass(GroundType.SIMPLE1);
        } else if (randint < 75) {
            randGrass = new Grass(GroundType.SIMPLE2);
        } else if (randint < 77) {
            randGrass = new Grass(GroundType.BONES1);
        } else if (randint < 79) {
            randGrass = new Grass(GroundType.BONES2);
        } else {
            randGrass = new Grass(GroundType.SIMPLE3);
        }
        return randGrass;
    }

    private void placeObjectOrNot(DecorType decorType) {
        Random rand = new Random();
        int randint = Math.abs(rand.nextInt()) % 2;

        // Une chance sur deux
        if (randint == 0) return;

        // TODO : ne pas hardcoder
        int randx = (Math.abs(rand.nextInt()) % 6) + 3;
        int randy = (Math.abs(rand.nextInt()) % 6) + 3;

        Decor c;
        switch (decorType) {
            case TREE:
                c = new Tree();
                break;
            case VASE:
                c = new Vase();
                break;
            default:
                c = new Vase();
        }

        room[randy][randx] = c;

    }

    /**
     * Retourne vrai si il y a un vase non cassé à la pos (X,Y)
     * @param posX
     * @param posY
     * @return
     */
    private boolean hasVase(int posX, int posY) {
        if (posX < 0 || posX >= SIZE || posY < 0 || posY >= SIZE) return false;
        return room[posY][posX].getType() == DecorType.VASE && ! room[posY][posX].hasBeenUsed();
    }

    /**
     *
     * @param posX position abscisse
     * @param posY position ordonnee
     * @return si la case a un chest
     */
    private boolean hasChest(int posX, int posY){
        if (posX < 0 || posX >= SIZE || posY < 0 || posY >= SIZE) return false;
        return room[posY][posX].getType() == DecorType.CHEST;
    }


    private Loot heroUseChest(int posX, int posY) {
        if (!hasChest(posX, posY)) return Loot.NONE;
        return Loot.VICTORY;
    }

        private Loot heroUseVase(int posX, int posY) {
        if (!hasVase(posX, posY)) return Loot.NONE;

        Random rand = new Random();

        room[posY][posX].setUsed();

        int randint = Math.abs(rand.nextInt()) % 2;

        if (randint == 0) {
            return Loot.HEART;
        } else {
            return Loot.POISON;
        }
    }

    public Loot heroUse(int posX, int posY) {
        Loot lootSiVase = heroUseVase(posX, posY);
        if (lootSiVase != Loot.NONE) return lootSiVase;

        Loot lootSiChest = heroUseChest(posX, posY);
        return lootSiChest;
    }
}
