package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.DrawableIndex;

public class Wall extends Tile {

	@Override
	public boolean canPlayerMoveHere() {
		return false;
	}

	@Override
	public ShotResult shootIt(ShotColor color) {
		return ShotResult.REGULAR_WALL_HIT;
	}

	@Override
	public void stepOnTile(Player player) {
		throw new PlayerInsideWallException();
	}
	
	public DrawableIndex getDrawableIndex() {
		return DrawableIndex.WALL;
	}
}
