package ballmerpeak.stargate;

import ballmerpeak.stargate.gui.Drawable;
import ballmerpeak.stargate.tiles.Floor;
import ballmerpeak.stargate.tiles.Pit;
import ballmerpeak.stargate.tiles.Tile;

/**
 * @author ballmerpeak
 *
 */
public abstract class Entity implements Drawable {

	protected Direction direction;
	protected boolean alive;
	protected Tile tile;

	public Entity() {
		alive = true;
		direction = Direction.UP;
	}
	
	public void steppedOnZPM(Floor floor) {
		
	}

	@Override
	public boolean isDirty() {
		return tile.isDirty();
	}

	@Override
	public void setDirty(boolean b) {
		tile.setDirty(b);
	}

	public boolean isAlive() {
		return alive;
	}

	public void kill() {
		alive = false;
		setDirty(true);
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
		setDirty(true);
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
			if (nextTile.canPlayerMoveHere()) {
				currentTile.leaveTile(this);
				nextTile.stepOnTile(this);
			}
		}
		setDirty(true);
	}
	
	public void shootIt() {
		// override in Replicator
	}

	public void fallInPit(Pit pit) {
		kill();		
	}
}
