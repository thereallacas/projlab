package ballmerpeak.stargate;

import java.util.ArrayList;
import java.util.List;

import ballmerpeak.stargate.tiles.Tile;

public class Labyrinth {
	
	private int height;
	private int width;

	private final List<Tile> tiles;

	public void addTile(Tile tile) {
		tiles.add(tile);
	}
	
	public List<Tile> getTiles() {
		return tiles;
	}
	
	public Labyrinth(int height, int width) {
		tiles = new ArrayList<Tile>();
		this.height = height;
		this.width = width;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
