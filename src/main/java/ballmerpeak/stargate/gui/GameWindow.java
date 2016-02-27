package ballmerpeak.stargate.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;

import ballmerpeak.stargate.InputCommand;
import ballmerpeak.stargate.Labyrinth;
import ballmerpeak.stargate.utils.MapLoader;

public class GameWindow extends JFrame implements KeyListener, InputCommandSource, GameRenderer {
	private GameCanvas canvas;
	private InputCommandHandler inputHandler;
	private Labyrinth labyrinth;
	
	public GameWindow() throws FileNotFoundException, IOException {
		MapLoader loader = new MapLoader();

		// TODO use Path API
		String dataDirectory = System.getProperty("user.dir") + "/src/test/resources";
		String mapDirectory = dataDirectory + "/maps";
		String mapFile = mapDirectory + "/map2.txt";
		labyrinth = loader.loadLabyrinth(mapFile);
		GameCanvas.loadAssets(dataDirectory + "/images/");
		
		canvas = new GameCanvas();
		canvas.setVisible(true);
		add(canvas);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(this);
		
		setInputCommandHandler(labyrinth);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(760, 760);
		setVisible(true);
		drawGame();
	}
	
	public static void main(String... args) throws IOException {
		GameWindow window = new GameWindow();
	}
	
	public void setInputCommandHandler(InputCommandHandler handler) {
		this.inputHandler = handler;
	}

	public void drawGame() {
		canvas.paintGame(labyrinth, canvas.getGraphics());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		InputCommand cmd = InputCommand.UNKNOWN_COMMAND;
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			cmd = InputCommand.UP_COMMAND;
			break;
		case KeyEvent.VK_DOWN:
			cmd = InputCommand.DOWN_COMMAND;
			break;
		case KeyEvent.VK_LEFT:
			cmd = InputCommand.LEFT_COMMAND;
			break;
		case KeyEvent.VK_RIGHT:
			cmd = InputCommand.RIGHT_COMMAND;
			break;
		case KeyEvent.VK_A:
			cmd = InputCommand.SHOOT_BLUE_COMMAND;
			break;
		case KeyEvent.VK_S:
			cmd = InputCommand.SHOOT_YELLOW_COMMAND;
			break;
		case KeyEvent.VK_D:
			cmd = InputCommand.PICKUP_COMMAND;
			break;
		case KeyEvent.VK_Q:
			cmd = InputCommand.QUIT_COMMAND;
			break;
		default:
			break;
		}
		if(inputHandler != null) inputHandler.receiveInput(cmd);
		this.drawGame();
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
