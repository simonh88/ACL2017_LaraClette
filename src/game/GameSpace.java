package game;

import environement.Room;

import java.util.ArrayList;
import java.util.List;

public class GameSpace {

    private final List<Room> rooms;

    public GameSpace() {
        rooms = new ArrayList<>();

        rooms.add(new Room());
        placeChest();
    }

    public void placeChest() {
        int choosedRoom = (int) (Math.random() * ((rooms.size() - 1) + 1));
        rooms.get(choosedRoom).placeChestInRoom();
    }


    public boolean isValidPosition(int x, int y) {
        return rooms.get(0).isValidPosition(x, y);
    }


    public String toString() {
        return rooms.get(0).toString();
    }

    public String toString(int posx, int posy) {
        return rooms.get(0).toString(posx, posy);
    }


    public boolean isChest(int posX, int posY){
        return rooms.get(0).hasChest(posX, posY);
    }
}
