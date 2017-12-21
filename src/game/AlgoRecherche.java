package game;

import com.sun.org.apache.xpath.internal.SourceTree;
import environement.Room;
import characters.Character;

import java.lang.reflect.Array;

public class AlgoRecherche {

    public AlgoRecherche() {
    }


    public String rechercheChemin(Character h, Character monster, Room room) {

        String mvmt = "";

        if (monster.isAlive()) {

            boolean[][] visited = new boolean[Room.SIZE][Room.SIZE];

            System.out.println(visited[2][2]);

            //visited[monster.getPosX()][monster.getPosY()] = true;

            int posX = monster.getPosX();
            int posY = monster.getPosY();

            int cheminHaut = Integer.MAX_VALUE, cheminBas = Integer.MAX_VALUE, cheminGauche = Integer.MAX_VALUE, cheminDroite = Integer.MAX_VALUE;


            if ((h.getPosX() == posX - 1 && h.getPosY() == posY) || (h.getPosX() == posX + 1 && h.getPosY() == posY) || (h.getPosX() == posX && h.getPosY() == posY - 1) || (h.getPosX() == posX && h.getPosY() == posY + 1)) {
                return "";
            }


            if (room.isValidPosition(posX, posY - 1)) {
                cheminHaut = rechercheChemin(1, h, monster.getPosX(), monster.getPosY() - 1, room, copyVisted(visited), true);
            }

            if (room.isValidPosition(posX, posY + 1)) {
                cheminBas = rechercheChemin(1, h, monster.getPosX(), monster.getPosY() + 1, room, copyVisted(visited), true);
            }


            if (room.isValidPosition(posX - 1, posY)) {
                cheminGauche = rechercheChemin(1, h, monster.getPosX() - 1, monster.getPosY(), room, copyVisted(visited), true);
            }


            if (room.isValidPosition(posX + 1, posY)) {
                cheminDroite = rechercheChemin(1, h, monster.getPosX() + 1, monster.getPosY(), room, copyVisted(visited), true);
            }


            System.out.println("cheminHaut : " + cheminHaut);
            System.out.println("cheminBas : " + cheminBas);
            System.out.println("cheminGauche : " + cheminGauche);
            System.out.println("cheminDroite : " + cheminDroite);
            System.out.println("____________________");

            int min = Integer.min(Integer.min(cheminDroite, cheminGauche), Integer.min(cheminBas, cheminHaut));


            if (cheminHaut == min) mvmt = "Z";
            if (cheminBas == min) mvmt = "S";
            if (cheminDroite == min) mvmt = "D";
            if (cheminGauche == min) mvmt = "Q";

            System.out.println(mvmt);

        }

        return mvmt;

    }


    private int rechercheChemin(int chemin, Character h, int posX, int posY, Room room, boolean[][] visited, boolean continu) {


        if ((h.getPosX() == posX - 1 && h.getPosY() == posY) || (h.getPosX() == posX + 1 && h.getPosY() == posY) || (h.getPosX() == posX && h.getPosY() == posY - 1) || (h.getPosX() == posX && h.getPosY() == posY + 1)) {
            System.out.println("TROUVE");
            //System.out.println("CHEMIN : "+chemin);

            return chemin;
        }

        if ((posX <= 0) || (posX >= Room.SIZE - 1) || (posY <= 0) || (posY >= Room.SIZE - 1)) {
            continu = false;
            System.out.println("FALSE OUT OF MAP");
            return Integer.MAX_VALUE;
        }

        if (visited[posX][posY]) {
            continu = false;
            System.out.println("FALSE VISITED");
            return Integer.MAX_VALUE;
        }

        if (!room.isValidPosition(posX, posY)) {
            continu = false;
            System.out.println("FALSE NOT VALID");
            return Integer.MAX_VALUE;
        }

        chemin++;
        visited[posX][posY] = true;

        if (continu) {
            int droite = rechercheChemin(chemin, h, posX + 1, posY, room, visited, true);
            int gauche = rechercheChemin(chemin, h, posX - 1, posY, room, visited, true);
            int haut = rechercheChemin(chemin, h, posX, posY - 1, room, visited, true);
            int bas = rechercheChemin(chemin, h, posX, posY + 1, room, visited, true);

            /*System.out.println("droite : "+droite);
            System.out.println("bas : "+bas);
            System.out.println("gauche : "+gauche);
            System.out.println("haut : "+haut);
            System.out.println("---");*/


            int min = Integer.min(Integer.min(droite, gauche), Integer.min(bas, haut));

            return min;
        }


        return chemin;

    }


    private boolean[][] copyVisted(boolean[][] visited) {
        boolean[][] newVisited = new boolean[Room.SIZE][Room.SIZE];

        for (int i = 0; i < Room.SIZE; i++) {
            for (int j = 0; j < Room.SIZE; j++) {
                newVisited[i][j] = visited[i][j];
            }
        }

        return newVisited;
    }

}
