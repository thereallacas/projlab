package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.DrawableIndex;

public class Pit extends Floor {

	@Override
	public void stepOnTile(Player player) {
		super.stepOnTile(player);
		player.kill();
	}
	
	@Override
	public boolean dropCrateHere(Player player) {
		player.setCarrying(false);
		return true;
	}

	public DrawableIndex getDrawableIndex() {
		return DrawableIndex.PIT;
	}
}
