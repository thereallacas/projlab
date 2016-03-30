package ballmerpeak.stargate;

import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.gui.InputCommandHandler;

public class Game implements InputCommandHandler {

	private Player player1;
	private Player player2;
	private int numberOfZPMs;

	private Player player;
	
	private Replicator replicator;

	public Game(Player player1, Player player2, Replicator replicator, int numberOfZPMs) {
		this.player1 = player1;
		this.player2 = player2;
		this.replicator = replicator;
		player = player1;
		this.numberOfZPMs = numberOfZPMs;
	}

	@Override
	public void receiveInput(InputCommand command) {
		if (player.isAlive())
			command.execute(player);
		if (replicator.isAlive())
			replicator.move(Direction.randomDirection());
	}
	
	public void switchPlayer() {
		player = player == player1 ? player2 : player1;
	}

	public boolean didPlayersWin() {
		return (player1.getZPMsCarried() + player2.getZPMsCarried()) == numberOfZPMs;
	}

	public boolean isPlayer1Alive() {
		return player1.isAlive();
	}

	public boolean isPlayer2Alive() {
		return player2.isAlive();
	}

	public boolean bothPlayersAlive() {
		return isPlayer1Alive() && isPlayer2Alive();
	}
}
