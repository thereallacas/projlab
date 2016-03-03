package ballmerpeak.stargate.utils;

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
		URL map1Path = this.getClass().getResource("/maps/map1.txt");
		MapLoader loader = new MapLoader(map1Path.getPath());
		game = loader.getGame();
	}

	@Test
	public void testLabyrinthAttribs() {
		
	}
	

}
