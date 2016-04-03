package ballmerpeak.stargate.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.commands.InputCommandHandler;
import ballmerpeak.stargate.commands.InputCommandSource;
import ballmerpeak.stargate.utils.MapLoader;

public class GameWindow extends JFrame implements KeyListener, InputCommandSource {
	private GameRenderer canvas;
	private InputCommandHandler inputHandler;
	private Game game;
	private DrawableSource gfxModel;
	private SwingInputCommandFactory ifc;
	private SwingMapLoaderHelper mlh;

	public GameWindow() throws Exception {
		String dataDirectory = System.getProperty("user.dir") + "/src/test/resources";
		String mapDirectory = dataDirectory + "/maps/";
		String mapFile = mapDirectory + "map4.txt";
		mlh = new SwingMapLoaderHelper();
		MapLoader loader = new MapLoader(mapFile);
		loader.setHelper(mlh);

		game = loader.getGame();
		dataDirectory = System.getProperty("user.dir") + "/src/test/resources";
		GameCanvas.loadAssets(dataDirectory + "/images/");

		gfxModel = mlh.getGraphicsModel();
		canvas = new GameCanvas(gfxModel.getHeight(), gfxModel.getWidth());
		canvas.setDrawableSource(gfxModel);

		add((JPanel) canvas);

		ifc = new SwingInputCommandFactory();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(this);
		setSize(760, 760);
		setVisible(true);
		canvas.drawGame();
	}

	public static void main(String... args) throws Exception {
		new GameWindow();
	}
	

	public void setInputCommandHandler(InputCommandHandler handler) {
		this.inputHandler = handler;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		ifc.setKeyEvent(e);
		InputCommand cmd = ifc.nextCommand();
		game.setPlayerSelectionStrategy(ifc.getPlayerSelectionStrategy());
		game.receiveInput(cmd);
		if (!game.isPlayer1Alive() || !game.isPlayer2Alive()) {
			System.exit(0);
		}
		if (game.didPlayersWin()) {
			System.exit(0);
		}
		canvas.drawGame();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public InputCommand getNextCommand() {
		return null;
	}

}
