package ballmerpeak.stargate;

import static ballmerpeak.stargate.Direction.DOWN;
import static ballmerpeak.stargate.Direction.LEFT;
import static ballmerpeak.stargate.Direction.RIGHT;
import static ballmerpeak.stargate.Direction.UP;

public class Position {

	public final int x;
	public final int y;
	
	Position(int y, int x) {
		this.x = x;
		this.y = y;
	}
	
	Position plusDir(Direction dir) {
		int dy = (dir == UP) ? 1 : (dir == DOWN) ? -1 : 0;
		int dx = (dir == RIGHT) ? 1 : (dir == LEFT) ? -1 : 0;
		return new Position(y + dy, x + dx);
	}
}
