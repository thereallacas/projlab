package ballmerpeak.stargate.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import ballmerpeak.stargate.Labyrinth;
import ballmerpeak.stargate.tiles.Tile;

public class GameCanvas extends JPanel {	
	public static int TILE_WIDTH = 16;
	public static int TILE_HEIGHT = 16;
	
	private static String imageFormat = "png";
	private static Image tileImages[] = new Image[DrawableIndex.values().length];
	private Labyrinth lastRenderedGame = null;
	
	public void paintGame(Labyrinth lab, Graphics g) {
			for(int y = 0; y < lab.getHeight(); y++) {
			for(int x = 0; x < lab.getWidth(); x++) {
				int scrX = TILE_WIDTH * x;
				int scrY = TILE_HEIGHT * y;
				Tile t = lab.getTile(y, x);
				Image tileImage = getAsset(t.getDrawableIndex());
				g.drawImage(tileImage, scrX, scrY, TILE_WIDTH, TILE_HEIGHT, null);
				if(t.getPosition().equals(lab.getPlayer().position)) {
					tileImage = getAsset(lab.getPlayer().getDrawableIndex());
					g.drawImage(tileImage, scrX, scrY, TILE_WIDTH, TILE_HEIGHT, null);
				}
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if(lastRenderedGame != null) paintGame(lastRenderedGame, g);
	}
	
	public static void loadAssets(String path) throws IOException {
		for(DrawableIndex asset: DrawableIndex.values()) {
			String assetFileName = path + asset.name() + "." + imageFormat;
			File assetFile = new File(assetFileName);
			if(!assetFile.exists()) continue;
			tileImages[asset.ordinal()] = ImageIO.read(assetFile);
		}
	}
	
	public static Image getAsset(DrawableIndex asset) {
		return tileImages[asset.ordinal()];
	}
}
