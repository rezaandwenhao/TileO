package ca.mcgill.ecse223.tileo.controller;

import ca.mcgill.ecse223.tileo.controller.InvalidInputException;

import java.util.List;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.ActionCard;
import ca.mcgill.ecse223.tileo.model.Connection;
import ca.mcgill.ecse223.tileo.model.Deck;
import ca.mcgill.ecse223.tileo.model.Die;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Game.Mode;
import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.RemoveConnectionActionCard;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.WinTile;

public class TileOController {

	public TileOController() {
	}

	public void setLocationHiddenTile(int x, int y) throws InvalidInputException{ // DESIGN 6: IMPLEMENTED BY BIJAN
		Game game = TileOApplication.getGame();

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

	public List<Tile> rollDie(){ // PLAY 2: IMPLEMENTED BY BIJAN [made changes to Die, Player, & Tile]
		Game game = TileOApplication.getGame();
		Die die = game.getDie();

		//roll the die
		int rollNumber = die.roll();

		Player currentPlayer = game.getCurrentPlayer();

		//stores all the tiles that the Player can move to with the die roll
		List<Tile> tiles = currentPlayer.getPossibleMoves(rollNumber);

		//returns the tiles that the Player can move to
		return tiles;
	}

	public void playRemoveConnectionActionCard(Connection connection){// PLAY 7: IMPLEMENTED BY BIJAN [made changes to Deck, ActionCard, RemoveConnectionActionCard]
		Game game = TileOApplication.getGame();

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
}
