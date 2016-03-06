package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.DrawableIndex;

public class Door extends Tile {

	private boolean open = false;

	@Override
	public boolean stepOnTile(Player player) {
		if (isOpen()) {
			player.setTile(this);
			if (!isOpen())
				player.kill();
			return super.stepOnTile(player);
		}
		return false;
	}

	@Override
	public void shootIt(ShotColor color, Direction dir) {
		if (isOpen())
			super.shootIt(color, dir);
	}

	public boolean isOpen() {
		return open;
	}

	public void close() {
		open = false;
		setDirty(true);
	}

	public void open() {
		open = true;
		setDirty(true);
	}

	@Override
	public DrawableIndex getDrawableIndex() {
		return open ? DrawableIndex.DOOR_OPEN : DrawableIndex.DOOR_CLOSED;
	}
}
