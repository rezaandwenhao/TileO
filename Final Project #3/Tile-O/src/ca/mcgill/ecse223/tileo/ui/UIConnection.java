package ca.mcgill.ecse223.tileo.ui;

public class UIConnection {
	
	// From leftmost, uppermost Tile of the connection
	int fromx;
	int fromy;
	
	// Horizontal or vertical Tile.
	boolean H_V;
	
	public UIConnection(int x, int y, boolean h_v){
		fromx = x;
		fromy = y;
		H_V = h_v;
	}
}
