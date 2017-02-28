package ca.mcgill.ecse223.tileo.application;

import java.io.*;

import javax.swing.*;

import ca.mcgill.ecse223.tileo.model.*;
import ca.mcgill.ecse223.tileo.ui.*;
import ca.mcgill.ecse223.tileo.controller.*;

/* CONTRIBUTOR: Hieu Chau Nguyen
 * The application starts here. This class also stores the current Game's data and
 * save and load its data.
 */
public class TileOApplication{
	
	private static Game currentGame;
	private static JFileChooser run;
	
	private static MainMenuPage UIMenu;
	private static DesignModePage UIDesign;
	//private static GameModePage UIGame;
	
	public static void main(String[] args){
		MainMenu();
	}
	
	/* reset()
	 * Reset all the windows.
	 */
	private static void reset(){
		if(UIMenu != null){
			UIMenu.setVisible(false);
			UIMenu.dispose();
			UIMenu = null;
		}
		/*if(UIDesign != null){
			UIDesign.setVisible(false);
			UIDesign.dispose();
			UIDesign = null;
		}*/
		/*if(UIGame != null){
			UIGame.setVisible(false);
			UIGame.dispose();
			UIGame = null;
		}*/
	}
	
	/* MainMenu()
	 * Open the Main Menu Page.
	 */
	public static void MainMenu(){
		reset();
		UIMenu = new MainMenuPage();
	}

	public static void DesignPage() {
		reset();
		UIDesign = new DesignModePage();
	}
	
	public static void setCurrentGame(Game game){
		currentGame = game;
	}

	public static Game getCurrentGame() {
		return currentGame;
	}
	
	public static void saveDesign(Game savedGame, TileOGamePage parent){
		run = new JFileChooser();
		if(run.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION){
			String filename = run.getSelectedFile().getName();
			if(!run.getSelectedFile().getName().endsWith(".td")) filename = filename + ".td";				
			try {
				FileOutputStream fileOut = new FileOutputStream(filename);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(savedGame);
				out.close();
				fileOut.close();
				JOptionPane.showMessageDialog(parent, "Successfully save " + filename);
			} catch (Exception e) {
				throw new RuntimeException("Could not save data to file '" + filename + "'.");
			}
		}
	}
	
	public static Game loadDesign(TileOGamePage parent){
		run = new JFileChooser();
		Game g = null;
		if(run.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION){
			File filename = run.getSelectedFile();
			ObjectInputStream in;
			try {
				if(!filename.getName().endsWith(".td")) throw new Exception();
				FileInputStream fileIn = new FileInputStream(filename);
				in = new ObjectInputStream(fileIn);
				g = (Game) in.readObject();
				in.close();
				fileIn.close();
			} catch (Exception e) {
				
				
				//JOptionPane.showMessageDialog(parent, "Could not open " + filename);
			}
		}
		return g;
	}
	
	public static Game loadGame(TileOGamePage parent){
		run = new JFileChooser();
		Game g = null;
		if(run.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION){
			File filename = run.getSelectedFile();
			ObjectInputStream in;
			try {
				if(!filename.getName().endsWith(".tp")) throw new Exception();
				FileInputStream fileIn = new FileInputStream(filename);
				in = new ObjectInputStream(fileIn);
				g = (Game) in.readObject();
				in.close();
				fileIn.close();
			} catch (Exception e) {
				
				
				//JOptionPane.showMessageDialog(parent, "Could not open " + filename);
			}
		}
		return g;
	}
}