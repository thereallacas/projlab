package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.Position;
import ballmerpeak.stargate.gui.Drawable;

public abstract class Tile implements Drawable {
	private final Position position;
	
	public Tile(Position pos) {
		position = pos;
	}
	
	public abstract void stepOnTile(Player player);
	
	public void leaveTile() {
		
	}
	
	public boolean dropCrateHere(Player player) {
		return false;
	}
	
	public boolean pickupCrate(Player player) {
		return false;
	}
	
	public boolean canPlayerMoveHere() {
		return true;
	}

	public ShotResult shootIt(ShotColor color) {
		return ShotResult.TILE_HIT;
	}

	public Position getPosition() {
		return position;
	}


}
