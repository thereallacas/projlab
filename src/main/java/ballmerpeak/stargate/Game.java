package ballmerpeak.stargate;

import java.util.List;

import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.gui.DrawableIndex;
import ballmerpeak.stargate.gui.InputCommandHandler;
import ballmerpeak.stargate.tiles.Tile;

public class Game implements InputCommandHandler {

	private Labyrinth labyrinth;
	private Player player;
	private Gate gate;

	public Game(int height, int width) {
		this.labyrinth = new Labyrinth(height, width);
		this.player = new Player();
		this.gate = new Gate();
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

	public Labyrinth getLabyrinth() {
		return labyrinth;
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

	public void setPlayerTile(Tile tile) {
		player.setTile(tile);;
	}
	
	public Gate getGate() {
		return gate;
	}

	public int getNumberOfZPMs() {
		return labyrinth.getNumberOfZPMs();
	}

}
