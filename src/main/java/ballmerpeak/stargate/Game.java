package ballmerpeak.stargate;

import java.util.List;

import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.gui.DrawableIndex;
import ballmerpeak.stargate.gui.InputCommandHandler;
import ballmerpeak.stargate.tiles.Tile;

public class Game implements InputCommandHandler {
	
	private Labyrinth labyrinth;
	
	public Game(Labyrinth labyrinth) {
		this.labyrinth = labyrinth;
	}
	
	@Override
	public void receiveInput(InputCommand command) {
		command.execute(this);
	}
	
	public boolean isPlayerAlive() {
		return labyrinth.getPlayer().isAlive();
	}

	public Tile getPlayerTile() {
		return labyrinth.getPlayerTile();
	}

	public DrawableIndex getPlayerDrawableIndex() {
		return labyrinth.getPlayer().getDrawableIndex();
	}

	public Tile getRootTile() {
		return labyrinth.getTileAtOrigin();
	}
	
	public Player getPlayer() {
		return labyrinth.getPlayer();
	}

	public Labyrinth getLabyrinth() {
		// TODO Auto-generated method stub
		return labyrinth;
	}
	
	public Tile getTileFrontOfPlayer() {
		return labyrinth.getTileFrontOfPlayer();
	}
	
	public Direction getPlayerDirection() {
		return labyrinth.getPlayer().getDirection();
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
}
