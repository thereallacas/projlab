package ballmerpeak.stargate;

import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.gui.InputCommandHandler;

public class Game implements InputCommandHandler {

	private Player player;
	private int numberOfZPMs;
	
	public Game(Player player, int numberOfZPMs) {
		this.player = player;
		this.numberOfZPMs = numberOfZPMs;
	}

	@Override
	public void receiveInput(InputCommand command) {
		command.execute(player);
	}

	public boolean didPlayerWin() {
		return player.getZPMsCarried() == numberOfZPMs;
	}
	
	public boolean isPlayerAlive() {
		return player.isAlive();
	}
}
