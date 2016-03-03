package ballmerpeak.stargate.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.tiles.Tile;

public class GameCanvas extends JPanel implements GameRenderer {
	private static final int TILE_WIDTH = 16;
	private static final int TILE_HEIGHT = 16;

	private static final String imageFormat = "png";
	private static final Image tileImages[] = new Image[DrawableIndex.values().length];

	private Image backBuffer;
	
	public GameCanvas(Game game) {
		this.backBuffer = new BufferedImage(game.getWidth() * TILE_WIDTH, game.getHeight() * TILE_HEIGHT, BufferedImage.TYPE_INT_RGB);
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
	
	private int indexFromXY(int y, int x, int width, int height) {
		return y * width + x;
	}

	public void drawGame(Game game) {
		synchronized(this.backBuffer) {
			List<Tile> tiles = game.getTiles();
			Tile playerTile = game.getPlayerTile();
			Graphics g = backBuffer.getGraphics();
			for (int y = 0; y < game.getHeight(); y++) {
				for (int x = 0; x < game.getWidth(); x++) {
					int index = indexFromXY(y, x, game.getWidth(), game.getHeight());
					Tile tile = tiles.get(index);
					DrawableIndex drawableIndex = tile == playerTile ? game.getPlayerDrawableIndex() : tile.getDrawableIndex(); 
					Image image = getAsset(drawableIndex);
					int srcX = x * TILE_WIDTH;
					int srcY = y * TILE_HEIGHT;
					g.drawImage(image, srcX, srcY, TILE_WIDTH, TILE_HEIGHT, null);
				}
			}
			redraw(getGraphics());
		}
	}
}
