package ballmerpeak.stargate.gui;

import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.commands.PlayerSelectionStrategy;

public interface InputCommandFactory {
	
	public InputCommand nextCommand();
	public PlayerSelectionStrategy getPlayerSelectionStrategy();
}
