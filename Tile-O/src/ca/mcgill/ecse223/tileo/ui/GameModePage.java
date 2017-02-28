/package ca.mcgill.ecse223.tileo.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import ca.mcgill.ecse223.tileo.model.Game.Mode;
import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.PlayController;
import ca.mcgill.ecse223.tileo.model.ActionCard;
import ca.mcgill.ecse223.tileo.model.Connection;
import ca.mcgill.ecse223.tileo.model.Deck;
import ca.mcgill.ecse223.tileo.model.Die;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.Tile;

public class GameModePage extends JFrame {
	private static final long serialVersionUID = -4426310869335015542L;

	// TITLE
	private JLabel playMode;
	// UI elements
    private JLabel errorMessage;
  //  private JLabel playerTurnLabel;
	private JLabel playerTurn;
    // spare connection
    private JLabel numSpareConnection;
	// roll a dice
	private JButton rollDiceButton; 
 	private JLabel rollDiceNumber;

	// tile type display
    private JLabel tileType; 
    private JLabel connectTilesActionCardDescription;
    private JLabel loseTurnActionCardDescription;
    private JLabel removeConnectionActionCardDescription;
    private JLabel rollDieActionCardDescription;
    private JLabel teleportActionCardDescription;
	// draw card 
	private JButton drawCardButton;
    // save and load button
	private JButton saveExistButton;
	
	// data elements
	private String error = null;
	private TileOGamePage parent;
	private PlayController pc;
	
	private DesignModeResources resource;
	private UITile uitile[][];
		
		
	public GameModePage() {
		initialize();
		refreshData();
	}
	
