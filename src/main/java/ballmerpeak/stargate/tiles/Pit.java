package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;

public class Pit extends Tile {

	@Override
	public void stepOnTile(Player player) {
		player.stepForward();
		player.isAlive = false;
	}
	
}
