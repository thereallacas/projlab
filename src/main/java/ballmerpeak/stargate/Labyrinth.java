package ballmerpeak.stargate;

import java.util.ArrayList;
import java.util.List;

import ballmerpeak.stargate.tiles.ShotColor;
import ballmerpeak.stargate.tiles.ShotResult;
import ballmerpeak.stargate.tiles.Tile;

public class Labyrinth {
	
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

	public void shoot(ShotColor color) {
		Tile tile = getTileFrontOfPlayer();
		Direction dir = player.getDirection();

		ShotResult result = tile.shootIt(color);
		while (result == ShotResult.TILE_HIT) {
			tile = tile.getNeighborForDirection(dir);
			result = tile.shootIt(color);
		}
	}

	public void pickup() {
		Tile tile = getTileFrontOfPlayer();
		if (player.isCarrying()) {
			tile.dropCrateHere(player);
		} else {
			tile.pickupCrate(player);
		}
	}

	public void movePlayer(Direction dir) {
		if (player.getDirection() != dir) {
			player.setDirection(dir);
		} else {
			Tile currentTile = player.getTile();
			Tile nextTile = currentTile.getNeighborForDirection(dir);
			if (nextTile.canPlayerMoveHere()) {
				currentTile.leaveTile();
				nextTile.stepOnTile(player);
			}
		}
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
}
