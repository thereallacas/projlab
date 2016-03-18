package ballmerpeak.stargate.skeleton.test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.utils.MapLoader;

public class SkeletonTest {

	protected Game game;
	protected List<InputCommand> commands;
	
	public SkeletonTest(Path map, List<InputCommand> commands) {
		try {
			MapLoader loader = new MapLoader(map.toString());
			game = loader.getGame();
			this.commands = commands;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		for (InputCommand command : commands) {
			game.receiveInput(command);
		}
	}
}
