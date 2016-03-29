package ballmerpeak.stargate.gui;

public interface DrawableSource {
	int getWidth();
	int getHeight();
	Drawable getPlayer1Tile();
	Drawable getPlayer2Tile();
	Drawable getReplicatorTile();
	DrawableIndex getPlayer1DrawableIndex();
	DrawableIndex getPlayer2DrawableIndex();
	DrawableIndex getReplicatorDrawableIndex();
	Drawable tileAt(int y, int x);
}
