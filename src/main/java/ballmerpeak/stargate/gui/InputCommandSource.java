package ballmerpeak.stargate.gui;

import ballmerpeak.stargate.commands.InputCommand;

public interface InputCommandSource {
	public void setInputCommandHandler(InputCommandHandler handler);

	public InputCommand getNextCommand();
	
}
