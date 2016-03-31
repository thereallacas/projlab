package ballmerpeak.stargate;

import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.commands.PlayerSelectionStrategy;
import ballmerpeak.stargate.gui.InputCommandHandler;
import ballmerpeak.stargate.tiles.Floor;

public class Game implements InputCommandHandler {

	private Player player1;
	private Player player2;
	
	private PlayerSelectionStrategy pss;
	
	private Replicator replicator;
	
	public Game(Player player1, Player player2, Replicator replicator) {
		this.player1 = player1;
		this.player2 = player2;
		this.replicator = replicator;
	}
	

	public boolean didPlayersWin() {
		return !anyZPMsLeft();
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

	public Player getJaffa() {
		return player2;
	}

	public Player getKebab() {
		return player1;
	}
	
	public Replicator getReplicator() {
		return replicator;
	}
	
	public void setPlayerSelectionStrategy(PlayerSelectionStrategy pss) {
		this.pss = pss;
	}
	
	@Override
	public void receiveInput(InputCommand command) {
		Player player = pss.getPlayer(this);
		if (player.isAlive()) {
			command.execute(player);
			if (replicator.isAlive())
				replicator.move(Direction.randomDirection());
		}
	}
	
	public boolean anyZPMsLeft() {
		return Floor.floors.stream().anyMatch(f -> f.hasZPM());
	}
}
