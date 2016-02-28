package ballmerpeak.stargate;

import ballmerpeak.stargate.gui.DrawableIndex;
import ballmerpeak.stargate.gui.DrawableSource;
import ballmerpeak.stargate.gui.InputCommandHandler;
import ballmerpeak.stargate.tiles.ShotColor;

public class Game implements InputCommandHandler, DrawableSource {
	
	private Labyrinth labyrinth;
	
	public Game(Labyrinth labyrinth) {
		this.labyrinth = labyrinth;
	}
	
	@Override
	public int getHeight() {
		return labyrinth.getHeight();
	}
	
	@Override
	public int getWidth() {
		return labyrinth.getWidth();
	}
	
	@Override
	public void receiveInput(InputCommand command) {
		switch (command) {
		case UP_COMMAND:
			labyrinth.movePlayer(Direction.UP);
			break;
		case DOWN_COMMAND:
			labyrinth.movePlayer(Direction.DOWN);
			break;
		case LEFT_COMMAND:
			labyrinth.movePlayer(Direction.LEFT);
			break;
		case RIGHT_COMMAND:
			labyrinth.movePlayer(Direction.RIGHT);
			break;
		case SHOOT_BLUE_COMMAND:
			labyrinth.shoot(ShotColor.BLUE);
			break;
		case SHOOT_YELLOW_COMMAND:
			labyrinth.shoot(ShotColor.YELLOW);
			break;
		case PICKUP_COMMAND:
			labyrinth.pickup();
			break;
		case QUIT_COMMAND:
			System.exit(0);
			break;
		case UNKNOWN_COMMAND:
		default:
			break;
		}
	}

	@Override
	public DrawableIndex getDrawable(int y, int x) {
		if (labyrinth.getPlayerPosition().equals(new Position(y, x))) {
			return getPlayerDrawableIndex();
		}
		return labyrinth.getTile(y, x).getDrawableIndex();
	}

	private DrawableIndex getPlayerDrawableIndex() {
		return labyrinth.getPlayer().getDrawableIndex();
	}
	
	public boolean isPlayerAlive() {
		return labyrinth.getPlayer().isAlive;
	}

}
