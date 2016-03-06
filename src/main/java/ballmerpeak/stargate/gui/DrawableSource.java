package ballmerpeak.stargate.gui;

public interface DrawableSource {
	int getWidth();
	int getHeight();
	Drawable getPlayerTile();
	DrawableIndex getPlayerDrawableIndex();
	Drawable tileAt(int y, int x);
}
