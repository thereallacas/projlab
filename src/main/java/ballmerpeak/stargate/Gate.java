package ballmerpeak.stargate;

import ballmerpeak.stargate.tiles.SpecialWall;

public class Gate {

	public SpecialWall yellowWall;
	public SpecialWall blueWall;
	public boolean active;
	boolean blueActive, yellowActive;
	
	Gate() {
		yellowWall = blueWall = null;
		active = false;
		blueActive = yellowActive = false;
	}
	
	void setYellowWall(SpecialWall wall) {
		yellowWall = wall;
		yellowActive = true;
		if (blueActive) {
			active = true;
		}
	}
	
	void setBlueWall(SpecialWall wall) {
		blueWall= wall;
		blueActive = true;
		if (yellowActive) {
			active = true;
		}
	}
}
