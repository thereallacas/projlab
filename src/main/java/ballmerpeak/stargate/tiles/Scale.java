package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;

public class Scale extends Floor {

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
	public void stepOnTile(Player player) {
		door.open();
		super.stepOnTile(player);
	}

	@Override
	public void leaveTile() {
		door.close();
	}

}
