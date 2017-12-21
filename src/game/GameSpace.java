package game;

import characters.Character;
import environement.DecorType;
import environement.Room;
import utils.Direction;

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
    }

    private void placeChest() {
        int choosedRoom = (int) (Math.random() * ((rooms.size() - 1) + 1));
        rooms.get(choosedRoom).placeChestInRoom();
    }

    int sizeRooms(){
        return rooms.size();
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


    void goRoom(Direction dir) {
        Room current = rooms.get(current_room);
        current_room = current.getNeighborRoomIndex(dir);
    }

    /**
     * Renvoie la room correspondante à l'index
     *
     * @param indexRoom l'index de la room
     * @return la room
     */
    Room getRoom(int indexRoom) {
        return rooms.get(indexRoom);
    }

    Room currentRoom() {
        return rooms.get(current_room);
    }

    int indexCurrentRoom() {
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
                            if (!current_room.hasNeighborRoom(Direction.UP)) {
                                // On créé le lien
                                current_room.setNeighborRoom(Direction.UP, index_nouvelle_room);
                                nouvelle_voisine.setNeighborRoom(Direction.BOTTOM, i);
                                choiceOK = true;
                            }
                            break;

                        // Bas
                        case 1:
                            if (!current_room.hasNeighborRoom(Direction.BOTTOM)) {
                                // On créé le lien
                                current_room.setNeighborRoom(Direction.BOTTOM, index_nouvelle_room);
                                nouvelle_voisine.setNeighborRoom(Direction.UP, i);
                                choiceOK = true;
                            }
                            break;

                        // Gauche
                        case 2:
                            if (!current_room.hasNeighborRoom(Direction.LEFT)) {
                                // On créé le lien
                                current_room.setNeighborRoom(Direction.LEFT, index_nouvelle_room);
                                nouvelle_voisine.setNeighborRoom(Direction.RIGHT, i);
                                choiceOK = true;
                            }
                            break;

                        // Droite
                        case 3:
                            if (!current_room.hasNeighborRoom(Direction.RIGHT)) {
                                // On créé le lien
                                current_room.setNeighborRoom(Direction.RIGHT, index_nouvelle_room);
                                nouvelle_voisine.setNeighborRoom(Direction.LEFT, i);
                                choiceOK = true;
                            }
                            break;
                    }
                }
            }
        }
    }

    void generateMonsters(GameState gameState) {
        int x ;
        int y ;
        int indexRoom ;

        Random rand = new Random();

        // Pour chaque room
        for (Room room : rooms) {

            // On itère aléatoirement entre 0 et 8 fois
            for (int i = 0; i < Math.abs(rand.nextInt()) % (8); i++) {

                x = Math.abs(rand.nextInt()) % (Room.SIZE - 2) + 1;
                y = Math.abs(rand.nextInt()) % (Room.SIZE - 2) + 1;
                while (room.get(x, y).getType() != DecorType.GRASS) {
                    x = Math.abs(rand.nextInt()) % (Room.SIZE - 2) + 1;
                    y = Math.abs(rand.nextInt()) % (Room.SIZE - 2) + 1;
                }

                //indexRoom = Math.abs(rand.nextInt()) % (rooms.size());
                indexRoom = rooms.indexOf(room);

                gameState.addMonster(new Character(x, y, indexRoom, 2, 0));
            }
        }
    }

}
