package ca.mcgill.ecse223.tileo.model;

import java.util.List;

public class DesignController {	 
	public static void main(String[] args) {
	}




	//=============================== Jiawei=====================================

	private static String filename = "designSave.to"; //----------------

	// renamed placeAtile() >> placeTile()
	public void placeTile (int x , int y)  throws InvalidInputException{
		Game theGame = this.game;  //
		Tile newTile = new NormalTile(x,y,theGame);
	}




	// Code according to sequence diagram
	public void deleteConnection(Connection aConnection){
		aConnection.delete();
	}


	// the method removes all tiles connected to a tile
	// requirements ask us to remove connection between 2 tiles
	// requires modification



	// GameController.java
	public void rollDieAgainAction(){
		Game theGame = this.game;
		for(Player p : theGame.getPlayers()){
			if( p!= theGame.getCurrentPlayer()){
				p.setTurnsUntilActive(p.getTurnsUntilActive()+1);
			}
		}
	}

	
	
	
	
	public static void save() {
		PersistenceObjectStream.serialize(Game);
		} 
	
	public static BTMS load() {
		PersistenceObjectStream.setFilename(filename);
		btms= (BTMS) PersistenceObjectStream.deserialize();
		// model cannot be loaded - create empty BTMS
		if   (btms== null) {
			btms= new BTMS();
			} 
		return btms;
	}
	
	

	//======================================================================



}
