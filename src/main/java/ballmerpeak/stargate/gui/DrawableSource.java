package ballmerpeak.stargate.gui;

public interface DrawableSource {

	DrawableIndex getDrawable(int y, int x);
	int getHeight();
	int getWidth();
}
