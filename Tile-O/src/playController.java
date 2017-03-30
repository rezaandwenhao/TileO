import java.util.List;


public class playController {
	  //The swapPositionActionCard implemented by Borui Tao
	  public boolean BoPlaySwapPositionActionCard(Player player)		
	  {		
	    boolean wasEventProcessed = false;		
	    		
	    PlayMode aPlayMode = playMode;		
	    switch (aPlayMode)		
	    {
	      case ActionCard:
	        if (isSwapPositionActionCard())		
	        {		
	      	 try {		
					playSwapPositionActionCard(player);		
				} catch (InvalidInputException e) {		
					// TODO Auto-generated catch block		
					e.printStackTrace();		
				}		       	
	          setPlayMode(PlayMode.Roll);	
	          TileOApplication.getCurrentGame().setMode(Mode.GAME);
	          wasEventProcessed = true;	
	        }		
	        break;		
	      default:		
	    }		
	    return wasEventProcessed;		
	  }	
	  
	  public void playSwapPositionActionCard(Player selectedPlayer) throws InvalidInputException{ // Borui Tao
			//check if tile needs to be tile of currentGame
			Game currentGame= TileOApplication.getCurrentGame();
			Player currentPlayer = currentGame.getCurrentPlayer();
			Tile srcTile = currentPlayer.getCurrentTile();
			Tile destTile = selectedPlayer.getCurrentTile();
			if(currentPlayer.equals(selectedPlayer)){
				throw new InvalidInputException("Mush choose a different player!");
			}
			
			//To make two player switch positions, first I find a temporary tile to let the selected player land on
			//Then the current player lands on the selected player's tile. Finally the selected player lands on
			//the current player's tile
		    int count=0;
			for(Tile tempTile: currentGame.getTiles()){
				boolean hasPlayer = false;
				for(Player p: currentGame.getPlayers()){
					if(p.getCurrentTile().equals(tempTile)){
						hasPlayer = true;
						break;
					}
				}
				if(!hasPlayer){
			     	count++;
				}
			}
			selectedPlayer.swappedPlayerLand(currentGame.getTiles().get(count)); //land on the temporary tile
			
			currentPlayer.swappedPlayerLand(destTile);
			selectedPlayer.swappedPlayerLand(srcTile);
		
			currentGame.selectnextCard();	
			currentGame.setMode(Mode.GAME);
  	        currentGame.determineNextPlayer();

		}
	  
	    public boolean isSwapPositionActionCard(){
			Game game = TileOApplication.getCurrentGame();
			Deck deck = game.getDeck();
			if(deck.getCurrentCard() instanceof SwapPositionActionCard)
				return true;
			return false;
			}
	    
	    /* in the ui, to determine the position of selected player, i use the following method inside the mouse clicked: 
	     * 
	     * case GAME_SWAPPOSITIONACTIONCARD:
								singleTile[0] = x;
								singleTile[1] = y;
							try {
								Player selectedPlayer = pc.getPlayer(singleTile[0], singleTile[1]);
								pc.BoPlaySwapPositionActionCard(selectedPlayer);
							} catch (InvalidInputException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							break;
	     * 
	     * The getPLayer method in the play controller is as follows: (it takes the position of the tile to determine the player 
	     * landing on that tile
	     *
	     * public Player getPlayer(int tileX, int tileY) throws InvalidInputException {
         
		Game game = TileOApplication.getCurrentGame();
		Player playerFound = game.getCurrentPlayer(); 
		try {
			int index1 =0;
			boolean foundTile = false;
			boolean foundPlayer = false;
			List<Tile> tiles = game.getTiles();
			for(Tile t: tiles){
				if(t.getX() == tileX && t.getY() == tileY){
					foundTile = true;
					index1 = game.indexOfTile(t);
				}
			}
			if(foundTile){
				for(Player p: game.getPlayers()){
					if(p.getCurrentTile().equals(tiles.get(index1))){
						foundPlayer = true;
					    playerFound = p;
					}
				}
				
			}
			if (foundPlayer)
				return playerFound;
			else {
				throw new InvalidInputException("Player not found.");
			}
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
			
	}
	     */
	     */
}
