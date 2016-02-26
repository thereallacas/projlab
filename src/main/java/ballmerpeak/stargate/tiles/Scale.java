package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.Position;
import ballmerpeak.stargate.gui.DrawableIndex;

public class Scale extends Floor {

	private Door door;
	
	public Scale(Position pos) {
		super(pos);
	}

	@Override
	public boolean pickupCrate() {
		boolean didPickUpCrate = super.pickupCrate();
		if (didPickUpCrate) {
			getDoor().close();
			return true;
		}
		return false;
	}

	@Override
	public boolean dropCrateHere() {
		boolean didDropCrate = super.dropCrateHere();
		if (didDropCrate) {
			getDoor().open();
		}
		return didDropCrate;
	}

	@Override
	public void stepOnTile(Player player) {
		getDoor().open();
		super.stepOnTile(player);
	}

	@Override
	public void leaveTile() {
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
