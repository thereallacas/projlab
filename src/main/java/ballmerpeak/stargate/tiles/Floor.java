package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.StepResult;

public class Floor extends Tile {
	
	boolean isOccupied;
	boolean hasZPM;

	@Override
	public boolean dropCrateHere() {
		if (!isOccupied) {
			isOccupied = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean pickupCrate() {
		if (isOccupied) {
			isOccupied = false;
			return true;
		}
		return false;
	}

	@Override
	public StepResult stepOnTile() {
		if (hasZPM) {
			hasZPM = false;
			return StepResult.ZPM;
		}
		return StepResult.OK;
	}
}
