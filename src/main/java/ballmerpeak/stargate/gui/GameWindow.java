package ballmerpeak.stargate.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;

import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.Labyrinth;
import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.utils.MapLoader;

public class GameWindow extends JFrame implements KeyListener, InputCommandSource {
	private GameCanvas canvas;
	private InputCommandHandler inputHandler;
	private Game game;

	public GameWindow() throws FileNotFoundException, IOException {
		MapLoader loader = new MapLoader();
		String dataDirectory = System.getProperty("user.dir") + "/src/test/resources";
		String mapDirectory = dataDirectory + "/maps/";
		String mapFile = mapDirectory + "map4.txt";
		Labyrinth labyrinth = loader.loadLabyrinth(mapFile);
		game = new Game(labyrinth);
		
		dataDirectory = System.getProperty("user.dir") + "/src/test/resources";
		GameCanvas.loadAssets(dataDirectory + "/images/");

		canvas = new GameCanvas();
		canvas.setVisible(true);
		add(canvas);
		
		setInputCommandHandler(game);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(this);
		setSize(760, 760);
		setVisible(true);
		canvas.drawGame(game);
	}

	public static void main(String... args) throws IOException {
		new GameWindow();
	}

	public void setInputCommandHandler(InputCommandHandler handler) {
		this.inputHandler = handler;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		InputCommand cmd = InputCommandFactory.inputCommandFromEvent(e);
		inputHandler.receiveInput(cmd);
		canvas.drawGame(game);
		if (!game.isPlayerAlive()) {
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
