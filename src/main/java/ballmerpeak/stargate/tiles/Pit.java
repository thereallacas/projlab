package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.DrawableIndex;

public class Pit extends Floor {

	@Override
	public boolean stepOnTile(Player player) {
		player.kill();
		return super.stepOnTile(player);
	}
	
	@Override
	public boolean dropCrateHere(Player player) {
		return true;
	}

	public DrawableIndex getDrawableIndex() {
		return DrawableIndex.PIT;
	}
}
