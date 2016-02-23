package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.Position;

public abstract class Tile {
	
	public Position position;
	
	public boolean dropCrateHere() {
		return false;
	}
	
	public abstract void stepOnTile(Player player);
	
	public void leaveTile() {
		
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

}
