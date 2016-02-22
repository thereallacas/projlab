package ballmerpeak.stargate.tiles;

public class Wall extends Tile {

	@Override
	public boolean canPlayerMoveHere() {
		return false;
	}
	
	@Override
	public ShotResult shootIt(ShotColor color) {
		return ShotResult.REGULAR_WALL_HIT;
	}

}
