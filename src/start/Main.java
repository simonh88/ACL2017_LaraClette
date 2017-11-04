package start;


import engine.GameEngineGraphical;
import game.Controller;
import game.Game;
import game.Painter;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        Controller controller = new Controller();
        Painter painter = new Painter();
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);

	}

}
