package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.DrawableIndex;

public class Scale extends Floor {

	private Door door;
	
	@Override
	public boolean pickupCrate(Player player) {
		boolean didPickUpCrate = super.pickupCrate(player);
		if (didPickUpCrate) {
			getDoor().close();
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
			getDoor().open();
		}
		return didDropCrate;
	}

	@Override
	public void stepOnTile(Player player) {
		super.stepOnTile(player);
		getDoor().open();
	}

	@Override
	public void leaveTile() {
		super.leaveTile();
		if (!occupied)
			getDoor().close();
	}

	public Door getDoor() {
		return door;
	}

	public void setDoor(Door door) {
		this.door = door;
	}

	@Override
	public DrawableIndex getDrawableIndex() {
		return occupied ? DrawableIndex.SCALE_WITH_CRATE : DrawableIndex.SCALE; 
	}
}
