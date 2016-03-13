package ballmerpeak.stargate.utils;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.tiles.Tile;

public interface MapLoaderHelper {

	public void dimensionsRead(int height, int width);
	public void playerGenerated(Player player);
	public void tileGenerated(Tile tile, int y, int x);
}
