import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Tile;

public class Player {
	  public void swappedPlayerLand(Tile tile){
		  
		  Game currentGame=this.getGame();
		  this.setCurrentTile(tile);
	      tile.setHasBeenVisited(true);
	  }
}
