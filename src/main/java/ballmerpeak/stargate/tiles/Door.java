package ballmerpeak.stargate.tiles;

import ballmerpeak.stargate.Position;

public class Door extends Floor {

	public Door(Position pos) {
		super(pos);
	}

	private boolean open = false;

	@Override
	public boolean canPlayerMoveHere() {
		return isOpen();
	}
	
	public boolean isOpen() {
		return open;
	}

	public void close() {
		open = false;
	}

	public void open() {
		open = true;
	}
}
