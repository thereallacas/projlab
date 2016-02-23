package ballmerpeak.stargate.tiles;

import static ballmerpeak.stargate.StepResult.*;
import ballmerpeak.stargate.StepResult;
import ballmerpeak.stargate.Direction;

public class SpecialWall extends Wall {

	public ShotColor color;
	public final Direction direction;
	
	SpecialWall(Direction dir) {
		super();
		direction = dir;
	}

	@Override
	public boolean canPlayerMoveHere() {
		return color != ShotColor.INACTIVE;
	}

	@Override
	public StepResult stepOnTile() {
		switch (direction) {
		case UP:
			return StepResult.TELEPORT_UP;
		case DOWN:
			return StepResult.TELEPORT_DOWN;
		case LEFT:
			return StepResult.TELEPORT_LEFT;
		case RIGHT:
			return StepResult.TELEPORT_RIGHT;
		case NOT_A_DIRECTION:
			return StepResult.OK;
		}
		return OK;
	}

	@Override
	public ShotResult shootIt(ShotColor color) {
		this.color = color == ShotColor.BLUE ? ShotColor.BLUE : ShotColor.YELLOW;
		return ShotResult.SPECIAL_WALL_HIT;
	}
}
