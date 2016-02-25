package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.Position;
import ballmerpeak.stargate.gui.ImageAssets;

public class Pit extends Tile {

	public Pit(Position pos) {
		super(pos);
	}

	@Override
	public void stepOnTile(Player player) {
		player.stepForward();
		player.isAlive = false;
	}

	@Override
	public ImageAssets getGraphicalAsset() {
		return ImageAssets.TILE_PIT;
	}
	
}
