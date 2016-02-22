package ballmerpeak.stargate.tiles;

public class Door extends Tile {

	public boolean isOpen = false;

	@Override
	public boolean canPlayerMoveHere() {
		return isOpen;
	}

	public void close() {
		isOpen = false;
	}

	public void open() {
		isOpen = true;
	}
	
}
