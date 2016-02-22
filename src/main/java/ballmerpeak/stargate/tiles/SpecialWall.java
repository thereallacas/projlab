package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.StepResult;

public class SpecialWall extends Wall {

	public ShotColor color;

	@Override
	public boolean canPlayerMoveHere() {
		return color != ShotColor.INACTIVE;
	}

	@Override
	public StepResult stepOnTile() {
		return StepResult.TELEPORT;
	}

	@Override
	public ShotResult shootIt(ShotColor color) {
		this.color = color == ShotColor.BLUE ? ShotColor.BLUE : ShotColor.YELLOW;
		return ShotResult.SPECIAL_WALL_HIT;
	}
}
