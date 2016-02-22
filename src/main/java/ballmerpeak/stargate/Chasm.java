package ballmerpeak.stargate;

import ballmerpeak.stargate.tiles.Tile;

public class Chasm extends Tile {

	@Override
	public StepResult stepOnTile() {
		return StepResult.FALL;
	}

	@Override
	public boolean dropCrateHere() {
		return false;
	}

	@Override
	public boolean pickupCrate() {
		return false;
	}
	
}
