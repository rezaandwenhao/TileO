package ca.mcgill.ecse223.tileo.controller;

import java.util.ArrayList;
import java.util.List;


import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.ActionCard;
import ca.mcgill.ecse223.tileo.model.ConnectTilesActionCard;
import ca.mcgill.ecse223.tileo.model.Connection;
import ca.mcgill.ecse223.tileo.model.Deck;
import ca.mcgill.ecse223.tileo.model.Die;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Game.Mode;
import ca.mcgill.ecse223.tileo.model.LoseTurnActionCard;
import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.RemoveConnectionActionCard;
import ca.mcgill.ecse223.tileo.model.RollDieActionCard;
import ca.mcgill.ecse223.tileo.model.TeleportActionCard;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.TileO;
import ca.mcgill.ecse223.tileo.model.WinTile;

public class DesignController {
	int standardDistance = 33;
	//not sure where we define the distance, but we need one indeed//
	int aCurrentConnectionPieces = 32;
	String instruction1, instruction2, instruction3, instruction4, instruction5;
	private List<Tile> neighbors = new ArrayList<Tile>();// ADDED BY BIJAN, USED IN GETNEIGHBORS() METHOD

	//Tile Associations
	private List<Connection> connections;
	private Game game;
	
	
	public DesignController() {
	}
	
	public void createDesign(int number) throws InvalidInputException{
		String error = "";
		Player player[] = null;
		if (number < 2 || number > 4) {
			throw new InvalidInputException("Invalid number of players "); 
		}
		try {
			TileO tileO = new TileO();
			Game game =  new Game(aCurrentConnectionPieces, tileO);
			game.setMode(Game.Mode.DESIGN);
			Player.Color pc[] = {Player.Color.RED, Player.Color.BLUE, Player.Color.GREEN, Player.Color.YELLOW};
			for(int i = 0; i < number; i++){
				player[i] = new Player(i+1, game);
				player[i].setColor(pc[i]);
			}
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			throw new InvalidInputException(error);
		}		
	}

	public void putConnection(int tile1x, int tile1y, int tile2x, int tile2y) throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		//how to set mode???//
		//check if the tiles are adjacent, if not, directly end the method//
		if(Math.abs(tile1y - tile2y) == standardDistance && tile1x == tile2x){
			break;
		}
		else if(Math.abs(tile1x - tile2x) == standardDistance && tile1y == tile2y){
			break;
		}
		else{
			throw new InvalidInputException("connection can not be created between non-adjacent tiles"); 
		}
		try {
			Connection connection = new Connection(game);
			int index1, index2;
			List<Tile> tiles = game.getTiles();
			for (int i = 0; tiles.get(i).getX() != tile1x && tiles.get(i).getY() != tile1y; i++) {
				index1 = i;				
			}
			index1++;
			for (int j = 0; tiles.get(j).getX() != tile2x && tiles.get(j).getY() != tile2y; j++) {
				index2 = j;				
			}
			index2++;
			connection.addTile(tiles.get(index1));
			connection.addTile(tiles.get(index2));
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
			
	}
	
