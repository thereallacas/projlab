package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.Position;

public class Floor extends Tile {
	
	private boolean occupied;
	private boolean ZPM;

	public Floor(Position pos) {
		super(pos);
		occupied = ZPM = false;
	}
	
	public static Floor floorWithZPM(Position pos) {
		Floor floor = new Floor(pos);
		floor.ZPM = true;
		return floor;
	}
	
	public static Floor floorWithCrate(Position pos) {
		Floor floor = new Floor(pos);
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
