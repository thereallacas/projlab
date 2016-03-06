package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.gui.DrawableIndex;

public class Wall extends Tile {

	@Override
	public void shootIt(ShotColor color, Direction dir) {
		// wall absorbs shots
	}
	
	@Override
	public DrawableIndex getDrawableIndex() {
		return DrawableIndex.WALL;
	}
}