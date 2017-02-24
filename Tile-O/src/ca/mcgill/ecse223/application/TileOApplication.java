package ca.mcgill.ecse223.tileo.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ca.mcgill.ecse223.tileo.model.*;
import ca.mcgill.ecse223.tileo.ui.*;

public class TileOApplication{
	
	private static Game currentGame;
	private static JFileChooser run;
	
	public static void main(String[] args){
		MainMenuPage menu = new MainMenuPage();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setSize(700,700);
		menu.setResizable(false);
		menu.setVisible(true);
	}
	
	public static void setCurrentGame(Game game){
		currentGame = game;
	}

	public static Game getCurrentGame() {
		return currentGame;
	}
	
	public static void saveDesign(Game savedGame, TileOGamePage parent){
		run = new JFileChooser();
		if(run.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION){
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
				JOptionPane.showMessageDialog(parent, "Could not open " + filename);
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
				JOptionPane.showMessageDialog(parent, "Could not open " + filename);
			}
		}
		return g;
	}
}