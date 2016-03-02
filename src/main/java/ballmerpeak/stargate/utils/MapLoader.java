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
import ballmerpeak.stargate.Gate;
import ballmerpeak.stargate.Labyrinth;
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
	
	Tile tiles[][];
	
	int zpms;
	Labyrinth labyrinth = null;

	List<SpecialWall> specialWalls;

	private int height;

	private int width;
	
	public Labyrinth loadLabyrinth(String filename) throws FileNotFoundException, IOException {
		gate = new Gate();
		doors = new HashMap<>();
		scales = new HashMap<>();
		specialWalls = new ArrayList<>();
		zpms = 0;
		try (FileReader fr = new FileReader(filename); BufferedReader br = new BufferedReader(fr)) {
			String lineOne = br.readLine();
			String lineTwo = br.readLine();
			height = Integer.parseInt(lineOne);
			width = Integer.parseInt(lineTwo);
			labyrinth = new Labyrinth(height, width);
			this.gate = labyrinth.getGate();
			
			tiles = new Tile[height][width];
			
			// get empty line between header and body
			br.readLine();
			
			for (int y = 0; y < height; y++) {
				String line = br.readLine();
				for (int x = 0; x < width; x++) {
					
					if ((y == 0 || y == height - 1 || x == 0 || x == width - 1) && (line.charAt(x) != '#'))
						throw new InvalidMapFileException("edge of map has to be walled");
					
					Tile tile = createTile(line.charAt(x), new Position(y, x));
					tiles[y][x] = tile; 
				}
			}
		}
		labyrinth.setTileAtOrigin(tiles[0][0]);
		setupDoors();
		labyrinth.setNumberOfZPMs(zpms);
		setupNeighbors();
		setupSpecialWalls();
		return labyrinth;
	}
	
	private void setupDoors() {
		for (Character c: scales.keySet()) {
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
					tile.setNeightborForDirection(Direction.UP, tiles[y-1][x]);
				if (x != 0)
					tile.setNeightborForDirection(Direction.LEFT, tiles[y][x-1]);
				if (y != height-1)
					tile.setNeightborForDirection(Direction.DOWN, tiles[y+1][x]);
				if (x != width-1)
					tile.setNeightborForDirection(Direction.RIGHT, tiles[y][x+1]);
				labyrinth.addTile(tile);
			}
		}
	}
	
	private void setupSpecialWalls() {
		for (SpecialWall wall : specialWalls) {
			Direction dir = wall.getDirection();
			Position pos = wall.getPosition();
			Position nextPos = pos.plusDir(dir);
			Tile nextTile = tiles[nextPos.y][nextPos.x];
			wall.setNextTile(nextTile);
		}
	}

	private Tile createTile(char c, Position pos) {

		switch (c) {

		case '@':
			labyrinth.setPlayerPos(pos);
			Tile tile = new Floor(pos);
			labyrinth.setPlayerTile(tile);
			return tile;
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
			SpecialWall rightWall = new SpecialWall(pos, Direction.RIGHT, gate);
			specialWalls.add(rightWall);
			return rightWall;
		case '<':
			SpecialWall leftWall = new SpecialWall(pos, Direction.LEFT, gate);
			specialWalls.add(leftWall);
			return leftWall;
		case '^':
			SpecialWall upWall = new SpecialWall(pos, Direction.UP, gate);
			specialWalls.add(upWall);
			return upWall;
		case '/':
			SpecialWall downWall = new SpecialWall(pos, Direction.DOWN, gate);
			specialWalls.add(downWall);
			return downWall;

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
