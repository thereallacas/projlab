package ballmerpeak.stargate.commands;

import ballmerpeak.stargate.Player;

public class QuitCommand implements InputCommand {
	
	@Override
	public void execute(Player player) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

}
