package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;

public class Floor extends Tile {
	
	public boolean isOccupied;
	public boolean hasZPM;

	public Floor() {
		super();
		isOccupied = hasZPM = false;
	}
	
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
	public void stepOnTile(Player player) {
		player.stepForward();
		if (hasZPM) {
			player.ZPMsCarried++;
			hasZPM = false;
		}
	}
}
