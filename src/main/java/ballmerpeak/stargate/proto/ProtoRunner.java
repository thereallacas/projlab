/**
 * 
 */
package ballmerpeak.stargate.proto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.utils.MapLoader;

/**
 * @author ballmerpeak
 *
 */
public class ProtoRunner {
	
	MapLoader loader;
	Game game;
	ProtoInputCommandFactory icf;
	ProtoIO io;
	
	private void reset() throws FileNotFoundException, IOException {
		String dataDirectory = System.getProperty("user.dir") + "/src/test/resources";
		String mapDirectory = dataDirectory + "/maps/";
		String mapFile = mapDirectory + "map4.txt";
		loader = new MapLoader(mapFile);
		game = loader.getGame();
		icf = new ProtoInputCommandFactory();
		io = new ProtoIO(game);
	}
	
	private void setRandom(boolean b) {
		
	}
	
	public ProtoRunner() throws FileNotFoundException, IOException {
		reset();
	}
	
	public void run() throws IOException {
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		io.printInfo();
		System.out.print("> ");
		while ((line = reader.readLine()) != null) {
			if (line.equals("reset")) {
				reset();
				continue;
			}
			
			if (line.startsWith("random")) {
				String words[] = line.split(" ");
				if (words.length != 2)
					continue;
				setRandom(words[1].equals("on"));
				continue;
			}
			icf.setInputString(line);
			InputCommand command = icf.nextCommand();
			game.setPlayerSelectionStrategy(icf.getPlayerSelectionStrategy());
			game.receiveInput(command);
			io.printInfo();
			System.out.print("> ");
		}
	}
	
	public static void main(String... args ) throws FileNotFoundException, IOException {
		new ProtoRunner().run();
	}
}