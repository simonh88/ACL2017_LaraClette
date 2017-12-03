package environement;

import javafx.geometry.Pos;
import utils.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Room {

    public static final int TILE_HEIGHT = 50;
    public static final int TILE_WIDTH = 50;
    public static final int SIZE = 11;

    private static final int NB_CHANCE_RIVER = 1;
    private static final int NB_CHANCE_OBJECT = 2;

    private static final int LAKE_SIZE = 1;

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

        // Place un (plusieurs?) arbre aléatoirement (il peut y en avoir 0
        placeObjectOrNot(DecorType.TREE);

        // Pareil pour les pots
        placeObjectOrNot(DecorType.VASE);

        // Rivière
        placeRiverOrNot();
    }

    public void setRoomUp(int index_room_up) {
        this.index_room_up = index_room_up;
        room[0][SIZE/2] = getRandomGround();
    }

    public void setRoomBottom(int index_room_bottom) {
        this.index_room_bottom = index_room_bottom;
        room[SIZE-1][SIZE/2] = getRandomGround();
    }

    public void setRoomLeft(int index_room_left) {
        this.index_room_left = index_room_left;
        room[SIZE/2][0] = getRandomGround();
    }

    public void setRoomRight(int index_room_right) {
        this.index_room_right = index_room_right;
        room[SIZE/2][SIZE-1] = getRandomGround();
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
                // Fond aléatoire
                Decor randGrass = getRandomGround();
                room[j][i] = randGrass;
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

        // Mur en bas
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

        while (room[y][x].getType() != DecorType.GRASS) {
            x = Math.abs(rand.nextInt()) % (SIZE - 4) + 2;
            y = Math.abs(rand.nextInt()) % (SIZE - 4) + 2;
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
        int randint = Math.abs(rand.nextInt()) % NB_CHANCE_OBJECT;

        // Une chance sur deux
        if (randint == 0) return;

        int randx = (Math.abs(rand.nextInt()) % (SIZE-4)) + 2;
        int randy = (Math.abs(rand.nextInt()) % (SIZE-4)) + 2;

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

    private void placeRiverOrNot() {
        Random rand = new Random();

        int randint = Math.abs(rand.nextInt()) % NB_CHANCE_RIVER;

        if (randint == 0) placeRiver();
    }

    private void placeRiver() {
        // Une rivière ira d'un coin A à un coin B

        // On choisit le coin A aléatoirement
        Random rand = new Random();

        final Corner coinA = Corner.values()[Math.abs(rand.nextInt()) % 4];

        // On choisit le coin B
        final Corner coinB = Corner.values()[Math.abs(rand.nextInt()) % 4];

        // Si le coin A est le même que le coin B, on fait un petit point d'eau au point A
        if (coinA == coinB) {
            placeLake(coinA);
            return;
        }

        // On détermine un chemin entre le coin A et le coin B
        List<Position> path = getPathBetween(coinA, coinB);

        // On place l'eau
        for (Position p : path) {
            room[p.getY()][p.getX()] = new Water();
        }

        // On place les bridges
        // On doit trouver un moyen de placer des ponts correctement

        // Il y a nbPos case de rivière
        int nbPos = path.size();

        // On va placer le pont dans le premier quart de la rivière
        int maxPosIndex = nbPos / 4;

        // L'index de la première tile du pont
        int chosenPosIndex = Math.abs(rand.nextInt()) % maxPosIndex;

        Position firstTilePos = path.get(chosenPosIndex);
        Position secondTilePos = path.get(chosenPosIndex+1);
        Position thirdTilePos = path.get(chosenPosIndex+2);

        room[firstTilePos.getY()][firstTilePos.getX()] = new Bridge();
        room[secondTilePos.getY()][secondTilePos.getX()] = new Bridge();
        room[thirdTilePos.getY()][thirdTilePos.getX()] = new Bridge();
    }

    private void placeLake(Corner corner) {
        // On détermine les positions des coins haut-gauche et bas-droit pour pouvoir dessiner le petit lac

        int startX, startY,  endX, endY = 0;

        switch (corner) {
            case TOP_LEFT:
                startX = 1;
                startY = 1;

                break;

            case TOP_RIGHT:
                startX = SIZE - 2 - LAKE_SIZE;
                startY = 1;
                break;

            case BOTTOM_LEFT:
                startX = 1;
                startY = SIZE - 2 - LAKE_SIZE;
                break;

            case BOTTOM_RIGHT:
                startX = SIZE - 2 - LAKE_SIZE;
                startY = SIZE - 2 - LAKE_SIZE;
                break;

            default:
                startX = 1;
                startY = 1;
        }

        endX = startX + LAKE_SIZE;
        endY = startY + LAKE_SIZE;


        for (int j = startY; j <= endY; j++) {
            for (int i = startX; i <= endX; i++) {
                room[j][i] = new Water();
            }
        }
    }

    private List<Position> getPathBetween(Corner coinA, Corner coinB) {
        // Une river passe toujours par le centre
        List<Position> res = getPathBetweenHelper(coinA, Corner.CENTER);
        res.addAll(getPathBetweenHelper(Corner.CENTER, coinB));
        return res;
    }

    private List<Position> getPathBetweenHelper(Corner coinA, Corner coinB) {
        Random rand = new Random();
        List<Position> res = new ArrayList<>();

        List<Position> forbiddenPosition = new ArrayList<>();
        // Porte haute
        forbiddenPosition.add(new Position(SIZE / 2, 1));
        forbiddenPosition.add(new Position(SIZE / 2 + 1, 1));
        forbiddenPosition.add(new Position(SIZE / 2 - 1, 1));

        // Porte basse
        forbiddenPosition.add(new Position(SIZE / 2, SIZE -2));
        forbiddenPosition.add(new Position(SIZE / 2, SIZE -2));
        forbiddenPosition.add(new Position(SIZE / 2, SIZE -2));

        // Porte droite
        forbiddenPosition.add(new Position(SIZE - 2, SIZE / 2));
        forbiddenPosition.add(new Position(SIZE - 2, SIZE / 2 + 1));
        forbiddenPosition.add(new Position(SIZE - 2, SIZE / 2 - 1));

        // Porte gauche
        forbiddenPosition.add(new Position(1, SIZE / 2));
        forbiddenPosition.add(new Position(1, SIZE / 2 + 1));
        forbiddenPosition.add(new Position(1, SIZE / 2 - 1));




        // On détermine les positions de départ et d'arrivé
        int startX = 1, startY = 1, endX = 1, endY = 1;

        switch (coinA) {
            case TOP_LEFT:
                startX = 1;
                endX = 1;
                break;
            case TOP_RIGHT:
                startX = SIZE - 2;
                startY = 1;
                break;
            case BOTTOM_LEFT:
                startX = 1;
                startY = SIZE - 2;
                break;
            case BOTTOM_RIGHT:
                startX = SIZE - 2;
                startY = SIZE - 2;
                break;
            case CENTER:
                startX = SIZE / 2 ;
                startY = SIZE / 2 ;
                break;
        }

        switch (coinB) {
            case TOP_LEFT:
                endX = 1;
                endY = 1;
                break;
            case TOP_RIGHT:
                endX = SIZE - 2;
                endY = 1;
                break;
            case BOTTOM_LEFT:
                endX = 1;
                endY = SIZE - 2;
                break;
            case BOTTOM_RIGHT:
                endX = SIZE - 2;
                endY = SIZE - 2;
                break;
            case CENTER:
                endX = SIZE / 2;
                endY = SIZE / 2;
                break;
        }

        // On ajoute la première position
        res.add(new Position(startX, startY));


        // Pour aller de A à B, il n'y a que deux directions qui permettent de réduire la distance
        // 1 ou 0 parmis G / D
        // 1 ou 0 parmis H / B

        int diffX, diffY, currentX, currentY;
        boolean go_right, go_left, go_up, go_down;

        currentX = startX;
        currentY = startY;

        diffX = endX - startX;
        diffY = endY - startY;

        while (Math.abs(diffX) + Math.abs(diffY) != 0) {
            // Mouvement horizontal ou vertical
            if (rand.nextBoolean()) {
                if (diffX == 0) continue;
                else if (diffX > 0 ) {
                    // On va à droite
                    if (! forbiddenPosition.contains(new Position(currentX + 1, currentY)))
                        currentX++;
                } else {
                    // On va à gauche
                    if (! forbiddenPosition.contains(new Position(currentX - 1, currentY)))
                        currentX--;
                }
            } else {
                if (diffY == 0) continue;
                else if (diffY > 0 ) {
                    // On va en bas
                    if (! forbiddenPosition.contains(new Position(currentX, currentY + 1)))
                        currentY++;
                } else {
                    // On va en haut
                    if (! forbiddenPosition.contains(new Position(currentX, currentY - 1)))
                    currentY--;
                }
            }

            diffX = endX - currentX;
            diffY = endY - currentY;

            res.add(new Position(currentX, currentY));
        }

        return res;
    }
}

enum Corner {
    TOP_LEFT,
    TOP_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_RIGHT,
    CENTER // Pas vraiment un coin mais utile pour getPathBetween
}

enum Direction {
    TOP,
    BOTTOM,
    LEFT,
    RIGHT
}

