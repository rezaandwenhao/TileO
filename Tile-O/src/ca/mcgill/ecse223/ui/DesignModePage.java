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
		panel.add(ExtraTurnCardMinus);
		
		AddConnectionCardMinus = new JButton("-");
		AddConnectionCardMinus.setFont(new Font("Arial",Font.PLAIN,15));
		AddConnectionCardMinus.setSize(15, 15);
		AddConnectionCardMinus.setLocation(230-50, 398+70);
		panel.add(AddConnectionCardMinus);
		
		RemoveConnectionCardMinus = new JButton("-");
		RemoveConnectionCardMinus.setFont(new Font("Arial",Font.PLAIN,15));
		RemoveConnectionCardMinus.setSize(15, 15);
		RemoveConnectionCardMinus.setLocation(230-50, 419+70);
		panel.add(RemoveConnectionCardMinus);
		
		MovePlayerCardMinus = new JButton("-");
		MovePlayerCardMinus.setFont(new Font("Arial",Font.PLAIN,15));
		MovePlayerCardMinus.setSize(15, 15);
		MovePlayerCardMinus.setLocation(230-50, 440+70);
		panel.add(MovePlayerCardMinus);
		
		LoseTurnCardMinus = new JButton("-");
		LoseTurnCardMinus.setFont(new Font("Arial",Font.PLAIN,15));
		LoseTurnCardMinus.setSize(15, 15);
		LoseTurnCardMinus.setLocation(230-50, 461+70);
		panel.add(LoseTurnCardMinus);
		
		// ACTION CARDS PLUS BUTTONS
		
		ExtraTurnCardPlus = new JButton("+");
		ExtraTurnCardPlus.setFont(new Font("Arial",Font.PLAIN,15));
		ExtraTurnCardPlus.setSize(15, 15);
		ExtraTurnCardPlus.setLocation(270-50, 377+70);
		panel.add(ExtraTurnCardPlus);
		
		AddConnectionCardPlus = new JButton("+");
		AddConnectionCardPlus.setFont(new Font("Arial",Font.PLAIN,15));
		AddConnectionCardPlus.setSize(15, 15);
		AddConnectionCardPlus.setLocation(270-50, 398+70);
		panel.add(AddConnectionCardPlus);
		
		RemoveConnectionCardPlus = new JButton("+");
		RemoveConnectionCardPlus.setFont(new Font("Arial",Font.PLAIN,15));
		RemoveConnectionCardPlus.setSize(15, 15);
		RemoveConnectionCardPlus.setLocation(270-50, 419+70);
		panel.add(RemoveConnectionCardPlus);
		
		MovePlayerCardPlus = new JButton("+");
		MovePlayerCardPlus.setFont(new Font("Arial",Font.PLAIN,15));
		MovePlayerCardPlus.setSize(15, 15);
		MovePlayerCardPlus.setLocation(270-50, 440+70);
		panel.add(MovePlayerCardPlus);
		
		LoseTurnCardPlus = new JButton("+");
		LoseTurnCardPlus.setFont(new Font("Arial",Font.PLAIN,15));
		LoseTurnCardPlus.setSize(15, 15);
		LoseTurnCardPlus.setLocation(270-50, 461+70);
		panel.add(LoseTurnCardPlus);
		
		// ACTION CARDS LABELS
		
		ExtraTurnCardNumber = new JLabel("7");
		ExtraTurnCardNumber.setFont(new Font("Arial",Font.PLAIN,15));
		ExtraTurnCardNumber.setSize(25, 15);
		ExtraTurnCardNumber.setLocation(252-50, 377+70);
		panel.add(ExtraTurnCardNumber);
		
		AddConnectionCardNumber = new JLabel("7");
		AddConnectionCardNumber.setFont(new Font("Arial",Font.PLAIN,15));
		AddConnectionCardNumber.setSize(25, 15);
		AddConnectionCardNumber.setLocation(252-50, 398+70);
		panel.add(AddConnectionCardNumber);
		
		RemoveConnectionCardNumber = new JLabel("6");
		RemoveConnectionCardNumber.setFont(new Font("Arial",Font.PLAIN,15));
		RemoveConnectionCardNumber.setSize(25, 15);
		RemoveConnectionCardNumber.setLocation(252-50, 419+70);
		panel.add(RemoveConnectionCardNumber);
		
		MovePlayerCardNumber = new JLabel("6");
		MovePlayerCardNumber.setFont(new Font("Arial",Font.PLAIN,15));
		MovePlayerCardNumber.setSize(25, 15);
		MovePlayerCardNumber.setLocation(252-50, 440+70);
		panel.add(MovePlayerCardNumber);
		
		LoseTurnCardNumber = new JLabel("6");
		LoseTurnCardNumber.setFont(new Font("Arial",Font.PLAIN,15));
		LoseTurnCardNumber.setSize(25, 15);
		LoseTurnCardNumber.setLocation(252-50, 461+70);
		panel.add(LoseTurnCardNumber);
		
		// PLAYERS SECTION
		
		Players = new JLabel();
		Players.setText("<html><u>Players</u></html>");
		Players.setFont(new Font("Arial",Font.BOLD,20));
		Players.setSize(150, 30);
		Players.setLocation(325-20, 345+70);
		panel.add(Players);
		
		Player1 = new JButton("Player 1");
		Player1.setFont(new Font("Arial",Font.PLAIN,15));
		Player1.setSize(160, 55);
	    Player1.setLocation(325-20, 375+70);
		panel.add(Player1);
		
		Player2 = new JButton("Player 2");
		Player2.setFont(new Font("Arial",Font.PLAIN,15));
		Player2.setSize(160, 55);
		Player2.setLocation(485-20, 375+70);
		panel.add(Player2);
		
		Player3 = new JButton("Player 3");
		Player3.setFont(new Font("Arial",Font.PLAIN,15));
		Player3.setSize(160, 55);
		Player3.setLocation(325-20, 430+70);
		panel.add(Player3);
		
		Player4 = new JButton("Player 4");
		Player4.setFont(new Font("Arial",Font.PLAIN,15));
		Player4.setSize(160, 55);
		Player4.setLocation(485-20, 430+70);
		panel.add(Player4);
		
		
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
