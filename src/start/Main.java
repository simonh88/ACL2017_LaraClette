package start;


import game.Game;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		Game game = new Game();
		game.show();
	}

}
