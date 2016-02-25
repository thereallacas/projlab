package ballmerpeak.stargate.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.Labyrinth;
import ballmerpeak.stargate.tiles.Door;
import ballmerpeak.stargate.tiles.Pit;
import ballmerpeak.stargate.tiles.Scale;
import ballmerpeak.stargate.tiles.SpecialWall;
import ballmerpeak.stargate.tiles.Tile;
import ballmerpeak.stargate.tiles.Wall;

public class GameCanvas extends JPanel {	
	public static int TILE_WIDTH = 16;
	public static int TILE_HEIGHT = 16;
	
	private Game lastRenderedGame = null;
	
	public void paintGame(Game game, Graphics g) {
		lastRenderedGame = game;
		Labyrinth lab = game.getLabyrinth();
		// Rajzolás demó.
		for(int y = 0; y < lab.getHeight(); y++) {
			for(int x = 0; x < lab.getWidth(); x++) {
				int scrX = TILE_WIDTH * x;
				int scrY = TILE_HEIGHT * y;
				Tile t = lab.getTile(y, x);
				Image tileImage = ImageAssets.getAsset(t.getGraphicalAsset());
				g.drawImage(tileImage, scrX, scrY, null);
				if(t.getPosition().equals(game.getLabyrinth().getPlayer().position)) {
					g.setColor(Color.cyan);
					g.fillRect(scrX, scrY, TILE_WIDTH, TILE_HEIGHT);
				}
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if(lastRenderedGame != null) paintGame(lastRenderedGame, g);
	}
}
