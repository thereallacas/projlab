package ballmerpeak.stargate.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.tiles.Tile;

public class GameCanvas extends JPanel implements GameRenderer {
	private static final int TILE_WIDTH = 16;
	private static final int TILE_HEIGHT = 16;

	private static final String imageFormat = "png";
	private static final Image tileImages[] = new Image[DrawableIndex.values().length];

	private Game lastRenderedGame = null;
	
	@Override
	protected void paintComponent(Graphics g) {
		drawGame(lastRenderedGame);
	}

	public static void loadAssets(String path) throws IOException {
		for (DrawableIndex asset : DrawableIndex.values()) {
			String assetFileName = path + asset.name() + "." + imageFormat;
			File assetFile = new File(assetFileName);
			if (!assetFile.exists())
				continue;
			tileImages[asset.ordinal()] = ImageIO.read(assetFile);
		}
	}

	private static Image getAsset(DrawableIndex asset) {
		return tileImages[asset.ordinal()];
	}

	public void drawGame(Game game) {
		if (lastRenderedGame == null) {
			lastRenderedGame = game;
		}
		Graphics g = getGraphics();
		Tile rootTile = game.getRootTile();
		Tile playerTile = game.getPlayerTile();
		Tile rowIt = rootTile;
		Tile columnIt = rootTile;
		int y = 0;
		int x = 0;
		while (rowIt.getNeighborForDirection(Direction.DOWN) != null) {
			while (columnIt.getNeighborForDirection(Direction.RIGHT) != null) {
				int srcX = x * TILE_WIDTH;
				int srcY = y * TILE_HEIGHT;
				DrawableIndex index = columnIt == playerTile ? game.getPlayerDrawableIndex() : columnIt.getDrawableIndex(); 
				Image image = getAsset(index);
				g.drawImage(image, srcX, srcY, TILE_WIDTH, TILE_HEIGHT, null);
				columnIt = columnIt.getNeighborForDirection(Direction.RIGHT);
				x++;
			}
			columnIt = rowIt = rowIt.getNeighborForDirection(Direction.DOWN);
			y++;
			x = 0;
		}
	}
}
