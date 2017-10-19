package environement;

public class Room {

    private final char[][] room;

    public Room() {
        room = new char[12][12];
        setupBorderWall(room);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < room[0].length; i++) {
            sb.append("___");
        }
        sb.append("\n");
        for (int j = 0; j < room.length; j++) {
            sb.append("|");
            for (int i = 0; i < room[0].length; i++) {
                sb.append("__|");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void setupBorderWall(Room room) {
        for (int j = 0; j < room.room.length; j++) {
            for (int i = 0; i < room.room[0].length; i++) {

            }
        }
    }
}
