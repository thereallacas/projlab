package ballmerpeak.stargate.gui;

public class SwingGraphicsModel implements DrawableSource {

	private Drawable tiles[][];

	private int height;
	private int width;

	public SwingGraphicsModel(Drawable tiles[][]) {
		this.tiles = tiles;
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
	public Drawable tileAt(int y, int x) {
		return tiles[y][x];
	}
}
