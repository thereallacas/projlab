package ballmerpeak.stargate.commands;

import ballmerpeak.stargate.Player;

public class PickupCommand implements InputCommand {

	@Override
	public void execute(Player player) {
		if (player.isAlive())
			player.pickupCrate();
	}

}
