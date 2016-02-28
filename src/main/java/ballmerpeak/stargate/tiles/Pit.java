package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.Position;
import ballmerpeak.stargate.gui.DrawableIndex;

public class Pit extends Floor {

	public Pit(Position pos) {
		super(pos);
	}

	@Override
	public void stepOnTile(Player player) {
		super.stepOnTile(player);
		player.kill();
	}

	public DrawableIndex getDrawableIndex() {
		return DrawableIndex.PIT;
	}
}
