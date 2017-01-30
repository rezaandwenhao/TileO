/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;

// line 7 "../../../../../GameEngine.ump"
public class Session
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final int NumberOfConnections = 32;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Session Attributes
  private int totalTurns;

  //Session Associations
  private List<ActionDeck> actionDeck;
  private List<Player> player;
  private Board board;
  private GameEngine gameEngine;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Session(int aTotalTurns, Board aBoard, GameEngine aGameEngine, Player... allPlayer)
  {
    totalTurns = aTotalTurns;
    actionDeck = new ArrayList<ActionDeck>();
    player = new ArrayList<Player>();
    boolean didAddPlayer = setPlayer(allPlayer);
    if (!didAddPlayer)
    {
      throw new RuntimeException("Unable to create Session, must have 2 to 4 player");
    }
    if (aBoard == null || aBoard.getSession() != null)
    {
      throw new RuntimeException("Unable to create Session due to aBoard");
    }
    board = aBoard;
    if (aGameEngine == null || aGameEngine.getSession() != null)
    {
      throw new RuntimeException("Unable to create Session due to aGameEngine");
    }
    gameEngine = aGameEngine;
  }

  public Session(int aTotalTurns, Player... allPlayer)
  {
    totalTurns = aTotalTurns;
    actionDeck = new ArrayList<ActionDeck>();
    player = new ArrayList<Player>();
    boolean didAddPlayer = setPlayer(allPlayer);
    if (!didAddPlayer)
    {
      throw new RuntimeException("Unable to create Session, must have 2 to 4 player");
    }
    board = new Board(this);
    gameEngine = new GameEngine(this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTotalTurns(int aTotalTurns)
  {
    boolean wasSet = false;
    totalTurns = aTotalTurns;
    wasSet = true;
    return wasSet;
  }

  public int getTotalTurns()
  {
    return totalTurns;
  }

  public ActionDeck getActionDeck(int index)
  {
    ActionDeck aActionDeck = actionDeck.get(index);
    return aActionDeck;
  }

  public List<ActionDeck> getActionDeck()
  {
    List<ActionDeck> newActionDeck = Collections.unmodifiableList(actionDeck);
    return newActionDeck;
  }

  public int numberOfActionDeck()
  {
    int number = actionDeck.size();
    return number;
  }

  public boolean hasActionDeck()
  {
    boolean has = actionDeck.size() > 0;
    return has;
  }

  public int indexOfActionDeck(ActionDeck aActionDeck)
  {
    int index = actionDeck.indexOf(aActionDeck);
    return index;
  }

  public Player getPlayer(int index)
  {
    Player aPlayer = player.get(index);
    return aPlayer;
  }

  public List<Player> getPlayer()
  {
    List<Player> newPlayer = Collections.unmodifiableList(player);
    return newPlayer;
  }

  public int numberOfPlayer()
  {
    int number = player.size();
    return number;
  }

  public boolean hasPlayer()
  {
    boolean has = player.size() > 0;
    return has;
  }

  public int indexOfPlayer(Player aPlayer)
  {
    int index = player.indexOf(aPlayer);
    return index;
  }

  public Board getBoard()
  {
    return board;
  }

  public GameEngine getGameEngine()
  {
    return gameEngine;
  }

  public static int minimumNumberOfActionDeck()
  {
    return 0;
  }

  public ActionDeck addActionDeck()
  {
    return new ActionDeck(this);
  }

  public boolean addActionDeck(ActionDeck aActionDeck)
  {
    boolean wasAdded = false;
    if (actionDeck.contains(aActionDeck)) { return false; }
    Session existingSession = aActionDeck.getSession();
    boolean isNewSession = existingSession != null && !this.equals(existingSession);
    if (isNewSession)
    {
      aActionDeck.setSession(this);
    }
    else
    {
      actionDeck.add(aActionDeck);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeActionDeck(ActionDeck aActionDeck)
  {
    boolean wasRemoved = false;
    //Unable to remove aActionDeck, as it must always have a session
    if (!this.equals(aActionDeck.getSession()))
    {
      actionDeck.remove(aActionDeck);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addActionDeckAt(ActionDeck aActionDeck, int index)
  {  
    boolean wasAdded = false;
    if(addActionDeck(aActionDeck))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfActionDeck()) { index = numberOfActionDeck() - 1; }
      actionDeck.remove(aActionDeck);
      actionDeck.add(index, aActionDeck);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveActionDeckAt(ActionDeck aActionDeck, int index)
  {
    boolean wasAdded = false;
    if(actionDeck.contains(aActionDeck))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfActionDeck()) { index = numberOfActionDeck() - 1; }
      actionDeck.remove(aActionDeck);
      actionDeck.add(index, aActionDeck);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addActionDeckAt(aActionDeck, index);
    }
    return wasAdded;
  }

  public boolean isNumberOfPlayerValid()
  {
    boolean isValid = numberOfPlayer() >= minimumNumberOfPlayer() && numberOfPlayer() <= maximumNumberOfPlayer();
    return isValid;
  }

  public static int minimumNumberOfPlayer()
  {
    return 2;
  }

  public static int maximumNumberOfPlayer()
  {
    return 4;
  }

  public boolean addPlayer(Player aPlayer)
  {
    boolean wasAdded = false;
    if (player.contains(aPlayer)) { return false; }
    if (numberOfPlayer() >= maximumNumberOfPlayer())
    {
      return wasAdded;
    }

    player.add(aPlayer);
    if (aPlayer.indexOfSession(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPlayer.addSession(this);
      if (!wasAdded)
      {
        player.remove(aPlayer);
      }
    }
    return wasAdded;
  }

  public boolean removePlayer(Player aPlayer)
  {
    boolean wasRemoved = false;
    if (!player.contains(aPlayer))
    {
      return wasRemoved;
    }

    if (numberOfPlayer() <= minimumNumberOfPlayer())
    {
      return wasRemoved;
    }

    int oldIndex = player.indexOf(aPlayer);
    player.remove(oldIndex);
    if (aPlayer.indexOfSession(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPlayer.removeSession(this);
      if (!wasRemoved)
      {
        player.add(oldIndex,aPlayer);
      }
    }
    return wasRemoved;
  }

  public boolean setPlayer(Player... newPlayer)
  {
    boolean wasSet = false;
    ArrayList<Player> verifiedPlayer = new ArrayList<Player>();
    for (Player aPlayer : newPlayer)
    {
      if (verifiedPlayer.contains(aPlayer))
      {
        continue;
      }
      verifiedPlayer.add(aPlayer);
    }

    if (verifiedPlayer.size() != newPlayer.length || verifiedPlayer.size() < minimumNumberOfPlayer() || verifiedPlayer.size() > maximumNumberOfPlayer())
    {
      return wasSet;
    }

    ArrayList<Player> oldPlayer = new ArrayList<Player>(player);
    player.clear();
    for (Player aNewPlayer : verifiedPlayer)
    {
      player.add(aNewPlayer);
      if (oldPlayer.contains(aNewPlayer))
      {
        oldPlayer.remove(aNewPlayer);
      }
      else
      {
        aNewPlayer.addSession(this);
      }
    }

    for (Player anOldPlayer : oldPlayer)
    {
      anOldPlayer.removeSession(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean addPlayerAt(Player aPlayer, int index)
  {  
    boolean wasAdded = false;
    if(addPlayer(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayer()) { index = numberOfPlayer() - 1; }
      player.remove(aPlayer);
      player.add(index, aPlayer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlayerAt(Player aPlayer, int index)
  {
    boolean wasAdded = false;
    if(player.contains(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayer()) { index = numberOfPlayer() - 1; }
      player.remove(aPlayer);
      player.add(index, aPlayer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlayerAt(aPlayer, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (actionDeck.size() > 0)
    {
      ActionDeck aActionDeck = actionDeck.get(actionDeck.size() - 1);
      aActionDeck.delete();
      actionDeck.remove(aActionDeck);
    }
    
    while (player.size() > 0)
    {
      Player aPlayer = player.get(player.size() - 1);
      aPlayer.delete();
      player.remove(aPlayer);
    }
    
    Board existingBoard = board;
    board = null;
    if (existingBoard != null)
    {
      existingBoard.delete();
    }
    GameEngine existingGameEngine = gameEngine;
    gameEngine = null;
    if (existingGameEngine != null)
    {
      existingGameEngine.delete();
    }
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "totalTurns" + ":" + getTotalTurns()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "board = "+(getBoard()!=null?Integer.toHexString(System.identityHashCode(getBoard())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "gameEngine = "+(getGameEngine()!=null?Integer.toHexString(System.identityHashCode(getGameEngine())):"null")
     + outputString;
  }
}