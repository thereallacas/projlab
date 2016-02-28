package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.Position;
import ballmerpeak.stargate.gui.DrawableIndex;

public class Door extends Floor {

	public Door(Position pos) {
		super(pos);
	}

	private boolean open = false;

	@Override
	public boolean canPlayerMoveHere() {
		return isOpen();
	}
	
	@Override
	public void stepOnTile(Player player) {
		super.stepOnTile(player);
		if (!isOpen())
			player.kill();
	}

	@Override
	public ShotResult shootIt(ShotColor color) {
		return isOpen() ? ShotResult.TILE_HIT : ShotResult.REGULAR_WALL_HIT;
	}

	public boolean isOpen() {
		return open;
	}

	public void close() {
		open = false;
	}

	public void open() {
		open = true;
	}

	@Override
	public DrawableIndex getDrawableIndex() {
		return open ? DrawableIndex.DOOR_OPEN : DrawableIndex.DOOR_CLOSED;
	}
}
