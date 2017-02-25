package ca.mcgill.ecse223.tileo.controller;
import ca.mcgill.ecse223.tileo.model.*;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

import java.util.List;

import ca.mcgill.ecse223.tileo.application.*;
import ca.mcgill.ecse223.tileo.ui.*;
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

	
	
	
}
