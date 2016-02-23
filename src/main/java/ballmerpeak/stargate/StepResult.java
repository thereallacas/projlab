package ballmerpeak.stargate;

import static ballmerpeak.stargate.Direction.*;

public enum StepResult {
	OK, FALL, ZPM,
	TELEPORT_UP, TELEPORT_DOWN, TELEPORT_LEFT, TELEPORT_RIGHT;
	
	public boolean isTeleport() {
		switch (this) {
		case TELEPORT_UP:
		case TELEPORT_DOWN:
		case TELEPORT_LEFT:
		case TELEPORT_RIGHT:
			return true;
		case OK:
		case FALL:
		case ZPM:
			return false;
		}
		return false;
	}
	
	public Direction getDirection() {
		switch (this) {
		case TELEPORT_UP:
			return UP;
		case TELEPORT_DOWN:
			return DOWN;
		case TELEPORT_LEFT:
			return LEFT;
		case TELEPORT_RIGHT:
			return RIGHT;
		case OK:
		case FALL:
		case ZPM:
			return null;
		}
		return null;
	}
}
