package ballmerpeak.stargate;


import ballmerpeak.stargate.tiles.ShotColor;
import ballmerpeak.stargate.tiles.ShotResult;
import ballmerpeak.stargate.tiles.SpecialWall;
import ballmerpeak.stargate.tiles.Tile;

public class Game {

	Player player;
	Labyrinth labyrinth;
	Gate gate;

	Game() {
		
	}

	void updatePos(Direction dir) {
		if (player.direction != dir) {
			player.direction = dir;
		} else {
			Tile nextTile = labyrinth.getTileFrontOfPlayer();
			if (nextTile.canPlayerMoveHere()) {
				Tile currentTile = labyrinth.getTile(player.position.y, player.position.x);
				currentTile.leaveTile();
				nextTile.stepOnTile(player);
			}
		}
	}

	void doPickup() {
		Tile tile = labyrinth.getTileFrontOfPlayer();
		if (player.isCarrying) {
			if (tile.dropCrateHere()) {
				player.isCarrying = false;
			}
		} else {
			if (tile.pickupCrate()) {
				player.isCarrying = true;
			}
		}
	}

	private void shoot(ShotColor color) {
		Position pos = player.getPositionFrontOfPlayer();
		Tile tile = labyrinth.getTileFrontOfPlayer();
		Direction dir = player.direction;
		
		ShotResult result = tile.shootIt(color);
		while (result == ShotResult.TILE_HIT) {
			pos = pos.plusDir(dir);
			tile = labyrinth.getTile(pos.y, pos.x);
			result = tile.shootIt(color);
		}
		
		if (result == ShotResult.REGULAR_WALL_HIT) {
			return;
		}

		if (color == ShotColor.BLUE) {
			gate.getBlueWall().setColor(ShotColor.INACTIVE);
			gate.setBlueWall((SpecialWall) tile);
		} else {
			gate.getYellowWall().setColor(ShotColor.INACTIVE);
			gate.setYellowWall((SpecialWall) tile);
		}
	}

}
