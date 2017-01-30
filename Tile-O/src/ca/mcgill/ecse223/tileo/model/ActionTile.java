/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;

// line 43 "../../../../../GameEngine.ump"
public class ActionTile extends Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ActionTile Attributes
  private int coolDownTurns;
  private int lastVisitedTurn;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ActionTile(Board aBoard, Location aLocation, int aCoolDownTurns, int aLastVisitedTurn)
  {
    super(aBoard, aLocation);
    coolDownTurns = aCoolDownTurns;
    lastVisitedTurn = aLastVisitedTurn;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCoolDownTurns(int aCoolDownTurns)
  {
    boolean wasSet = false;
    coolDownTurns = aCoolDownTurns;
    wasSet = true;
    return wasSet;
  }

  public boolean setLastVisitedTurn(int aLastVisitedTurn)
  {
    boolean wasSet = false;
    lastVisitedTurn = aLastVisitedTurn;
    wasSet = true;
    return wasSet;
  }

  public int getCoolDownTurns()
  {
    return coolDownTurns;
  }

  public int getLastVisitedTurn()
  {
    return lastVisitedTurn;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "coolDownTurns" + ":" + getCoolDownTurns()+ "," +
            "lastVisitedTurn" + ":" + getLastVisitedTurn()+ "]"
     + outputString;
  }
}