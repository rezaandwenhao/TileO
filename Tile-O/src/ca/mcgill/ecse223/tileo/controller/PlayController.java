package ca.mcgill.ecse223.tileo.controller;
import ca.mcgill.ecse223.tileo.model.*;
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
}
