package environement;

import utils.*;
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

    private List<GroundLoot> groundLoots;


    public Room() {

        index_room_bottom = -1;
        index_room_up = -1;
        index_room_left = -1;
        index_room_right = -1;

        groundLoots = new ArrayList<>();

        room = new Decor[SIZE][SIZE];

        setupRoomAndBorder();

        // Place un (plusieurs?) arbre aléatoirement (il peut y en avoir 0
        placeObjectOrNot(DecorType.TREE);

        // Pareil pour les pots
        placeObjectOrNot(DecorType.VASE);

        // Rivière
        placeRiverOrNot();
    }

    public void setNeighborRoom(Direction direction, int index_room) {
        switch (direction) {
            case UP:
                this.index_room_up = index_room;
                room[0][SIZE/2] = getRandomGround();
                break;

            case BOTTOM:
                this.index_room_bottom = index_room;
                room[SIZE-1][SIZE/2] = getRandomGround();
                break;

            case LEFT:
                this.index_room_left = index_room;
                room[SIZE/2][0] = getRandomGround();
                break;

            case RIGHT:
                this.index_room_right = index_room;
                room[SIZE/2][SIZE-1] = getRandomGround();

        }
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
     */
    private void setupRoomAndBorder() {
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
        // Pour les cas in-map, la position est valide ssi le decor sur lequel on veut aller est traversable
        return room[y][x].isTraversable();
    }

    public DecorType getType (int x, int y){
        return room[y][x].getType();
    }

    public int getWidth() {
        return SIZE;
    }

    public int getHeight() {
        return SIZE;
    }

    public Decor get(int i, int j) {
        return room[j][i];
    }

    public boolean hasNeighborRoom(Direction direction) {
        switch (direction) {
            case UP:
                return index_room_up != -1;
            case LEFT:
                return index_room_left != -1;
            case RIGHT:
                return index_room_right != -1;
            case BOTTOM:
                return index_room_bottom != -1;
        }
        return true;
    }

    public int getNeighborRoomIndex(Direction direction) {
        switch (direction) {
            case UP:
                return index_room_up;
            case LEFT:
                return index_room_left;
            case RIGHT:
                return index_room_right;
            case BOTTOM:
                return index_room_bottom;
        }
        return 0;
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
     * @param posX position en X
     * @param posY position en Y
     * @return boolean
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

    /**
     * Met la pot dans l'état cassé (setUsed) et place un coeur sur le sol
     * @param posX la position en X à laquelle on attaque
     * @param posY la position en Y à laquelle on attaque
     */
    private void heroAttackVase(int posX, int posY) {
        // Si il n'y a pas de vase, return
        if (!hasVase(posX, posY)) return;

        // Sinon, on place un coeur au sol et et met le vase dans l'état used
        placeGroundLoot(posX, posY, Loot.HEART);
        room[posY][posX].setUsed();
    }

    /**
     * Gère la touche ACTION
     * @param posX la position en X à laquelle on "action"
     * @param posY la position en X à laquelle on "action"
     * @return Loot
     */
    public Loot heroUse(int posX, int posY, boolean hasKey) {
        Loot loot = heroUseGroundObject(posX, posY);
        if (loot != Loot.NONE) return loot;

        //Il faut qu'il ai la clé pour ouvrir
        if(hasKey) {
            loot = heroUseChest(posX, posY);
            if (loot != Loot.NONE) return loot;
        }



        return loot;
    }

    /**
     * Gère l'attaque sur pot
     * @param posX la position de l'attaque
     * @param posY la position de l'attaque
     */
    public void heroAttack(int posX, int posY) {
        heroAttackVase(posX, posY);
    }

    private Loot heroUseGroundObject(int posX, int posY) {
        for (GroundLoot loot : groundLoots) {
            if (loot.getPosition().getX() == posX && loot.getPosition().getY() == posY) {
                groundLoots.remove(loot);
                return loot.getType();
            }
        }
        return Loot.NONE;
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

        boolean bridgeOK = false;
        while (!bridgeOK) {
            int choosenIndex = Math.abs(rand.nextInt()) % nbPos;
            Position pos = path.get(choosenIndex);
            if (room[pos.getY() - 1][pos.getX()].getType() == DecorType.GRASS &&
                    room[pos.getY() + 1][pos.getX()].getType() == DecorType.GRASS) {
                bridgeOK = true;
                room[pos.getY()][pos.getX()] = new Bridge();
            }
            if (room[pos.getY()][pos.getX() + 1].getType() == DecorType.GRASS &&
                    room[pos.getY()][pos.getX() - 1].getType() == DecorType.GRASS) {
                bridgeOK = true;
                room[pos.getY()][pos.getX()] = new Bridge();
            }
        }
    }

    private void placeLake(Corner corner) {
        // On détermine les positions des coins haut-gauche et bas-droit pour pouvoir dessiner le petit lac

        int startX, startY,  endX, endY;

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
        List<Position> res = getPathBetweenHelper(coinA, Corner.CENTER, null);
        res.addAll(getPathBetweenHelper(Corner.CENTER, coinB, res));
        return res;
    }

    private List<Position> getPathBetweenHelper(Corner coinA, Corner coinB, List<Position> alreadyInList) {
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


        if (alreadyInList != null) {
            forbiddenPosition.addAll(alreadyInList);
        }




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
            if (rand.nextBoolean() && diffX != 0) {
                if (diffX > 0 ) {
                    // On va à droite
                    if (! forbiddenPosition.contains(new Position(currentX + 1, currentY)))
                        currentX++;
                } else {
                    // On va à gauche
                    if (! forbiddenPosition.contains(new Position(currentX - 1, currentY)))
                        currentX--;
                }
            } else if (diffY != 0){
                if (diffY > 0 ) {
                    // On va en bas
                    if (! forbiddenPosition.contains(new Position(currentX, currentY + 1)))
                        currentY++;
                } else {
                    // On va en haut
                    if (! forbiddenPosition.contains(new Position(currentX, currentY - 1)))
                    currentY--;
                }
            }

            if (!((diffX == (endX - currentX)) && diffY == (endY - currentY))) {
                diffX = endX - currentX;
                diffY = endY - currentY;

                res.add(new Position(currentX, currentY));
            }
        }

        return res;
    }

    public void placeGroundLoot(int posX, int posY, Loot loot) {
        groundLoots.add(new GroundLoot(posX, posY, loot));
    }

    public List<GroundLoot> getGroundLoots() {
        return groundLoots;
    }
}

enum Corner {
    TOP_LEFT,
    TOP_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_RIGHT,
    CENTER // Pas vraiment un coin mais utile pour getPathBetween
}

