package ballmerpeak.stargate.commands;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.tiles.ShotColor;
import ballmerpeak.stargate.tiles.ShotResult;
import ballmerpeak.stargate.tiles.Tile;

public class ShootCommand extends InputCommand {
	
	private ShotColor color;

	public ShootCommand(ShotColor color) {
		this.color = color;
	}

	@Override
	public void execute(Game game) {
		Tile tile = game.getTileFrontOfPlayer();
		Direction dir = game.getPlayerDirection();
		tile.shootIt(color, dir);
	}

}