	//loseTurnActionCard//
	public void addLoseTurnActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			LoseTurnActionCard loseTurnActionCard = new LoseTurnActionCard(instruction1, deck);
			deck.addCard(loseTurnActionCard);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public void removeLoseTurnActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			List<ActionCard> cards = deck.getCards();
			boolean cardFound = false;
			int cardFoundIndex = 0;
			for(ActionCard card : cards){
				if(card instanceof LoseTurnActionCard){
					cardFound = true;
					cardFoundIndex=cards.indexOf(card);
				}
			}
			if(cardFound)
				deck.removeCard(deck.getCard(cardFoundIndex));
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	//connectTilesActionCard//
	public void addConnectTilesActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			ConnectTilesActionCard connectTilesActionCard = new ConnectTilesActionCard(instruction1, deck);
			deck.addCard(connectTilesActionCard);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public void removeConnectTilesActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			List<ActionCard> cards = deck.getCards();
			boolean cardFound = false;
			int cardFoundIndex = 0;
			for(ActionCard card : cards){
				if(card instanceof ConnectTilesActionCard){
					cardFound = true;
					cardFoundIndex=cards.indexOf(card);
				}
			}
			if(cardFound)
				deck.removeCard(deck.getCard(cardFoundIndex));
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	//removeConnectionActionCard//
	public void addRemoveConnectionActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			RemoveConnectionActionCard removeConnectionActionCard = new RemoveConnectionActionCard(instruction1, deck);
			deck.addCard(removeConnectionActionCard);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public void removeRemoveConnectionActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			List<ActionCard> cards = deck.getCards();
			boolean cardFound = false;
			int cardFoundIndex = 0;
			for(ActionCard card : cards){
				if(card instanceof RemoveConnectionActionCard){
					cardFound = true;
					cardFoundIndex=cards.indexOf(card);
				}
			}
			if(cardFound)
				deck.removeCard(deck.getCard(cardFoundIndex));
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	//RollDieActionCard//
	public void addRollDieActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			RollDieActionCard rollDieActionCard = new RollDieActionCard(instruction1, deck);
			deck.addCard(rollDieActionCard);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public void removeRollDieActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			List<ActionCard> cards = deck.getCards();
			boolean cardFound = false;
			int cardFoundIndex = 0;
			for(ActionCard card : cards){
				if(card instanceof RollDieActionCard){
					cardFound = true;
					cardFoundIndex=cards.indexOf(card);
				}
			}
			if(cardFound)
				deck.removeCard(deck.getCard(cardFoundIndex));
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	//TeleportActionCard//
	public void addTeleportActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			TeleportActionCard teleportActionCard = new TeleportActionCard(instruction1, deck);
			deck.addCard(teleportActionCard);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public void removeTeleportActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			List<ActionCard> cards = deck.getCards();
			boolean cardFound = false;
			int cardFoundIndex = 0;
			for(ActionCard card : cards){
				if(card instanceof TeleportActionCard){
					cardFound = true;
					cardFoundIndex=cards.indexOf(card);
				}
			}
			if(cardFound)
				deck.removeCard(deck.getCard(cardFoundIndex));
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	
	
	public void setLocationHiddenTile(int x, int y) throws InvalidInputException{ // DESIGN 6: IMPLEMENTED BY BIJAN
		Game game = TileOApplication.getCurrentGame();

		//catches invalid input exception
		try {

			//first check if x,y associated with an existing Tile in game. If so, delete that tile.
			List<Tile> tiles = game.getTiles();
			boolean locationUsed = false;
			for(Tile tile : tiles){
				if(x==tile.getX() && y==tile.getY())
					tile.delete();
			}
			//No Tile at x,y anymore

			//check if game already has a winTile
			boolean hasWinTile = game.hasWinTile();

			//condition where there is no winTile
			if(hasWinTile==false){
				WinTile updatedWinTile = new WinTile(x,y,game);
				game.setWinTile(updatedWinTile);
			}

			//condition where there is a winTile
			else{
				WinTile existingWinTile = game.getWinTile();
				existingWinTile.delete();
				//Tile updatedTile = new Tile(existingWinTile.getX(), existingWinTile.getY(), game);//BORUI NOT SURE WHAT THIS IS FOR
				WinTile updatedWinTile = new WinTile(x,y,game);
				game.setWinTile(updatedWinTile);
			}
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	
		  public void placeTile (int x , int y)  throws InvalidInputException{
		  Game theGame = this.game;  //
		  Tile newTile = new NormalTile(x,y,theGame);
		  theGame.addTile(newTile);
	  }
	  
	  
	  

	  // Code according to sequence diagram
	  public void deleteConnection(Connection aConnection){
		 aConnection.delete();
	  }
	
	//Borui Tao
	public void setStartTile(Tile startTile, Player player) throws InvalidInputException {
		Game currentGame = TileOApplication.getGame();
		String error = "";
	 if (currentGame.indexOfPlayer(player) < 0 || player==null ) {
		 error = error + "The current player does not exist in the current game";
	 }
	 
	 if (currentGame.indexOfTile(startTile) <0 || startTile==null) { 
		 error = error + "The startTile does not exist in the current game";
	 }
	 
	 if (error.length() > 0){
		 throw new InvalidInputException(error.trim());
	 }
	 
	 try{
	    player.setStartingTile(startTile);
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	
	
}
