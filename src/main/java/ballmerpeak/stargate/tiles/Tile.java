package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.Position;
import ballmerpeak.stargate.gui.GameDrawable;

public abstract class Tile implements GameDrawable {
	
	private final Position position;
	
	public Tile(Position pos) {
		position = pos;
	}
	
	public abstract void stepOnTile(Player player);
	
	public void leaveTile() {
		
	}
	
	public boolean dropCrateHere() {
		return false;
	}
	
	public boolean pickupCrate() {
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
