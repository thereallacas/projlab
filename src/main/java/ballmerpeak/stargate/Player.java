package ballmerpeak.stargate;

import ballmerpeak.stargate.gui.Drawable;
import ballmerpeak.stargate.gui.DrawableIndex;
import ballmerpeak.stargate.tiles.ShotColor;
import ballmerpeak.stargate.tiles.Tile;

public class Player implements Drawable {

	private Direction direction = Direction.UP;
	private boolean carrying;
	private boolean isAlive;
	private int ZPMsCarried;
	private Tile tile;

	public Player() {
		isAlive = true;
		ZPMsCarried = 0;
		carrying = false;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void kill() {
		isAlive = false;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
		setDirty(true);
	}

	public int getZPMsCarried() {
		return ZPMsCarried;
	}

	public void pickupZPM() {
		ZPMsCarried++;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public void move(Direction direction) {
		if (this.direction != direction) {
			this.direction = direction;
		} else {
			Tile currentTile = tile;
			Tile nextTile = currentTile.getNeighborForDirection(direction);
			if (nextTile.stepOnTile(this)) {
				currentTile.leaveTile(this);
			}
		}
		setDirty(true);
	}

	public void pickupCrate() {
		Tile nextTile = getTileFrontOfPlayer();
		if (carrying) {
			if (nextTile.dropCrateHere(this))
				carrying = false;
		} else {
			if (nextTile.pickupCrate(this))
				carrying = true;
		}
		nextTile.setDirty(true);
		setDirty(true);
	}

	public void shoot(ShotColor color) {
		Tile nextTile = getTileFrontOfPlayer();
		nextTile.shootIt(color, direction);
	}

	@Override
	public boolean isDirty() {
		return tile.isDirty();
	}

	@Override
	public void setDirty(boolean b) {
		tile.setDirty(b);
	}

	@Override
	public DrawableIndex getDrawableIndex() {
		switch (direction) {
		case UP:
			return DrawableIndex.PLAYER_FACING_UP;
		case DOWN:
			return DrawableIndex.PLAYER_FACING_DOWN;
		case LEFT:
			return DrawableIndex.PLAYER_FACING_LEFT;
		case RIGHT:
			return DrawableIndex.PLAYER_FACING_RIGHT;
		default:
			throw new RuntimeException("shouldn't be here...");
		}
	}

	private Tile getTileFrontOfPlayer() {
		return tile.getNeighborForDirection(direction);
	}
}
