package ballmerpeak.stargate.gui;

import java.util.List;

public interface DrawableSource {
	int getWidth();
	int getHeight();
	List<Drawable> getTiles();
	Drawable getPlayerTile();
	DrawableIndex getPlayerDrawableIndex();
}
