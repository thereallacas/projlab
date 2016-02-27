package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.Position;
import ballmerpeak.stargate.gui.DrawableIndex;

public class Pit extends Tile {

	public Pit(Position pos) {
		super(pos);
	}
	
	@Override
	public boolean dropCrateHere() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void stepOnTile(Player player) {
		player.stepForward();
		player.isAlive = false;
	}

	public DrawableIndex getDrawableIndex() {
		return DrawableIndex.PIT;
	}
}
