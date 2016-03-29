package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Entity;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.DrawableIndex;

public class Pit extends Floor {

	private boolean filled;

	public Pit() {
		super();
		filled = false;
	}

	public void setFilled() {
		filled = true;
	}
	
	@Override
	public void stepOnTile(Entity entity) {
		if (!filled) {
			super.stepOnTile(entity);
			entity.fallInPit(this);
		} else {
			super.stepOnTile(entity);
		}
	}

	@Override
	public void dropCrateHere(Player player) {
		if (filled) {
			super.dropCrateHere(player);
		} else {
			player.setCarrying(false);
		}
	}

	public DrawableIndex getDrawableIndex() {
		return !filled ? DrawableIndex.PIT : hasCrate() ? DrawableIndex.FLOOR_WITH_CRATE : DrawableIndex.FLOOR;
	}
}
