package ballmerpeak.stargate;

import ballmerpeak.stargate.tiles.ShotColor;
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
		if (yellowWall != null) {
			yellowWall.setColor(ShotColor.INACTIVE);
		}
		yellowWall = wall;
		yellowWall.setColor(ShotColor.YELLOW);
		yellowActive = true;
		if (blueActive) {
			active = true;
		}
	}
	
	public void setBlueWall(SpecialWall wall) {
		if (blueWall != null) {
			blueWall.setColor(ShotColor.INACTIVE);
		}
		blueWall= wall;
		blueWall.setColor(ShotColor.BLUE);
		blueActive = true;
		if (yellowActive) {
			active = true;
		}
	}
	
	public boolean isActive() {
		return active;
	}
	
	public SpecialWall getWallForColor(ShotColor color) {
		return color == ShotColor.YELLOW ? getYellowWall() : getBlueWall();
	}
}
