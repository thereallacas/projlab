package ballmerpeak.stargate;

import java.util.ArrayList;
import java.util.List;

import ballmerpeak.stargate.tiles.Tile;

public class Labyrinth {
	
	private int height;
	private int width;

	private final List<Tile> tiles;

	private int numberOfZPMs;

	public int getNumberOfZPMs() {
		return numberOfZPMs;
	}

	public void setNumberOfZPMs(int numberOfZPMs) {
		this.numberOfZPMs = numberOfZPMs;
	}

	public void addTile(Tile tile) {
		tiles.add(tile);
	}
	
	public List<Tile> getTiles() {
		return tiles;
	}
	
	public Labyrinth(int height, int width) {
		tiles = new ArrayList<Tile>();
		numberOfZPMs = 0;
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
