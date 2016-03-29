package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Entity;
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
		return hasPortal() && gate.isActive();
	}

	@Override
	public void stepOnTile(Entity player) {
		setDirty(true);
		SpecialWall distantWall = gate.getOtherWall(color);
		distantWall.teleport(player);
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
	
	private void teleport(Entity player) {
		player.setDirection(direction);
		getNextTile().stepOnTile(player);
	}
	
	private boolean hasPortal() {
		return color != ShotColor.INACTIVE;
	}

}
