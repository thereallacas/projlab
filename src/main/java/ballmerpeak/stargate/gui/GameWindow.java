package ballmerpeak.stargate.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.InputCommand;

public class GameWindow extends JFrame implements KeyListener {
	private Game game;
	private GameCanvas canvas;
	
	public GameWindow(Game g) {
		this.game = g;
		this.canvas = new GameCanvas(this.game);
		this.canvas.setVisible(true);
		this.add(canvas);
		this.game.setWindow(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addKeyListener(this);
	}

	public void redraw() {
		canvas.paintComponent(canvas.getGraphics());
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
		game.receiveInput(cmd);
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
