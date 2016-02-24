package ballmerpeak.stargate;

import ballmerpeak.stargate.tiles.SpecialWall;

public class Gate {

	private SpecialWall yellowWall;
	private SpecialWall blueWall;
	private boolean active;
	boolean blueActive, yellowActive;
	
	public Gate() {
		yellowWall = blueWall = null;
		active = false;
		blueActive = yellowActive = false;
	}
	
	public SpecialWall getYellowWall() {
		return yellowWall;
	}
	
	public SpecialWall getBlueWall() {
		return blueWall;
	}
	
	public void setYellowWall(SpecialWall wall) {
		yellowWall = wall;
		yellowActive = true;
		if (blueActive) {
			active = true;
		}
	}
	
	public void setBlueWall(SpecialWall wall) {
		blueWall= wall;
		blueActive = true;
		if (yellowActive) {
			active = true;
		}
	}
	
	public boolean isActive() {
		return active;
	}
}
