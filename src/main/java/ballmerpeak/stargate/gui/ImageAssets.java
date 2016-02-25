package ballmerpeak.stargate.gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;

public enum ImageAssets {
	TILE_FLOOR, TILE_PIT, TILE_SCALE,
	TILE_WALL, TILE_SPECIALWALL, TILE_DOOR,
	TILE_ASSETCOUNT;
	
	private static String imageFormat = "png";
	private static Image tileImages[] = new Image[TILE_ASSETCOUNT.ordinal()];
	
	public static void loadAssets(String path) throws IOException {
		for(ImageAssets asset: ImageAssets.values()) {
			if(asset == TILE_ASSETCOUNT) continue;
			String assetFileName = path + asset.name() + "." + imageFormat;
			File assetFile = new File(assetFileName);
			if(!assetFile.exists()) throw new IOException("Can't open file " + assetFileName);
			tileImages[asset.ordinal()] = ImageIO.read(assetFile);
		}
	}
	
	public static Image getAsset(ImageAssets asset) {
		return tileImages[asset.ordinal()];
	}
}
