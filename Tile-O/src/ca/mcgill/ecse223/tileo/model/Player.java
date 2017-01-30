/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;

// line 15 "../../../../../GameEngine.ump"
public class Player
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private boolean skipTurn;

  //Player Associations
  private Tile tile;
  private List<Session> session;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(boolean aSkipTurn, Tile aTile)
  {
    skipTurn = aSkipTurn;
    boolean didAddTile = setTile(aTile);
    if (!didAddTile)
    {
      throw new RuntimeException("Unable to create player due to tile");
    }
    session = new ArrayList<Session>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSkipTurn(boolean aSkipTurn)
  {
    boolean wasSet = false;
    skipTurn = aSkipTurn;
    wasSet = true;
    return wasSet;
  }

  public boolean getSkipTurn()
  {
    return skipTurn;
  }

  public Tile getTile()
  {
    return tile;
  }

  public Session getSession(int index)
  {
    Session aSession = session.get(index);
    return aSession;
  }

  public List<Session> getSession()
  {
    List<Session> newSession = Collections.unmodifiableList(session);
    return newSession;
  }

  public int numberOfSession()
  {
    int number = session.size();
    return number;
  }

  public boolean hasSession()
  {
    boolean has = session.size() > 0;
    return has;
  }

  public int indexOfSession(Session aSession)
  {
    int index = session.indexOf(aSession);
    return index;
  }

  public boolean setTile(Tile aTile)
  {
    boolean wasSet = false;
    //Must provide tile to player
    if (aTile == null)
    {
      return wasSet;
    }

    //tile already at maximum (4)
    if (aTile.numberOfPlayers() >= Tile.maximumNumberOfPlayers())
    {
      return wasSet;
    }
    
    Tile existingTile = tile;
    tile = aTile;
    if (existingTile != null && !existingTile.equals(aTile))
    {
      boolean didRemove = existingTile.removePlayer(this);
      if (!didRemove)
      {
        tile = existingTile;
        return wasSet;
      }
    }
    tile.addPlayer(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfSession()
  {
    return 0;
  }

  public boolean addSession(Session aSession)
  {
    boolean wasAdded = false;
    if (session.contains(aSession)) { return false; }
    session.add(aSession);
    if (aSession.indexOfPlayer(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aSession.addPlayer(this);
      if (!wasAdded)
      {
        session.remove(aSession);
      }
    }
    return wasAdded;
  }

  public boolean removeSession(Session aSession)
  {
    boolean wasRemoved = false;
    if (!session.contains(aSession))
    {
      return wasRemoved;
    }

    int oldIndex = session.indexOf(aSession);
    session.remove(oldIndex);
    if (aSession.indexOfPlayer(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aSession.removePlayer(this);
      if (!wasRemoved)
      {
        session.add(oldIndex,aSession);
      }
    }
    return wasRemoved;
  }

  public boolean addSessionAt(Session aSession, int index)
  {  
    boolean wasAdded = false;
    if(addSession(aSession))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSession()) { index = numberOfSession() - 1; }
      session.remove(aSession);
      session.add(index, aSession);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSessionAt(Session aSession, int index)
  {
    boolean wasAdded = false;
    if(session.contains(aSession))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSession()) { index = numberOfSession() - 1; }
      session.remove(aSession);
      session.add(index, aSession);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSessionAt(aSession, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Tile placeholderTile = tile;
    this.tile = null;
    placeholderTile.removePlayer(this);
    ArrayList<Session> copyOfSession = new ArrayList<Session>(session);
    session.clear();
    for(Session aSession : copyOfSession)
    {
      if (aSession.numberOfPlayer() <= Session.minimumNumberOfPlayer())
      {
        aSession.delete();
      }
      else
      {
        aSession.removePlayer(this);
      }
    }
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "skipTurn" + ":" + getSkipTurn()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "tile = "+(getTile()!=null?Integer.toHexString(System.identityHashCode(getTile())):"null")
     + outputString;
  }
}