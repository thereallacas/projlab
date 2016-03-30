package ballmerpeak.stargate.gui;

import ballmerpeak.stargate.tiles.Tile;
import ballmerpeak.stargate.utils.MapLoaderHelper;

public class SwingMapLoaderHelper implements MapLoaderHelper {
	
	private Tile tiles[][];
	private int width;
	private int height;
	
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
		return new SwingGraphicsModel(tiles);
	}
}
