package ca.mcgill.ecse223.tileo.controller;
import ca.mcgill.ecse223.tileo.model.*;
import ca.mcgill.ecse223.tileo.model.ActionTile.ActionTileStatus;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import ca.mcgill.ecse223.tileo.application.*;
import ca.mcgill.ecse223.tileo.ui.*;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

//line 3 "PlayControllerSM.ump"
public class PlayController		
{		
//------------------------		
// MEMBER VARIABLES		
//------------------------		
//PlayController Attributes		
private int rollNumber;		
private int standardDistance;		
//PlayController State Machines		
public enum PlayMode { Ready, Roll, Move, ActionCard, Won }		
private PlayMode playMode;		
//PlayController Associations		
private List<Tile> possibleMoves;		
//------------------------		
// CONSTRUCTOR		
//------------------------		
	public PlayController()		
	{		
	 rollNumber = 0;		
	 standardDistance = 1;		
	 possibleMoves = new ArrayList<Tile>();		
	 setPlayMode(PlayMode.Ready);
	 Game currentGame = TileOApplication.getCurrentGame();
	 
	 try{
		 BoLoad(null);
		 if(playMode == PlayMode.Move){
			 rollNumber = currentGame.getDie().getLatest_roll();
			 possibleMoves = getFinalTiles(rollNumber);
		 }
	 }catch(NullPointerException e){}
	}		
	//------------------------		
	// INTERFACE		
	//------------------------		
	public boolean setRollNumber(int aRollNumber)		
	{		
	 boolean wasSet = false;		
	 rollNumber = aRollNumber;		
	 wasSet = true;		
	 return wasSet;		
	}		
	public boolean setStandardDistance(int aStandardDistance)		
	{		
	 boolean wasSet = false;		
	 standardDistance = aStandardDistance;		
	 wasSet = true;		
	 return wasSet;		
	}		
	public int getRollNumber()		
	{		
	 return rollNumber;		
	}		
	public int getStandardDistance()		
	{		
	 return standardDistance;		
	}		
	public String getPlayModeFullName()		
	{		
	 String answer = playMode.toString();		
	 return answer;		
	}		
	public PlayMode getPlayMode()		
	{		
	 return playMode;		
	}		
	public boolean startGame()		
	{		
	 boolean wasEventProcessed = false;		
	 		
	 PlayMode aPlayMode = playMode;		
	 switch (aPlayMode)		
	 {		
	   case Ready:		
	     // line 6 "PlayControllerSM.ump"		
	     try {		
				Start();		
			} catch (InvalidInputException e) {		
				// TODO Auto-generated catch block		
				e.printStackTrace();		
			}		
	     setPlayMode(PlayMode.Roll);		
	     wasEventProcessed = true;		
	     break;		
	   default:		
	     // Other states do respond to this event		
	 }		
	 return wasEventProcessed;		
}

	public void startNewGame(Game selectedGame) throws InvalidInputException{  // Nguyen Hieu Chau
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
		deck.setCurrentCard(deck.getCard(0));
		
		for(int i = 0;i<selectedGame.numberOfTiles();i++){
			selectedGame.getTile(i).setHasBeenVisited(false);
		}
		
		for(int i = 0;i<selectedGame.numberOfPlayers();i++){
			selectedGame.getPlayer(i).setCurrentTile(selectedGame.getPlayer(i).getStartingTile());
			selectedGame.getPlayer(i).setTurnsUntilActive(0);
			selectedGame.getPlayer(i).getCurrentTile().setHasBeenVisited(true);
		}
		
		selectedGame.setCurrentPlayer(selectedGame.getPlayer(0));
		selectedGame.setCurrentConnectionPieces(Game.SpareConnectionPieces);
		selectedGame.setMode(Game.Mode.GAME);
		
		TileOApplication.GamePage();
	}
	
