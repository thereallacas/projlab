package ballmerpeak.stargate.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Gate;
import ballmerpeak.stargate.Labyrinth;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.Position;
import ballmerpeak.stargate.tiles.Door;
import ballmerpeak.stargate.tiles.Floor;
import ballmerpeak.stargate.tiles.Pit;
import ballmerpeak.stargate.tiles.Scale;
import ballmerpeak.stargate.tiles.SpecialWall;
import ballmerpeak.stargate.tiles.Tile;
import ballmerpeak.stargate.tiles.Wall;

public class MapLoader {

	Gate gate;

	Map<Character, Door> doors;
	Map<Character, Scale> scales;

	public Labyrinth loadLabyrinth(String filename) throws FileNotFoundException, IOException {
		gate = new Gate();
		doors = new HashMap<>();
		scales = new HashMap<>();
		Labyrinth labyrinth = null;
		try (FileReader fr = new FileReader(filename); BufferedReader br = new BufferedReader(fr)) {
			String lineOne = br.readLine();
			String lineTwo = br.readLine();
			int height = Integer.parseInt(lineOne);
			int width = Integer.parseInt(lineTwo);

			Player player = new Player();
			labyrinth = new Labyrinth(height, width);
			labyrinth.setPlayer(player);
			for (int i = 0; i < height; i++) {
				String line = br.readLine();
				for (int j = 0; j < width; j++) {
					if (line.charAt(j) == '@') {
						player.position = new Position(i, j);
						player.direction = Direction.UP;
					}
					Tile tile = readTile(line.charAt(j));
					labyrinth.tiles[i][j] = tile;
				}
			}
		}
		setupDoors();
		return labyrinth;
	}
	
	private void setupDoors() {
		for (Character c: doors.keySet()) {
			scales.get(c).door = doors.get(c);
		}
	}

	private Tile readTile(char c) {

		switch (c) {

		case '@':
		case ' ':
			return new Floor();
		case '#':
			return new Wall();
		case '0':
			return new Pit();
		case '$':
			Floor floorWithZPM = new Floor();
			floorWithZPM.hasZPM = true;
			return floorWithZPM;
		case '%':
			Floor floorWithCrate = new Floor();
			floorWithCrate.isOccupied = true;
			return floorWithCrate;

		case '>':
			return new SpecialWall(Direction.RIGHT, gate);
		case '<':
			return new SpecialWall(Direction.LEFT, gate);
		case '^':
			return new SpecialWall(Direction.UP, gate);
		case '/':
			return new SpecialWall(Direction.DOWN, gate);

		}

		if (Character.isAlphabetic(c)) {
			if (Character.isLowerCase(c)) {
				Door door = new Door();
				doors.put(c, door);
				return door;
			} else {
				Scale scale = new Scale();
				scales.put(c,  scale);
				return scale;
			}
		}
		
		throw new InvalidMapFileException("bad character read");

	}
}
