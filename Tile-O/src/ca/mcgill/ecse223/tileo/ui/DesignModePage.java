package ca.mcgill.ecse223.tileo.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.font.TextAttribute;

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
	
	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initialize() {
		Border something = BorderFactory.createLineBorder(Color.BLACK, 10, false);
		Panel panel = new Panel();
		panel.setLayout(null);
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
