package ca.mcgill.ecse223.tileo.ui;

import java.awt.*;

import java.util.List;
import java.util.*;

import javax.swing.*;

public class DesignModeResources {
	
	final int xTiles = 18;
	final int yTiles = 10;
	final int block_Size = 18;
	final int inner_Size = 14;
	
	List<UIConnection> uiconnect = new ArrayList<UIConnection>();
	UITile uitile[][];
	
	Color background = Color.WHITE;
	HashMap<Boolean, Color> outerTile= new HashMap<Boolean, Color>();
	
	enum Type{Empty, Normal, Win, Action}
	HashMap<Type, Color> typecolor = new HashMap<Type, Color>();
	
	HashMap<Integer, Color> playercolor = new HashMap<Integer, Color>();
	
	public DesignModeResources(){
		outerTile.put(true, Color.BLACK);
		outerTile.put(false, Color.LIGHT_GRAY);
		
		typecolor.put(Type.Empty, Color.LIGHT_GRAY);
		typecolor.put(Type.Normal, Color.WHITE);
		typecolor.put(Type.Win, Color.WHITE);
		typecolor.put(Type.Action, Color.WHITE);
		
		playercolor.put(1, Color.RED);
		playercolor.put(2, Color.BLUE);
		playercolor.put(3, Color.GREEN);
		playercolor.put(4, Color.ORANGE);
	}
}
