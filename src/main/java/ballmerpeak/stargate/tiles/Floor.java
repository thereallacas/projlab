package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.DrawableIndex;

public class Floor extends Tile {
	
	private boolean hasCrate;
	private boolean ZPM;

	public Floor() {
		hasCrate = ZPM = false;
	}
	
	public static Floor floorWithZPM() {
		Floor floor = new Floor();
		floor.ZPM = true;
		return floor;
	}
	
	public static Floor floorWithCrate() {
		Floor floor = new Floor();
		floor.hasCrate = true;
		return floor;
	}
	
	public boolean hasCrate() {
		return hasCrate;
	}
	
	@Override
	public boolean dropCrateHere(Player player) {
		if (!hasCrate()) {
			hasCrate = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean pickupCrate(Player player) {
		if (hasCrate) {
			hasCrate = false;
			return true;
		}
		return false;
	}

	@Override
	public void stepOnTile(Player player) {
		if (ZPM) {
			player.pickupZPM();
			ZPM = false;
		}
		player.setTile(this);
		super.stepOnTile(player);
	}

	public DrawableIndex getDrawableIndex() {
		return ZPM ? DrawableIndex.FLOOR_WITH_ZPM :
			hasCrate ? DrawableIndex.FLOOR_WITH_CRATE :
				DrawableIndex.FLOOR;
	}
}
