package ballmerpeak.stargate.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.Gate;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.Replicator;
import ballmerpeak.stargate.gui.DrawableIndex;
import ballmerpeak.stargate.tiles.Door;
import ballmerpeak.stargate.tiles.Floor;
import ballmerpeak.stargate.tiles.Pit;
import ballmerpeak.stargate.tiles.Scale;
import ballmerpeak.stargate.tiles.ShotColor;
import ballmerpeak.stargate.tiles.SpecialWall;
import ballmerpeak.stargate.tiles.Tile;
import ballmerpeak.stargate.tiles.Wall;

public class MapLoader {

	Game game;

	protected Player player1, player2;
	protected Replicator replicator;
	protected Gate gate;

	protected Map<Character, Door> doors;
	protected Map<Character, Scale> scales;

	protected List<SpecialWall> specialWalls;

	Tile tiles[][];

	private int height;
	private int width;

	private MapLoaderHelper helper;

	private String filename;

	public MapLoader(String filename) throws FileNotFoundException, IOException {
		this.filename = filename;
	}

	public void setHelper(MapLoaderHelper helper) {
		this.helper = helper;
	}

	public Game getGame() throws FileNotFoundException, IOException {
		if (game != null)
			return game;

		gate = new Gate();
		doors = new HashMap<>();
		scales = new HashMap<>();
		specialWalls = new ArrayList<>();
		gate.setSpecialWalls(specialWalls);

		player1 = new Player() {

			@Override
			public void pickupZPM() {
				super.pickupZPM();
				if (getZPMsCarried() % 2 == 0)
					Floor.generateNewZPM();
			}
		};

		player2 = new Player() {

			@Override
			public void shoot(ShotColor color) {
				color = color == ShotColor.BLUE ? ShotColor.GREEN : ShotColor.RED;
				super.shoot(color);
			}

			@Override
			public DrawableIndex getDrawableIndex() {
				switch (direction) {
				case UP:
					return DrawableIndex.JAFFA_FACING_UP;
				case DOWN:
					return DrawableIndex.JAFFA_FACING_DOWN;
				case LEFT:
					return DrawableIndex.JAFFA_FACING_LEFT;
				case RIGHT:
					return DrawableIndex.JAFFA_FACING_RIGHT;
				default:
					throw new RuntimeException("shouldn't be here...");
				}
			}

		};
		replicator = new Replicator();
		try (FileReader fr = new FileReader(filename); BufferedReader br = new BufferedReader(fr)) {
			String lineOne = br.readLine();
			String lineTwo = br.readLine();
			height = Integer.parseInt(lineOne);
			width = Integer.parseInt(lineTwo);
			tiles = new Tile[height][width];
			if (helper != null)
				helper.dimensionsRead(height, width);

			// get empty line between header and body
			br.readLine();

			for (int y = 0; y < height; y++) {
				String line = br.readLine();
				for (int x = 0; x < width; x++) {

					if ((y == 0 || y == height - 1 || x == 0 || x == width - 1) && (line.charAt(x) != '#'))
						throw new InvalidMapFileException("edge of map has to be walled");

					Tile tile = createTile(line.charAt(x));
					tiles[y][x] = tile;
					if (helper != null) {
						helper.tileGenerated(tile, y, x);
					}
				}
			}
		}
		setupDoors();
		setupNeighbors();
		game = new Game(player1, player2, replicator);
		return game;
	}

	private void setupDoors() {
		for (Character c : scales.keySet()) {
			Scale scale = scales.get(c);
			Door door = doors.get(Character.toLowerCase(c));
			scale.setDoor(door);
		}
	}

	private void setupNeighbors() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Tile tile = tiles[y][x];
				if (y != 0)
					tile.setNeightborForDirection(Direction.UP, tiles[y - 1][x]);
				if (x != 0)
					tile.setNeightborForDirection(Direction.LEFT, tiles[y][x - 1]);
				if (y != height - 1)
					tile.setNeightborForDirection(Direction.DOWN, tiles[y + 1][x]);
				if (x != width - 1)
					tile.setNeightborForDirection(Direction.RIGHT, tiles[y][x + 1]);
			}
		}
	}

	private Tile createTile(char c) {
		switch (c) {

		case '@':
			Tile floorWithPlayer1 = new Floor();
			floorWithPlayer1.stepOnTile(player1);
			Floor.addFloor((Floor) floorWithPlayer1);
			return floorWithPlayer1;
		case '?':
			Tile floorWithPlayer2 = new Floor();
			floorWithPlayer2.stepOnTile(player2);
			Floor.addFloor((Floor) floorWithPlayer2);
			return floorWithPlayer2;
		case '*':
			Tile floorWithReplicator = new Floor();
			floorWithReplicator.stepOnTile(replicator);
			Floor.addFloor((Floor) floorWithReplicator);
			return floorWithReplicator;
		case ' ':
			Tile floor = new Floor();
			Floor.addFloor((Floor) floor);
			return floor;
		case '#':
			return new Wall();
		case '0':
			return new Pit();
		case '$':
			Tile floorWithZPM = Floor.floorWithZPM();
			Floor.addFloor((Floor) floorWithZPM);
			return floorWithZPM;
		case '%':
			Tile floorWithCrate = Floor.floorWithCrate();
			Floor.addFloor((Floor) floorWithCrate);
			return floorWithCrate;

		case '>':
			SpecialWall rightWall = new SpecialWall(Direction.RIGHT, gate);
			specialWalls.add(rightWall);
			return rightWall;
		case '<':
			SpecialWall leftWall = new SpecialWall(Direction.LEFT, gate);
			specialWalls.add(leftWall);
			return leftWall;
		case '^':
			SpecialWall upWall = new SpecialWall(Direction.UP, gate);
			specialWalls.add(upWall);
			return upWall;
		case '/':
			SpecialWall downWall = new SpecialWall(Direction.DOWN, gate);
			specialWalls.add(downWall);
			return downWall;

		}

		if (Character.isAlphabetic(c)) {
			if (Character.isLowerCase(c)) {
				Door door = new Door();
				doors.put(c, door);
				return door;
			} else {
				Scale scale = new Scale();
				scales.put(c, scale);
				return scale;
			}
		}

		throw new InvalidMapFileException("bad character read");

	}
}