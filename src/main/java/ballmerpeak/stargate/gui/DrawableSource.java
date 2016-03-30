package ballmerpeak.stargate.gui;

public interface DrawableSource {
	int getWidth();
	int getHeight();
	Drawable tileAt(int y, int x);
}
