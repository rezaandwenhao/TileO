/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;
import ca.mcgill.ecse223.tileo.model.Game.Mode;


// line 50 "../../../../../TileO (updated Feb10).ump"
public class WinTile extends Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WinTile(int aX, int aY, Game aGame)
  {
    super(aX, aY, aGame);
  }

  //------------------------
  // INTERFACE
  //------------------------
  
  public void land(){
	  Game currentGame = this.getGame();
	  this.setHasBeenVisited(true);
	  currentGame.setMode(Mode.GAME_WON);
  }

  public void delete()
  {
	getGame().setWinTile(null);
    super.delete();
  }

}
