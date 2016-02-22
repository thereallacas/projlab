package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Position;
import ballmerpeak.stargate.StepResult;

public abstract class Tile {
	
	public Position position;
	
	public boolean dropCrateHere() {
		return false;
	}
	
	public StepResult stepOnTile() {
		return StepResult.OK;
	}
	
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
