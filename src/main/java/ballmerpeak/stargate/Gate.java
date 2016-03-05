package ballmerpeak.stargate;

import ballmerpeak.stargate.tiles.ShotColor;
import ballmerpeak.stargate.tiles.SpecialWall;
import ballmerpeak.stargate.tiles.Tile;

public class Gate {

	private SpecialWall yellowWall;
	private SpecialWall blueWall;
	private boolean blueActive; 
	private boolean yellowActive;
	
	public Gate() {
		yellowWall = blueWall = null;
		blueActive = yellowActive = false;
	}
	
	public boolean isActive() {
		return blueActive && yellowActive;
	}
	
	public void wallShot(SpecialWall wall, ShotColor color) {
		setWallForColor(color, wall);
	}
	
	public void playerSteppedOnWall(Player player, SpecialWall wall) {
		SpecialWall distantWall = (wall == blueWall) ? yellowWall : blueWall;
		Tile nextTile = distantWall.getNextTile();
		player.setDirection(distantWall.getDirection());
		nextTile.stepOnTile(player);
	}
	
	private void setYellowWall(SpecialWall wall) {
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
	
	private void setBlueWall(SpecialWall wall) {
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
	
	private void setWallForColor(ShotColor color, SpecialWall wall) {
		if (color == ShotColor.BLUE)
			setBlueWall(wall);
		else if (color == ShotColor.YELLOW)
			setYellowWall(wall);
	}
}
