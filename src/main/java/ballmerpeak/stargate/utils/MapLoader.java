package ballmerpeak.stargate.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.Gate;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.DrawableSource;
import ballmerpeak.stargate.gui.SwingGraphicsModel;
import ballmerpeak.stargate.tiles.Door;
import ballmerpeak.stargate.tiles.Floor;
import ballmerpeak.stargate.tiles.Pit;
import ballmerpeak.stargate.tiles.Scale;
import ballmerpeak.stargate.tiles.SpecialWall;
import ballmerpeak.stargate.tiles.Tile;
import ballmerpeak.stargate.tiles.Wall;

public class MapLoader {

	Game game;

	protected Player player;
	protected Gate gate;

	protected Map<Character, Door> doors;
	protected Map<Character, Scale> scales;

	Tile tiles[][];

	int zpms;

	private int height;
	private int width;

	private MapLoaderHelper helper;

	private String filename;

	public MapLoader(String filename) throws FileNotFoundException, IOException {
		this.filename = filename;
		this.helper = helper;
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
		zpms = 0;
		player = new Player();
		if (helper != null) {
			helper.playerGenerated(player);
		}
		try (FileReader fr = new FileReader(filename); BufferedReader br = new BufferedReader(fr)) {
			String lineOne = br.readLine();
			String lineTwo = br.readLine();
			height = Integer.parseInt(lineOne);
			width = Integer.parseInt(lineTwo);
			tiles = new Tile[height][width];
			if (helper != null)
				helper.setDimensions(height, width);

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
		game = new Game(player, zpms);
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
			Tile floorWithPlayer = new Floor();
			player.setTile(floorWithPlayer);
			return floorWithPlayer;
		case ' ':
			return new Floor();
		case '#':
			return new Wall();
		case '0':
			return new Pit();
		case '$':
			zpms++;
			return Floor.floorWithZPM();
		case '%':
			return Floor.floorWithCrate();

		case '>':
			SpecialWall rightWall = new SpecialWall(Direction.RIGHT, gate);
			return rightWall;
		case '<':
			SpecialWall leftWall = new SpecialWall(Direction.LEFT, gate);
			return leftWall;
		case '^':
			SpecialWall upWall = new SpecialWall(Direction.UP, gate);
			return upWall;
		case '/':
			SpecialWall downWall = new SpecialWall(Direction.DOWN, gate);
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