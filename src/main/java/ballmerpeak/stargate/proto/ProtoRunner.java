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
	private boolean prompt;

	private void reset() throws Exception {
		String mapDirectory = dataDirectory + "/maps/";
		String mapFile = mapDirectory + "map5.txt";
		loader = new MapLoader(mapFile);
		game = loader.getGame();
		icf = new ProtoInputCommandFactory();
		io = new ProtoIO(game);
		fixedReplicatorMovementStrategy = new FixedReplicatorMovementStrategy(replicatorFile);
		prompt = true;
	}

	private void setRandom(boolean b) {
		if (b) {
			game.setReplicatorMovementStrategy(new RandomReplicatorMovement());
		} else {
			game.setReplicatorMovementStrategy(fixedReplicatorMovementStrategy);
		}
	}
	
	private void setPrompt(boolean b) {
		prompt = b;
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
			
			else if (line.startsWith("prompt")) {
				String words[] = line.split(" ");
				if (words.length != 2)
					continue;
				setPrompt(words[1].equals("on"));

			}

			else if (line.startsWith("random")) {
				String words[] = line.split(" ");
				if (words.length != 2)
					continue;
				setRandom(words[1].equals("on"));
				continue;
			}
			
			else if (line.startsWith("rep")) {
				io.printReplicatorInfo();
			}
			
			else {
				icf.setInputString(line);
				InputCommand command = icf.nextCommand();
				game.setPlayerSelectionStrategy(icf.getPlayerSelectionStrategy());
				game.receiveInput(command);
				icf.setInputString(line);
			}
			
			io.printInfo();
			if (prompt)
				prompt();
		}
	}

	private void prompt() {
		System.out.format("%s> ", icf.oneil ? "oneil" : "jaffa");
	}

	public static void main(String... args) throws Exception {
		new ProtoRunner("zpm", "replicator").run();
	}
}