package game;

import environement.Room;

import java.util.ArrayList;
import java.util.List;

public class GameSpace {

    private final List<Room> rooms;

    public GameSpace() {
        rooms = new ArrayList<>();

        rooms.add(new Room());
    }

    public void placeChest() {
        int choosedRoom = (int) (Math.random() * ((rooms.size() - 1) + 1));
        rooms.get(choosedRoom).placeChestInRoom();
    }

    public String toString() {
        return rooms.get(0).toString();

    }
}
