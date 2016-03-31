package ballmerpeak.stargate;

import ballmerpeak.stargate.gui.DrawableIndex;
import ballmerpeak.stargate.tiles.ShotColor;

public class Jaffa extends Player {
	@Override
	public void shoot(ShotColor color) {
		color = color == ShotColor.BLUE ? ShotColor.GREEN : ShotColor.RED;
		super.shoot(color);
	}

	@Override
	public DrawableIndex getDrawableIndex() {
		switch (direction) {
		case UP:
			return DrawableIndex.JAFFA_FACING_UP;
		case DOWN:
			return DrawableIndex.JAFFA_FACING_DOWN;
		case LEFT:
			return DrawableIndex.JAFFA_FACING_LEFT;
		case RIGHT:
			return DrawableIndex.JAFFA_FACING_RIGHT;
		default:
			throw new RuntimeException("shouldn't be here...");
		}
	}
}
