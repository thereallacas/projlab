package ballmerpeak.stargate;

import static ballmerpeak.stargate.Direction.*;
import ballmerpeak.stargate.gui.InputCommandHandler;
import ballmerpeak.stargate.tiles.ShotColor;
import ballmerpeak.stargate.tiles.ShotResult;
import ballmerpeak.stargate.tiles.SpecialWall;
import ballmerpeak.stargate.tiles.Tile;

public class Game implements InputCommandHandler {

	Player player;
	Labyrinth labyrinth;
	Gate gate;

	public Game(Labyrinth l) {
		this.player = l.getPlayer();
		this.labyrinth = l;
		this.gate = l.getGate();
	}
	
	public void receiveInput(InputCommand command) {
		switch (command) {
		case UP_KEY:
			updatePos(UP);
			break;
		case DOWN_KEY:
			updatePos(DOWN);
			break;
		case LEFT_KEY:
			updatePos(LEFT);
			break;
		case RIGHT_KEY:
			updatePos(RIGHT);
			break;
		case SHOOT_BLUE_KEY:
			shoot(ShotColor.BLUE);
			break;
		case SHOOT_YELLOW_KEY:
			shoot(ShotColor.YELLOW);
			break;
		case PICKUP_KEY:
			doPickup();
			break;
		case QUIT_KEY:
			System.exit(0);
			break;
		case UNKNOWN_KEY:
		default:
			break;
		}
		
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
	
	public Labyrinth getLabyrinth() {
		return this.labyrinth;
	}
	
	public void setLabyrinth(Labyrinth l) {
		this.labyrinth = l;
		this.player = l.getPlayer();
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
		if(ShotColor.BLUE == color) gate.setBlueWall((SpecialWall)tile);
		else gate.setYellowWall((SpecialWall)tile);
	}
}
