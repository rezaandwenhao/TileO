/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

import java.util.List;
import java.util.Random;

// line 88 "../../../../../TileO (updated Feb10).ump"
public class LoseRandomTurnActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public LoseRandomTurnActionCard(String aInstructions, Deck aDeck)
  {
    super(aInstructions, aDeck);
  }

  public void LoseRanNumTurn(List<Player> players){
	  
	for(Player player: players){
		int  turnLost = (int)(Math.random()*2);
		player.setTurnsUntilActive(player.getTurnsUntilActive()+turnLost);
	}
	  
  }
  //=--=-=-=-=
  
  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}