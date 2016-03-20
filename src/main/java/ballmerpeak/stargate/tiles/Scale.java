package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.DrawableIndex;

public class Scale extends Floor {

	private Door door;
	
	@Override
	public boolean pickupCrate(Player player) {
		boolean didPickUpCrate = super.pickupCrate(player);
		if (didPickUpCrate) {
			door.close();
			if (player.getTile() == door) {
				player.kill();
			}
			return true;
		}
		return false;
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
	public void stepOnTile(Player player) {
		door.open();
		super.stepOnTile(player);
	}

	@Override
	public void leaveTile(Player player) {
		super.leaveTile(player);
		if (!hasCrate())
			door.close();
	}

	public void setDoor(Door door) {
		this.door = door;
	}

	@Override
	public DrawableIndex getDrawableIndex() {
		return hasCrate() ? DrawableIndex.SCALE_WITH_CRATE : DrawableIndex.SCALE; 
	}
}