	 public boolean BoLoad(TileOGamePage parent)			
		  {		
		    boolean wasEventProcessed = false;		
		    		
		    PlayMode aPlayMode = playMode;	
		    switch (aPlayMode)		
		    {		
		      case Ready:		
		        if (isInGameMode())		
		        {		
		        // line 9 "PlayControllerSM.ump"	
		          setPlayMode(PlayMode.Roll);		
		          wasEventProcessed = true;		
		          break;		
		        }		
		        if (isInWonMode())		
		        {		
		        // line 12 "PlayControllerSM.ump"	
		          setPlayMode(PlayMode.Won);		
		          wasEventProcessed = true;		
		          break;		
		        }	
		        if(isInMoveMode()){
		        	setPlayMode(PlayMode.Move);
		        	wasEventProcessed = true;		
			        break;
		        }
		        if (isInNotInGameOrWonMode())		
		        {		
		        // line 15 "PlayControllerSM.ump"	
		          setPlayMode(PlayMode.ActionCard);		
		          wasEventProcessed = true;		
		          break;		
		        }	
		        break;		
		      default:		
		        // Other states do respond to this event		
		    }		
		    return wasEventProcessed;		
		  }		
		  
		public boolean BoRollDie()		
		  {		
		    boolean wasEventProcessed = false;		
		    		
		    PlayMode aPlayMode = playMode;		
		    switch (aPlayMode)		
		    {		
		      case Roll:		
		        // line 20 "PlayControllerSM.ump"
		    	possibleMoves.clear();
		        possibleMoves = getFinalTiles(rollDie());		
		        setPlayMode(PlayMode.Move);	
		        TileOApplication.getCurrentGame().setMode(Mode.GAME_TAKETURN);
		        wasEventProcessed = true;		
		        break;		
		      default:		
		        // Other states do respond to this event		
		    }		
		    return wasEventProcessed;		
		  }		
		  public boolean BoLand(Tile tile)		
		  {		
		    boolean wasEventProcessed = false;		
		    		
		    PlayMode aPlayMode = playMode;	
		    tile.land();
		    switch (aPlayMode)		
		    {		
		      case Move:		
		        if (isNormalTile(tile))		
		        {			
		          TileOApplication.getCurrentGame().setMode(Mode.GAME);  	
		          setPlayMode(PlayMode.Roll);		
		          wasEventProcessed = true;
		          break;		
		        }		
		        if (isWinTile(tile))		
		        {
		          TileOApplication.getCurrentGame().setMode(Mode.GAME_WON);  	
		          setPlayMode(PlayMode.Won);		
		          wasEventProcessed = true;		
		          break;		
		        }		
		        if (isActionTile(tile))		
		        {	
		          if(((ActionTile) tile).getTurnsUntilActive() == 0){
		        	  TileOApplication.getCurrentGame().setMode(Mode.GAME_DRAWCARD); 
		        	  setPlayMode(PlayMode.ActionCard);		
		        	  wasEventProcessed = true;
		          }
		          else{
		        	  TileOApplication.getCurrentGame().setMode(Mode.GAME); 
		        	  TileOApplication.getCurrentGame().determineNextPlayer();
		        	  setPlayMode(PlayMode.Roll);
		        	  wasEventProcessed = true;
		          }
		          break;		
		        }		
		        break;		
		      default:		
		        // Other states do respond to this event		
		    }		
		    return wasEventProcessed;		
		  }		
		  public boolean BoPlayRollDieActionCard()		
		  {		
		    boolean wasEventProcessed = false;		
		    		
		    PlayMode aPlayMode = playMode;		
		    switch (aPlayMode)		
		    {		
		      case ActionCard:		
		        if (isRollDieActionCard())		
		        {		
		        // line 36 "PlayControllerSM.ump"		
		          //possibleMoves = playRollDieAgainActionCard();	
		          Game currentGame = TileOApplication.getCurrentGame();
		          currentGame.selectnextCard();
		          setPlayMode(PlayMode.Roll);		
		          wasEventProcessed = true;		
		          break;		
		        }		
		        break;		
		      default:		
		        // Other states do respond to this event		
		    }		
		    return wasEventProcessed;		
		  }	
		 
