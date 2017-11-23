package start;


import engine.GameEngineGraphical;
import factory.SoundFactory;
import game.Controller;
import game.Game;
import game.Painter;
import factory.TileFactory;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
        TileFactory.instance();
        //SoundFactory.instance();
        Game game = new Game();
        Controller controller = new Controller();
        Painter painter = new Painter(game);
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
        engine.run();
	}

}
