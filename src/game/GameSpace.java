package game;

import characters.Character;
import environement.Chest;
import environement.Decor;
import environement.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameSpace {

    private static final int NB_ROOMS = 15;

    private final List<Room> rooms;
    private int current_room;

    public GameSpace() {
        rooms = new ArrayList<>();
        current_room = 0;

        generateRandomRooms();

        placeChest();

        System.out.println(rooms.get(0).toString());
    }

    public void placeChest() {
        int choosedRoom = (int) (Math.random() * ((rooms.size() - 1) + 1));
        rooms.get(choosedRoom).placeChestInRoom();
    }


    public boolean isValidPosition(int x, int y) {
        return rooms.get(current_room).isValidPosition(x, y);
    }


    public String toString() {
        return rooms.get(current_room).toString();
    }

    public String toString(int posx, int posy) {
        return rooms.get(current_room).toString(posx, posy);
    }


    public boolean isChest(int posX, int posY) {
        return rooms.get(current_room).hasChest(posX, posY);
    }


    public void goRoomLeft() {
        Room current = rooms.get(current_room);
        int index_room_left = current.getIndexRoomLeft();
        current_room = index_room_left;
    }

    public void goRoomRight() {
        Room current = rooms.get(current_room);
        int index_room_right = current.getIndexRoomRight();
        current_room = index_room_right;
    }

    public void goRoomUp() {
        Room current = rooms.get(current_room);
        int index_room_up = current.getIndexRoomUp();
        current_room = index_room_up;
    }

    public void goRoomBottom() {
        Room current = rooms.get(current_room);
        int index_room_bottom = current.getIndexRoomBottom();
        current_room = index_room_bottom;
    }

    /**
     * Renvoie la room correspondante à l'index
     *
     * @param indexRoom
     * @return
     */
    public Room getRoom(int indexRoom) {
        return rooms.get(indexRoom);
    }

    public Room currentRoom() {
        return rooms.get(current_room);
    }

    public int indexCurrentRoom() {
        return current_room;
    }

    /**
     * Génère des rooms aléatoires
     */
    private void generateRandomRooms() {

        Random rand = new Random();

        Room r = new Room();
        rooms.add(r);

        for (int i = 0; i < 10; i++) {

            // La room i
            Room current_room = rooms.get(i);

            // Combien de room voisines : (Nombre entre 1 et 3)
            int nb_room_voisines = (Math.abs(rand.nextInt()) % 3) + 1;

            // Il ne faut pas dépasser NB_ROOMS, si on a tiré un nombre de room voisines trop grand, on le passe à 0
            // -> choix totalement arbitraire

            if (rooms.size() + nb_room_voisines > NB_ROOMS) nb_room_voisines = 0;

            Room nouvelle_voisine;
            int index_nouvelle_room;

            // On ajoute les rooms voisines et les liens avec la room i
            for (int j = 0; j < nb_room_voisines; j++) {
                // On tire au hasard la position de la nouvelle room par rapport à la room i
                // En checkant si la room i a déjà une voisine en H/B/G/D

                boolean choiceOK = false;

                // On créé la room
                nouvelle_voisine = new Room();

                // On l'add au tableau
                rooms.add(nouvelle_voisine);

                while (!choiceOK) {

                    // On get son index
                    index_nouvelle_room = rooms.indexOf(nouvelle_voisine);

                    int direction = Math.abs(rand.nextInt()) % 4;

                    // Selon la direction, on créé le lien
                    switch (direction) {
                        // Haut
                        case 0:
                            if (!current_room.hasUpRoom()) {
                                // On créé le lien
                                current_room.setRoomUp(index_nouvelle_room);
                                nouvelle_voisine.setRoomBottom(i);
                                choiceOK = true;
                            }
                            break;

                        // Bas
                        case 1:
                            if (!current_room.hasBottomRoom()) {
                                // On créé le lien
                                current_room.setRoomBottom(index_nouvelle_room);
                                nouvelle_voisine.setRoomUp(i);
                                choiceOK = true;
                            }
                            break;

                        // Gauche
                        case 2:
                            if (!current_room.hasLeftRoom()) {
                                // On créé le lien
                                current_room.setRoomLeft(index_nouvelle_room);
                                nouvelle_voisine.setRoomRight(i);
                                choiceOK = true;
                            }
                            break;

                        // Droite
                        case 3:
                            if (!current_room.hasRightRoom()) {
                                // On créé le lien
                                current_room.setRoomRight(index_nouvelle_room);
                                nouvelle_voisine.setRoomLeft(i);
                                choiceOK = true;
                            }
                            break;
                    }
                }
            }
        }
    }

    public void generateMonsters(GameState gameState) {
        int x = 0;
        int y = 0;
        int indexRoom = 0;

        Random rand = new Random();

        for (Room room : rooms) {
            for (int i = 0; i < Math.abs(rand.nextInt()) % (8); i++) {
                x = Math.abs(rand.nextInt()) % (Room.SIZE - 2) + 1;
                y = Math.abs(rand.nextInt()) % (Room.SIZE - 2) + 1;
                while (room.getType(x, y) != Decor.GRASS) {
                    x = Math.abs(rand.nextInt()) % (Room.SIZE - 2) + 1;
                    y = Math.abs(rand.nextInt()) % (Room.SIZE - 2) + 1;
                }
                indexRoom = Math.abs(rand.nextInt()) % (rooms.size());


                gameState.addMonster(new Character(x, y, indexRoom));
            }
        }
    }

}