		 public boolean BoPlayConnectTilesActionCard()		
		  {		
		    boolean wasEventProcessed = false;		
		    		
		    PlayMode aPlayMode = playMode;		
		    switch (aPlayMode)		
		    {	
		      case Roll:
		      case ActionCard:
		        if (isConnectTilesActionCard())		
		        {		
		        // line 39 "PlayControllerSM.ump"		
		          Game currentGame = TileOApplication.getCurrentGame();
			      currentGame.selectnextCard();
			      currentGame.setMode(Mode.GAME);
		          setPlayMode(PlayMode.Roll);		
		          wasEventProcessed = true;	
		        }		
		        break;		
		      default:		
		        // Other states do respond to this event		
		    }		
		    return wasEventProcessed;		
		  }		
		  public boolean BoPlayRemoveConnectionActionCard()		
		  {		
		    boolean wasEventProcessed = false;		
		    		
		    PlayMode aPlayMode = playMode;		
		    switch (aPlayMode)		
		    {		
		      case ActionCard:		
		        if (isRemoveConnectionActionCard())		
		        {		
		        // line 42 "PlayControllerSM.ump"		
		        	Game currentGame = TileOApplication.getCurrentGame();
			        currentGame.selectnextCard();	
			        currentGame.setMode(Mode.GAME);
		          setPlayMode(PlayMode.Roll);		
		          wasEventProcessed = true;		
		        }		
		        break;		
		      default:		
		        // Other states do respond to this event		
		    }		
		    return wasEventProcessed;		
		  }		
		  public boolean BoPlayTeleportActionCard(Tile tile)		
		  {		
		    boolean wasEventProcessed = false;		
		    		
		    PlayMode aPlayMode = playMode;		
		    switch (aPlayMode)		
		    {		
		      case ActionCard:		
		        if (isTeleportAndNormalTile(tile))		
		        {		
		        // line 45 "PlayControllerSM.ump"		
		          try {		
					playTeleportActionCard(tile);		
				} catch (InvalidInputException e) {		
					// TODO Auto-generated catch block		
					e.printStackTrace();		
				}		
		          setPlayMode(PlayMode.Roll);	
		          TileOApplication.getCurrentGame().setMode(Mode.GAME);
		          wasEventProcessed = true;		
		          break;		
		        }		
		        if (isTeleportAndWinTile(tile))		
		        {		
		        // line 48 "PlayControllerSM.ump"		
		          try {		
					playTeleportActionCard(tile);		
				} catch (InvalidInputException e) {		
					// TODO Auto-generated catch block		
					e.printStackTrace();		
				}		
		          setPlayMode(PlayMode.Won);
		          TileOApplication.getCurrentGame().setMode(Mode.GAME_WON);
		          wasEventProcessed = true;		
		          break;		
		        }
		        if (isTeleportAndActionTile(tile))		
		        {		
		        // line 51 "PlayControllerSM.ump"		
		          try {		
					playTeleportActionCard(tile);		
				} catch (InvalidInputException e) {		
					// TODO Auto-generated catch block		
					e.printStackTrace();		
				}		
		          setPlayMode(PlayMode.ActionCard);	
		          TileOApplication.getCurrentGame().setMode(Mode.GAME_DRAWCARD);
		          wasEventProcessed = true;		
		          break;		
		        }		
		        break;		
		      default:		
		        // Other states do respond to this event		
		    }		
		    return wasEventProcessed;		
		  }	
		  public boolean BoPlayLoseTurnActionCard()		
		  {		
		    boolean wasEventProcessed = false;		
		    		
		    PlayMode aPlayMode = playMode;		
		    switch (aPlayMode)		
		    {		
		      case ActionCard:		
		        if (isLoseTurnActionCard())		
		        {		
		        // line 54 "PlayControllerSM.ump"
		        	Game currentGame = TileOApplication.getCurrentGame();
			        currentGame.selectnextCard();
			      currentGame.setMode(Mode.GAME);
					currentGame.determineNextPlayer();
		          setPlayMode(PlayMode.Roll);		
		          wasEventProcessed = true;		
		          break;		
		        }		
		        break;		
		      default:		
		        // Other states do respond to this event		
		    }		
		    return wasEventProcessed;		
		  }
		  public void noMoveAvailable() {
			  boolean wasEventProcessed = false;		
	    		
			    PlayMode aPlayMode = playMode;		
			    switch (aPlayMode)		
			    {		
			      case Move:			
			        // line 54 "PlayControllerSM.ump"
			        	Game currentGame = TileOApplication.getCurrentGame();
				        currentGame.selectnextCard();
				      currentGame.setMode(Mode.GAME);
						currentGame.determineNextPlayer();
			          setPlayMode(PlayMode.Roll);		
			          wasEventProcessed = true;		
			          break;	
			      default:		
			        // Other states do respond to this event
			    }
		}
		  
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
				selectedPlayer.swappedPlayerLand(currentGame.getTiles().get(count));
				
