package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Gate;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.Position;

public class SpecialWall extends Wall {

	private ShotColor color;
	private final Direction direction;

	public final Gate gate;

	public SpecialWall(Direction dir, Gate gate) {
		super();
		direction = dir;
		this.gate = gate;
	}

	@Override
	public boolean canPlayerMoveHere() {
		return getColor() != ShotColor.INACTIVE && gate.isActive();
	}

	@Override
	public void stepOnTile(Player player) {
		SpecialWall distantWall = (getColor() == ShotColor.BLUE) ? gate.getYellowWall() : gate.getBlueWall();
		Position wallPos = distantWall.getPosition();
		Position newPos = wallPos.plusDir(distantWall.direction);
		player.position = newPos;
		player.direction = distantWall.direction;
	}

	@Override
	public ShotResult shootIt(ShotColor color) {
		this.setColor(color == ShotColor.BLUE ? ShotColor.BLUE : ShotColor.YELLOW);
		return ShotResult.SPECIAL_WALL_HIT;
	}

	public ShotColor getColor() {
		return color;
	}

	public void setColor(ShotColor color) {
		this.color = color;
	}
}
