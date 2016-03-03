package ballmerpeak.stargate.commands;

import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.tiles.Tile;

public class PicupCommand extends InputCommand {

	@Override
	public void execute(Game game) {
		// TODO Auto-generated method stub
		Tile tile = game.getTileFrontOfPlayer();
		Player player = game.getPlayer();
		if (player.isCarrying()) {
			tile.dropCrateHere(player);
		} else {
			tile.pickupCrate(player);
		}
		tile.setDirty(true);
		player.getTile().setDirty(true);
	}

}
