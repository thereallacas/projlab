package ballmerpeak.stargate.commands;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.tiles.ShotColor;

public abstract class InputCommandFactory {
	
	protected PlayerSelectionStrategy jaffaChooser = new SelectJaffaStrategy();
	protected PlayerSelectionStrategy kebabChooser = new SelectKebabStrategy();
	
	protected PlayerSelectionStrategy pss = kebabChooser;
	
	protected InputCommand moveUp = new MoveCommand(Direction.UP);
	protected InputCommand moveDown = new MoveCommand(Direction.DOWN);
	protected InputCommand moveLeft = new MoveCommand(Direction.LEFT);
	protected InputCommand moveRight = new MoveCommand(Direction.RIGHT);

	protected InputCommand shootBlue = new ShootCommand(ShotColor.BLUE);
	protected InputCommand shootYellow = new ShootCommand(ShotColor.YELLOW);

	protected InputCommand pickup = new PickupCommand();

	protected InputCommand quit = new QuitCommand();

	protected InputCommand unknown = new UnknownCommand();
	
	public abstract InputCommand nextCommand();
	
	public PlayerSelectionStrategy getPlayerSelectionStrategy() {
		return pss;
	}
}
