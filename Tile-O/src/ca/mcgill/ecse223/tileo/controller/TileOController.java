package ca.mcgill.ecse223.tileo.controller;

import java.util.List;


import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.ActionCard;
import ca.mcgill.ecse223.tileo.model.ConnectTilesActionCard;
import ca.mcgill.ecse223.tileo.model.Connection;
import ca.mcgill.ecse223.tileo.model.Deck;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Game.Mode;
import ca.mcgill.ecse223.tileo.model.LoseTurnActionCard;
import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.RemoveConnectionActionCard;
import ca.mcgill.ecse223.tileo.model.RollDieActionCard;
import ca.mcgill.ecse223.tileo.model.TeleportActionCard;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.TileO;

public class TileOController {
	int standardDistance = 33;
	//not sure where we define the distance, but we need one indeed//
	int aCurrentConnectionPieces = 32;
	String instruction1, instruction2, instruction3, instruction4, instruction5;
	public TileOController() {
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
		Game game = TileOApplication.getGame();
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
		Game game = TileOApplication.getGame();
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
		Game game = TileOApplication.getGame();
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
		Game game = TileOApplication.getGame();
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
		Game game = TileOApplication.getGame();
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
		Game game = TileOApplication.getGame();
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
		Game game = TileOApplication.getGame();
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
		Game game = TileOApplication.getGame();
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
		Game game = TileOApplication.getGame();
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
		Game game = TileOApplication.getGame();
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
		Game game = TileOApplication.getGame();
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
}
