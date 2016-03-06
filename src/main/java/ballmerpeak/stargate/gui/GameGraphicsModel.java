package ballmerpeak.stargate.gui;

import ballmerpeak.stargate.Player;

public class GameGraphicsModel implements DrawableSource {
	
	private Drawable tiles[][];
	private Player player;
	
	private int height;
	private int width;
	
	public GameGraphicsModel(Drawable tiles[][], Player player) {
		this.tiles = tiles;
		this.player = player;
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
	public Drawable getPlayerTile() {
		return player.getTile();
	}

	@Override
	public DrawableIndex getPlayerDrawableIndex() {
		return player.getDrawableIndex();
	}

	@Override
	public Drawable tileAt(int y, int x) {
		return tiles[y][x];
	}

}
