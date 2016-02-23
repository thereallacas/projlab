package ballmerpeak.stargate;

import ballmerpeak.stargate.tiles.Tile;

public class Labyrinth {

	public Tile[][] tiles;
	
	public Player player;
	
	Labyrinth(int height, int width, Player player) {
		tiles = new Tile[height][width];
		this.player = player;
	}

	Position getPositionFrontOfPlayer() {
		Direction dir = player.direction;
		Position pos = player.position;
		Position newPos = pos.plusDir(dir);
		return newPos;
	}

	Tile getTileFrontOfPlayer() {
		Position newPos = getPositionFrontOfPlayer();
		Tile nextTile = tiles[newPos.y][newPos.x];
		return nextTile;
	}
	
	Tile getTile(int y, int x) {
		return tiles[y][x];
	}

}
