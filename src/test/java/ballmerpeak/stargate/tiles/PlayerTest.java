package ballmerpeak.stargate.tiles;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.Position;

public class PlayerTest {
	
	Player player;
	
	@Before
	public void setup() {
		player = new Player();
		player.position = new Position(50, 50);
		player.direction = Direction.UP;
	}

	@Test
	public void testStepForward() {
		player.stepForward();
		assertEquals(new Position(49, 50), player.position);
	}

	@Test
	public void testPositionFrontOfPlayer() {
		Position pos = player.getPositionFrontOfPlayer();
		assertEquals(new Position(49, 50), pos);
	}
}
