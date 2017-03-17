/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

import ca.mcgill.ecse223.tileo.model.*;
import ca.mcgill.ecse223.tileo.model.ActionTile.ActionTileStatus;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ca.mcgill.ecse223.tileo.application.*;
import ca.mcgill.ecse223.tileo.ui.*;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.Game.Mode;
import java.util.*;

// line 3 "PlayControllerSM.ump"
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
          LoadGame(parent);
          setPlayMode(PlayMode.Roll);
          wasEventProcessed = true;
          break;
        }
        if (isInWonMode())
        {
        // line 12 "PlayControllerSM.ump"
          LoadGame(parent);
          setPlayMode(PlayMode.Won);
          wasEventProcessed = true;
          break;
        }
        if (isInNotInGameOrWonMode())
        {
        // line 15 "PlayControllerSM.ump"
          LoadGame(parent);
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
        possibleMoves = getFinalTiles(rollDie());
        setPlayMode(PlayMode.Move);
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
    switch (aPlayMode)
    {
      case Move:
        if (isNormalTile(tile))
        {
        // line 25 "PlayControllerSM.ump"
          try {
			land(tile);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          setPlayMode(PlayMode.Roll);
          wasEventProcessed = true;
          break;
        }
        if (isWinTile(tile))
        {
        // line 28 "PlayControllerSM.ump"
          try {
			land(tile);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          setPlayMode(PlayMode.Won);
          wasEventProcessed = true;
          break;
        }
        if (isActionTile(tile))
        {
        // line 31 "PlayControllerSM.ump"
          try {
			land(tile);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
          possibleMoves = playRollDieAgainActionCard();
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

  public boolean BoPlayConnectTilesActionCard(Tile tile1,Tile tile2)
  {
    boolean wasEventProcessed = false;
    
    PlayMode aPlayMode = playMode;
    switch (aPlayMode)
    {
      case ActionCard:
        if (isConnectTilesActionCard())
        {
        // line 39 "PlayControllerSM.ump"
          try {
			playConnectTilesActionCard(tile1, tile2);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

  public boolean BoPlayRemoveConnectionActionCard(Connection c)
  {
    boolean wasEventProcessed = false;
    
    PlayMode aPlayMode = playMode;
    switch (aPlayMode)
    {
      case ActionCard:
        if (isRemoveConnectionActionCard())
        {
        // line 42 "PlayControllerSM.ump"
          playRemoveConnectionActionCard(c);
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
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean BopPlayTeleportActionCard(Tile tile)
  {
    boolean wasEventProcessed = false;
    
    PlayMode aPlayMode = playMode;
    switch (aPlayMode)
    {
      case ActionCard:
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
          playLoseTurnActionCard();
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

  // line 64 "PlayControllerSM.ump"
   public void startGame(Game selectedGame) throws InvalidInputException{
    // Nguyen Hieu Chau
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
		
		TileOApplication.GamePage();
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
				List<Tile> finalTiles = currentPlayer.getPossibleMoves(currentTile, null, rollNumber);

				game.setMode(Mode.GAME_TAKETURN);

				return finalTiles;
  }

  // line 125 "PlayControllerSM.ump"
   public void resetFinalTiles(){
    //Implemented by Bijan Sadeghi
				Game game = TileOApplication.getCurrentGame();

				// the current player
				Player currentPlayer = game.getCurrentPlayer();

				// resets the finalTiles attribute in Player for the next time he plays
				currentPlayer.resetFinalTiles();
  }

  // line 136 "PlayControllerSM.ump"
   public int[][] getHighlight(){
    List<Tile> getTile = getFinalTiles(rollNumber);
				int highlight[][] = new int[getTile.size()][2];
				int index = 0;
				for (Tile t : getTile) {
					highlight[index][0] = t.getX();
					highlight[index][1] = t.getY();
					index++;
				}
				return highlight;
  }

  // line 147 "PlayControllerSM.ump"
   public void land(Tile tile) throws InvalidInputException{
    // Implemented by Nguyen Hieu Chau and Borui Tao, including taking the first card
				Game currentGame = TileOApplication.getCurrentGame();
				
				//check if the tile exist in the game.
				if(!currentGame.getTiles().contains(tile)) throw new InvalidInputException("Tile not existed.");
				tile.land();
  }

  // line 155 "PlayControllerSM.ump"
   public void landOnTile(int x, int y) throws InvalidInputException{
    // HIEU CHAU NGUYEN, Bijan Sadeghi and Borui Tao
		Game currentGame = TileOApplication.getCurrentGame();
		Player currentPlayer = currentGame.getCurrentPlayer();
		//boolean wasSet = false;
		for(Tile tile : currentGame.getTiles()){
			if(tile.getX()==x && tile.getY()==y){
				currentPlayer.setCurrentTile(tile);
				//wasSet=true;
			}
		}
		//if(wasSet==false) throw new InvalidInputException("Tile not existed.");
  }

  // line 167 "PlayControllerSM.ump"
   public List<Tile> playRollDieAgainActionCard(){
    // Implemented by Jiawei Ni
		 return getFinalTiles(rollDie());
		  /*Game theGame = TileOApplication.getCurrentGame();
		 for(Player p : theGame.getPlayers()){
			 if( p!= theGame.getCurrentPlayer()){
				 p.setTurnsUntilActive(p.getTurnsUntilActive()+1);
			 }
	  }*/
  }

  // line 177 "PlayControllerSM.ump"
   public void playRemoveConnectionActionCard(Connection connection){
    // PLAY 7: IMPLEMENTED BY Bijan Sadeghi [made changes to Deck, ActionCard, RemoveConnectionActionCard]
		Game game = TileOApplication.getCurrentGame();

		//VALIDATION CHECK 1: only proceed if connection is one of the connections of game
		if(game.indexOfConnection(connection)!=-1){
			Deck deck = game.getDeck();
			ActionCard currentCard = deck.getCurrentCard();

			//VALIDATION CHECK 2: Check if currentCard is a RemoveConnectionActionCard
			if(deck.getCurrentCard() instanceof RemoveConnectionActionCard){
				((RemoveConnectionActionCard) currentCard).play(connection);//remove the connection indicated
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

  // line 213 "PlayControllerSM.ump"
   public void playConnectTilesActionCard(Tile tile1, Tile tile2) throws InvalidInputException{
    //Implemented by Borui Tao
		
		Game currentGame = TileOApplication.getCurrentGame();
		String error = "";
		
		// Validation check: if tile1 and tile2 exist in the current game
		if (currentGame.indexOfTile(tile1) < 0 || currentGame.indexOfTile(tile2) <0){
			error = error + "The tile1 or tile2 do not exist in the current game";
		}
		
		// Validation check: if tile1 and tile2 are adjacent
		if(! (Math.abs(tile1.getY() - tile2.getY()) == standardDistance && tile1.getX() == tile2.getX()) || 
		! (Math.abs(tile1.getX() - tile2.getX()) == standardDistance && tile1.getY() == tile2.getY())) {
			error = error +"connection can not be created between non-adjacent tiles"; 
		}
		
		// validation check: if the connectionPieces are smaller or equal to zero
		if (currentGame.numberOfConnections() <= 0){
			error = error +"The currentConnectionPieces is equal or smaller than zero";
		}
		
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
	
		Deck deck = currentGame.getDeck();
		ConnectTilesActionCard currentCard = (ConnectTilesActionCard) deck.getCurrentCard();
		currentCard.play(tile1, tile2);
		
		Player currentPlayer = currentGame.getCurrentPlayer();
		
        // check: if the currentPlayer is the last player
		if (currentGame.indexOfPlayer(currentPlayer) != currentGame.numberOfPlayers()-1) {
		currentGame.setCurrentPlayer(currentGame.getPlayer(currentGame.indexOfPlayer(currentPlayer)+1));
		}
		else {
			currentGame.setCurrentPlayer(currentGame.getPlayer(0));
		}
		
		// check if the currentCard is the last card
		if (deck.indexOfCard(currentCard) == deck.numberOfCards()-1) {
		deck.setCurrentCard(deck.getCard(deck.indexOfCard(currentCard)+1));
		}
		
		else {
			deck.setCurrentCard(deck.getCard(0));
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

  // line 271 "PlayControllerSM.ump"
   public void playTeleportActionCard(Tile tile) throws InvalidInputException{
    // Ebou Jobe
		//check if tile needs to be tile of currentGame
		Game currentGame= TileOApplication.getCurrentGame();
		if(isTileInCurrentGame(currentGame,tile)){	
		}else{
			throw new InvalidInputException("Tile need to be a tile of current Game");
		}
		Deck deck= currentGame.getDeck();
		
		ActionCard playedCard = deck.getCurrentCard();
		if(deck.getCurrentCard() instanceof TeleportActionCard){
			((TeleportActionCard) playedCard).play(tile);//remove the connection indicated
		}
		
		if(deck.setCurrentCard(deck.getCard(deck.indexOfCard(playedCard)+1))){
			int numberOfCards= deck.numberOfCards();
			   if(numberOfCards<=1){
			//	   Collections.shuffle(deck.getCards());
			   }
		}else {
			throw new InvalidInputException("Unable to set current card");
		}
  }

  // line 295 "PlayControllerSM.ump"
   public static  boolean isTileInCurrentGame(Game game, Tile tile){
    //helper method for playTeleportActionCard
	    	
			int index=game.indexOfTile(tile);
			for(int i=0; i< game.getTiles().size();i++){
			//	if(index== indexOfTile(game.getTiles().get(i))){
			//d		return true;
			//	}
			}
			return false;
  }

  // line 306 "PlayControllerSM.ump"
   public void SaveandExitGame(TileOGamePage parent){
    // Implemented by Nguyen Hieu Chau
		//parent is needed for filechooser.
		
		TileOApplication.saveGame(TileOApplication.getCurrentGame(),parent);
		TileOApplication.GamePage();//tell the application to boot the title page
  }

  // line 313 "PlayControllerSM.ump"
   public void LoadGame(TileOGamePage parent){
    // Implemented by Nguyen Hieu Chau
		//parent is needed for filechooser.
		
		TileOApplication.setCurrentGame(TileOApplication.loadGame(parent));
		TileOApplication.GamePage();//tell the application to boot the game page.
  }


  /**
   * some helper methods:
   */
  // line 323 "PlayControllerSM.ump"
   public void loadDesign(TileOGamePage parent){
    // TODO Auto-generated method stub
  }

  // line 354 "PlayControllerSM.ump"
   public int[] updateCards(){
    Game currentGame = TileOApplication.getCurrentGame();
		int cardlist[] = new int[5];
		for(int i = 0; i <5; i++) cardlist[i]=0;
		for(ActionCard a: currentGame.getDeck().getCards()){
			cardlist[a.type()]++;
		}
		return cardlist;
  }

  // line 364 "PlayControllerSM.ump"
   public List<UIConnection> updateConnection(){
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

  // line 378 "PlayControllerSM.ump"
   public void Start() throws InvalidInputException{
    startGame(TileOApplication.getCurrentGame());
  }

  // line 382 "PlayControllerSM.ump"
   public Mode getMode(){
    return TileOApplication.getCurrentGame().getMode();
  }

  // line 386 "PlayControllerSM.ump"
   public void NewGame(TileOGamePage parent){
    //parent is needed for filechooser.
		Game game = TileOApplication.loadDesign(parent);
		try {
			startGame(game);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
  }

  // line 396 "PlayControllerSM.ump"
   public int getcurrentPlayer(){
    Game game = TileOApplication.getCurrentGame();
		return game.getCurrentPlayer().getNumber() + 1;
  }

  // line 401 "PlayControllerSM.ump"
   public int getcurrentSpareConnection(){
    Game game = TileOApplication.getCurrentGame();
		return game.getCurrentConnectionPieces();
  }

  // line 406 "PlayControllerSM.ump"
   public boolean isInGameMode(){
    Game game = TileOApplication.getCurrentGame();
		if(game.getMode().equals(Game.Mode.GAME))
			return true;
		return false;
  }

  // line 413 "PlayControllerSM.ump"
   public boolean isInWonMode(){
    Game game = TileOApplication.getCurrentGame();
		if(game.getMode().equals(Game.Mode.GAME_WON))
			return true;
		return false;
  }

  // line 420 "PlayControllerSM.ump"
   public boolean isInNotInGameOrWonMode(){
    Game game = TileOApplication.getCurrentGame();
		if(!game.getMode().equals(Game.Mode.GAME) && !game.getMode().equals(Game.Mode.GAME_WON))
			return true;
		return false;
  }

  // line 427 "PlayControllerSM.ump"
   public boolean isWinTile(Tile tile){
    if (tile instanceof NormalTile)
			return true;
		return false;
  }

  // line 433 "PlayControllerSM.ump"
   public boolean isNormalTile(Tile tile){
    if(tile instanceof NormalTile)
			return true;
		return false;
  }

  // line 439 "PlayControllerSM.ump"
   public boolean isActionTile(Tile tile){
    if(tile instanceof ActionTile)
			return true;
		return false;
  }

  // line 445 "PlayControllerSM.ump"
   public boolean isConnectTilesActionCard(){
    Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		if(deck.getCurrentCard() instanceof ConnectTilesActionCard)
			return true;
		return false;
  }

  // line 453 "PlayControllerSM.ump"
   public boolean isRollDieActionCard(){
    Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		if(deck.getCurrentCard() instanceof RollDieActionCard)
			return true;
		return false;
  }

  // line 461 "PlayControllerSM.ump"
   public boolean isRemoveConnectionActionCard(){
    Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		if(deck.getCurrentCard() instanceof RemoveConnectionActionCard)
			return true;
		return false;
  }

  // line 469 "PlayControllerSM.ump"
   public boolean isTeleportAndNormalTile(Tile tile){
    Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		if(deck.getCurrentCard() instanceof TeleportActionCard && tile instanceof NormalTile)
			return true;
		return false;
  }

  // line 477 "PlayControllerSM.ump"
   public boolean isTeleportAndWinTile(Tile tile){
    Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		if(deck.getCurrentCard() instanceof TeleportActionCard && tile instanceof WinTile)
			return true;
		return false;
  }

  // line 485 "PlayControllerSM.ump"
   public boolean isTeleportAndActionTile(Tile tile){
    Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		if(deck.getCurrentCard() instanceof TeleportActionCard && tile instanceof ActionTile)
			return true;
		return false;
  }

  // line 493 "PlayControllerSM.ump"
   public boolean isLoseTurnActionCard(){
    Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		if(deck.getCurrentCard() instanceof LoseTurnActionCard)
			return true;
		return false;
  }

  // line 502 "PlayControllerSM.ump"
   public void playLoseTurnActionCard(){
    Game currentGame = TileOApplication.getCurrentGame();
		Player currentPlayer = currentGame.getCurrentPlayer();
		Deck deck = currentGame.getDeck();
		LoseTurnActionCard currentCard = (LoseTurnActionCard) deck.getCurrentCard();
		currentCard.play();
		
		
		Player nextPlayer = currentGame.determineNextPlayer();
		currentGame.setCurrentPlayer(nextPlayer);
		
		
		ActionTile currentTile = (ActionTile) currentPlayer.getCurrentTile(); 
	    currentTile.setActionTileStatus(ActionTileStatus.Inactive);
	
	    
		deck.setCurrentCard(deck.getCard(deck.indexOfCard(deck.getCurrentCard())+1));
		currentGame.setMode(Mode.GAME);
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "rollNumber" + ":" + getRollNumber()+ "," +
            "standardDistance" + ":" + getStandardDistance()+ "]"
     + outputString;
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 327 PlayControllerSM.ump
  public UITile[][] updateTiles (UITile uitile[][]) 
  {
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
				uitile[tile.getX()][tile.getY()].setCooldown(((ActionTile)tile).getInactivityPeriod());
			}
		}
		return uitile;
  }

  
}