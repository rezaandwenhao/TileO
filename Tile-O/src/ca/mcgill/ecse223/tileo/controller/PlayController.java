package ca.mcgill.ecse223.tileo.controller;
import ca.mcgill.ecse223.tileo.model.*;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

import java.util.List;

import ca.mcgill.ecse223.tileo.application.*;
import ca.mcgill.ecse223.tileo.ui.*;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.ActionTile;
import ca.mcgill.ecse223.tileo.model.ConnectTilesActionCard;
import ca.mcgill.ecse223.tileo.model.Deck;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Game.Mode;
import ca.mcgill.ecse223.tileo.model.NormalTile;
import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.WinTile;
public class PlayController {
	public void startGame(Game selectedGame) throws InvalidInputException{
		TileOApplication.setCurrentGame(selectedGame);
		if(selectedGame.getDeck().getCards().size() !=32)
			throw new InvalidInputException("Incorrect number of action cards");
		else if(!selectedGame.hasWinTile())
			throw new InvalidInputException("No Win Tile exist on the board");
		else for(Player player:selectedGame.getPlayers()){
			if(!player.hasStartingTile())
				throw new InvalidInputException("At least one of the player has no starting Tile");
		}
		
		Deck deck = selectedGame.getDeck();
		deck.shuffle();
		
		for(int i = 0;i<selectedGame.numberOfTiles();i++){
			selectedGame.getTile(i).setHasBeenVisited(false);
		}
		
		for(int i = 0;i<selectedGame.numberOfPlayers();i++){
			selectedGame.getPlayer(i).setCurrentTile(selectedGame.getPlayer(i).getStartingTile());
			selectedGame.getPlayer(i).getCurrentTile().setHasBeenVisited(true);
		}
		
		selectedGame.setCurrentPlayer(selectedGame.getPlayer(0));
		
		selectedGame.setCurrentConnectionPieces(selectedGame.SpareConnectionPieces);
		selectedGame.setMode(Game.Mode.GAME);
		
	}	
	
	public void land(Tile tile) throws InvalidInputException{
		Game currentGame = TileOApplication.getCurrentGame();
		
		//check if the tile exist in the game.
		if(!currentGame.getTiles().contains(tile)) throw new InvalidInputException("Tile not existed.");
		tile.land();
	}
	
	public void NewGame(){
		
	}
	
	public void SaveandExitGame(TileOGamePage parent){
		//parent is needed for filechooser.
		
		TileOApplication.save(TileOApplication.getCurrentGame(),parent);
		TileOApplication.TitlePage();//tell the application to boot the title page
	}
	
	public void LoadGame(TileOGamePage parent){
		//parent is needed for filechooser.
		
		TileOApplication.setCurrentGame(TileOApplication.loadGame(parent));
		TileOApplication.GamePage();//tell the application to boot the game page.
	}
	
	
	public void playRemoveConnectionActionCard(Connection connection){// PLAY 7: IMPLEMENTED BY BIJAN [made changes to Deck, ActionCard, RemoveConnectionActionCard]
		Game game = TileOApplication.getCurrentGame();

		//VALIDATION CHECK 1: only proceed if connection is one of the connections of game
		if(game.indexOfConnection(connection)!=-1){
			Deck deck = game.getDeck();
			ActionCard currentCard = deck.getCurrentCard();

			//VALIDATION CHECK 2: Check if currentCard is a RemoveConnectionActionCard
			if(deck.getCurrentCard() instanceof RemoveConnectionActionCard){
				currentCard.play(connection);//remove the connection indicated
			}

			//VALIDATION CHECK 3: check if currentPlayer is last player, and set it to first player accordingly
			if(game.indexOfPlayer(game.getCurrentPlayer())==game.getPlayers().size()-1)
				game.setCurrentPlayer(game.getPlayer(0));

			//otherwise set currentPlayer to the next one in the list
			else
				game.setCurrentPlayer(game.getPlayer(game.indexOfPlayer(game.getCurrentPlayer())+1));

			//VALIDATION CHECK 4: check if currentCard is the last card in deck, and shuffle deck accordingly, as well as set 1st card to currentCard
			if(deck.indexOfCard(deck.getCurrentCard())==deck.numberOfCards()-1){
				deck.shuffle();
				deck.setCurrentCard(deck.getCard(0));
			}

			//otherwise set currentCard to the next one in the deck
			else
				deck.setCurrentCard(deck.getCard(deck.indexOfCard(deck.getCurrentCard())+1));

			//set game mode to game
			game.setMode(Mode.GAME);
		}
	}
	public List<Tile> rollDie(){ // PLAY 2: IMPLEMENTED BY BIJAN [made changes to Die, Player, & Tile]
		Game game = TileOApplication.getCurrentGame();
		Die die = game.getDie();

		//roll the die
		int rollNumber = die.roll();

		Player currentPlayer = game.getCurrentPlayer();

		//stores all the tiles that the Player can move to with the die roll
		List<Tile> tiles = currentPlayer.getPossibleMoves(rollNumber);

		//returns the tiles that the Player can move to
		return tiles;
	}
	
	//Borui Tao
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
	
	//Borui Tao
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
	
	//Borui Tao
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
	
	//Borui Tao
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

	  public void rollDieAgainAction(){
		  Game theGame = TileOApplication.getCurrentGame();
		 for(Player p : theGame.getPlayers()){
			 if( p!= theGame.getCurrentPlayer()){
				 p.setTurnsUntilActive(p.getTurnsUntilActive()+1);
			 }
	  }



}
