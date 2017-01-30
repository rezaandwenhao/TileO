/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;

// line 20 "../../../../../GameEngine.ump"
public class Board
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Board Associations
  private List<Tile> tile;
  private Session session;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Board(Session aSession)
  {
    tile = new ArrayList<Tile>();
    if (aSession == null || aSession.getBoard() != null)
    {
      throw new RuntimeException("Unable to create Board due to aSession");
    }
    session = aSession;
  }

  public Board(int aTotalTurnsForSession, GameEngine aGameEngineForSession, Player... allPlayerForSession)
  {
    tile = new ArrayList<Tile>();
    session = new Session(aTotalTurnsForSession, this, aGameEngineForSession, allPlayerForSession);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Tile getTile(int index)
  {
    Tile aTile = tile.get(index);
    return aTile;
  }

  public List<Tile> getTile()
  {
    List<Tile> newTile = Collections.unmodifiableList(tile);
    return newTile;
  }

  public int numberOfTile()
  {
    int number = tile.size();
    return number;
  }

  public boolean hasTile()
  {
    boolean has = tile.size() > 0;
    return has;
  }

  public int indexOfTile(Tile aTile)
  {
    int index = tile.indexOf(aTile);
    return index;
  }

  public Session getSession()
  {
    return session;
  }

  public static int minimumNumberOfTile()
  {
    return 0;
  }

  public Tile addTile(Location aLocation)
  {
    return new Tile(this, aLocation);
  }

  public boolean addTile(Tile aTile)
  {
    boolean wasAdded = false;
    if (tile.contains(aTile)) { return false; }
    Board existingBoard = aTile.getBoard();
    boolean isNewBoard = existingBoard != null && !this.equals(existingBoard);
    if (isNewBoard)
    {
      aTile.setBoard(this);
    }
    else
    {
      tile.add(aTile);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTile(Tile aTile)
  {
    boolean wasRemoved = false;
    //Unable to remove aTile, as it must always have a Board
    if (!this.equals(aTile.getBoard()))
    {
      tile.remove(aTile);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addTileAt(Tile aTile, int index)
  {  
    boolean wasAdded = false;
    if(addTile(aTile))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTile()) { index = numberOfTile() - 1; }
      tile.remove(aTile);
      tile.add(index, aTile);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTileAt(Tile aTile, int index)
  {
    boolean wasAdded = false;
    if(tile.contains(aTile))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTile()) { index = numberOfTile() - 1; }
      tile.remove(aTile);
      tile.add(index, aTile);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTileAt(aTile, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (tile.size() > 0)
    {
      Tile aTile = tile.get(tile.size() - 1);
      aTile.delete();
      tile.remove(aTile);
    }
    
    Session existingSession = session;
    session = null;
    if (existingSession != null)
    {
      existingSession.delete();
    }
  }

}