package ballmerpeak.stargate.commands;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Player;

public class MoveCommand implements InputCommand {
	
	private Direction dir;
	
	public MoveCommand(Direction dir) {
		this.dir = dir;
	}
	
	@Override
	public void execute(Player player) {
		player.move(dir);
	}

}
