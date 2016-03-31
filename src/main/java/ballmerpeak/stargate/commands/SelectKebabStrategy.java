package ballmerpeak.stargate.commands;

import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.Player;

public class SelectKebabStrategy implements PlayerSelectionStrategy {

	@Override
	public Player getPlayer(Game game) {
		return game.getKebab();
	}

}
