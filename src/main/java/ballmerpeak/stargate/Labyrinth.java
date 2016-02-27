package ballmerpeak.stargate;

import ballmerpeak.stargate.gui.InputCommandHandler;
import ballmerpeak.stargate.tiles.ShotColor;
import ballmerpeak.stargate.tiles.ShotResult;
import ballmerpeak.stargate.tiles.Tile;

public class Labyrinth implements InputCommandHandler {

	private final int height, width;
	

	private Tile[][] tiles;
	
	private Player player;

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
	
	public Player getPlayer() {
		return this.player;
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

	@Override
	public void receiveInput(InputCommand command) {
		switch (command) {
		case UP_KEY:
			movePlayer(Direction.UP);
			break;
		case DOWN_KEY:
			movePlayer(Direction.DOWN);
			break;
		case LEFT_KEY:
			movePlayer(Direction.LEFT);
			break;
		case RIGHT_KEY:
			movePlayer(Direction.RIGHT);
			break;
		case SHOOT_BLUE_KEY:
			shoot(ShotColor.BLUE);
			break;
		case SHOOT_YELLOW_KEY:
			shoot(ShotColor.YELLOW);
			break;
		case PICKUP_KEY:
			pickup();
			break;
		case QUIT_KEY:
			System.exit(0);
			break;
		case UNKNOWN_KEY:
		default:
			break;
		}
	}

	private void shoot(ShotColor color) {
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

	private void pickup() {
		Tile tile = getTileFrontOfPlayer();
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

	private void movePlayer(Direction dir) {
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
