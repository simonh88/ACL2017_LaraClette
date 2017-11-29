package game;

import environement.Room;

import java.util.ArrayList;
import java.util.List;

public class GameSpace {

    private final List<Room> rooms;
    private int current_room;

    public GameSpace() {
        rooms = new ArrayList<>();
        current_room = 0;

        Room middle_room = new Room();
        Room right_room = new Room();



        rooms.add(middle_room);
        rooms.add(right_room);

        // TODO : Get les index proprement...
        middle_room.setRoomRight(1);
        right_room.setRoomLeft(0);

        placeChest();
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
     * @param indexRoom
     * @return
     */
    public Room getRoom(int indexRoom) {
        return rooms.get(indexRoom);
    }

    public Room currentRoom() {
        return rooms.get(current_room);
    }

}
