package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.Position;

public abstract class Tile {
	
	private Position position;
	
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

	public void setPosition(Position position) {
		this.position = position;
	}

}
