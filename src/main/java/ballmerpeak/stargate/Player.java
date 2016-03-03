package ballmerpeak.stargate;

import ballmerpeak.stargate.gui.Drawable;
import ballmerpeak.stargate.gui.DrawableIndex;
import ballmerpeak.stargate.tiles.Tile;

public class Player implements Drawable {

	private Direction direction = Direction.UP;
	private boolean isCarrying;
	private boolean isAlive;
	private int ZPMsCarried;
	private Tile tile;
	
	public Player() {
		isAlive = true;
		ZPMsCarried = 0;
		isCarrying = false;
	}
	
	public boolean isCarrying() {
		return isCarrying;
	}
	
	public void setCarrying() {
		isCarrying = true;
	}
	
	public void unsetCarrying() {
		isCarrying = false;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void kill() {
		isAlive = false;
	}
	
	@Override
	public DrawableIndex getDrawableIndex() {
		switch (getDirection()) {
		case UP: return DrawableIndex.PLAYER_FACING_UP;
		case DOWN: return DrawableIndex.PLAYER_FACING_DOWN;
		case LEFT: return DrawableIndex.PLAYER_FACING_LEFT;
		case RIGHT: return DrawableIndex.PLAYER_FACING_RIGHT;
		default: throw new RuntimeException("shouldn't be here...");
		}
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
		getTile().setDirty(true);
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
}
