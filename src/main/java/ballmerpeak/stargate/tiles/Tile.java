package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.Drawable;

public abstract class Tile implements Drawable {
	private Tile neighbors[];
	private boolean isDirty = true;
	
	public Tile() {
		neighbors = new Tile[Direction.values().length];
	}
	
	public Tile getNeighborForDirection(Direction dir) {
		return neighbors[dir.ordinal()];
	}
	
	public void setNeightborForDirection(Direction dir, Tile tile) {
		neighbors[dir.ordinal()] = tile;
	}

	public boolean canPlayerMoveHere() {
		return true;
	}

	public void stepOnTile(Player player) {
		setDirty(true);
	}
	
	public void leaveTile() {
		setDirty(true);
	}
	
	public boolean dropCrateHere(Player player) {
		return false;
	}
	
	public boolean pickupCrate(Player player) {
		return false;
	}
	
	public void shootIt(ShotColor color, Direction dir) {
		getNeighborForDirection(dir).shootIt(color, dir);
	}

	@Override
	public boolean isDirty() {
		return isDirty;
	}

	@Override
	public void setDirty(boolean isDirty) {
		this.isDirty = isDirty;
	}
}
