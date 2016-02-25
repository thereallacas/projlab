package ballmerpeak.stargate.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.InputCommand;

public class GameWindow extends JFrame implements KeyListener, InputCommandSource, GameRenderer {
	private GameCanvas canvas;
	private InputCommandHandler inputHandler;
	
	public GameWindow() {
		this.canvas = new GameCanvas();
		this.canvas.setVisible(true);
		this.add(canvas);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.inputHandler = null;
		this.addKeyListener(this);
	}
	
	public static void main(String... args) {
		Game game = new Game();
		GameWindow window = new GameWindow();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(760, 760);
		window.setVisible(true);
		game.setRenderer(window);
		window.setInputCommandHandler(game);
		window.drawGame(game);
	}
	
	public void setInputCommandHandler(InputCommandHandler handler) {
		this.inputHandler = handler;
	}

	public void drawGame(Game game) {
		canvas.paintGame(game, canvas.getGraphics());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		InputCommand cmd = InputCommand.UNKNOWN_KEY;
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			cmd = InputCommand.UP_KEY;
			break;
		case KeyEvent.VK_DOWN:
			cmd = InputCommand.DOWN_KEY;
			break;
		case KeyEvent.VK_LEFT:
			cmd = InputCommand.LEFT_KEY;
			break;
		case KeyEvent.VK_RIGHT:
			cmd = InputCommand.RIGHT_KEY;
		default:
			break;
		}
		if(inputHandler != null) inputHandler.receiveInput(cmd);
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
