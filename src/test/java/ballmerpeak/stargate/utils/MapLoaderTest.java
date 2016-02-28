package ballmerpeak.stargate.utils;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import ballmerpeak.stargate.Labyrinth;
import ballmerpeak.stargate.Position;

public class MapLoaderTest {

	Labyrinth labyrinth;
	
	@Before
	public void setup() throws FileNotFoundException, IOException {
		MapLoader loader = new MapLoader();
		URL map1Path = this.getClass().getResource("/maps/map1.txt");
		labyrinth = loader.loadLabyrinth(map1Path.getPath());
	}

	@Test
	public void testLabyrinthAttribs() {
		assertEquals(3, labyrinth.getNumberOfZPMs());
		assertEquals(100, labyrinth.getHeight());
		assertEquals(100, labyrinth.getWidth());
		
		assertEquals(new Position(22, 59), labyrinth.getPlayer().getPosition());
	}
	

}
