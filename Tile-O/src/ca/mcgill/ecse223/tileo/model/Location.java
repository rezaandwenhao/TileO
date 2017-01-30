/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse223.tileo.model;

// line 32 "../../../../../GameEngine.ump"
public class Location
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Location Attributes
  private int x;
  private int y;

  //Location Associations
  private Tile tile;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Location(int aX, int aY)
  {
    x = aX;
    y = aY;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setX(int aX)
  {
    boolean wasSet = false;
    x = aX;
    wasSet = true;
    return wasSet;
  }

  public boolean setY(int aY)
  {
    boolean wasSet = false;
    y = aY;
    wasSet = true;
    return wasSet;
  }

  public int getX()
  {
    return x;
  }

  public int getY()
  {
    return y;
  }

  public Tile getTile()
  {
    return tile;
  }

  public boolean hasTile()
  {
    boolean has = tile != null;
    return has;
  }

  public boolean setTile(Tile aNewTile)
  {
    boolean wasSet = false;
    if (tile != null && !tile.equals(aNewTile) && equals(tile.getLocation()))
    {
      //Unable to setTile, as existing tile would become an orphan
      return wasSet;
    }

    tile = aNewTile;
    Location anOldLocation = aNewTile != null ? aNewTile.getLocation() : null;

    if (!this.equals(anOldLocation))
    {
      if (anOldLocation != null)
      {
        anOldLocation.tile = null;
      }
      if (tile != null)
      {
        tile.setLocation(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Tile existingTile = tile;
    tile = null;
    if (existingTile != null)
    {
      existingTile.delete();
      existingTile.setLocation(null);
    }
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "x" + ":" + getX()+ "," +
            "y" + ":" + getY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "tile = "+(getTile()!=null?Integer.toHexString(System.identityHashCode(getTile())):"null")
     + outputString;
  }
}