package game;

import characters.Hero;

import java.util.Scanner;

public class Game {

    private final GameSpace gameSpace;
    private final GameState gameState;

    //O


    public Game() {
        this.gameSpace = new GameSpace();
        this.gameState = new GameState();
    }

    public void show() {
        System.out.println(gameSpace);
    }

    public void run(){
        while(gameState.isRunning()){
            //PRINT DE LETAT DU JEU
            System.out.println(toString());
            //DEMANDE DE COMMANDE DE MOUVEMENT
            System.out.println("Commandes de deplacement:\nZ -> haut S -> bas, Q -> gauche, D -> droite");
            System.out.println("Quel deplacement ?");
            Scanner in = new Scanner(System.in);

            char key = in.next().charAt(0);
            Hero h = gameState.getHero();
            switch (key){
                case 'q'://Gauche
                    if(gameSpace.isValidPosition(gameState.getHero().getPosX(), gameState.getHero().getPosY()-1)){
                    h.setPosX(gameState.getHero().getPosX()-1);
                    h.setPosY(gameState.getHero().getPosY());
                }
                    break;
                case 's':
                    if(gameSpace.isValidPosition(gameState.getHero().getPosX(), gameState.getHero().getPosY()+1)){
                    h.setPosX(gameState.getHero().getPosX());
                    h.setPosY(gameState.getHero().getPosY()+1);
                }

                    break;
                case 'd':
                    if(gameSpace.isValidPosition(gameState.getHero().getPosX()+1, gameState.getHero().getPosY())){
                    h.setPosX(gameState.getHero().getPosX()+1);
                    h.setPosY(gameState.getHero().getPosY());
                }

                    break;
                case 'z':
                    if(gameSpace.isValidPosition(gameState.getHero().getPosX()-1, gameState.getHero().getPosY())){
                        h.setPosX(gameState.getHero().getPosX());
                        h.setPosY(gameState.getHero().getPosY()-1);
                    }

                    break;
                case 'e':
                    break;
            }

            if(gameSpace.isChest(h.getPosY(), h.getPosX())){
                gameState.setVictory();
            }
        }
        System.out.println("\n\n====== VICTOIRE ==== ");
    }


    public boolean isValidPosition(int x, int y) {
        return gameSpace.isValidPosition(x, y);
    }

    public String toString(){
        return gameSpace.toString(gameState.getHero().getPosX(), gameState.getHero().getPosY());

    }
}
