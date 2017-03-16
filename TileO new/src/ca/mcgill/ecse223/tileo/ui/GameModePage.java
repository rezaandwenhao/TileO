package ca.mcgill.ecse223.tileo.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
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

import ca.mcgill.ecse223.tileo.model.Connection;
import ca.mcgill.ecse223.tileo.model.Game.Mode;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.ui.GameModeResources.GameState;
import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.PlayController;
public class GameModePage extends TileOGamePage {
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
	private TileOGamePage this_page = this;
	private PlayController pc;
	
	private GameModeResources resource;

	private HashMap<Mode, JLabel> action_highlight;
	private HashMap<Mode, JButton> enabled_button;
	
	private int highlightedTiles[][];
	private int rollNumber = 0;
	public void refresh(){
		for(int x =0;x<resource.xTiles;x++){
			for(int y =0;y<resource.yTiles;y++){
				resource.uitile[x][y] = new UITile(DesignModeResources.Type.Empty,0,0,false);
			}
		}
		resource.uitile = pc.updateTiles(resource.uitile);
		resource.uiconnect = pc.updateConnection();
		resource.currentmode = pc.getMode();
		
		connectTilesActionCardDescription.setForeground(Color.BLACK);
	    loseTurnActionCardDescription.setForeground(Color.BLACK);
	    removeConnectionActionCardDescription.setForeground(Color.BLACK);
	    rollDieActionCardDescription.setForeground(Color.BLACK);
	    teleportActionCardDescription.setForeground(Color.BLACK);
	    rollDieActionCardDescription.setFont(new Font("Arial",Font.PLAIN,12));
		connectTilesActionCardDescription.setFont(new Font("Arial",Font.PLAIN,12));
		removeConnectionActionCardDescription.setFont(new Font("Arial",Font.PLAIN,12));
		teleportActionCardDescription.setFont(new Font("Arial",Font.PLAIN,12));
		loseTurnActionCardDescription.setFont(new Font("Arial",Font.PLAIN,12));
		
	    try{
	    	action_highlight.get(resource.currentmode).setForeground(Color.RED);
	    }catch(NullPointerException e){
	    	
	    }
	    rollDiceButton.setEnabled(false);
	    drawCardButton.setEnabled(false);
	    try{
	    	enabled_button.get(resource.currentmode).setEnabled(true);
	    }catch(NullPointerException e){}
	    
	    playerTurn.setForeground(resource.playercolor.get(pc.getcurrentPlayer()));
	    playerTurn.setText("<html><u>Player " + pc.getcurrentPlayer() + "'s Turn</u></html>");
	    
	    numSpareConnection.setText("Spare connections: " + pc.getcurrentSpareConnection());
	    
	    rollDiceNumber.setText(rollNumber+"");
	    
		repaint();
	}
	
	public void initialize(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(700,700);
		resource = new GameModeResources();
		pc = new PlayController();
		resource.uitile = new UITile[18][10];
		for(int x =0;x<resource.xTiles;x++){
			for(int y =0;y<resource.yTiles;y++){
				resource.uitile[x][y] = new UITile(DesignModeResources.Type.Empty,0,0,false);
			}
		}
		
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
			// Not reacting to click out the board
			if (!(e.getX() > 659 || e.getY() > 386 || e.getX() < 30 || e.getY() < 45)) {

				// Clicked on Block. Because of time constraint, we use
				// switch instead of polymorphism
				if ((e.getX() - 30) % (resource.block_Size * 2) < resource.block_Size && (e.getY() - 45) % (resource.block_Size * 2) < resource.block_Size) {
					int x = (e.getX()-30)/(resource.block_Size*2);
					int y = (e.getY()-45)/(resource.block_Size*2);
					switch(resource.state){
					case PlayerSelectingTile:
						resource.state = GameState.Nothing;
						try {
							for(int i=0; i<highlightedTiles.length; i++){
								if(highlightedTiles[i][0]==x && highlightedTiles[i][1]==y)
									pc.landOnTile(x,y);
							}
						} catch (InvalidInputException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					case Nothing:
						break;
					default:
						break;
					}

				}
			}
			refresh();
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	});
		
