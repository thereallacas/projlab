package ballmerpeak.stargate.tiles;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Player;

public class DoorAndScaleTest {
	
	Floor floor;
	Door door;
	Scale scale;
	Player player;
	
	@Before
	public void setup() {
		floor = new Floor();
		door = new Door();
		scale = new Scale();
		scale.setDoor(door);
		
		player = new Player();
	}

	@Test
	public void testStepOn() {
		scale.stepOnTile(player);
		assertTrue(door.isOpen());
		scale.leaveTile(player);
		assertFalse(door.isOpen());
	}
	
	@Test
	public void dropCrateTest() {
		scale.dropCrateHere(player);
		assertTrue(door.isOpen());
		assertTrue(scale.hasCrate());
	}
	
	@Test
	public void testDoorCanKillPlayer() {
		door = new Door();
		scale = new Scale();
		scale.setDoor(door);
		player.setTile(floor);
		player.setDirection(Direction.RIGHT);
		floor.leaveTile(null);
		scale.stepOnTile(player);
		assertTrue(door.isOpen());
		scale.leaveTile(player);
		door.stepOnTile(player);
		assertFalse(player.isAlive());
		assertFalse(door.isOpen());
	}

}
