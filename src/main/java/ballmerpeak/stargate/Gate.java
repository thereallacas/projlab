package ballmerpeak.stargate;

import ballmerpeak.stargate.tiles.ShotColor;
import ballmerpeak.stargate.tiles.SpecialWall;

public class Gate {

	private SpecialWall yellowWall;
	private SpecialWall blueWall;
	private boolean blueActive, yellowActive;
	
	public Gate() {
		yellowWall = blueWall = null;
		blueActive = yellowActive = false;
	}
	
	public SpecialWall getYellowWall() {
		return yellowWall;
	}
	
	public SpecialWall getBlueWall() {
		return blueWall;
	}
	
	public void setWallForColor(ShotColor color, SpecialWall wall) {
		if (color == ShotColor.BLUE)
			setBlueWall(wall);
		else if (color == ShotColor.YELLOW)
			setYellowWall(wall);
	}
	
	public void setYellowWall(SpecialWall wall) {
		if (yellowWall != null) {
			yellowWall.setColor(ShotColor.INACTIVE);
		}
		if (wall == blueWall) {
			blueWall = null;
			blueActive = false;
		}
		yellowWall = wall;
		yellowWall.setColor(ShotColor.YELLOW);
		yellowActive = true;
	}
	
	public void setBlueWall(SpecialWall wall) {
		if (blueWall != null) {
			blueWall.setColor(ShotColor.INACTIVE);
		}
		if (wall == yellowWall) {
			yellowWall = null;
			yellowActive = false;
		}
		blueWall= wall;
		blueWall.setColor(ShotColor.BLUE);
		blueActive = true;
	}
	
	public boolean isActive() {
		return blueActive && yellowActive;
	}
	
	public SpecialWall getWallForColor(ShotColor color) {
		return color == ShotColor.YELLOW ? getYellowWall() : getBlueWall();
	}
}
