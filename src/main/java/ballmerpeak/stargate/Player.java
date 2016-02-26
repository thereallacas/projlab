package ballmerpeak.stargate;

import ballmerpeak.stargate.gui.Drawable;
import ballmerpeak.stargate.gui.DrawableIndex;

public class Player implements Drawable {

	public Direction direction;
	public Position position;
	public boolean isCarrying;
	public boolean isAlive;
	public int ZPMsCarried;
	
	public Player() {
		isAlive = true;
		ZPMsCarried = 0;
		direction = Direction.UP;
		isCarrying = false;
	}
	
	public Position getPositionFrontOfPlayer() {
		return position.plusDir(direction);
	}
	
	public void stepForward() {
		if(!isAlive) return;
		position = getPositionFrontOfPlayer();
	}

	@Override
	public DrawableIndex getDrawableIndex() {
		switch (direction) {
		case UP: return DrawableIndex.PLAYER_FACING_UP;
		case DOWN: return DrawableIndex.PLAYER_FACING_DOWN;
		case LEFT: return DrawableIndex.PLAYER_FACING_LEFT;
		case RIGHT: return DrawableIndex.PLAYER_FACING_RIGHT;
		default: throw new RuntimeException("shouldn't be here...");
		}
	}
}
