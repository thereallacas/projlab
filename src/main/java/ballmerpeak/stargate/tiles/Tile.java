package ballmerpeak.stargate.tiles;

import java.util.ArrayList;
import java.util.List;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Entity;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.Drawable;

public abstract class Tile implements Drawable {
	private List<Tile> neighbors;
	private boolean isDirty = true;
	
	public Tile() {
		neighbors = new ArrayList<Tile>(Direction.values().length);
		for (int i = 0; i < Direction.values().length; i++) {
			neighbors.add(null);
		}
	}
	
	public Tile getNeighborForDirection(Direction dir) {
		return neighbors.get(dir.ordinal());
	}
	
	public void setNeightborForDirection(Direction dir, Tile tile) {
		neighbors.set(dir.ordinal(), tile);
	}

	public boolean canPlayerMoveHere() {
		return true;
	}
	
	public void stepOnTile(Entity player) {
		setDirty(true);
	}
	
	public void leaveTile(Entity player) {
		setDirty(true);
	}
	
	public boolean dropCrateHere(Player player) {
		return false;
	}
	
	public boolean pickupCrate(Player player) {
		return false;
	}
	
	public void shootIt(ShotColor color, Direction dir) {
		Tile nextTile = getNeighborForDirection(dir);
		nextTile.shootIt(color, dir);
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
