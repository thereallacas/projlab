package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Entity;
import ballmerpeak.stargate.gui.DrawableIndex;

public class Door extends Floor {

	private boolean open;

	public Door() {
		super();
		open = false;
	}

	@Override
	public boolean canPlayerMoveHere() {
		return isOpen();
	}

	@Override
	public void stepOnTile(Entity player) {
		super.stepOnTile(player);
		if (!isOpen())
			killEntities();
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
		killEntities();
		setDirty(true);
	}

	public void open() {
		open = true;
		setDirty(true);
	}

	@Override
	public DrawableIndex getDrawableIndex() {
		return !entities.isEmpty() ? super.getDrawableIndex()
				: open ? DrawableIndex.DOOR_OPEN : DrawableIndex.DOOR_CLOSED;
	}

	private void killEntities() {
		for (Entity entity : entities)
			entity.kill();
	}
}
