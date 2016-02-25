package ballmerpeak.stargate;

import static ballmerpeak.stargate.Direction.*;
import java.io.IOException;

import javax.swing.JFrame;

import ballmerpeak.stargate.gui.GameRenderer;
import ballmerpeak.stargate.gui.GameWindow;
import ballmerpeak.stargate.gui.InputCommandHandler;
import ballmerpeak.stargate.tiles.ShotColor;
import ballmerpeak.stargate.tiles.ShotResult;
import ballmerpeak.stargate.tiles.SpecialWall;
import ballmerpeak.stargate.tiles.Tile;
import ballmerpeak.stargate.utils.MapLoader;

public class Game implements InputCommandHandler {

	Player player;
	Labyrinth labyrinth;
	Gate gate;
	GameRenderer renderer = null;

	public void setRenderer(GameRenderer renderer) {
		this.renderer = renderer;
	}
	
	public void requestRedraw() {
		draw();
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
		draw();
	}

	public static void main(String... args) {
		MapLoader loader = new MapLoader();
		Labyrinth labyrinth = null;
		try {
			String mapFile = "/tmp/map.txt";
			labyrinth = loader.loadLabyrinth(mapFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		GameWindow window = new GameWindow();
		Game game = new Game();
		game.player = labyrinth.getPlayer();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(760, 760);
		window.setVisible(true);
		game.setRenderer(window);
		window.setInputCommandHandler(game);
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

		if (color == ShotColor.BLUE) {
			gate.getBlueWall().setColor(ShotColor.INACTIVE);
			gate.setBlueWall((SpecialWall) tile);
		} else {
			gate.getYellowWall().setColor(ShotColor.INACTIVE);
			gate.setYellowWall((SpecialWall) tile);
		}
	}

	private void draw() {
		if(renderer != null) renderer.drawGame(this);
	}
}
