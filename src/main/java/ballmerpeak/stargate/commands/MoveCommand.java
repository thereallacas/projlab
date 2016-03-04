package ballmerpeak.stargate.commands;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.tiles.Tile;

public class MoveCommand implements InputCommand {
	
	private Direction dir;
	
	public MoveCommand(Direction dir) {
		this.dir = dir;
	}
	
	@Override
	public void execute(Player player) {
		if (player.getDirection() != dir) {
			player.setDirection(dir);
		} else {
			Tile currentTile = player.getTile();
			Tile nextTile = currentTile.getNeighborForDirection(dir);
			if (nextTile.canPlayerMoveHere()) {
				currentTile.leaveTile();
				nextTile.stepOnTile(player);
			}
		}
	}

}
