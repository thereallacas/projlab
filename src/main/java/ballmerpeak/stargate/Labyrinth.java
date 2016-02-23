package ballmerpeak.stargate;

import ballmerpeak.stargate.tiles.Tile;

public class Labyrinth {

	public Tile[][] tiles;
	
	public Player player;
	
	public Labyrinth(int height, int width) {
		tiles = new Tile[height][width];
	}

	Tile getTileFrontOfPlayer() {
		Position newPos = player.getPositionFrontOfPlayer();
		Tile nextTile = tiles[newPos.y][newPos.x];
		return nextTile;
	}
	
	Tile getTile(int y, int x) {
		return tiles[y][x];
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
