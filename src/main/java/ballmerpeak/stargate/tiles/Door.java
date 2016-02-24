package ballmerpeak.stargate.tiles;

public class Door extends Floor {

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
