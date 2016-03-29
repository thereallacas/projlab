package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Entity;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.DrawableIndex;

public class Scale extends Floor {

	private Door door;

	public Scale() {
		super();
	}

	@Override
	public boolean pickupCrate(Player player) {
		boolean didPickupCrate = super.pickupCrate(player);
		if (didPickupCrate && entities.isEmpty()) {
			door.close();
		}
		return didPickupCrate;
	}

	@Override
	public boolean dropCrateHere(Player player) {
		boolean didDropCrate = super.dropCrateHere(player);
		if (didDropCrate) {
			door.open();
		}
		return didDropCrate;
	}

	@Override
	public void stepOnTile(Entity player) {
		super.stepOnTile(player);
		door.open();
	}

	@Override
	public void leaveTile(Entity player) {
		super.leaveTile(player);
		if (!hasCrate() && entities.isEmpty())
			door.close();
	}

	public void setDoor(Door door) {
		this.door = door;
	}

	@Override
	public DrawableIndex getDrawableIndex() {
		return !entities.isEmpty() ? super.getDrawableIndex()
				: hasCrate() ? DrawableIndex.SCALE_WITH_CRATE : DrawableIndex.SCALE;
	}
}
