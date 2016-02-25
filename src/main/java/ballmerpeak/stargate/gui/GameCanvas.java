package ballmerpeak.stargate.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import ballmerpeak.stargate.Game;
import ballmerpeak.stargate.Labyrinth;
import ballmerpeak.stargate.tiles.Tile;

public class GameCanvas extends JPanel {	
	public static int TILE_WIDTH = 16;
	public static int TILE_HEIGHT = 16;
	
	public void paintGame(Game game, Graphics g) {
		Labyrinth lab = game.getLabyrinth();
		// Rajzolás demó.
		for(int y = 0; y < lab.getHeight(); y++) {
			for(int x = 0; x < lab.getWidth(); x++) {
				Tile t = lab.getTile(y, x);
				int red = x*y % 256;
				int green = y*y % 256;
				int blue = x*y % 256;
				Color fillColor = new Color(red, green, blue);
				g.setColor(fillColor);
				g.fillRect(TILE_WIDTH * x, TILE_HEIGHT * y, TILE_WIDTH, TILE_HEIGHT);
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
	}

}
