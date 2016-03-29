package ballmerpeak.stargate.tiles;

import java.util.ArrayList;
import java.util.List;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Entity;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.Replicator;
import ballmerpeak.stargate.gui.DrawableIndex;

public class Floor extends Tile {

	protected List<Entity> entities;

	private boolean ZPM;

	private int numCrates;

	public Floor() {
		entities = new ArrayList<Entity>();
		ZPM = false;
		numCrates = 0;
	}

	public static Floor floorWithZPM() {
		Floor floor = new Floor();
		floor.ZPM = true;
		return floor;
	}

	public static Floor floorWithCrate() {
		Floor floor = new Floor();
		floor.numCrates = 1;
		return floor;
	}

	public boolean hasCrate() {
		return numCrates != 0;
	}

	@Override
	public boolean dropCrateHere(Player player) {
		if (!hasCrate()) {
			numCrates++;
			return true;
		}
		return false;
	}

	@Override
	public boolean pickupCrate(Player player) {
		if (hasCrate()) {
			numCrates--;
			return true;
		}
		return false;
	}

	@Override
	public void stepOnTile(Entity player) {
		entities.add(player);
		if (ZPM) {
			if (player instanceof Player) {
				Player p = (Player) player;
				p.pickupZPM();
				ZPM = false;
			}
		}
		player.setTile(this);
		super.stepOnTile(player);
	}

	@Override
	public void leaveTile(Entity entity) {
		super.leaveTile(entity);
		entities.remove(entity);
	}

	@Override
	public void shootIt(ShotColor color, Direction dir) {
		if (entities.isEmpty())
			super.shootIt(color, dir);
		else {
			for (Entity entity : entities) {
				entity.kill();
			}
		}
	}

	public DrawableIndex getDrawableIndex() {
		return ZPM ? DrawableIndex.FLOOR_WITH_ZPM : hasCrate() ? DrawableIndex.FLOOR_WITH_CRATE : DrawableIndex.FLOOR;
	}
}
