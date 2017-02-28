package ca.mcgill.ecse223.tileo.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import ca.mcgill.ecse223.tileo.controller.DesignController;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;

/* CONTRIBUTOR: Wenhao Geng and Bijan Sadeghi
 * 
 */
public class DesignModePage extends TileOGamePage {
	// TITLE
	private JLabel DesignMode;
	
	// ACTION CARD LABELS
	private JLabel ActionCards;
	private JLabel ExtraTurnCard;
	private JLabel AddConnectionCard;
	private JLabel RemoveConnectionCard;
	private JLabel MovePlayerCard;
	private JLabel LoseTurnCard;
	
	// ACTION CARD MINUS/PLUS BUTTONS
	private JButton ExtraTurnCardMinus;
	private JButton ExtraTurnCardPlus;
	private JButton AddConnectionCardMinus;
	private JButton AddConnectionCardPlus;
	private JButton RemoveConnectionCardMinus;
	private JButton RemoveConnectionCardPlus;
	private JButton MovePlayerCardMinus;
	private JButton MovePlayerCardPlus;
	private JButton LoseTurnCardMinus;
	private JButton LoseTurnCardPlus;
	
	// ACTION CARD NUMBER LABELS
	private JLabel ExtraTurnCardNumber;
	private JLabel AddConnectionCardNumber;
	private JLabel RemoveConnectionCardNumber;
	private JLabel MovePlayerCardNumber;
	private JLabel LoseTurnCardNumber;
	
	// PLAYER LABEL/BUTTONS
	private JLabel Players;
	private JButton Player1;
	private JButton Player2;
	private JButton Player3;
	private JButton Player4;
	
	//BOARD MODIFICATION BUTTONS
	private JButton PlaceTile;
	private JButton PlaceActionTile;
	private JButton PlaceHiddenTile;
	private JButton AddConnection;
	private JButton RemoveConnection;
	private JButton RemoveTile;
	
	// START / SAVE & EXIT BUTTONS
	private JButton Start;
	private JButton SaveAndExit;
	
