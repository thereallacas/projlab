package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Gate;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.Position;

public class SpecialWall extends Wall {

	public ShotColor color;
	public final Direction direction;

	public final Gate gate;

	public SpecialWall(Direction dir, Gate gate) {
		super();
		direction = dir;
		this.gate = gate;
	}

	@Override
	public boolean canPlayerMoveHere() {
		return color != ShotColor.INACTIVE && gate.active;
	}

	@Override
	public void stepOnTile(Player player) {
		SpecialWall distantWall = (color == ShotColor.BLUE) ? gate.yellowWall : gate.blueWall;
		Position wallPos = distantWall.position;
		Position newPos = wallPos.plusDir(distantWall.direction);
		player.position = newPos;
		player.direction = distantWall.direction;
	}

	@Override
	public ShotResult shootIt(ShotColor color) {
		this.color = color == ShotColor.BLUE ? ShotColor.BLUE : ShotColor.YELLOW;
		return ShotResult.SPECIAL_WALL_HIT;
	}
}
