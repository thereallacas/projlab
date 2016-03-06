package ballmerpeak.stargate.skeleton;

import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.gui.InputCommandFactory;
import ballmerpeak.stargate.utils.MapLoader;

public class Main {
	
	private InputCommandFactory ifc;
	private Game game;
	private MapLoader loader;
	
	public Main(MapLoader loader, InputCommandFactory ifc) {
		game = loader.getGame();
		this.loader = loader;
		this.ifc = ifc;
	}
	
	public void run() {
		while (!game.didPlayerWin() && game.isPlayerAlive()) {
			InputCommand cmd = ifc.nextCommand();
			game.receiveInput(cmd);
		}
	}

	public static void main(String args[]) {
		Main main = new Main(new SkeletonMapLoader(), new SkeletonInputCommandFactory());
		main.run();
	}
}
