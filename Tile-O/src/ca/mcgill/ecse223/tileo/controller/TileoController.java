package ca.mcgill.ecse223.tileo.controller;


import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.ActionTile;
import ca.mcgill.ecse223.tileo.model.ConnectTilesActionCard;
import ca.mcgill.ecse223.tileo.model.Deck;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Game.Mode;
import ca.mcgill.ecse223.tileo.model.NormalTile;
import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.WinTile;

public class TileoController {
	
    int standardDistance;
    
    public TileoController(){
    	
    }
	
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
	
	public void playConnectTilesActionCard(Tile tile1, Tile tile2) throws InvalidInputException{
		
		Game currentGame = TileOApplication.getGame();
		String error = "";
		
		if (currentGame.indexOfTile(tile1) < 0 || currentGame.indexOfTile(tile2) <0){
			error = error + "The tile1 or tile2 do not exist in the current game";
		}
		
		if(! (Math.abs(tile1.getY() - tile2.getY()) == standardDistance && tile1.getX() == tile2.getX()) || 
		! (Math.abs(tile1.getX() - tile2.getX()) == standardDistance && tile1.getY() == tile2.getY())) {
			error = error +"connection can not be created between non-adjacent tiles"; 
		}
		
		if (currentGame.numberOfConnections() <= 0){
			error = error +"The currentConnectionPieces is equal or smaller than zero";
		}
		
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		
		Deck deck = currentGame.getDeck();
		ConnectTilesActionCard currentCard = (ConnectTilesActionCard) deck.getCurrentCard();
		Player currentPlayer = currentGame.getCurrentPlayer();
		

		if (currentGame.indexOfPlayer(currentPlayer) != currentGame.numberOfPlayers()-1) {
		currentGame.setCurrentPlayer(currentGame.getPlayer(currentGame.indexOfPlayer(currentPlayer)+1));
		}
		else {
			currentGame.setCurrentPlayer(currentGame.getPlayer(0));
			error = error + "THe current player is the last player. The first player is set";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		
		if (deck.indexOfCard(currentCard) == deck.numberOfCards()-1) {
		deck.setCurrentCard(deck.getCard(deck.indexOfCard(currentCard)+1));
		}
		
		else {
			deck.setCurrentCard(deck.getCard(0));
			error = error + "The current card is the last card. The first card is set";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		
		try{
		currentGame.setMode(Mode.GAME);
		}catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public void land(NormalTile tile) throws InvalidInputException{
		String error = "";
		Game currentGame = TileOApplication.getGame();
		if (currentGame.indexOfTile(tile) < 0 || tile == null){
			error = "The tile does not exist in the currentGame!";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		try{
		tile.land();
		}catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public void land(WinTile tile) throws InvalidInputException{
		String error = "";
		Game currentGame = TileOApplication.getGame();
		if (currentGame.indexOfTile(tile) < 0 || tile == null){
			error = "The tile does not exist in the currentGame!";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		try{
			tile.land();
			}catch (RuntimeException e) {
				throw new InvalidInputException(e.getMessage());
			}
	}
	
	public void land(ActionTile tile) throws InvalidInputException{
		String error = "";
		Game currentGame = TileOApplication.getGame();
		if (currentGame.indexOfTile(tile) < 0 || tile == null){
			error = "The tile does not exist in the currentGame!";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		try{
			tile.land();
			}catch (RuntimeException e) {
				throw new InvalidInputException(e.getMessage());
			}
	}
}
