package ballmerpeak.stargate;

import ballmerpeak.stargate.tiles.Floor;
import ballmerpeak.stargate.tiles.ShotColor;
import ballmerpeak.stargate.tiles.Tile;

public abstract class Player extends Entity {

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

	private Tile getTileFrontOfPlayer() {
		return tile.getNeighborForDirection(direction);
	}

	public void shoot(ShotColor color) {
		Tile nextTile = getTileFrontOfPlayer();
		nextTile.shootIt(color, direction);
	}
	
	public void setCarrying(boolean c) {
		this.carrying = c;
	}
}