				currentPlayer.swappedPlayerLand(destTile);
				selectedPlayer.swappedPlayerLand(srcTile);
			
				currentGame.selectnextCard();	
				currentGame.setMode(Mode.GAME);
	    	    currentGame.determineNextPlayer();

			}
		  
		  private void setPlayMode(PlayMode aPlayMode)		
		  {		
		    playMode = aPlayMode;		
		  }		
		  public Tile getPossibleMove(int index)		
		  {		
		    Tile aPossibleMove = possibleMoves.get(index);		
		    return aPossibleMove;		
		  }		
		  public List<Tile> getPossibleMoves()		
		  {		
		    List<Tile> newPossibleMoves = Collections.unmodifiableList(possibleMoves);		
		    return newPossibleMoves;		
		  }		
		  public int numberOfPossibleMoves()		
		  {		
		    int number = possibleMoves.size();		
		    return number;		
		  }		
		  public boolean hasPossibleMoves()		
		  {		
		    boolean has = possibleMoves.size() > 0;		
		    return has;		
		  }		
		  public int indexOfPossibleMove(Tile aPossibleMove)		
		  {		
		    int index = possibleMoves.indexOf(aPossibleMove);		
		    return index;		
		  }		
		  public static int minimumNumberOfPossibleMoves()		
		  {		
		    return 0;		
		  }		
		  public boolean addPossibleMove(Tile aPossibleMove)		
		  {		
		    boolean wasAdded = false;		
		    if (possibleMoves.contains(aPossibleMove)) { return false; }		
		    possibleMoves.add(aPossibleMove);		
		    wasAdded = true;		
		    return wasAdded;		
		  }		
		  public boolean removePossibleMove(Tile aPossibleMove)		
		  {		
		    boolean wasRemoved = false;		
		    if (possibleMoves.contains(aPossibleMove))		
		    {		
		      possibleMoves.remove(aPossibleMove);		
		      wasRemoved = true;		
		    }		
		    return wasRemoved;		
		  }		
		  public boolean addPossibleMoveAt(Tile aPossibleMove, int index)		
		  {  		
		    boolean wasAdded = false;		
		    if(addPossibleMove(aPossibleMove))		
		    {		
		      if(index < 0 ) { index = 0; }		
		      if(index > numberOfPossibleMoves()) { index = numberOfPossibleMoves() - 1; }		
		      possibleMoves.remove(aPossibleMove);		
		      possibleMoves.add(index, aPossibleMove);		
		      wasAdded = true;		
		    }		
		    return wasAdded;		
		  }		
		  public boolean addOrMovePossibleMoveAt(Tile aPossibleMove, int index)		
		  {		
		    boolean wasAdded = false;		
		    if(possibleMoves.contains(aPossibleMove))		
		    {		
		      if(index < 0 ) { index = 0; }		
		      if(index > numberOfPossibleMoves()) { index = numberOfPossibleMoves() - 1; }		
		      possibleMoves.remove(aPossibleMove);		
		      possibleMoves.add(index, aPossibleMove);		
		      wasAdded = true;		
		    } 		
		    else 		
		    {		
		      wasAdded = addPossibleMoveAt(aPossibleMove, index);		
		    }		
		    return wasAdded;		
		  }		
		  public void delete()		
		  {		
		    possibleMoves.clear();		
		  }		
		  /**
		   * Bijan Sadeghi
		   */		
		  // line 96 "PlayControllerSM.ump"		
		   public int rollDie(){		
		    Game game = TileOApplication.getCurrentGame();

				Die die = game.getDie();

				// roll the die
				rollNumber = die.roll();

				return rollNumber;
			}
			  /**					
		    * Bijan Sadeghi			
		    */						
		   // line 108 "PlayControllerSM.ump"		
		    public List<Tile> getFinalTiles(int rollNumber){		
		    	Game game = TileOApplication.getCurrentGame();

				// the current player
				Player currentPlayer = game.getCurrentPlayer();
				
				// the current player's tile
				Tile currentTile = currentPlayer.getCurrentTile();
				
				// stores all the tiles that the Player can move to with the die roll
				resetFinalTiles();
				List<Tile> finalTiles = currentPlayer.getPossibleMoves(currentTile, null, rollNumber);
				game.setMode(Mode.GAME_TAKETURN);

				return finalTiles;
			}
			
			
	public void resetFinalTiles() { //Implemented by Bijan Sadeghi
				Game game = TileOApplication.getCurrentGame();

				// the current player
				Player currentPlayer = game.getCurrentPlayer();

				// resets the finalTiles attribute in Player for the next time he plays
				currentPlayer.resetFinalTiles();
			}
			
	public int[][] getHighlight() {
				int highlight[][] = new int[possibleMoves.size()][2];
				int index = 0;
				for (Tile t : possibleMoves) {
					highlight[index][0] = t.getX();
					highlight[index][1] = t.getY();
					index++;
				}
				return highlight;
			}
	
	public void landOnTile(int x, int y) throws InvalidInputException { // HIEU CHAU NGUYEN, Bijan Sadeghi and Borui Tao
		Game currentGame = TileOApplication.getCurrentGame();
		boolean wasSet = false;
		for(Tile tile : currentGame.getTiles()){
			if(tile.getX()==x && tile.getY()==y){
				BoLand(tile);
				wasSet=true;
			}
		}
		if(wasSet==false) throw new InvalidInputException("Tile not existed.");
	}
	public List<Tile> playRollDieAgainActionCard(){  // Implemented by Jiawei Ni
		 return getFinalTiles(rollDie());
    }
	
	public void playTeleportActionCard(Tile tile) throws InvalidInputException{ // Ebou Jobe
		//check if tile needs to be tile of currentGame
		Game currentGame= TileOApplication.getCurrentGame();
		tile.land();
		currentGame.selectnextCard();	
	}
    
     public static boolean isTileInCurrentGame(Game game, Tile tile){ //helper method for playTeleportActionCard
	    	
			int index=game.indexOfTile(tile);
			for(int i=0; i< game.getTiles().size();i++){
			//	if(index== indexOfTile(game.getTiles().get(i))){
			//d		return true;
			//	}
			}
			return false;
	    }
     
    public void SaveandExitGame(TileOGamePage parent){ // Implemented by Nguyen Hieu Chau
		//parent is needed for filechooser.
		try{
			TileOApplication.saveGame(TileOApplication.getCurrentGame(),parent);
			TileOApplication.MainMenu();//tell the application to boot the title page
		}catch(RuntimeException e){
			JOptionPane.showMessageDialog(parent, "Unable to save the game");
		}
		
	}
	
	public void LoadGame(TileOGamePage parent){   // Implemented by Nguyen Hieu Chau
		//parent is needed for filechooser.
		
		try{
			TileOApplication.setCurrentGame(TileOApplication.loadGame(parent));
			TileOApplication.GamePage();//tell the application to boot the game page.
		}catch(RuntimeException e){
			
		}
	}
	
	public UITile[][] updateTiles(UITile uitile[][]) {
		Game currentGame = TileOApplication.getCurrentGame();
		List<Player> playerlist = currentGame.getPlayers();
		for(Player p: playerlist){
			if(p.hasCurrentTile()){
				uitile[p.getCurrentTile().getX()][p.getCurrentTile().getY()].setCurrentPlayer(p.getNumber()+1);
			}
		}
		for(Tile tile: currentGame.getTiles()){
			if(tile instanceof NormalTile){
				uitile[tile.getX()][tile.getY()].setVisited(tile.getHasBeenVisited());
				uitile[tile.getX()][tile.getY()].setType(DesignModeResources.Type.Normal);
			}
			else if(tile instanceof WinTile){
				uitile[tile.getX()][tile.getY()].setVisited(tile.getHasBeenVisited());
				uitile[tile.getX()][tile.getY()].setType(DesignModeResources.Type.Win);
			}
			else if(tile instanceof ActionTile){
				uitile[tile.getX()][tile.getY()].setVisited(tile.getHasBeenVisited());
				uitile[tile.getX()][tile.getY()].setType(DesignModeResources.Type.Action);
				uitile[tile.getX()][tile.getY()].setCooldown(((ActionTile)tile).getTurnsUntilActive());
			}
		}
		return uitile;
	}

	public int[] updateCards(){
		Game currentGame = TileOApplication.getCurrentGame();
		int cardlist[] = new int[5];
		for(int i = 0; i <5; i++) cardlist[i]=0;
		for(ActionCard a: currentGame.getDeck().getCards()){
			cardlist[a.type()]++;
		}
		return cardlist;
	}
	
	public List<UIConnection> updateConnection() {
		List<UIConnection> uiconnect = new ArrayList<UIConnection>();
		Game currentGame = TileOApplication.getCurrentGame();
		for(Connection c: currentGame.getConnections()){
			if(c.getTile(0).getX() == c.getTile(1).getX()){
				uiconnect.add(new UIConnection(c.getTile(0).getX(), Math.min(c.getTile(0).getY(),c.getTile(1).getY()), false));
			}
			else if(c.getTile(0).getY() == c.getTile(1).getY()){
				uiconnect.add(new UIConnection(Math.min(c.getTile(0).getX(),c.getTile(1).getX()), c.getTile(0).getY(), true));
			}
		}
		return uiconnect;
	}

	public void Start() throws InvalidInputException {
		startNewGame(TileOApplication.getCurrentGame());
	}

	public Mode getMode() {
		return TileOApplication.getCurrentGame().getMode();
	}
	
	public void NewGame(TileOGamePage parent){
		//parent is needed for filechooser.
		Game game = TileOApplication.loadDesign(parent);
		try {
			startNewGame(game);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
	}

	public int getcurrentPlayer(){
		Game game = TileOApplication.getCurrentGame();
		return game.getCurrentPlayer().getNumber() + 1;
	}

	public int getcurrentSpareConnection() {
		Game game = TileOApplication.getCurrentGame();
		return game.getCurrentConnectionPieces();
	}
	
	//new//
	public boolean isInGameMode(){
		Game game = TileOApplication.getCurrentGame();
		if(game.getMode().equals(Game.Mode.GAME) || game.getMode().equals(Game.Mode.GAME_ROLLDIEACTIONCARD))
			return true;
		return false;	
	}
	
	public boolean isInWonMode(){
		Game game = TileOApplication.getCurrentGame();
		if(game.getMode().equals(Game.Mode.GAME_WON))
			return true;
		return false;	
	}
	
	public boolean isInNotInGameOrWonMode(){
		Game game = TileOApplication.getCurrentGame();
		if(!game.getMode().equals(Game.Mode.GAME) && !game.getMode().equals(Game.Mode.GAME_WON))
			return true;
		return false;	
	}
	private boolean isInMoveMode() {
		Game game = TileOApplication.getCurrentGame();
		if(game.getMode().equals(Game.Mode.GAME_TAKETURN))
			return true;
		return false;	
	}
	public boolean isWinTile(Tile tile){
		if (tile instanceof WinTile)
			return true;
		return false;
	}
	
	public boolean isNormalTile(Tile tile){
		if(tile instanceof NormalTile)
			return true;
		return false;
	}
	
	public boolean isActionTile(Tile tile){
		if(tile instanceof ActionTile)
			return true;
		return false;
	}
	
	public boolean isConnectTilesActionCard(){
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		if(deck.getCurrentCard() instanceof ConnectTilesActionCard)
			return true;
		return false;
		}
	
	public boolean isSwapPositionActionCard(){
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		if(deck.getCurrentCard() instanceof SwapPositionActionCard)
			return true;
		return false;
		}
	
	public boolean isRollDieActionCard(){
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		if(deck.getCurrentCard() instanceof RollDieActionCard)
			return true;
		return false;
	}
	
	public boolean isRemoveConnectionActionCard(){
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		if(deck.getCurrentCard() instanceof RemoveConnectionActionCard)
			return true;
		return false;
	}
	
	public boolean isTeleportAndNormalTile(Tile tile){
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		if(deck.getCurrentCard() instanceof TeleportActionCard && tile instanceof NormalTile)
			return true;
		return false;
	}
	
	public boolean isTeleportAndWinTile(Tile tile){
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		if(deck.getCurrentCard() instanceof TeleportActionCard && tile instanceof WinTile)
			return true;
		return false;
	}
	
	public boolean isTeleportAndActionTile(Tile tile){
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		if(deck.getCurrentCard() instanceof TeleportActionCard && tile instanceof ActionTile)
			return true;
		return false;
	}
	
	//Added by Hieu Chau Nguyen. The other 3 teleport method above is considered useless.
	public boolean isTeleportActionCard(){
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		if(deck.getCurrentCard() instanceof TeleportActionCard)
			return true;
		return false;
	}
		
	public boolean isLoseTurnActionCard(){
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		if(deck.getCurrentCard() instanceof LoseTurnActionCard)
			return true;
		return false;
	}
	
	public void startGame_mode() {
		playMode = PlayMode.Roll;
		
	}
	public void Congratulations(TileOGamePage parent) {
		Game currentGame = TileOApplication.getCurrentGame();
		JOptionPane.showMessageDialog(parent, "Player " + currentGame.getCurrentPlayer().getNumber() + " has won. (Please do not deduct marks for being scared, I'm extremely bored by now).");
		if(JOptionPane.showConfirmDialog(parent,"Do you want to save your achievement?","Saving Games",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
			TileOApplication.saveGame(currentGame, parent);
		}
		TileOApplication.MainMenu();
	}
	public void DrawCard() {
		Game currentGame = TileOApplication.getCurrentGame();
		ActionTile currentTile = ((ActionTile) currentGame.getCurrentPlayer().getCurrentTile());
		currentTile.deactivate();
		if(isRollDieActionCard()){
			currentGame.setMode(Mode.GAME_ROLLDIEACTIONCARD);
			BoPlayRollDieActionCard();
		}
		else if(isConnectTilesActionCard()){
			currentGame.setMode(Mode.GAME_CONNECTTILESACTIONCARD);
			if(OutOfConnection()) setPlayMode(PlayMode.Roll);
		}
		else if(isRemoveConnectionActionCard()){
			currentGame.setMode(Mode.GAME_REMOVECONNECTIONACTIONCARD);
			
		}
		else if(isTeleportActionCard()){
			currentGame.setMode(Mode.GAME_TELEPORTACTIONCARD);
			
		}
		else if(isLoseTurnActionCard()){
			currentGame.setMode(Mode.GAME_LOSETURNACTIONCARD);
			currentGame.getCurrentPlayer().loseTurns(currentGame.numberOfPlayers());
		}
		
		else if(isSwapPositionActionCard()){
			currentGame.setMode(Mode.GAME_SWAPPOSITIONACTIONCARD);
		}
		
	}
	
	//Borrowed from Design Controller.
	public void putConnection(int tile1x, int tile1y, int tile2x, int tile2y) throws InvalidInputException {
		
		Game game = TileOApplication.getCurrentGame();
		
		//how to set mode???//
		//check if the tiles are adjacent, if not, directly end the method//
		if(Math.abs(tile1y - tile2y) == standardDistance && tile1x == tile2x);
		else if(Math.abs(tile1x - tile2x) == standardDistance && tile1y == tile2y);
		else{
			throw new InvalidInputException("Connection can not be created between non-adjacent tiles"); 
		}
		try {
			int index1 =0, index2 =0;
			boolean foundTile1 = false;
			boolean foundTile2 = false;
			List<Tile> tiles = game.getTiles();
			for(Tile t: tiles){
				if(t.getX() == tile1x && t.getY() == tile1y){
					foundTile1 = true;
					index1 = game.indexOfTile(t);
				}
				if(t.getX() == tile2x && t.getY() == tile2y){
					foundTile2 = true;
					index2 = game.indexOfTile(t);
				}
			}
			if(foundTile1 && foundTile2){
				
				for(Connection c:game.getConnections()){
					if(c.getTiles().contains(tiles.get(index1)) && c.getTiles().contains(tiles.get(index2))){
						throw new InvalidInputException("Connection already exist.");
					}
				}
				Connection connection = new Connection(game);
				connection.addTile(tiles.get(index1));
				connection.addTile(tiles.get(index2));
				game.setCurrentConnectionPieces(game.getCurrentConnectionPieces()-1);
				game.determineNextPlayer();
				BoPlayConnectTilesActionCard();
			}
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
			
	}
	
	public Player getPlayer(int tileX, int tileY) throws InvalidInputException {
         
		Game game = TileOApplication.getCurrentGame();
		Player playerFound = game.getCurrentPlayer(); 
		//how to set mode???//
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
	//Borrowed from Design Controller
	public void deleteConnection(int x1, int y1, int x2, int y2){
		Game theGame = TileOApplication.getCurrentGame();
		List<Connection> clist = theGame.getConnections();
		boolean tileFound1 = false;
		boolean tileFound2 = false;
		for(Connection c: clist){
			if((c.getTile(0).getX()==x1 && c.getTile(0).getY()==y1)||(c.getTile(0).getX()==x2 && c.getTile(0).getY()==y2)) tileFound1 = true;
			if((c.getTile(1).getX()==x1 && c.getTile(1).getY()==y1)||(c.getTile(1).getX()==x2 && c.getTile(1).getY()==y2)) tileFound2 = true;
			if(tileFound1 && tileFound2){
				c.delete();
				theGame.setCurrentConnectionPieces(theGame.getCurrentConnectionPieces()+1);
				theGame.determineNextPlayer();
				theGame.setMode(Mode.GAME);
				BoPlayRemoveConnectionActionCard();
				return;
			}
			else{
				tileFound1 = false; tileFound2 = false;
			}
		}
	}
	
	public boolean OutOfConnection(){
		Game currentGame = TileOApplication.getCurrentGame();
		return currentGame.getCurrentConnectionPieces() == 0;
	}
	public void teleportLocation(int x, int y) {
		Game currentGame = TileOApplication.getCurrentGame();
		boolean TileFound = false;
		Tile teleportTile= null;
		for(Tile t:currentGame.getTiles()){
			if(t.getX() == x && t.getY() == y){
				TileFound = true;
				teleportTile = t;
				break;
			}
		}
		if(TileFound){
			BoPlayTeleportActionCard(teleportTile);
		}
		
	}
	
	}
