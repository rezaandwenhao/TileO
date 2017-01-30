/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;

// line 38 "../../../../../GameEngine.ump"
public class Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Tile Associations
  private List<Tile> tileB;
  private List<Player> players;
  private Board Board;
  private Location Location;
  private List<Tile> tileA;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Tile(Board aBoard, Location aLocation)
  {
    tileB = new ArrayList<Tile>();
    players = new ArrayList<Player>();
    boolean didAddBoard = setBoard(aBoard);
    if (!didAddBoard)
    {
      throw new RuntimeException("Unable to create tile due to Board");
    }
    boolean didAddLocation = setLocation(aLocation);
    if (!didAddLocation)
    {
      throw new RuntimeException("Unable to create tile due to Location");
    }
    tileA = new ArrayList<Tile>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Tile getTileB(int index)
  {
    Tile aTileB = tileB.get(index);
    return aTileB;
  }

  public List<Tile> getTileB()
  {
    List<Tile> newTileB = Collections.unmodifiableList(tileB);
    return newTileB;
  }

  public int numberOfTileB()
  {
    int number = tileB.size();
    return number;
  }

  public boolean hasTileB()
  {
    boolean has = tileB.size() > 0;
    return has;
  }

  public int indexOfTileB(Tile aTileB)
  {
    int index = tileB.indexOf(aTileB);
    return index;
  }

  public Player getPlayer(int index)
  {
    Player aPlayer = players.get(index);
    return aPlayer;
  }

  public List<Player> getPlayers()
  {
    List<Player> newPlayers = Collections.unmodifiableList(players);
    return newPlayers;
  }

  public int numberOfPlayers()
  {
    int number = players.size();
    return number;
  }

  public boolean hasPlayers()
  {
    boolean has = players.size() > 0;
    return has;
  }

  public int indexOfPlayer(Player aPlayer)
  {
    int index = players.indexOf(aPlayer);
    return index;
  }

  public Board getBoard()
  {
    return Board;
  }

  public Location getLocation()
  {
    return Location;
  }

  public Tile getTileA(int index)
  {
    Tile aTileA = tileA.get(index);
    return aTileA;
  }

  public List<Tile> getTileA()
  {
    List<Tile> newTileA = Collections.unmodifiableList(tileA);
    return newTileA;
  }

  public int numberOfTileA()
  {
    int number = tileA.size();
    return number;
  }

  public boolean hasTileA()
  {
    boolean has = tileA.size() > 0;
    return has;
  }

  public int indexOfTileA(Tile aTileA)
  {
    int index = tileA.indexOf(aTileA);
    return index;
  }

  public static int minimumNumberOfTileB()
  {
    return 0;
  }

  public static int maximumNumberOfTileB()
  {
    return 4;
  }

  public boolean addTileB(Tile aTileB)
  {
    boolean wasAdded = false;
    if (tileB.contains(aTileB)) { return false; }
    if (tileB.contains(aTileB)) { return false; }
    if (tileB.contains(aTileB)) { return false; }
    if (numberOfTileB() >= maximumNumberOfTileB())
    {
      return wasAdded;
    }

    tileB.add(aTileB);
    if (aTileB.indexOfTileA(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aTileB.addTileA(this);
      if (!wasAdded)
      {
        tileB.remove(aTileB);
      }
    }
    return wasAdded;
  }

  public boolean removeTileB(Tile aTileB)
  {
    boolean wasRemoved = false;
    if (!tileB.contains(aTileB))
    {
      return wasRemoved;
    }

    int oldIndex = tileB.indexOf(aTileB);
    tileB.remove(oldIndex);
    if (aTileB.indexOfTileA(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aTileB.removeTileA(this);
      if (!wasRemoved)
      {
        tileB.add(oldIndex,aTileB);
      }
    }
    return wasRemoved;
  }

  public boolean addTileBAt(Tile aTileB, int index)
  {  
    boolean wasAdded = false;
    if(addTileB(aTileB))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTileB()) { index = numberOfTileB() - 1; }
      tileB.remove(aTileB);
      tileB.add(index, aTileB);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTileBAt(Tile aTileB, int index)
  {
    boolean wasAdded = false;
    if(tileB.contains(aTileB))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTileB()) { index = numberOfTileB() - 1; }
      tileB.remove(aTileB);
      tileB.add(index, aTileB);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTileBAt(aTileB, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfPlayers()
  {
    return 0;
  }

  public static int maximumNumberOfPlayers()
  {
    return 4;
  }

  public Player addPlayer(boolean aSkipTurn)
  {
    if (numberOfPlayers() >= maximumNumberOfPlayers())
    {
      return null;
    }
    else
    {
      return new Player(aSkipTurn, this);
    }
  }

  public boolean addPlayer(Player aPlayer)
  {
    boolean wasAdded = false;
    if (players.contains(aPlayer)) { return false; }
    if (players.contains(aPlayer)) { return false; }
    if (players.contains(aPlayer)) { return false; }
    if (numberOfPlayers() >= maximumNumberOfPlayers())
    {
      return wasAdded;
    }

    Tile existingTile = aPlayer.getTile();
    boolean isNewTile = existingTile != null && !this.equals(existingTile);
    if (isNewTile)
    {
      aPlayer.setTile(this);
    }
    else
    {
      players.add(aPlayer);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePlayer(Player aPlayer)
  {
    boolean wasRemoved = false;
    //Unable to remove aPlayer, as it must always have a tile
    if (!this.equals(aPlayer.getTile()))
    {
      players.remove(aPlayer);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addPlayerAt(Player aPlayer, int index)
  {  
    boolean wasAdded = false;
    if(addPlayer(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlayerAt(Player aPlayer, int index)
  {
    boolean wasAdded = false;
    if(players.contains(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlayerAt(aPlayer, index);
    }
    return wasAdded;
  }

  public boolean setBoard(Board aBoard)
  {
    boolean wasSet = false;
    if (aBoard == null)
    {
      return wasSet;
    }

    Board existingBoard = Board;
    Board = aBoard;
    if (existingBoard != null && !existingBoard.equals(aBoard))
    {
      existingBoard.removeTile(this);
    }
    Board.addTile(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setLocation(Location aNewLocation)
  {
    boolean wasSet = false;
    if (aNewLocation == null)
    {
      //Unable to setLocation to null, as tile must always be associated to a Location
      return wasSet;
    }
    
    Tile existingTile = aNewLocation.getTile();
    if (existingTile != null && !equals(existingTile))
    {
      //Unable to setLocation, the current Location already has a tile, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Location anOldLocation = Location;
    Location = aNewLocation;
    Location.setTile(this);

    if (anOldLocation != null)
    {
      anOldLocation.setTile(null);
    }
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfTileA()
  {
    return 0;
  }

  public static int maximumNumberOfTileA()
  {
    return 4;
  }

  public boolean addTileA(Tile aTileA)
  {
    boolean wasAdded = false;
    if (tileA.contains(aTileA)) { return false; }
    if (tileA.contains(aTileA)) { return false; }
    if (tileA.contains(aTileA)) { return false; }
    if (numberOfTileA() >= maximumNumberOfTileA())
    {
      return wasAdded;
    }

    tileA.add(aTileA);
    if (aTileA.indexOfTileB(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aTileA.addTileB(this);
      if (!wasAdded)
      {
        tileA.remove(aTileA);
      }
    }
    return wasAdded;
  }

  public boolean removeTileA(Tile aTileA)
  {
    boolean wasRemoved = false;
    if (!tileA.contains(aTileA))
    {
      return wasRemoved;
    }

    int oldIndex = tileA.indexOf(aTileA);
    tileA.remove(oldIndex);
    if (aTileA.indexOfTileB(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aTileA.removeTileB(this);
      if (!wasRemoved)
      {
        tileA.add(oldIndex,aTileA);
      }
    }
    return wasRemoved;
  }

  public boolean addTileAAt(Tile aTileA, int index)
  {  
    boolean wasAdded = false;
    if(addTileA(aTileA))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTileA()) { index = numberOfTileA() - 1; }
      tileA.remove(aTileA);
      tileA.add(index, aTileA);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTileAAt(Tile aTileA, int index)
  {
    boolean wasAdded = false;
    if(tileA.contains(aTileA))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTileA()) { index = numberOfTileA() - 1; }
      tileA.remove(aTileA);
      tileA.add(index, aTileA);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTileAAt(aTileA, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Tile> copyOfTileB = new ArrayList<Tile>(tileB);
    tileB.clear();
    for(Tile aTileB : copyOfTileB)
    {
      aTileB.removeTileA(this);
    }
    for(int i=players.size(); i > 0; i--)
    {
      Player aPlayer = players.get(i - 1);
      aPlayer.delete();
    }
    Board placeholderBoard = Board;
    this.Board = null;
    placeholderBoard.removeTile(this);
    Location existingLocation = Location;
    Location = null;
    if (existingLocation != null)
    {
      existingLocation.setTile(null);
    }
    ArrayList<Tile> copyOfTileA = new ArrayList<Tile>(tileA);
    tileA.clear();
    for(Tile aTileA : copyOfTileA)
    {
      aTileA.removeTileB(this);
    }
  }

}