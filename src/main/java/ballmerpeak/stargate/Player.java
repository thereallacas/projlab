package ballmerpeak.stargate;

public class Player {

	public Direction direction;
	public Position position;
	public boolean isCarrying;
	public boolean isAlive;
	public int ZPMsCarried;
	
	public Player() {
		isAlive = true;
		ZPMsCarried = 0;
	}
	
	public Position getPositionFrontOfPlayer() {
		return position.plusDir(direction);
	}
	
	public void stepForward() {
		position = getPositionFrontOfPlayer();
	}
}
