package ballmerpeak.stargate;

import static ballmerpeak.stargate.Direction.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;

import ballmerpeak.stargate.gui.GameWindow;
import ballmerpeak.stargate.tiles.ShotColor;
import ballmerpeak.stargate.tiles.ShotResult;
import ballmerpeak.stargate.tiles.SpecialWall;
import ballmerpeak.stargate.tiles.Tile;
import ballmerpeak.stargate.utils.MapLoader;

public class Game {
	private List<InputCommand> receivedKeyCommands = new LinkedList<InputCommand>();

	static final int WIDTH = 100;
	static final int HEIGHT = 100;

	static final int MAX_ZPMS = 10;

	private static final Position START_POS = new Position(20, 20);

	Player player;
	Labyrinth labyrinth;
	Gate gate;
	GameWindow window;

	Game() {
		player = new Player();
		player.direction = UP;
		player.position = START_POS;
		labyrinth = new Labyrinth(HEIGHT, WIDTH);
		labyrinth.setPlayer(player);
		gate = new Gate();
	}
	
	public void setWindow(GameWindow window) {
		this.window = window;
	}
	
	public void receiveInput(InputCommand command) {
		if(command == InputCommand.UNKNOWN_KEY) return;
		receivedKeyCommands.add(command);
	}

	InputCommand readInput() {
		InputCommand returnedCommand = InputCommand.UNKNOWN_KEY;
		if(receivedKeyCommands.size() > 0) {
			returnedCommand = receivedKeyCommands.get(0);
			receivedKeyCommands.remove(0);
		}
		return returnedCommand;
	}

	public static void main(String... args) {
		Game game = new Game();
		MapLoader loader = new MapLoader();
		try {
			game.labyrinth = loader.loadLabyrinth("/tmp/map.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GameWindow window = new GameWindow(game);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(760, 760);
		window.setVisible(true);
		game.setWindow(window);
		game.run();
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
		} while (running && player.isAlive);

		if (!player.isAlive) {
			printGameOverMessage();
		} else if (player.ZPMsCarried == MAX_ZPMS) {
			printWinMessage();
		} else {
			printEndMessage();
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

	private void draw() {
		window.redraw();
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
