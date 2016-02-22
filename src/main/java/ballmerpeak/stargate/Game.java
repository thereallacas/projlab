package ballmerpeak.stargate;

import static ballmerpeak.stargate.Direction.*;
import static ballmerpeak.stargate.InputCommand.*;

import ballmerpeak.stargate.tiles.ShotColor;
import ballmerpeak.stargate.tiles.ShotResult;
import ballmerpeak.stargate.tiles.SpecialWall;
import ballmerpeak.stargate.tiles.Tile;

public class Game {

	static final int WIDTH = 100;
	static final int HEIGHT = 100;

	static final int MAX_ZPMS = 10;

	private static final Position START_POS = new Position(20, 20);

	Player player;
	Labyrinth labyrinth;
	Gate gate;

	int zpmsFound;
	boolean alive;

	Game() {
		labyrinth.tiles = new Tile[HEIGHT][WIDTH];
		player = new Player();
		player.direction = UP;
		player.position = START_POS;
		zpmsFound = 0;
		alive = true;
		gate = null;
	}

	InputCommand readInput() {
		return UP_KEY;
	}

	public static void main(String... args) {
		Game game = new Game();
		game.run();
	}

	Position getPositionFrontOfPlayer() {
		Direction dir = player.direction;
		Position pos = player.position;
		Position newPos = pos.plusDir(dir);
		return newPos;
	}

	Tile getTileFrontOfPlayer() {
		Position newPos = getPositionFrontOfPlayer();
		Tile nextTile = labyrinth.tiles[newPos.y][newPos.x];
		return nextTile;
	}

	void updatePos(Direction dir) {
		if (player.direction != dir) {
			player.direction = dir;
		} else {
			Tile nextTile = getTileFrontOfPlayer();
			if (nextTile.canPlayerMoveHere()) {
				StepResult sr = nextTile.stepOnTile();
				if (sr == StepResult.FALL) {
					alive = false;
				} else if (sr == StepResult.TELEPORT) {
					if (gate.active) {
						SpecialWall wall = (SpecialWall) nextTile;
						ShotColor color = wall.color;
						Position wallPos = color == ShotColor.BLUE ? gate.yellowWall.position : gate.blueWall.position;
						Position newPos = wallPos.plusDir(dir);
						player.position = newPos;
					}
				} else {
					// regular move
					Position newPos = getPositionFrontOfPlayer();
					player.position = newPos;
					if (sr == StepResult.ZPM) {
						zpmsFound++;
					}
				}
			}
		}
	}

	void doPickup() {
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

	private void run() {
		boolean running = true;
		do {
			InputCommand command = readInput();
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
				running = false;
				break;
			case UNKNOWN_KEY:
			default:
				break;
			}
			draw();
		} while (running && alive);

		if (!alive) {
			printGameOverMessage();
		} else if (zpmsFound == MAX_ZPMS) {
			printWinMessage();
		} else {
			printEndMessage();
		}
	}

	private void shoot(ShotColor color) {
		Position pos = getPositionFrontOfPlayer();
		Tile tile = getTileFrontOfPlayer();
		Direction dir = player.direction;
		
		ShotResult result = tile.shootIt(color);
		while (result == ShotResult.TILE_HIT) {
			pos = pos.plusDir(dir);
			tile = labyrinth.tiles[pos.y][pos.x];
			result = tile.shootIt(color);
		}
		
		if (result == ShotResult.REGULAR_WALL_HIT) {
			return;
		}

		if (color == ShotColor.BLUE) {
			gate.blueWall.color = ShotColor.INACTIVE;
			gate.setBlueTile((SpecialWall) tile);
		} else {
			gate.yellowWall.color = ShotColor.INACTIVE;
			gate.setYellowTile((SpecialWall) tile);
		}
	}

	private void draw() {
		// TODO Auto-generated method stub
	}

	private void printEndMessage() {
		System.out.println("ended");
	}

	private void printWinMessage() {
		System.out.println("victory!");
	}

	private void printGameOverMessage() {
		System.out.println("game over");
	}
}