		// elements for error message
		errorMessage = new JLabel("");
		errorMessage.setFont(new Font("Arial",Font.BOLD,15));
		errorMessage.setSize(200, 30);
		errorMessage.setLocation(15, 15);
		panel.add(errorMessage);
		
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
		numSpareConnection.setText("Spare connections: 32");
		numSpareConnection.setFont(new Font("Arial",Font.BOLD,20));
		numSpareConnection.setSize(250, 30);
		numSpareConnection.setLocation(325-20, 345+70);
		panel.add(numSpareConnection);
		
		// elements for roll a dice button
		rollDiceButton = new JButton("Roll");
		rollDiceButton.setFont(new Font("Arial",Font.PLAIN,15));
		rollDiceButton.setSize(100, 30);
		rollDiceButton.setLocation(100-50, 390+72);
		panel.add(rollDiceButton);
		
		// elements for roll a dice number
		rollDiceNumber = new JLabel("0");
		rollDiceNumber.setFont(new Font("Arial",Font.BOLD,20));
		rollDiceNumber.setSize(150, 30);
		rollDiceNumber.setLocation(210-50, 390+72);
		panel.add(rollDiceNumber);
		
		// elements for card type display
		tileType = new JLabel("Tile Type: ");
		tileType.setFont(new Font("Arial",Font.BOLD,20));
		tileType.setSize(150, 30);
		tileType.setLocation(70-50, 430+80);
	    panel.add(tileType);
	    
	 // elements for rollDie ActionCard: 
	 	rollDieActionCardDescription = new JLabel("Extra Turn: Roll another Dice", SwingConstants.LEFT);
	 	rollDieActionCardDescription.setFont(new Font("Arial",Font.PLAIN,12));
	 	rollDieActionCardDescription.setSize(250,20);
	 	rollDieActionCardDescription.setLocation(70-50, 460+80);
	 	panel.add(rollDieActionCardDescription);
		
		// elements for connectTiles ActionCard: 
		connectTilesActionCardDescription = new JLabel("Add Connection: Click between them", SwingConstants.LEFT);
		connectTilesActionCardDescription.setFont(new Font("Arial",Font.PLAIN,12));
		connectTilesActionCardDescription.setSize(250, 20);
		connectTilesActionCardDescription.setLocation(70-50, 480+80);
		panel.add(connectTilesActionCardDescription);
		
		// elements for removeConnection ActionCard: 
		removeConnectionActionCardDescription = new JLabel("Remove Connection: Click between them", SwingConstants.LEFT);
		removeConnectionActionCardDescription.setFont(new Font("Arial",Font.PLAIN,12));
		removeConnectionActionCardDescription.setSize(250, 20);
		removeConnectionActionCardDescription.setLocation(70-50, 500+80);
		panel.add(removeConnectionActionCardDescription);
		
		// elements for teleport ActionCard
		teleportActionCardDescription = new JLabel("Move Players: Choose new tile", SwingConstants.LEFT);
		teleportActionCardDescription.setFont(new Font("Arial",Font.PLAIN,12));
		teleportActionCardDescription.setSize(250, 20);
		teleportActionCardDescription.setLocation(70-50, 520+80);
		panel.add(teleportActionCardDescription);
		
		// elements for loseTurn ActionCard:
		loseTurnActionCardDescription = new JLabel("Lose Turn: Skip a turn", SwingConstants.LEFT);
		loseTurnActionCardDescription.setFont(new Font("Arial",Font.PLAIN,12));
		loseTurnActionCardDescription.setSize(250, 20);
		loseTurnActionCardDescription.setLocation(70-50, 540+80);
		panel.add(loseTurnActionCardDescription);
		
		// elements for draw card
		drawCardButton= new JButton("Draw Card");
		drawCardButton.setFont(new Font("Arial",Font.BOLD,15));
		drawCardButton.setSize(200, 50);
		drawCardButton.setLocation(325-20,480+80);
		panel.add(drawCardButton);
		
