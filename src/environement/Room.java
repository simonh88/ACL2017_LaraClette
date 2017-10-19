package environement;

public class Room {

    private final char[][] room;

    public Room() {
        room = new char[12][12];
        setupRoomAndBorder(room);
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




                if (room[j][i] == ' ') {
                    sb.append("__|");
                }

                if (room[j][i] == 'W') {
                    sb.append("WW|");
                }

            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Place les murs sur le bord de la room
     * @param room
     */
    private void setupRoomAndBorder(char[][] room) {

        // Le vide central
        for (int j = 0; j < room.length; j++) {
            for (int i = 0; i < room[0].length; i++) {
                room[j][i] = ' ';
            }
        }

        // Mur à gauche
        for (int j = 0; j < room.length; j++) {
            room[j][0] = 'W';
        }

        // Mur à droite
        for (int j = 0; j < room.length; j++) {
            room[j][room[0].length-1] = 'W';
        }

        // Mur en haut
        for (int i = 0; i < room[0].length; i++) {
            room[0][i] = 'W';
        }

        // Mur à droite
        for (int i = 0; i < room.length; i++) {
            room[room.length - 1][i] = 'W';
        }
    }
}
