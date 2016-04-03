/**
 * 
 */
package ballmerpeak.stargate.proto;

import java.util.HashMap;
import java.util.Map;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.gui.DrawableIndex;
import ballmerpeak.stargate.tiles.Tile;

/**
 * @author ballmerpeak
 *
 */
public class ProtoIO {

	Map<DrawableIndex, String> tileInfo = new HashMap<>();
	Game game;
	
	public ProtoIO(Game game) {
		tileInfo.put(DrawableIndex.FLOOR_WITH_ZPM, "Floor <ZPM>");
		tileInfo.put(DrawableIndex.FLOOR, "Floor");
		tileInfo.put(DrawableIndex.FLOOR_WITH_CRATE, "Floor <crate)>");
		tileInfo.put(DrawableIndex.SCALE, "Scale");
		tileInfo.put(DrawableIndex.SCALE_WITH_CRATE, "Scale <crate>");
		tileInfo.put(DrawableIndex.DOOR_CLOSED, "Door <closed>");
		tileInfo.put(DrawableIndex.DOOR_OPEN, "Door <open>");
		
		tileInfo.put(DrawableIndex.WALL, "Wall");
		tileInfo.put(DrawableIndex.SPECIAL_WALL_INACTIVE, "SpecialWall");
		tileInfo.put(DrawableIndex.SPECIAL_WALL_BLUE, "SpecialWall <blue>");
		tileInfo.put(DrawableIndex.SPECIAL_WALL_YELLOW, "SpecialWall <yellow>");
		tileInfo.put(DrawableIndex.SPECIAL_WALL_GREEN, "SpecialWall <green>");
		tileInfo.put(DrawableIndex.SPECIAL_WALL_RED, "SpecialWall <red>");
		
		tileInfo.put(DrawableIndex.PIT, "Pit");
		
		tileInfo.put(DrawableIndex.ONEILL_FACING_DOWN, "Oneil <down>");
		tileInfo.put(DrawableIndex.ONEILL_FACING_LEFT, "Oneil <left>");
		tileInfo.put(DrawableIndex.ONEILL_FACING_RIGHT, "Oneil <right>");
		tileInfo.put(DrawableIndex.ONEILL_FACING_UP, "Oneil <up>");

		tileInfo.put(DrawableIndex.JAFFA_FACING_DOWN, "Jaffa <down>");
		tileInfo.put(DrawableIndex.JAFFA_FACING_LEFT, "Jaffa <left>");
		tileInfo.put(DrawableIndex.JAFFA_FACING_RIGHT, "Jaffa <right>");
		tileInfo.put(DrawableIndex.JAFFA_FACING_UP, "Jaffa <up>");

		tileInfo.put(DrawableIndex.REPLICATOR_FACING_DOWN, "REPLICATOR <down>");
		tileInfo.put(DrawableIndex.REPLICATOR_FACING_LEFT, "REPLICATOR <left>");
		tileInfo.put(DrawableIndex.REPLICATOR_FACING_RIGHT, "REPLICATOR <right>");
		tileInfo.put(DrawableIndex.REPLICATOR_FACING_UP, "REPLICATOR <up>");	
		
		this.game = game;
	}

	public void printInfo() {
		Player player = game.getKebab();
		Player jaffa = game.getJaffa();
		
		String playerFacing = "";
		switch (player.getDirection()) {
		case UP:
			playerFacing = "up";
			break;
		case DOWN:
			playerFacing = "down";
			break;
		case LEFT:
			playerFacing = "left";
			break;
		case RIGHT:
			playerFacing = "right";
			break;
		}
		
		System.out.format("oneil: <%s, %s, %d ZPM(s), %s crate>\n",
				player.isAlive() ? "alive" : "dead", playerFacing, player.getZPMsCarried(), player.isCarrying() ? "" : "no");
		
		Tile playerTile = player.getTile();
		System.out.println("\tup: " + tileInfo.get(playerTile.getNeighborForDirection(Direction.UP).getDrawableIndex()));
		System.out.println("\tleft: " + tileInfo.get(playerTile.getNeighborForDirection(Direction.LEFT).getDrawableIndex()));
		System.out.println("\tdown: " + tileInfo.get(playerTile.getNeighborForDirection(Direction.DOWN).getDrawableIndex()));
		System.out.println("\tright: " + tileInfo.get(playerTile.getNeighborForDirection(Direction.RIGHT).getDrawableIndex()));
		System.out.println();
		
		playerFacing = "";
		switch (jaffa.getDirection()) {
		case UP:
			playerFacing = "up";
			break;
		case DOWN:
			playerFacing = "down";
			break;
		case LEFT:
			playerFacing = "left";
			break;
		case RIGHT:
			playerFacing = "right";
			break;
		}
		
		System.out.format("jaffa: <%s, %s, %d ZPM(s), %s crate>\n",
				jaffa.isAlive() ? "alive" : "dead", playerFacing, jaffa.getZPMsCarried(), jaffa.isCarrying() ? "" : "no");
		
		playerTile = jaffa.getTile();
		System.out.println("\tup: " + tileInfo.get(playerTile.getNeighborForDirection(Direction.UP).getDrawableIndex()));
		System.out.println("\tleft: " + tileInfo.get(playerTile.getNeighborForDirection(Direction.LEFT).getDrawableIndex()));
		System.out.println("\tdown: " + tileInfo.get(playerTile.getNeighborForDirection(Direction.DOWN).getDrawableIndex()));
		System.out.println("\tright: " + tileInfo.get(playerTile.getNeighborForDirection(Direction.RIGHT).getDrawableIndex()));
		System.out.println();
	}
}
