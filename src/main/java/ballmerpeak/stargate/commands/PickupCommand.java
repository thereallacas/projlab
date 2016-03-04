package ballmerpeak.stargate.commands;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.tiles.Tile;

public class PickupCommand implements InputCommand {

	@Override
	public void execute(Player player) {
		// TODO Auto-generated method stub
		Tile tile = player.getTileFrontOfPlayer();
		if (player.isCarrying()) {
			tile.dropCrateHere(player);
		} else {
			tile.pickupCrate(player);
		}
		tile.setDirty(true);
		player.setDirty(true);
	}

}
