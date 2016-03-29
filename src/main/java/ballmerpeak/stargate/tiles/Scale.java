package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Entity;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.DrawableIndex;

public class Scale extends Floor {

	private Door door;

	public Scale() {
		super();
	}
	
	private boolean isPressed() {
		return hasCrate() || hasEntity();
	}
	
	@Override
	public void pickupCrate(Player player) {
		super.pickupCrate(player);
		if (!isPressed()) {
			door.close();
		}
	}

	@Override
	public void dropCrateHere(Player player) {
		super.dropCrateHere(player);
		if (isPressed()) {
			door.open();
		}
	}

	@Override
	public void stepOnTile(Entity player) {
		super.stepOnTile(player);
		door.open();
	}

	@Override
	public void leaveTile(Entity player) {
		super.leaveTile(player);
		if (!hasCrate() && !hasEntity())
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
