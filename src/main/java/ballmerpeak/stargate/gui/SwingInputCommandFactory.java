package ballmerpeak.stargate.gui;

import static java.awt.event.KeyEvent.*;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.commands.InputCommandFactory;
import ballmerpeak.stargate.commands.MoveCommand;
import ballmerpeak.stargate.commands.PickupCommand;
import ballmerpeak.stargate.commands.PlayerSelectionStrategy;
import ballmerpeak.stargate.commands.QuitCommand;
import ballmerpeak.stargate.commands.SelectJaffaStrategy;
import ballmerpeak.stargate.commands.SelectKebabStrategy;
import ballmerpeak.stargate.commands.ShootCommand;
import ballmerpeak.stargate.commands.UnknownCommand;
import ballmerpeak.stargate.tiles.ShotColor;

public class SwingInputCommandFactory extends InputCommandFactory {

	private KeyEvent event;

	

	public void setKeyEvent(KeyEvent e) {
		event = e;
	}
	
	private List<Integer> jaffaEvents = Arrays.asList(
			VK_W, VK_S, VK_A, VK_D,
			VK_T, VK_Y, VK_U, VK_Q
	);
	
	private List<Integer> kebabEvents = Arrays.asList(
			VK_UP, VK_DOWN, VK_LEFT, VK_RIGHT,
			VK_COMMA, VK_PERIOD, VK_SLASH, VK_M
	);

	@Override
	public InputCommand nextCommand() {
		
		if (kebabEvents.contains(event.getKeyCode())) {
			pss = kebabChooser;
		} else {
			pss = jaffaChooser; 
		}
		
		switch (event.getKeyCode()) {
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			return moveUp;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			return moveDown;
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			return moveLeft;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			return moveRight;
		case KeyEvent.VK_T:
		case KeyEvent.VK_COMMA:
			return shootYellow;
		case KeyEvent.VK_Y:
		case KeyEvent.VK_PERIOD:
			return shootBlue;
		case KeyEvent.VK_U:
		case KeyEvent.VK_SLASH:
			return pickup;
		case KeyEvent.VK_Q:
		case KeyEvent.VK_M:
			return quit;

		default:
			return unknown;
		}
	}
}
