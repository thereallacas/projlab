package ballmerpeak.stargate.gui;

import java.awt.event.KeyEvent;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.commands.MoveCommand;
import ballmerpeak.stargate.commands.PickupCommand;
import ballmerpeak.stargate.commands.QuitCommand;
import ballmerpeak.stargate.commands.ShootCommand;
import ballmerpeak.stargate.commands.UnknownCommand;
import ballmerpeak.stargate.tiles.ShotColor;

public class SwingInputCommandFactory implements InputCommandFactory {
	
	private KeyEvent event;
	
	public void setKeyEvent(KeyEvent e) {
		event = e;
	}
	
	@Override
	public InputCommand nextCommand() {
		switch (event.getKeyCode()) {
		case KeyEvent.VK_UP:
			return new MoveCommand(Direction.UP);
		case KeyEvent.VK_DOWN:
			return new MoveCommand(Direction.DOWN);
		case KeyEvent.VK_LEFT:
			return new MoveCommand(Direction.LEFT);
		case KeyEvent.VK_RIGHT:
			return new MoveCommand(Direction.RIGHT);
		case KeyEvent.VK_A:
			return new ShootCommand(ShotColor.BLUE);
		case KeyEvent.VK_S:
			return new ShootCommand(ShotColor.YELLOW);
		
		case KeyEvent.VK_D:
			return new PickupCommand();
		case KeyEvent.VK_Q:
			return new QuitCommand();
		default:
			return new UnknownCommand(); 
		}
	}
}
