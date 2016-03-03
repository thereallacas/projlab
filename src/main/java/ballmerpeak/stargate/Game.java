package ballmerpeak.stargate;

import java.util.List;

import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.gui.DrawableIndex;
import ballmerpeak.stargate.gui.InputCommandHandler;
import ballmerpeak.stargate.tiles.Tile;

public class Game implements InputCommandHandler {

	private Labyrinth labyrinth;
	private Player player;

	public Game(Labyrinth labyrinth, Player player) {
		this.labyrinth = labyrinth;
		this.player = player;
	}

	@Override
	public void receiveInput(InputCommand command) {
		command.execute(player);
	}

	public boolean isPlayerAlive() {
		return player.isAlive();
	}

	public DrawableIndex getPlayerDrawableIndex() {
		return player.getDrawableIndex();
	}

	public Tile getPlayerTile() {
		return player.getTile();
	}

	public int getHeight() {
		return labyrinth.getHeight();
	}

	public int getWidth() {
		return labyrinth.getWidth();
	}

	public List<Tile> getTiles() {
		return labyrinth.getTiles();
	}
	
	public int getNumberOfZPMs() {
		return labyrinth.getNumberOfZPMs();
	}

}
