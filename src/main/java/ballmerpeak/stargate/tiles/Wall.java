package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;

public class Wall extends Tile {

	@Override
	public boolean canPlayerMoveHere() {
		return false;
	}

	@Override
	public ShotResult shootIt(ShotColor color) {
		return ShotResult.REGULAR_WALL_HIT;
	}

	@Override
	public void stepOnTile(Player player) {
		throw new PlayerInsideWallException();
	}

}
