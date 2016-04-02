package ballmerpeak.stargate;

import ballmerpeak.stargate.gui.DrawableIndex;
import ballmerpeak.stargate.tiles.Floor;
import ballmerpeak.stargate.tiles.ShotColor;
import ballmerpeak.stargate.tiles.Tile;

public class Player extends Entity {

	private int ZPMsCarried;
	private boolean carrying;
	
	public Player() {
		super();
		ZPMsCarried = 0;
		carrying = false;
	}

	public boolean isCarrying() {
		return carrying;
	}
	
	public int getZPMsCarried() {
		return ZPMsCarried;
	}

	public void pickupZPM() {
		ZPMsCarried++;
	}

	@Override
	public void steppedOnZPM(Floor floor) {
		pickupZPM();
		floor.setZPM(false);
	}

	public void pickupCrate() {
		Tile nextTile = getTileFrontOfPlayer();
		if (carrying) {
			nextTile.dropCrateHere(this);
		} else {
			nextTile.pickupCrate(this);
		}
		nextTile.setDirty(true);
		setDirty(true);
	}

	public void shoot(ShotColor color) {
		Tile nextTile = getTileFrontOfPlayer();
		nextTile.shootIt(color, direction);
	}


	private Tile getTileFrontOfPlayer() {
		return tile.getNeighborForDirection(direction);
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
	
	public void setCarrying(boolean c) {
		this.carrying = c;
	}
}
