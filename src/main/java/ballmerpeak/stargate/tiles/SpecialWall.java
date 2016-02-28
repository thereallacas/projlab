package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Gate;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.Position;
import ballmerpeak.stargate.gui.DrawableIndex;

public class SpecialWall extends Wall {

	private ShotColor color;
	private final Direction direction;

	public final Gate gate;

	public SpecialWall(Position pos, Direction dir, Gate gate) {
		super(pos);
		color = ShotColor.INACTIVE;
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
		player.setPosition(newPos);
		player.setDirection(distantWall.direction);
	}

	@Override
	public ShotResult shootIt(ShotColor color) {
		gate.setWallForColor(color, this);
		return ShotResult.SPECIAL_WALL_HIT;
	}

	public ShotColor getColor() {
		return color;
	}

	public void setColor(ShotColor color) {
		this.color = color;
	}

	@Override
	public DrawableIndex getDrawableIndex() {
		return color == ShotColor.BLUE ? DrawableIndex.SPECIAL_WALL_BLUE :
			color == ShotColor.YELLOW ? DrawableIndex.SPECIAL_WALL_YELLOW :
				DrawableIndex.SPECIAL_WALL_INACTIVE;
	}
}
