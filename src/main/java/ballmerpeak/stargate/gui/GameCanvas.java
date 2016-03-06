package ballmerpeak.stargate.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameCanvas extends JPanel implements GameRenderer {
	private static final int TILE_WIDTH = 16;
	private static final int TILE_HEIGHT = 16;

	private static final String imageFormat = "png";
	private static final Image tileImages[] = new Image[DrawableIndex.values().length];

	private DrawableSource gfxModel;
	private Image backBuffer;
	
	public GameCanvas(int height, int width) {
		this.backBuffer = new BufferedImage(width * TILE_WIDTH, height * TILE_HEIGHT, BufferedImage.TYPE_INT_RGB);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		synchronized (backBuffer) {
			redraw(g);
		}
	}
	
	public void redraw(Graphics g) {
		g.drawImage(backBuffer, 0, 0, super.getWidth(), super.getHeight(), null);
	}
	
	@Override
	public boolean isDoubleBuffered() {
		return true;
	}
	
	public static void loadAssets(String path) throws IOException {
		for (DrawableIndex asset : DrawableIndex.values()) {
			String assetFileName = path + asset.name() + "." + imageFormat;
			File assetFile = new File(assetFileName);
			if (!assetFile.exists()) {
				System.err.println("[WARNING] Asset not found: " + assetFileName);
				continue;
			}
			tileImages[asset.ordinal()] = ImageIO.read(assetFile);
		}
	}

	private static Image getAsset(DrawableIndex asset) {
		return tileImages[asset.ordinal()];
	}

	@Override
	public void drawGame() {
		synchronized(this.backBuffer) {
			Drawable playerTile = gfxModel.getPlayerTile();
			Graphics g = backBuffer.getGraphics();
			for (int y = 0; y < gfxModel.getHeight(); y++) {
				for (int x = 0; x < gfxModel.getWidth(); x++) {
					Drawable tile = gfxModel.tileAt(y, x);
					if(!tile.isDirty()) continue;
					DrawableIndex drawableIndex = tile == playerTile ? gfxModel.getPlayerDrawableIndex() : tile.getDrawableIndex(); 
					Image image = getAsset(drawableIndex);
					int scrX = x * TILE_WIDTH;
					int scrY = y * TILE_HEIGHT;
					g.drawImage(image, scrX, scrY, TILE_WIDTH, TILE_HEIGHT, null);
					tile.setDirty(false);
				}
			}
			redraw(getGraphics());
		}
	}

	@Override
	public void setDrawableSource(DrawableSource src) {
		gfxModel = src;		
	}
}
