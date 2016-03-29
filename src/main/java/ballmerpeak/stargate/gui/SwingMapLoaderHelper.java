package ballmerpeak.stargate.gui;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.Replicator;
import ballmerpeak.stargate.tiles.Tile;
import ballmerpeak.stargate.utils.MapLoaderHelper;

public class SwingMapLoaderHelper implements MapLoaderHelper {
	
	private Tile tiles[][];
	private Player player1, player2;
	private int width;
	private int height;
	private Replicator replicator;
	
	@Override
	public void tileGenerated(Tile tile, int y, int x) {
		tiles[y][x] = tile;
	}

	@Override
	public void dimensionsRead(int height, int width) {
		this.height = height;
		this.width = width;
		tiles = new Tile[width][height];
	}
	
	public DrawableSource getGraphicsModel() {
		return new SwingGraphicsModel(tiles, player1, player2, replicator);
	}

	@Override
	public void player1Generated(Player player1) {
		this.player1 = player1;
	}

	@Override
	public void player2Generated(Player player2) {
		this.player2 = player2;
	}

	@Override
	public void replicatorGenerated(Replicator replicator) {
		this.replicator = replicator;
	}

}