	public void initialize(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(700,700);
		resource = new DesignModeResources();
		
		uitile = new UITile[18][10];
		for(int x =0;x<resource.xTiles;x++){
			for(int y =0;y<resource.yTiles;y++){
				uitile[x][y] = new UITile(DesignModeResources.Type.Empty,0,0,false);
			}
		}
		
		UIConnection s = new UIConnection(0,4,false);
		resource.uiconnect.add(s);
		Border something = BorderFactory.createLineBorder(Color.BLACK, 10, false);
		
		Panel panel = new Panel();
		panel.setLayout(null);
        panel.addMouseListener(new MouseListener(){
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			//Not reacting to click out the board
			if(e.getX()> 659 || e.getY() > 386 || e.getX() < 30 || e.getY() < 45) System.out.println("OUT!");
			if((e.getX()-30)%(resource.block_Size*2) < resource.block_Size && (e.getY()-45)%(resource.block_Size*2) < resource.block_Size) 
				System.out.printf("Clicked on block %d, %d\n" , (e.getX()-30)/(resource.block_Size*2), (e.getY()-45)/(resource.block_Size*2));
			System.out.println(e.getX());
			System.out.println(e.getY());
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	});
		
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		// element for play mode title
		playMode = new JLabel("PLAY MODE");
		playMode.setFont(new Font("Arial",Font.BOLD,25));
		playMode.setSize(200, 30);
		playMode.setLocation(270, 15);
		panel.add(playMode);
		
		// elements for player's turn label
		playerTurn = new JLabel();
		playerTurn.setForeground(Color.RED);
		playerTurn.setText("<html><u>Player 1's Turn</u></html>");
		playerTurn.setFont(new Font("Arial",Font.BOLD,20));
		playerTurn.setSize(150, 30);
	    playerTurn.setLocation(80-50, 345+70);
		panel.add(playerTurn);
		
		// elements for spare connections:
		numSpareConnection = new JLabel();
		numSpareConnection.setText("<html><u>Spare connections: 32</u></html>");
		numSpareConnection.setFont(new Font("Arial",Font.BOLD,20));
		numSpareConnection.setSize(150, 30);
		numSpareConnection.setLocation(325-20, 345+70);
		panel.add(numSpareConnection);
		
		// elements for roll a dice button
		rollDiceButton = new JButton("Roll");
		rollDiceButton.setFont(new Font("Arial",Font.PLAIN,15));
		rollDiceButton.setSize(150, 30);
		rollDiceButton.setLocation(80-50, 410+72);
		panel.add(rollDiceButton);
		
		// elements for roll a dice number
		rollDiceNumber = new JLabel("0");
		rollDiceNumber.setFont(new Font("Arial",Font.PLAIN,15));
		rollDiceNumber.setSize(150, 30);
		rollDiceNumber.setLocation(230-50, 410+72);
		panel.add(rollDiceNumber);
		
		// elements for card type display
		tileType = new JLabel("Tile Type: ");
		tileType.setFont(new Font("Arial",Font.BOLD,20));
		tileType.setSize(150, 30);
		tileType.setLocation(70-50, 370+80);
	    panel.add(tileType);
		
		// elements for connectTiles ActionCard: 
		connectTilesActionCardDescription = new JLabel("Extra Turn: Roll another Dice", SwingConstants.LEFT);
		connectTilesActionCardDescription.setFont(new Font("Arial",Font.PLAIN,15));
		connectTilesActionCardDescription.setSize(140, 30);
		connectTilesActionCardDescription.setLocation(70-50, 400+80);
		panel.add(connectTilesActionCardDescription);
		
		// elements for loseTurn ActionCard:
		loseTurnActionCardDescription = new JLabel("Extra Turn: Roll another Dice", SwingConstants.LEFT);
		loseTurnActionCardDescription.setFont(new Font("Arial",Font.PLAIN,15));
		loseTurnActionCardDescription.setSize(140, 30);
		loseTurnActionCardDescription.setLocation(70-50, 430+80);
		panel.add(loseTurnActionCardDescription);
		
		// elements for removeConnection ActionCard: 
		removeConnectionActionCardDescription = new JLabel("Extra Turn: Roll another Dice", SwingConstants.LEFT);
		removeConnectionActionCardDescription.setFont(new Font("Arial",Font.PLAIN,15));
		removeConnectionActionCardDescription.setSize(140, 30);
		removeConnectionActionCardDescription.setLocation(70-50, 470+80);
		panel.add(removeConnectionActionCardDescription);
		
		// elements for rollDie ActionCard: 
		rollDieActionCardDescription = new JLabel("Extra Turn: Roll another Dice", SwingConstants.LEFT);
		rollDieActionCardDescription.setFont(new Font("Arial",Font.PLAIN,15));
		rollDieActionCardDescription.setSize(140, 30);
		rollDieActionCardDescription.setLocation(70-50, 500+80);
		panel.add(rollDieActionCardDescription);
		
		// elements for teleport ActionCard
		teleportActionCardDescription = new JLabel("ELose Turn: Skip a turn", SwingConstants.LEFT);
		teleportActionCardDescription.setFont(new Font("Arial",Font.PLAIN,15));
		teleportActionCardDescription.setSize(140, 30);
		teleportActionCardDescription.setLocation(70-50, 530+80);
		panel.add(teleportActionCardDescription);
		
		// elements for draw card
		drawCardButton= new JButton("Draw Card");
		drawCardButton.setFont(new Font("Arial",Font.BOLD,15));
		drawCardButton.setSize(150,30);
		drawCardButton.setLocation(553,600);
		panel.add(drawCardButton);
		
		// elements for save and exist;
		saveExistButton= new JButton("SAVE & EXIT");
		saveExistButton.setFont(new Font("Arial",Font.BOLD,15));
		saveExistButton.setSize(140, 40);
		saveExistButton.setLocation(553,632);
		panel.add(saveExistButton);
		
		
		rollDiceButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
		//		rollDiceButtonActionPerformed(evt);
			}
		});
		
		drawCardButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
		//		drawCardButtonActionPerformed(evt);
			}
		});
		
		saveExistButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
		//		saveExistButtonActionPerformed(evt);
			}
		});
		add(panel);
	
	}

    public void refreshData(){
    	errorMessage.setText(error);
    	if (error == null || error.length() == 0) {
    		Game currentGame = TileOApplication.getCurrentGame();
    		
    		Player currentPlayer = currentGame.getCurrentPlayer();
    		if (currentGame.indexOfPlayer(currentPlayer) != currentGame.numberOfPlayers()-1) {
    		currentGame.setCurrentPlayer(currentGame.getPlayer(currentGame.indexOfPlayer(currentPlayer)+1));
    		   int playerPosition = currentGame.indexOfPlayer(currentPlayer)+1;
    		   String s = "<html><u>Player" + playerPosition + "'s Turn</u></html>";
    		   playerTurn.setText(s);
    		}
    		else {
    			playerTurn.setText("<html><u>Player 1's Turn</u></html>");
    		}
    		
    		
    		int numConnectionPieces = currentGame.getCurrentConnectionPieces();
    		if (numConnectionPieces > 0) {
    			String s = "<html><u>Spare connections:" + numConnectionPieces + "</u></html>";
    			numSpareConnection.setText(s);
    		}
    		else{
    			numSpareConnection.setText("<html><u>Spare connections:0</u></html>");
    		}
    		
    		rollDiceNumber.setText("0");
    		tileType.setText("Tile Type:");
    		connectTilesActionCardDescription.setFont(new Font("Arial",Font.PLAIN,15));
    		loseTurnActionCardDescription.setFont(new Font("Arial",Font.PLAIN,15));
    		removeConnectionActionCardDescription.setFont(new Font("Arial",Font.PLAIN,15));
    		rollDieActionCardDescription.setFont(new Font("Arial",Font.PLAIN,15));
    		teleportActionCardDescription.setFont(new Font("Arial",Font.PLAIN,15));

    	}
    	
    }
    
	/*public void initialize() {
		// TODO Auto-generated method stub
	}
	*/
	private void rollDiceButtonActionPerformed(java.awt.event.ActionEvent evt) {
		String error = "";
		String dieNumber = rollDiceNumber.getText();
		Game currentGame = TileOApplication.getCurrentGame();
		Integer rollResult = 0;
		Tile selectedTile = null;
		
		Player currentPlayer = currentGame.getCurrentPlayer();
		Die currentDie = currentGame.getDie();
		
		if(!dieNumber.equals("0")){
			error += "You have already rolled a die";
		}
		
		if(error.length() == 0){
		    try {
			// player roll a die
			rollResult = currentDie.roll();
			List<Tile> tiles = currentPlayer.getPossibleMoves(rollResult);
			rollDiceNumber.setText(rollResult.toString());
			
			// player land on tile
		    selectedTile = tiles.get(0);
		    selectedTile.land();
		    }catch(Exception e){
		    	error+=e.getMessage();
		    }
			
		}
		refreshData();
	}
	
	private void drawCardButtonActionPerformed(java.awt.event.ActionEvent evt) {
		String error = "";
		Game currentGame = TileOApplication.getCurrentGame();
		
		Player currentPlayer = currentGame.getCurrentPlayer();
		Deck currentDeck = currentGame.getDeck();
		Die currentDie = currentGame.getDie();
		//if it is not an action card

		if (tileType.getText().equals("Tile Type: Action"))
			error+="You are not on an action tile, cannot draw a card";
		
		if (error.length() == 0){
		try {
			    ActionCard currentCard = currentDeck.getCurrentCard();
			    Mode currentMode = currentCard.getActionCardGameMode();
		        currentGame.setMode(currentMode);
			
		    if (currentMode.equals(Mode.GAME_ROLLDIEACTIONCARD)){
		    	
		    	rollDiceNumber.setText("0");
		    	//Extra Turn becomes bold
		    	rollDieActionCardDescription.setFont(new Font("Arial",Font.BOLD,15));
		    	
		    	//player roll a dice again and move to a new tile
		    	
				Integer rollResult = 0;
				Tile selectedTile = null;
				
				rollResult = currentDie.roll();
				List<Tile> tiles = currentPlayer.getPossibleMoves(rollResult);
				rollDiceNumber.setText(rollResult.toString());
				
				// player chooses a tile and lands on tile (hardcode)
			    selectedTile = tiles.get(0);
			    selectedTile.land();
			    
		    }
		    else if(currentMode.equals(Mode.GAME_CONNECTTILESACTIONCARD)){
		    	connectTilesActionCardDescription.setFont(new Font("Arial",Font.BOLD,15));
		    	
		    	// player select two tiles (hardcode)
                Tile tile1;
                Tile tile2;
                if (currentGame.getCurrentConnectionPieces() > 0){
                tc.playConnectTilesActionCard(tile1, tile2);
                currentGame.setCurrentConnectionPieces(currentGame.getCurrentConnectionPieces()-1);
                // two set the spare connections testfield: 
                String s = "<html><u>Spare connections: " + currentGame.getCurrentConnectionPieces() + "</u></html>";
                numSpareConnection.setText(s);
                }
                else {
                	error+="Cannot add connections because there is no connection piece left!";
                }
			}
		    else if(currentMode.equals(Mode.GAME_REMOVECONNECTIONACTIONCARD)){
		    	removeConnectionActionCardDescription.setFont(new Font("Arial",Font.BOLD,15));
		    	
		        //player selected a connection to be deleted. (hardcode)
		    	Connection currentConnection;
		    	tc.playRemoveConnectionActionCard(currentConnection);
			}
		    
			else if(currentMode.equals(Mode.GAME_TELEPORTACTIONCARD)){
		    	removeConnectionActionCardDescription.setFont(new Font("Arial",Font.BOLD,15));
		    	
		    	//player selected a tile and move to that tile
				Tile tile;
				tc.playTeleportActionCard(tile);
			}
			else{
		    	loseTurnActionCardDescription.setFont(new Font("Arial",Font.BOLD,15));

				tc.loseTurn();
			}
			
		}catch(Exception e){
		    error+= e.getMessage();
		    throw new InvalidInputException(error.trim());
		}
		}
	   refreshData();
	}
	
	private void saveExistButtonActionPerformed(java.awt.event.ActionEvent evt) {
		String error = "";
		try{
			pc.SaveandExitGame(parent);
		}catch(Exception e){
		    error+= e.getMessage();
		}
	}

	class Panel extends JPanel{
		public Panel() {
	        setBorder(BorderFactory.createLineBorder(Color.black,10));
	    }

	    /*public Dimension getPreferredSize() {
	        return new Dimension(100,200);
	    }*/

	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);  
	        
	        // DRAWING THE TILES
	        for(int x = 0; x < 20; x++){
	        	for(int y = 0; y < 10; y++){
	        		g.setColor(Color.LIGHT_GRAY);
	        		g.fillRect(20 + 33*x, 50 + 33*y, 20, 20);
	        		g.setColor(Color.RED);
	        		g.fillOval(20 + 33*x, 50 + 33*y, 20, 20);
	        	}
			}
	        
	        g.setColor(Color.BLACK);
	        
	        // OUTLINE FOR SAVE SECTION
	        g.drawRect(553,598, 140, 80);
	        g.drawRect(554,599, 140, 80);
	        g.drawRect(555,600, 140, 80);
	        
	        // LINE IN MIDDLE
	        g.fillRect(0, 390, 700, 10);
	        
	        // PLAYER TEST
	        
	   }
    }
}
