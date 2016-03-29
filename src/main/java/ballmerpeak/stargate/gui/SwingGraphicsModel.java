package ballmerpeak.stargate.gui;

import ballmerpeak.stargate.Player;
import ballmerpeak.stargate.Replicator;

public class SwingGraphicsModel implements DrawableSource {

	private Drawable tiles[][];
	private Player player1, player2;
	private Replicator replicator;

	private int height;
	private int width;

	public SwingGraphicsModel(Drawable tiles[][], Player player1, Player player2, Replicator replicator) {
		this.tiles = tiles;
		this.player1 = player1;
		this.player2 = player2;
		this.replicator = replicator;
		this.height = tiles.length;
		this.width = tiles[0].length;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public Drawable getPlayer1Tile() {
		return player1.getTile();
	}

	@Override
	public DrawableIndex getPlayer1DrawableIndex() {
		return player1.getDrawableIndex();
	}

	@Override
	public Drawable getPlayer2Tile() {
		return player2.getTile();
	}

	@Override
	public DrawableIndex getPlayer2DrawableIndex() {
		return player2.getDrawableIndex();
	}

	@Override
	public Drawable tileAt(int y, int x) {
		return tiles[y][x];
	}

	@Override
	public Drawable getReplicatorTile() {
		return replicator.getTile();
	}

	@Override
	public DrawableIndex getReplicatorDrawableIndex() {
		return replicator.getDrawableIndex();
	}

}