		// elements for save and exist;
		saveExistButton= new JButton("SAVE & EXIT");
		saveExistButton.setFont(new Font("Arial",Font.BOLD,15));
		saveExistButton.setSize(140, 40);
		saveExistButton.setLocation(545,622);
		panel.add(saveExistButton);
		
		
		rollDiceButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				rollDiceButtonActionPerformed(evt);
			}
		});
		
		drawCardButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
			//	drawCardButtonActionPerformed(evt);
			}
		});
		
		saveExistButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
		//		saveExistButtonActionPerformed(evt);
			}
		});
		add(panel);
	
		action_highlight = new HashMap<Mode, JLabel>();
		action_highlight.put(Mode.GAME_CONNECTTILESACTIONCARD, connectTilesActionCardDescription);
		action_highlight.put(Mode.GAME_ROLLDIEACTIONCARD, rollDieActionCardDescription);
		action_highlight.put(Mode.GAME_LOSETURNACTIONCARD, loseTurnActionCardDescription);
		action_highlight.put(Mode.GAME_REMOVECONNECTIONACTIONCARD, removeConnectionActionCardDescription);
		action_highlight.put(Mode.GAME_TELEPORTACTIONCARD, teleportActionCardDescription);
		
		enabled_button = new HashMap<Mode, JButton>();
		enabled_button.put(Mode.GAME, rollDiceButton);
		enabled_button.put(Mode.GAME_DRAWCARD, drawCardButton);
		
		setVisible(true);
	}
	
	private void rollDiceButtonActionPerformed(java.awt.event.ActionEvent evt) {
		String error = "";

		rollNumber = pc.rollDie();
		highlightedTiles = pc.getHighlight();
		pc.resetFinalTiles();

		resource.state = GameState.PlayerSelectingTile;

		refresh();
	}

	
	private void drawCardButtonActionPerformed(java.awt.event.ActionEvent evt) throws InvalidInputException {
		String error = "";
	
		
		Mode currentMode = pc.getMode();
		//if it is not an action card

		if (tileType.getText().equals("Tile Type: Action"))
			error+="You are not on an action tile, cannot draw a card";
		
		if (error.length() == 0){
		try {
		    if (currentMode.equals(Mode.GAME_ROLLDIEACTIONCARD)){
		    	
		    	rollDieActionCardDescription.setFont(new Font("Arial",Font.BOLD,12));

				rollNumber = pc.rollDie();
				highlightedTiles = pc.getHighlight();
				pc.resetFinalTiles();
				
				refresh();
		    }
		    else if(currentMode.equals(Mode.GAME_CONNECTTILESACTIONCARD)){
		    	connectTilesActionCardDescription.setFont(new Font("Arial",Font.BOLD,15));
		    	
		    	// player select two tiles (hardcode for now)
                Tile tile1 = null;
                Tile tile2 = null;
                if (pc.getcurrentSpareConnection() > 0){
                pc.playConnectTilesActionCard(tile1, tile2);
                // two set the spare connections testfield: 
                String s = "<html><u>Spare connections: " + pc.getcurrentSpareConnection() + "</u></html>";
                numSpareConnection.setText(s);
                }
                else {
                	error+="Cannot add connections because there is no connection piece left!";
                }
                refresh();
			}
		    else if(currentMode.equals(Mode.GAME_REMOVECONNECTIONACTIONCARD)){
		    	removeConnectionActionCardDescription.setFont(new Font("Arial",Font.BOLD,15));
		    	
		        //player selected a connection to be deleted. (hardcode for now)
		    	Connection currentConnection = null;
		    	pc.playRemoveConnectionActionCard(currentConnection);
			}
		    
			else if(currentMode.equals(Mode.GAME_TELEPORTACTIONCARD)){
		    	removeConnectionActionCardDescription.setFont(new Font("Arial",Font.BOLD,15));
		    	
		    	//player selected a tile and move to that tile (hardcode for now)
				Tile tile = null;
				pc.playTeleportActionCard(tile);
			}
			else{
		    	loseTurnActionCardDescription.setFont(new Font("Arial",Font.BOLD,15));

			//	pc.loseTurn();
			}
			
		}catch(Exception e){
		    error+= e.getMessage();
		    throw new InvalidInputException(error.trim());
		}
		}
	   refresh();
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
	        g.setColor(resource.background);
	        g.fillRect(0, 0, 700, 400);
	        // DRAWING THE TILES
	        for(int x = 0; x < resource.xTiles; x++){
	        	for(int y = 0; y < resource.yTiles; y++){
	        		OuterTile(g,x,y);
	        		if(!resource.uitile[x][y].isVisited() || (resource.uitile[x][y].getType() == DesignModeResources.Type.Empty)){
	        			g.setColor(Color.LIGHT_GRAY);
	        			g.fillRect(32 + resource.block_Size*2*x, 47 + resource.block_Size*2*y, resource.inner_Size, resource.inner_Size);
	        		}
	        		else{
	        			switch(resource.uitile[x][y].getType()){
						case Action:
							g.setColor(Color.WHITE);
							g.fillRect(32 + resource.block_Size*2*x, 47 + resource.block_Size*2*y, resource.inner_Size, resource.inner_Size);
							g.setColor(Color.BLACK);
							g.setFont(resource.ActionTileFont);
							g.drawString(""+resource.uitile[x][y].getCooldown(), 36 + resource.block_Size*2*x, 59 + resource.block_Size*2*y);
							break;
						case Normal:
							g.setColor(Color.WHITE);
							g.fillRect(32 + resource.block_Size*2*x, 47 + resource.block_Size*2*y, resource.inner_Size, resource.inner_Size);
							break;
						case Win:
							g.setColor(Color.BLACK);
							g.fillRect(32 + resource.block_Size*2*x, 47 + resource.block_Size*2*y, resource.inner_Size, resource.inner_Size);
							break;
						default:
							break;
	        			}
	        			if(resource.uitile[x][y].getCurrentPlayer()!= 0){
	        				g.setColor(resource.playercolor.get(resource.uitile[x][y].getCurrentPlayer()));
	        				g.fillOval(30 + resource.block_Size*2*x, 45 + resource.block_Size*2*y, resource.block_Size, resource.block_Size);
	        			}
	        		}
	        	}
			}
	        
	        if(resource.currentmode==Mode.GAME_TAKETURN){
	        	for(int i = 0; i < highlightedTiles.length; i++){
	        		System.out.println("Something");
	        		Highlight(g, highlightedTiles[i][0], highlightedTiles[i][1]);
	        	}
	        }
	        
	        for(UIConnection i: resource.uiconnect){
	        	Connection(g,i.isH_V(),i.getFromx(),i.getFromy());
	        }
	        g.setColor(Color.BLACK);
	        
	        /*// OUTLINE FOR SAVE SECTION
	        g.drawRect(553,598, 140, 80);
	        g.drawRect(554,599, 140, 80);
	        g.drawRect(555,600, 140, 80);
	        */
	        
	        // LINE IN MIDDLE
	        g.fillRect(0, 390, 700, 10);
	        
	        // TILE INFORMATION
	        g.setColor(Color.WHITE);
	        g.fillRect(15, 540, 250, 100);
	        g.setColor(Color.BLACK);
	        g.drawRect(15, 540, 250, 100);
	        
	        // PLAYER TEST
	        
	   }
	    
	    private void OuterTile(Graphics g, int x ,int y){
	    	g.setColor(resource.outerTile.get(!(resource.uitile[x][y].getType() == DesignModeResources.Type.Empty)));
	    	g.fillRect(30 + resource.block_Size*2*x, 45 + resource.block_Size*2*y, resource.block_Size, resource.block_Size);
	    };
	    
	    private void Connection(Graphics g, boolean H_V, int x, int y){
	    	g.setColor(Color.BLACK);
	    	if(H_V){
	    		g.drawLine(30 + resource.block_Size + resource.block_Size*2*x, 51 + resource.block_Size*2*y, 29 + resource.block_Size*2*(x+1), 51 + resource.block_Size*2*y);
	    		g.fillRect(30 + resource.block_Size + resource.block_Size*2*x, 53 + resource.block_Size*2*y, resource.block_Size, 3);
	    		g.drawLine(30 + resource.block_Size + resource.block_Size*2*x, 57 + resource.block_Size*2*y, 29 + resource.block_Size*2*(x+1), 57 + resource.block_Size*2*y);
	    	}
	    	else{
	    		g.drawLine(36 + resource.block_Size*2*x, 45 + resource.block_Size + resource.block_Size*2*y, 36 + resource.block_Size*2*x, 44 + resource.block_Size*2*(y+1));
	    		g.fillRect(38 + resource.block_Size*2*x, 45 + resource.block_Size + resource.block_Size*2*y, 3 , resource.block_Size);
	    		g.drawLine(42 + resource.block_Size*2*x, 45 + resource.block_Size + resource.block_Size*2*y, 42 + resource.block_Size*2*x, 44 + resource.block_Size*2*(y+1));
	    	}
	    }
	    
	    private void Highlight(Graphics g, int x, int y){
	    	g.setColor(new Color(255,255,0,128));
	    	g.fillRect(30 + resource.block_Size*2*x, 45 + resource.block_Size*2*y, resource.block_Size, resource.block_Size);
	    }
    }
}