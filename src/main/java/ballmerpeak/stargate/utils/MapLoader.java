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
	Player player;
	
	int zpms;

	public Labyrinth loadLabyrinth(String filename) throws FileNotFoundException, IOException {
		gate = new Gate();
		doors = new HashMap<>();
		scales = new HashMap<>();
		zpms = 0;
		Labyrinth labyrinth = null;
		try (FileReader fr = new FileReader(filename); BufferedReader br = new BufferedReader(fr)) {
			String lineOne = br.readLine();
			String lineTwo = br.readLine();
			int height = Integer.parseInt(lineOne);
			int width = Integer.parseInt(lineTwo);

			labyrinth = new Labyrinth(height, width);
			
			// get empty line between header and body
			br.readLine();
			
			for (int i = 0; i < height; i++) {
				String line = br.readLine();
				for (int j = 0; j < width; j++) {
					
					if ((i == 0 || i == height - 1 || j == 0 || j == width - 1) && (line.charAt(j) != '#'))
						throw new InvalidMapFileException("edge of map has to be walled");
					
					Tile tile = createTile(line.charAt(j), new Position(i, j));
					labyrinth.setTile(i, j, tile);
				}
			}
		}
		setupDoors();
		labyrinth.setNumberOfZPMs(zpms);
		return labyrinth;
	}
	
	private void setupDoors() {
		for (Character c: scales.keySet()) {
			Scale scale = scales.get(c);
			Door door = doors.get(Character.toLowerCase(c));
			scale.setDoor(door);
		}
	}

	private Tile createTile(char c, Position pos) {

		switch (c) {

		case '@':
			player = new Player();
			player.position = pos;
			player.direction = Direction.UP;
		case ' ':
			return new Floor(pos);
		case '#':
			return new Wall(pos);
		case '0':
			return new Pit(pos);
		case '$':
			zpms++;
			return Floor.floorWithZPM(pos);
		case '%':
			return Floor.floorWithCrate(pos);

		case '>':
			return new SpecialWall(pos, Direction.RIGHT, gate);
		case '<':
			return new SpecialWall(pos, Direction.LEFT, gate);
		case '^':
			return new SpecialWall(pos, Direction.UP, gate);
		case '/':
			return new SpecialWall(pos, Direction.DOWN, gate);

		}

		if (Character.isAlphabetic(c)) {
			if (Character.isLowerCase(c)) {
				Door door = new Door(pos);
				doors.put(c, door);
				return door;
			} else {
				Scale scale = new Scale(pos);
				scales.put(c,  scale);
				return scale;
			}
		}
		
		throw new InvalidMapFileException("bad character read");

	}
}
