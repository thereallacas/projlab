package ballmerpeak.stargate.tiles;

import org.junit.Before;
import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Player;

public class PlayerTest {
	
	Player player;
	
	@Before
	public void setup() {
		player = new Player();
		player.setDirection(Direction.UP);
	}
}
