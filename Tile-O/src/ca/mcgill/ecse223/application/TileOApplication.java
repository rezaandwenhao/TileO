package ca.mcgill.ecse223.tileo.application;

import javax.swing.JFrame;

import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.ui.DesignModePage;
import ca.mcgill.ecse223.tileo.ui.TileOGamePage;

public class TileOApplication {

	public static void main(String[] args){
		DesignModePage page = new DesignModePage();
		page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		page.setResizable(false);
		page.setBounds(0, 0, 700, 700);
		page.setVisible(true);
		
	}
	
	public static Game getGame() {
		return null;
	}
}

