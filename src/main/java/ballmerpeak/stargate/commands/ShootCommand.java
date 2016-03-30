package ballmerpeak.stargate.commands;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.tiles.ShotColor;

public class ShootCommand implements InputCommand {
	
	private ShotColor color;

	public ShootCommand(ShotColor color) {
		this.color = color;
	}

	@Override
	public void execute(Player player) {
		player.shoot(color);
	}

}
