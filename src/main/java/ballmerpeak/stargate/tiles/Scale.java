package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.StepResult;

public class Scale extends Tile {

	public Door door = null;

	@Override
	public boolean pickupCrate() {
		boolean didPickUpCrate = super.pickupCrate();
		if (didPickUpCrate) {
			door.close();
			return true;
		}
		return false;
	}

	@Override
	public boolean dropCrateHere() {
		boolean didDropCrate = super.dropCrateHere();
		if (didDropCrate) {
			door.open();
		}
		return didDropCrate;
	}

	@Override
	public StepResult stepOnTile() {
		door.open();
		return super.stepOnTile();
	}

	@Override
	public void leaveTile() {
		door.close();
	}

}
