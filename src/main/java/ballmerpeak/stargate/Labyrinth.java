package ballmerpeak.stargate;

import ballmerpeak.stargate.tiles.ShotColor;
import ballmerpeak.stargate.tiles.ShotResult;
import ballmerpeak.stargate.tiles.Tile;

public class Labyrinth {

	private final int height, width;

	private final Tile[][] tiles;

	private final Player player;

	private int numberOfZPMs;

	private final Gate gate = new Gate();

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
		player = new Player();
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

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayerPos(Position pos) {
		player.position = pos;
	}

	public Position getPlayerPosition() {
		return player.position;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Gate getGate() {
		return gate;
	}

	public void shoot(ShotColor color) {
		Position pos = player.getPositionFrontOfPlayer();
		Tile tile = getTileFrontOfPlayer();
		Direction dir = player.direction;

		ShotResult result = tile.shootIt(color);
		while (result == ShotResult.TILE_HIT) {
			pos = pos.plusDir(dir);
			tile = getTile(pos.y, pos.x);
			result = tile.shootIt(color);
		}
	}

	public void pickup() {
		Tile tile = getTileFrontOfPlayer();
		if (player.isCarrying) {
			tile.dropCrateHere(player);
		} else {
			tile.pickupCrate(player);
		}
	}

	public void movePlayer(Direction dir) {
		if (player.direction != dir) {
			player.direction = dir;
		} else {
			Tile nextTile = getTileFrontOfPlayer();
			if (nextTile.canPlayerMoveHere()) {
				Tile currentTile = getTile(player.position.y, player.position.x);
				currentTile.leaveTile();
				nextTile.stepOnTile(player);
			}
		}
	}
}
