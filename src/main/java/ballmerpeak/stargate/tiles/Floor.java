package ballmerpeak.stargate.tiles;

import java.util.ArrayList;
import java.util.List;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Entity;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.DrawableIndex;

public class Floor extends Tile {

	private static ZPMGeneratingStrategy zpmgs;
	public static List<Floor> floors = new ArrayList<>();

	protected List<Entity> entities;

	private boolean ZPM;

	private int numCrates;
	
	public static void setZPMGeneratingStrategy(ZPMGeneratingStrategy zpmgs_) {
		zpmgs = zpmgs_;
	}

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
	public void dropCrateHere(Player player) {
		if (!hasCrate()) {
			numCrates++;
			player.setCarrying(false);
		}
	}

	@Override
	public void pickupCrate(Player player) {
		if (hasCrate()) {
			numCrates--;
			player.setCarrying(true);
		}
	}

	@Override
	public void stepOnTile(Entity entity) {
		entities.add(entity);
		if (ZPM) {
			entity.steppedOnZPM(this);
		}
		entity.setTile(this);
		super.stepOnTile(entity);
	}

	@Override
	public void leaveTile(Entity entity) {
		super.leaveTile(entity);
		entities.remove(entity);
	}

	@Override
	public void shootIt(ShotColor color, Direction dir) {
		if (!hasEntity() || allEntitiesOnFloorAreDead())
			super.shootIt(color, dir);
		else {
			for (Entity entity : entities) {
				entity.shootIt();
			}
			cleanupDeadEntities();
		}
	}

	public DrawableIndex getDrawableIndex() {
		return hasEntity() ? entities.get(0).getDrawableIndex()
				: hasZPM() ? DrawableIndex.FLOOR_WITH_ZPM
						: hasCrate() ? DrawableIndex.FLOOR_WITH_CRATE : DrawableIndex.FLOOR;
	}

	public boolean hasEntity() {
		return !entities.isEmpty();
	}

	protected boolean allEntitiesOnFloorAreDead() {
		return entities.stream().allMatch(e -> !e.isAlive());
	}

	protected void cleanupDeadEntities() {
		entities.removeIf(e -> !e.isAlive());
	}

	public void setZPM(boolean b) {
		this.ZPM = b;
		setDirty(true);
	}

	public boolean hasZPM() {
		return ZPM;
	}

	public static void generateNewZPM() {
		Floor floor = zpmgs.getFloorForNewZPM();
		floor.setZPM(true);
	}

	public static void addFloor(Floor floor) {
		floors.add(floor);
	}
}