	private DesignModeResources resource;
	private UITile uitile[][];
	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}
	private DesignController control;
	@Override
	public void initialize() {
		
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
		//panel.setBorder(something);
		//panel.setLayout(new GridBagLayout());
		//GridBagConstraints c = new GridBagConstraints();
		
		
		
		// TITLE
		
		DesignMode = new JLabel("DESIGN MODE");
		DesignMode.setFont(new Font("Arial",Font.BOLD,25));
		DesignMode.setSize(200, 30);
		DesignMode.setLocation(270, 15);
		panel.add(DesignMode);
		
		// ACTION CARDS SECTION
		
		ActionCards = new JLabel();
		ActionCards.setText("<html><u>Action Cards</u></html>");
		ActionCards.setFont(new Font("Arial",Font.BOLD,20));
		ActionCards.setSize(150, 30);
	    ActionCards.setLocation(80-50, 345+70);
		panel.add(ActionCards);
		
		ExtraTurnCard = new JLabel("Exra Turn", SwingConstants.RIGHT);
		ExtraTurnCard.setFont(new Font("Arial",Font.PLAIN,15));
		ExtraTurnCard.setSize(140, 30);
		ExtraTurnCard.setLocation(80-50, 370+70);
		panel.add(ExtraTurnCard);
		
		AddConnectionCard = new JLabel("Add Connection", SwingConstants.RIGHT);
		AddConnectionCard.setFont(new Font("Arial",Font.PLAIN,15));
		AddConnectionCard.setSize(140, 30);
		AddConnectionCard.setLocation(80-50, 390+71);
		panel.add(AddConnectionCard);
		
		RemoveConnectionCard = new JLabel("Remove Connection", SwingConstants.RIGHT);
		RemoveConnectionCard.setFont(new Font("Arial",Font.PLAIN,15));
		RemoveConnectionCard.setSize(140, 30);
		RemoveConnectionCard.setLocation(80-50, 410+72);
		panel.add(RemoveConnectionCard);
		
		MovePlayerCard = new JLabel("Move Player", SwingConstants.RIGHT);
		MovePlayerCard.setFont(new Font("Arial",Font.PLAIN,15));
		MovePlayerCard.setSize(140, 30);
		MovePlayerCard.setLocation(80-50, 430+73);
		panel.add(MovePlayerCard);
		
		LoseTurnCard = new JLabel("Lose Turn", SwingConstants.RIGHT);
		LoseTurnCard.setFont(new Font("Arial",Font.PLAIN,15));
		LoseTurnCard.setSize(140, 30);
		LoseTurnCard.setLocation(80-50, 450+74);
		panel.add(LoseTurnCard);
		
// ACTION CARDS MINUS BUTTONS
		
		ExtraTurnCardMinus = new JButton("-");
		ExtraTurnCardMinus.setFont(new Font("Arial",Font.PLAIN,15));
		ExtraTurnCardMinus.setSize(15, 15);
		ExtraTurnCardMinus.setLocation(230-50, 377+70);
		
		ExtraTurnCardMinus.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					
					control.removeRollDieActionCard();
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		});
		
		panel.add(ExtraTurnCardMinus);
		
		AddConnectionCardMinus = new JButton("-");
		AddConnectionCardMinus.setFont(new Font("Arial",Font.PLAIN,15));
		AddConnectionCardMinus.setSize(15, 15);
		AddConnectionCardMinus.setLocation(230-50, 398+70);
		AddConnectionCardMinus.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					control.removeConnectTilesActionCard();
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		panel.add(AddConnectionCardMinus);
		
		RemoveConnectionCardMinus = new JButton("-");
		RemoveConnectionCardMinus.setFont(new Font("Arial",Font.PLAIN,15));
		RemoveConnectionCardMinus.setSize(15, 15);
		RemoveConnectionCardMinus.setLocation(230-50, 419+70);
		RemoveConnectionCardMinus.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					control.removeRemoveConnectionActionCard();
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		panel.add(RemoveConnectionCardMinus);
		
		MovePlayerCardMinus = new JButton("-");
		MovePlayerCardMinus.setFont(new Font("Arial",Font.PLAIN,15));
		MovePlayerCardMinus.setSize(15, 15);
		MovePlayerCardMinus.setLocation(230-50, 440+70);
		MovePlayerCardMinus.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					control.removeTeleportActionCard();
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});	
		panel.add(MovePlayerCardMinus);
		
		LoseTurnCardMinus = new JButton("-");
		LoseTurnCardMinus.setFont(new Font("Arial",Font.PLAIN,15));
		LoseTurnCardMinus.setSize(15, 15);
		LoseTurnCardMinus.setLocation(230-50, 461+70);
		LoseTurnCardMinus.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					control.removeLoseTurnActionCard();
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		panel.add(LoseTurnCardMinus);
		
		// ACTION CARDS PLUS BUTTONS
		
		ExtraTurnCardPlus = new JButton("+");
		ExtraTurnCardPlus.setFont(new Font("Arial",Font.PLAIN,15));
		ExtraTurnCardPlus.setSize(15, 15);
		ExtraTurnCardPlus.setLocation(270-50, 377+70);
		ExtraTurnCardPlus.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					control.addRollDieActionCard();
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		panel.add(ExtraTurnCardPlus);
		
		AddConnectionCardPlus = new JButton("+");
		AddConnectionCardPlus.setFont(new Font("Arial",Font.PLAIN,15));
		AddConnectionCardPlus.setSize(15, 15);
		AddConnectionCardPlus.setLocation(270-50, 398+70);
		AddConnectionCardPlus.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					control.addConnectTilesActionCard();
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		panel.add(AddConnectionCardPlus);
		
		RemoveConnectionCardPlus = new JButton("+");
		RemoveConnectionCardPlus.setFont(new Font("Arial",Font.PLAIN,15));
		RemoveConnectionCardPlus.setSize(15, 15);
		RemoveConnectionCardPlus.setLocation(270-50, 419+70);
		RemoveConnectionCardPlus.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					control.addRemoveConnectionActionCard();
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		panel.add(RemoveConnectionCardPlus);
		
		MovePlayerCardPlus = new JButton("+");
		MovePlayerCardPlus.setFont(new Font("Arial",Font.PLAIN,15));
		MovePlayerCardPlus.setSize(15, 15);
		MovePlayerCardPlus.setLocation(270-50, 440+70);
		MovePlayerCardPlus.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					control.addTeleportActionCard();
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		panel.add(MovePlayerCardPlus);
		
		LoseTurnCardPlus = new JButton("+");
		LoseTurnCardPlus.setFont(new Font("Arial",Font.PLAIN,15));
		LoseTurnCardPlus.setSize(15, 15);
		LoseTurnCardPlus.setLocation(270-50, 461+70);
		LoseTurnCardPlus.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					control.addLoseTurnActionCard();
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		panel.add(LoseTurnCardPlus);
		
		
		// BOARD MODIFICATION BUTTONS
		
		PlaceTile = new JButton("Place Tile");
		PlaceTile.setFont(new Font("Arial",Font.BOLD,15));
		PlaceTile.setLocation(70-50, 490+80);
		PlaceTile.setSize(90, 40);
		panel.add(PlaceTile);
		
		PlaceActionTile = new JButton("Place Action Tile");
		PlaceActionTile.setFont(new Font("Arial",Font.BOLD,15));
		PlaceActionTile.setLocation(160-50, 490+80);
		PlaceActionTile.setSize(140, 40);
		panel.add(PlaceActionTile);
		
		PlaceHiddenTile = new JButton("Place Hidden Tile");
		PlaceHiddenTile.setFont(new Font("Arial",Font.BOLD,15));
		PlaceHiddenTile.setLocation(300-50, 490+80);
		PlaceHiddenTile.setSize(140, 40);
		panel.add(PlaceHiddenTile);
		
		AddConnection = new JButton("Add Connection");
		AddConnection.setFont(new Font("Arial",Font.BOLD,15));
		AddConnection.setLocation(70-50, 530+80);
		AddConnection.setSize(140, 40);
		panel.add(AddConnection);
		
		RemoveConnection= new JButton("Remove Connection");
		RemoveConnection.setFont(new Font("Arial",Font.BOLD,15));
		RemoveConnection.setLocation(210-50, 530+80);
		RemoveConnection.setSize(180, 40);
		panel.add(RemoveConnection);
		
		RemoveTile= new JButton("Remove Tile");
		RemoveTile.setFont(new Font("Arial",Font.BOLD,15));
		RemoveTile.setLocation(440-50, 490+80);
		RemoveTile.setSize(140, 40);
		panel.add(RemoveTile);
		
		
		
		
		
		Start= new JButton("START");
		Start.setFont(new Font("Arial",Font.BOLD,15));
		Start.setSize(140, 40);
		Start.setLocation(553,632);
		panel.add(Start);
		
		SaveAndExit= new JButton("SAVE & EXIT");
		SaveAndExit.setFont(new Font("Arial",Font.BOLD,15));
		SaveAndExit.setSize(140, 40);
		SaveAndExit.setLocation(553,598);
		panel.add(SaveAndExit);
		
		add(panel);
		
		//Finalize
		setVisible(true);
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
	        //BackGround
	        g.setColor(resource.background);
	        g.fillRect(0, 0, 700, 400);
	        
	        
	        // DRAWING THE TILES
	        for(int x = 0; x < 18; x++){
	        	for(int y = 0; y < 10; y++){
	        		
	        		OuterTile(g,x,y);
	        		if(!uitile[x][y].Visited || (uitile[x][y].type == DesignModeResources.Type.Empty)){
	        			g.setColor(Color.LIGHT_GRAY);
	        			g.fillRect(32 + resource.block_Size*2*x, 47 + resource.block_Size*2*y, resource.inner_Size, resource.inner_Size);
	        		}
	        		else{
	        			//g.setColor(Color.RED);
	        			//g.fillOval(30 + resource.block_Size*2*x, 45 + resource.block_Size*2*y, resource.block_Size, resource.block_Size);
	        		}
	        	}
			}
	        
	        for(UIConnection i: resource.uiconnect){
	        	Connection(g,i.H_V,i.fromx,i.fromy);
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

		private void OuterTile(Graphics g, int x ,int y){
	    	g.setColor(resource.outerTile.get(!(uitile[x][y].type == DesignModeResources.Type.Empty)));
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
	}

}
