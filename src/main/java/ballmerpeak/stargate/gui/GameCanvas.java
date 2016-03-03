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
	private Drawable[][] graphicsModel;

	private Image backBuffer;
	
	public GameCanvas(Game game) {
		this.backBuffer = new BufferedImage(game.getWidth() * TILE_WIDTH, game.getHeight() * TILE_HEIGHT, BufferedImage.TYPE_INT_RGB);
		buildGraphicsModel(game);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		synchronized (backBuffer) {
			redraw(g);
		}
	}
	
	private void buildGraphicsModel(Game game) {
		List<Drawable> tiles = game.getTiles();
		int modelWidth = game.getWidth();
		int modelHeight = game.getHeight();
		graphicsModel = new Tile[modelHeight][modelWidth];
		for (int y = 0; y < modelHeight; y++) {
			for (int x = 0; x < modelWidth; x++) {
				int index = indexFromXY(y, x, modelWidth, modelHeight);
				Drawable tile = tiles.get(index);
				graphicsModel[y][x] = tile;
			}
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
			Drawable playerTile = game.getPlayerTile();
			Graphics g = backBuffer.getGraphics();
			for (int y = 0; y < game.getHeight(); y++) {
				for (int x = 0; x < game.getWidth(); x++) {
					Drawable tile = graphicsModel[y][x];
					if(!tile.isDirty()) continue;
					DrawableIndex drawableIndex = tile == playerTile ? game.getPlayerDrawableIndex() : tile.getDrawableIndex(); 
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
}
