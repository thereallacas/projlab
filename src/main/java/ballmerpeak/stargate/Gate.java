package ballmerpeak.stargate;

import ballmerpeak.stargate.tiles.SpecialWall;

public class Gate {

	SpecialWall yellowWall, blueWall;
	boolean active;
	boolean blueActive, yellowActive;
	
	Gate() {
		yellowWall = blueWall = null;
		active = false;
		blueActive = yellowActive = false;
	}
	
	void setYellowTile(SpecialWall wall) {
		yellowWall = wall;
		yellowActive = true;
		if (blueActive) {
			active = true;
		}
	}
	
	void setBlueTile(SpecialWall wall) {
		blueWall= wall;
		blueActive = true;
		if (yellowActive) {
			active = true;
		}
	}
}
