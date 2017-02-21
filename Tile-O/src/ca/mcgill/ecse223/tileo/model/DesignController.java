package ca.mcgill.ecse223.tileo.model;

import java.util.List;

public class DesignController {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
//a method to convert location/int x,y to index needed
  public int aMethodWhichIsSupposedToConvertsLocationIntoIndexButNotTested(int x,int y){
	  int index=-1;
	List<Tile> tileList = .getTiles();// getTile
	for(int i =0;i<tileList.size();i++){
		Tile atile=tileList.get(i);
		if (atile.getX()==x &&atile.getY() ==y){
			index = i;
			break;
		}
	}
	 return index; 
  }


	 // renamed placeAtile() >> placeTile()
	  public void placeTile (int x , int y)  throws InvalidInputException{
		  Game theGame = this.game;  //
		  Tile newTile = new Tile(x,y,theGame);
	  }
	  
	  
	  

	  // Code according to sequence diagram
	  public void deleteConnection(Connection aConnection){
		 aConnection.delete();
	  }
	 
	  
	  // the method removes all tiles connected to a tile
	  // requirements ask us to remove connection between 2 tiles
	  // will modify

	  public void rollDieAgainAction(){
		  Game theGame = this.game;
		 for(Player p : theGame.getPlayers()){
			 if( p!= theGame.getCurrentPlayer()){
				 p.setTurnsUntilActive(p.getTurnsUntilActive()+1);
			 }
	  }
		   
	  }
	
	
	
}
