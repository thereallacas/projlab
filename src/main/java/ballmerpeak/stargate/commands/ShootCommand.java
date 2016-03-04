package ballmerpeak.stargate.commands;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.tiles.ShotColor;
import ballmerpeak.stargate.tiles.Tile;

public class ShootCommand implements InputCommand {
	
	private ShotColor color;

	public ShootCommand(ShotColor color) {
		this.color = color;
	}

	@Override
	public void execute(Player player) {
		Tile tile = player.getTileFrontOfPlayer();
		Direction dir = player.getDirection();
		tile.shootIt(color, dir);
	}

}
