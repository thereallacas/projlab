package ballmerpeak.stargate;

import ballmerpeak.stargate.gui.DrawableIndex;

public class Oneill extends Player {
	@Override
	public DrawableIndex getDrawableIndex() {
		switch (direction) {
		case UP:
			return DrawableIndex.ONEILL_FACING_UP;
		case DOWN:
			return DrawableIndex.ONEILL_FACING_DOWN;
		case LEFT:
			return DrawableIndex.ONEILL_FACING_LEFT;
		case RIGHT:
			return DrawableIndex.ONEILL_FACING_RIGHT;
		default:
			throw new RuntimeException("shouldn't be here...");
		}
	}
}
