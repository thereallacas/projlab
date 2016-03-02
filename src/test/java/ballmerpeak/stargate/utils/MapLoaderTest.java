package ballmerpeak.stargate.utils;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import ballmerpeak.stargate.Game;

public class MapLoaderTest {

	Game game;
	
	@Before
	public void setup() throws FileNotFoundException, IOException {
		MapLoader loader = new MapLoader();
		URL map1Path = this.getClass().getResource("/maps/map1.txt");
		game = loader.loadLabyrinth(map1Path.getPath());
	}

	@Test
	public void testLabyrinthAttribs() {
		assertEquals(3, game.getNumberOfZPMs());
		
	}
	

}
