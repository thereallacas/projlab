package ballmerpeak.stargate;

import ballmerpeak.stargate.tiles.Tile;

public class Labyrinth {

	private final int height, width;
	

	private Tile[][] tiles;
	
	private Player player;

	private int numberOfZPMs;
	
	public int getNumberOfZPMs() {
		return numberOfZPMs;
	}

	public void setNumberOfZPMs(int numberOfZPMs) {
		this.numberOfZPMs = numberOfZPMs;
	}

	public Labyrinth(int height, int width) {
		this.height = height;
		this.width = width;
		tiles = new Tile[height][width];
		numberOfZPMs = 0;
	}

	public Tile getTileFrontOfPlayer() {
		Position newPos = player.getPositionFrontOfPlayer();
		Tile nextTile = tiles[newPos.y][newPos.x];
		return nextTile;
	}
	
	public Tile getTile(int y, int x) {
		return tiles[y][x];
	}
	
	public void setTile(int y, int x, Tile tile) {
		tiles[y][x] = tile;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
