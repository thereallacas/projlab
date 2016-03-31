/**
 * 
 */
package ballmerpeak.stargate.commands;

import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.Player;

public interface PlayerSelectionStrategy {

	public Player getPlayer(Game game);
}
