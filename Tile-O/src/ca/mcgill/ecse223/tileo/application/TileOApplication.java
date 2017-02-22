package ca.mcgill.ecse223.tileo.application;

import ca.mcgill.ecse223.tileo.model.*;
import ca.mcgill.ecse223.tileo.ui.*;

public class TileOApplication{
	
	private static Game currentGame;
	private static String something;
	
	public static void main(String[] args){
		MainMenuPage menu = new MainMenuPage();
		menu.setSize(600,700);
		menu.setResizable(false);
		menu.setVisible(true);
	}
	
	public static void setCurrentGame(Game game){
		currentGame = game;
	}
}