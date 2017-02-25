/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;

import ca.mcgill.ecse223.tileo.model.Game.Mode;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;

// line 46 "../../../../../TileO (updated Feb10).ump"
public class NormalTile extends Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public NormalTile(int aX, int aY, Game aGame)
  {
    super(aX, aY, aGame);
  }

  //------------------------
  // INTERFACE
  //------------------------
  public void land(){
	  Game currentGame = this.getGame();
	  Player currentPlayer = currentGame.getCurrentPlayer();
      currentPlayer.setCurrentTile(this);
      
      if (currentGame.indexOfPlayer(currentPlayer) != currentGame.numberOfPlayers()-1) {
  		currentGame.setCurrentPlayer(currentGame.getPlayer(currentGame.indexOfPlayer(currentPlayer)+1));
  		}
  		else {
  			currentGame.setCurrentPlayer(currentGame.getPlayer(0));
  			}
      
      this.setHasBeenVisited(true);
      currentGame.setMode(Mode.GAME);
  }
  
  public void delete()
  {
    super.delete();
  }

}
