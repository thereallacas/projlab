package ballmerpeak.stargate;

import java.util.List;

import ballmerpeak.stargate.tiles.ShotColor;
import ballmerpeak.stargate.tiles.SpecialWall;

public class Gate {

	List<SpecialWall> walls;

	public boolean isActiveForColor(ShotColor color) {
		ShotColor destinationColor = getOtherColor(color);
		for (SpecialWall wall : walls) {
			if (wall.getColor() == destinationColor)
				return true;
		}
		return false;
	}

	public void setSpecialWalls(List<SpecialWall> walls) {
		this.walls = walls;
	}

	public void wallShot(SpecialWall wall, ShotColor color) {
		for (SpecialWall w : walls) {
			if (wall == w) {
				w.setColor(color);
			} else if (w.getColor() == color) {
				w.setColor(ShotColor.INACTIVE);
			}
		}
	}

	public void teleport(Entity player, ShotColor originColor) {
		ShotColor destinationColor = getOtherColor(originColor);
		for (SpecialWall wall : walls) {
			if (wall.getColor() == destinationColor)
				wall.teleport(player);
		}
	}

	private ShotColor getOtherColor(ShotColor color) {
		return color == ShotColor.BLUE ? ShotColor.YELLOW
				: color == ShotColor.YELLOW ? ShotColor.BLUE
						: color == ShotColor.GREEN ? ShotColor.RED : ShotColor.GREEN;
	}
}