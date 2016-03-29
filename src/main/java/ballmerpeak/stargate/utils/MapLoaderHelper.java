package ballmerpeak.stargate.utils;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.Replicator;
import ballmerpeak.stargate.tiles.Tile;

public interface MapLoaderHelper {

	public void dimensionsRead(int height, int width);
	public void tileGenerated(Tile tile, int y, int x);
	public void player1Generated(Player player1);
	public void player2Generated(Player player1);
	public void replicatorGenerated(Replicator replicator);
}
