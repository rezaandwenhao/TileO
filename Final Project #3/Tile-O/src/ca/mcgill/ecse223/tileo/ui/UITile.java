package ca.mcgill.ecse223.tileo.ui;

import java.awt.Color;
import java.util.HashMap;


public class UITile {
	
	DesignModeResources.Type type;
	
	//Only used for action
	int cooldown;
	
	boolean Visited;
	//0 means no player visited. The number correspond to what player is visiting it.
	int currentPlayer;
	
	public UITile(DesignModeResources.Type t, int player, int c, boolean visited){
		type = t;
		currentPlayer = player;
		cooldown = c;
		Visited = visited;
	}
	
	
}
