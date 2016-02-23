package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.StepResult;

public class Pit extends Tile {

	@Override
	public StepResult stepOnTile() {
		return StepResult.FALL;
	}
	
}
