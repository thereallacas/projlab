package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Gate;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.DrawableIndex;

public class SpecialWall extends Wall {

	private ShotColor color;
	private final Direction direction;

	private final Gate gate;

	public SpecialWall(Direction dir, Gate gate) {
		color = ShotColor.INACTIVE;
		direction = dir;
		this.gate = gate;
	}

	@Override
	public boolean canPlayerMoveHere() {
		return (color != ShotColor.INACTIVE) && gate.isActive();
	}

	@Override
	public void stepOnTile(Player player) {
		SpecialWall distantWall = (color == ShotColor.BLUE) ? gate.getYellowWall() : gate.getBlueWall();
		Tile nextTile = distantWall.getNextTile();
		player.setDirection(distantWall.direction);
		nextTile.stepOnTile(player);
		super.stepOnTile(player);
	}

	@Override
	public void shootIt(ShotColor color, Direction dir) {
		gate.wallShot(this, color);
	}

	public void setColor(ShotColor color) {
		this.color = color;
		setDirty(true);
	}

	@Override
	public DrawableIndex getDrawableIndex() {
		return color == ShotColor.BLUE ? DrawableIndex.SPECIAL_WALL_BLUE
				: color == ShotColor.YELLOW ? DrawableIndex.SPECIAL_WALL_YELLOW : DrawableIndex.SPECIAL_WALL_INACTIVE;
	}

	private Tile getNextTile() {
		return getNeighborForDirection(direction);
	}

}
