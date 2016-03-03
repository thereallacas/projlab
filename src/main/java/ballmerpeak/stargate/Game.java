package ballmerpeak.stargate;

import java.util.ArrayList;
import java.util.List;

import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.gui.Drawable;
import ballmerpeak.stargate.gui.DrawableIndex;
import ballmerpeak.stargate.gui.DrawableSource;
import ballmerpeak.stargate.gui.InputCommandHandler;
import ballmerpeak.stargate.tiles.Tile;

public class Game implements InputCommandHandler, DrawableSource {

	private Labyrinth labyrinth;
	private Player player;
	
	/* TODO
	 * List<Drawable> is not covariant
	 * need better way to solve this
	 * maybe subclass ArrayList?
	 */
	private List<Drawable> drawables;

	public Game(Labyrinth labyrinth, Player player) {
		this.labyrinth = labyrinth;
		this.player = player;
		this.drawables = new ArrayList<Drawable>();
		for (Tile t : labyrinth.getTiles()) {
			drawables.add(t);
		}
	}

	@Override
	public void receiveInput(InputCommand command) {
		command.execute(player);
	}

	@Override
	public DrawableIndex getPlayerDrawableIndex() {
		return player.getDrawableIndex();
	}

	@Override
	public Drawable getPlayerTile() {
		return player.getTile();
	}

	@Override
	public int getHeight() {
		return labyrinth.getHeight();
	}

	@Override
	public int getWidth() {
		return labyrinth.getWidth();
	}

	@Override
	public List<Drawable> getTiles() {
		return drawables;
	}
	
	public boolean didPlayerWin() {
		return player.getZPMsCarried() == labyrinth.getNumberOfZPMs();
	}
	
	public boolean isPlayerAlive() {
		return player.isAlive();
	}
}
