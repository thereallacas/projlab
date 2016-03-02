package ballmerpeak.stargate;

import java.util.ArrayList;
import java.util.List;

import ballmerpeak.stargate.tiles.ShotColor;
import ballmerpeak.stargate.tiles.ShotResult;
import ballmerpeak.stargate.tiles.Tile;

public class Labyrinth {
	
	private int height;

	private int width;
	
	private Tile tileAtOrigin;

	private final List<Tile> tiles;

	private final Player player;

	private int numberOfZPMs;

	private final Gate gate = new Gate();

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
		player = new Player();
		this.height = height;
		this.width = width;
	}

	public Tile getTileFrontOfPlayer() {
		Direction dir = player.getDirection();
		Tile playerTile = player.getTile();
		
		return playerTile.getNeighborForDirection(dir);
	}

	public Player getPlayer() {
		return this.player;
	}

	public Gate getGate() {
		return gate;
	}

	public Tile getPlayerTile() {
		return player.getTile();
	}
	
	public Tile getTileAtOrigin() {
		return tileAtOrigin;
	}

	public void setTileAtOrigin(Tile tileAtOrigin) {
		this.tileAtOrigin = tileAtOrigin;
	}

	public void setPlayerTile(Tile tile) {
		player.setTile(tile);;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
