package ballmerpeak.stargate.tiles;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.Position;

public class DoorAndScaleTest {
	
	Door door;
	Scale scale;
	Player player;
	
	@Before
	public void setup() {
		door = new Door();
		scale = new Scale();
		scale.setDoor(door);
		
		player = new Player();
		player.position = new Position(0, 0);
	}

	@Test
	public void testStepOn() {
		scale.stepOnTile(player);
		assertTrue(door.isOpen());
		scale.leaveTile();
		assertFalse(door.isOpen());
	}
	
	@Test
	public void dropCrateTest() {
		scale.dropCrateHere();
		assertTrue(door.isOpen());
		assertTrue(scale.isOccupied());
	}

}
