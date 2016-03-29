/**
 * 
 */
package ballmerpeak.stargate;

import ballmerpeak.stargate.gui.DrawableIndex;
import ballmerpeak.stargate.tiles.Pit;
import ballmerpeak.stargate.tiles.Tile;

/**
 * @author ballmerpeak
 *
 */
public class Replicator extends Entity {

	@Override
	public void move(Direction direction) {
		Tile currentTile = tile;
		Tile nextTile = currentTile.getNeighborForDirection(direction);
		if (nextTile.canPlayerMoveHere()) {
			currentTile.leaveTile(this);
			nextTile.stepOnTile(this);
		}
		setDirty(true);
	}

	@Override
	public DrawableIndex getDrawableIndex() {
		if (!isAlive()) {
			return DrawableIndex.FLOOR;
		}
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

	@Override
	public void shootIt() {
		kill();
	}

	@Override
	public void fallInPit(Pit pit) {
		super.fallInPit(pit);
		pit.setFilled();
	}
}
