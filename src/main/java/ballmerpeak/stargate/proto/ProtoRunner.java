/**
 * 
 */
package ballmerpeak.stargate.proto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.RandomReplicatorMovement;
import ballmerpeak.stargate.ReplicatorMovementStrategy;
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

	String replicatorFile, zpmFile;
	
	String dataDirectory;
	
	ReplicatorMovementStrategy fixedReplicatorMovementStrategy;

	private void reset() throws Exception {
		String mapDirectory = dataDirectory + "/maps/";
		String mapFile = mapDirectory + "map4.txt";
		loader = new MapLoader(mapFile);
		game = loader.getGame();
		icf = new ProtoInputCommandFactory();
		io = new ProtoIO(game);
		fixedReplicatorMovementStrategy = new FixedReplicatorMovementStrategy(replicatorFile);
		
	}

	private void setRandom(boolean b) {
		if (b) {
			game.setReplicatorMovementStrategy(new RandomReplicatorMovement());
			prompt();
		} else {
			game.setReplicatorMovementStrategy(fixedReplicatorMovementStrategy);
			prompt();
		}
	}

	public ProtoRunner(String zpmFile, String replicatorFile) throws Exception {
		dataDirectory = System.getProperty("user.dir") + "/src/test/resources";
		this.zpmFile = dataDirectory + "/random/" + zpmFile;
		this.replicatorFile = dataDirectory + "/random/" + replicatorFile;
		reset();
	}

	public void run() throws Exception {
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		io.printInfo();
		prompt();
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
			
			prompt();
		}
	}

	/**
	 * 	
	 */
	private void prompt() {
		// TODO Auto-generated method stub
		System.out.format("%s> ", icf.oneil ? "oneil" : "jaffa");
	}

	public static void main(String... args) throws Exception {
		
		new ProtoRunner("zpm", "replicator").run();
	}
}