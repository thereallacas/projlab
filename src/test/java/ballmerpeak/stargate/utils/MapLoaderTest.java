package ballmerpeak.stargate.utils;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import ballmerpeak.stargate.Labyrinth;

public class MapLoaderTest {

	Labyrinth labyrinth;
	
	@Before
	public void setup() throws FileNotFoundException, IOException {
		MapLoader loader = new MapLoader();
		URL map1Path = this.getClass().getResource("/map1.txt");
		labyrinth = loader.loadLabyrinth(map1Path.getPath());
	}

	@Test
	public void testNumberOfZPMs() {
		assertEquals(3, labyrinth.numberOfZPMs);
	}

}
