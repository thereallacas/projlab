package ballmerpeak.stargate;

import ballmerpeak.stargate.tiles.Tile;

public class Labyrinth {

	public Tile[][] tiles;
	
	public Player player;
	
	Labyrinth(int height, int width, Player player) {
		tiles = new Tile[height][width];
		this.player = player;
	}

	Tile getTileFrontOfPlayer() {
		Position newPos = player.getPositionFrontOfPlayer();
		Tile nextTile = tiles[newPos.y][newPos.x];
		return nextTile;
	}
	
	Tile getTile(int y, int x) {
		return tiles[y][x];
	}

}
