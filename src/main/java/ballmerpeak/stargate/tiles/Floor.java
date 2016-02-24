package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;

public class Floor extends Tile {
	
	private boolean occupied;
	private boolean ZPM;

	public Floor() {
		occupied = ZPM = false;
	}
	
	public static Floor floorWithZPM() {
		Floor floor = new Floor();
		floor.ZPM = true;
		return floor;
	}
	
	public static Floor floorWithCrate() {
		Floor floor = new Floor();
		floor.occupied = true;
		return floor;
	}
	
	public boolean isOccupied() {
		return occupied;
	}
	
	@Override
	public boolean dropCrateHere() {
		if (!isOccupied()) {
			occupied = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean pickupCrate() {
		if (occupied) {
			occupied = false;
			return true;
		}
		return false;
	}

	@Override
	public void stepOnTile(Player player) {
		player.stepForward();
		if (ZPM) {
			player.ZPMsCarried++;
			ZPM = false;
		}
	}
}
