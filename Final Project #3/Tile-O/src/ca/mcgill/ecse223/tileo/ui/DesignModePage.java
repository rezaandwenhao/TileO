package ca.mcgill.ecse223.tileo.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.DesignController;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.ActionTile;
import ca.mcgill.ecse223.tileo.model.NormalTile;
import ca.mcgill.ecse223.tileo.ui.DesignModeResources.State;

/* CONTRIBUTOR: Wenhao Geng and Bijan Sadeghi
 * 
 */
public class DesignModePage extends TileOGamePage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -86390520594590777L;
	private TileOGamePage this_page;
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
	private JTextField ExtraTurnCardNumber;
	private JTextField AddConnectionCardNumber;
	private JTextField RemoveConnectionCardNumber;
	private JTextField MovePlayerCardNumber;
	private JTextField LoseTurnCardNumber;
	
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
	private Panel panel;
	private DesignModeResources resource;

	int highlightedTile[];
	boolean highlighted;
	HashMap<State, JButton> state_button;
	@Override
	public void refresh() {
		for(int x =0;x<resource.xTiles;x++){
			for(int y =0;y<resource.yTiles;y++){
				resource.uitile[x][y] = new UITile(DesignModeResources.Type.Empty,0,0,false);
				resource.uitile[x][y].setCurrentPlayer(0);
			}
		}
		resource.uitile = control.updateTiles(resource.uitile);
		
		resource.uiconnect = control.updateConnection();
		
		int cardlist[] = control.updateCards();
		ExtraTurnCardNumber.setText(""+cardlist[0]);
		AddConnectionCardNumber.setText(""+cardlist[1]);
		RemoveConnectionCardNumber.setText(""+cardlist[2]);
		MovePlayerCardNumber.setText(""+cardlist[3]);
		LoseTurnCardNumber.setText(""+cardlist[4]);
		
		if(resource.state != State.AddConnection2 && resource.state != State.RemoveConnection2) highlighted = false;
		Player1.setForeground(Color.BLACK);
		Player2.setForeground(Color.BLACK);
		if(control.hasPlayer(3))Player3.setForeground(Color.BLACK);
		if(control.hasPlayer(4))Player4.setForeground(Color.BLACK);
		PlaceTile.setForeground(Color.BLACK);
		PlaceActionTile.setForeground(Color.BLACK);
		PlaceHiddenTile.setForeground(Color.BLACK);
		AddConnection.setForeground(Color.BLACK);
		RemoveConnection.setForeground(Color.BLACK);
		RemoveTile.setForeground(Color.BLACK);
		if(resource.state != State.Nothing)
			state_button.get(resource.state).setForeground(Color.RED);
		repaint();
	}
	
	private DesignController control;
	@Override
	public void initialize(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(700,700);
		resource = new DesignModeResources();
		control = new DesignController();
		state_button = new HashMap<State, JButton>();
		
		highlightedTile = new int[2];
		highlighted = false;
		resource.uitile = new UITile[18][10];
		for(int x =0;x<resource.xTiles;x++){
			for(int y =0;y<resource.yTiles;y++){
				resource.uitile[x][y] = new UITile(DesignModeResources.Type.Empty,0,0,false);
			}
		}
		
		panel = new Panel();
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
				if(!(e.getX()> 659 || e.getY() > 386 || e.getX() < 30 || e.getY() < 45)){
				
				//Clicked on Block. Because of time constraint, we use switch instead of polymorphism
				if((e.getX()-30)%(resource.block_Size*2) < resource.block_Size && (e.getY()-45)%(resource.block_Size*2) < resource.block_Size){
					int x = (e.getX()-30)/(resource.block_Size*2);
					int y = (e.getY()-45)/(resource.block_Size*2);
					System.out.printf("%d %d\n", x, y);
					switch(resource.state){
					case AddConnection1:
						resource.state = State.AddConnection2;
						highlightedTile[0] = x; highlightedTile[1] = y;
						highlighted = true;
						break;
						
					case AddConnection2:
						resource.state = State.AddConnection1;
						try {
							control.putConnection(highlightedTile[0], highlightedTile[1], x, y);
						} catch (InvalidInputException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;	
						
					case Nothing:
						break;
						
					case PlaceActionTile:
						
						break;
					case PlaceTile:
						try {
							control.placeNormalTile(x, y);
						} catch (InvalidInputException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					case PlaceWinTile:
						try {
							control.setLocationHiddenTile(x, y);
						} catch (InvalidInputException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					case Player1:
						try {
							control.setStartTile(x, y, 0);
						} catch (InvalidInputException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						break;
					case Player2:
						try {
							control.setStartTile(x, y, 1);
						} catch (InvalidInputException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						break;
					case Player3:
						try {
							control.setStartTile(x, y, 2);
						} catch (InvalidInputException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						break;
					case Player4:
						try {
							control.setStartTile(x, y, 3);
						} catch (InvalidInputException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						break;
					case RemoveConnection1:
						resource.state = State.RemoveConnection2;
						highlightedTile[0] = x; highlightedTile[1] = y;
						highlighted = true;
						break;
					case RemoveConnection2:
						resource.state = State.RemoveConnection1;
						control.deleteConnection(highlightedTile[0], highlightedTile[1], x, y);
						break;
					case RemoveTile:
						try {
							control.removeTile(x, y);
						} catch (InvalidInputException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					default:
						break;
						
					}
				}
				refresh();
			}
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
		DesignMode.setFont(resource.DesignModeFont);
		DesignMode.setSize(200, 30);
		DesignMode.setLocation(270, 15);
		panel.add(DesignMode);
		
		// ACTION CARDS SECTION
		
		ActionCards = new JLabel();
		ActionCards.setText("<html><u>Action Cards</u></html>");
		ActionCards.setFont(resource.ActionCardsFont);
		ActionCards.setSize(150, 30);
	    ActionCards.setLocation(80-50, 345+70);
		panel.add(ActionCards);
		
		ExtraTurnCard = new JLabel("Exra Turn", SwingConstants.RIGHT);
		ExtraTurnCard.setFont(resource.ActionFont);
		ExtraTurnCard.setSize(140, 30);
		ExtraTurnCard.setLocation(80-50, 370+70);
		panel.add(ExtraTurnCard);
		
		AddConnectionCard = new JLabel("Add Connection", SwingConstants.RIGHT);
		AddConnectionCard.setFont(resource.ActionFont);
		AddConnectionCard.setSize(140, 30);
		AddConnectionCard.setLocation(80-50, 390+71);
		panel.add(AddConnectionCard);
		
		RemoveConnectionCard = new JLabel("Remove Connection", SwingConstants.RIGHT);
		RemoveConnectionCard.setFont(resource.ActionFont);
		RemoveConnectionCard.setSize(140, 30);
		RemoveConnectionCard.setLocation(80-50, 410+72);
		panel.add(RemoveConnectionCard);
		
		MovePlayerCard = new JLabel("Move Player", SwingConstants.RIGHT);
		MovePlayerCard.setFont(resource.ActionFont);
		MovePlayerCard.setSize(140, 30);
		MovePlayerCard.setLocation(80-50, 430+73);
		panel.add(MovePlayerCard);
		
		LoseTurnCard = new JLabel("Lose Turn", SwingConstants.RIGHT);
		LoseTurnCard.setFont(resource.ActionFont);
		LoseTurnCard.setSize(140, 30);
		LoseTurnCard.setLocation(80-50, 450+74);
		panel.add(LoseTurnCard);
		
// ACTION CARDS MINUS BUTTONS
		
		ExtraTurnCardMinus = new JButton("-");
		ExtraTurnCardMinus.setFont(resource.PlusMinusFont);
		ExtraTurnCardMinus.setMargin(new Insets(0,0,0,0));
		ExtraTurnCardMinus.setSize(15, 15);
		ExtraTurnCardMinus.setLocation(230-50, 377+70);
		
		ExtraTurnCardMinus.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					control.removeRollDieActionCard();
					refresh();
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}			
		});
		
		panel.add(ExtraTurnCardMinus);
		
		AddConnectionCardMinus = new JButton("-");
		AddConnectionCardMinus.setFont(resource.PlusMinusFont);
		AddConnectionCardMinus.setSize(15, 15);
		AddConnectionCardMinus.setMargin(new Insets(0,0,0,0));
		ExtraTurnCardMinus.setMargin(new Insets(0,0,0,0));
		AddConnectionCardMinus.setLocation(230-50, 398+70);
		AddConnectionCardMinus.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					control.removeConnectTilesActionCard();
					refresh();
			} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		panel.add(AddConnectionCardMinus);
		
		RemoveConnectionCardMinus = new JButton("-");
		RemoveConnectionCardMinus.setFont(resource.PlusMinusFont);
		RemoveConnectionCardMinus.setSize(15, 15);
		RemoveConnectionCardMinus.setMargin(new Insets(0,0,0,0));
		RemoveConnectionCardMinus.setLocation(230-50, 419+70);
		RemoveConnectionCardMinus.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					control.removeRemoveConnectionActionCard();
					refresh();
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		panel.add(RemoveConnectionCardMinus);
		
		MovePlayerCardMinus = new JButton("-");
		MovePlayerCardMinus.setFont(resource.PlusMinusFont);
		MovePlayerCardMinus.setSize(15, 15);
		MovePlayerCardMinus.setMargin(new Insets(0,0,0,0));
		MovePlayerCardMinus.setLocation(230-50, 440+70);
		MovePlayerCardMinus.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					control.removeTeleportActionCard();
					refresh();
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});	
		panel.add(MovePlayerCardMinus);
		
		LoseTurnCardMinus = new JButton("-");
		LoseTurnCardMinus.setFont(resource.PlusMinusFont);
		LoseTurnCardMinus.setSize(15, 15);
		LoseTurnCardMinus.setMargin(new Insets(0,0,0,0));
		LoseTurnCardMinus.setLocation(230-50, 461+70);
		LoseTurnCardMinus.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					control.removeLoseTurnActionCard();
					refresh();
			} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		panel.add(LoseTurnCardMinus);
		
		// ACTION CARDS PLUS BUTTONS
		
		ExtraTurnCardPlus = new JButton("+");
		ExtraTurnCardPlus.setFont(resource.PlusMinusFont);
		ExtraTurnCardPlus.setSize(15, 15);
		ExtraTurnCardPlus.setMargin(new Insets(0,0,0,0));
		ExtraTurnCardPlus.setLocation(270-50, 377+70);
		ExtraTurnCardPlus.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					control.addRollDieActionCard();
					refresh();
			} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		panel.add(ExtraTurnCardPlus);
		
		AddConnectionCardPlus = new JButton("+");
		AddConnectionCardPlus.setFont(resource.PlusMinusFont);
		AddConnectionCardPlus.setSize(15, 15);
		AddConnectionCardPlus.setMargin(new Insets(0,0,0,0));
		AddConnectionCardPlus.setLocation(270-50, 398+70);
		AddConnectionCardPlus.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					control.addConnectTilesActionCard();
					refresh();
			} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		panel.add(AddConnectionCardPlus);
		
		RemoveConnectionCardPlus = new JButton("+");
		RemoveConnectionCardPlus.setFont(resource.PlusMinusFont);
		RemoveConnectionCardPlus.setSize(15, 15);
		RemoveConnectionCardPlus.setMargin(new Insets(0,0,0,0));
		RemoveConnectionCardPlus.setLocation(270-50, 419+70);
		RemoveConnectionCardPlus.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					control.addRemoveConnectionActionCard();
					refresh();
			} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		panel.add(RemoveConnectionCardPlus);
		
		MovePlayerCardPlus = new JButton("+");
		MovePlayerCardPlus.setFont(resource.PlusMinusFont);
		MovePlayerCardPlus.setSize(15, 15);
		MovePlayerCardPlus.setMargin(new Insets(0,0,0,0));
		MovePlayerCardPlus.setLocation(270-50, 440+70);
		MovePlayerCardPlus.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					control.addTeleportActionCard();
					refresh();
			} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		panel.add(MovePlayerCardPlus);
		
		LoseTurnCardPlus = new JButton("+");
		LoseTurnCardPlus.setFont(resource.PlusMinusFont);
		LoseTurnCardPlus.setMargin(new Insets(0,0,0,0));
		LoseTurnCardPlus.setSize(15, 15);
		LoseTurnCardPlus.setLocation(270-50, 461+70);
		LoseTurnCardPlus.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					control.addLoseTurnActionCard();
					refresh();
			} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		panel.add(LoseTurnCardPlus);
		
		//ACTION CARD NUMBER
		ExtraTurnCardNumber = new JTextField(resource.ExtraTurnText);
		ExtraTurnCardNumber.setFont(resource.ActionCardsFont);
		ExtraTurnCardNumber.setSize(25, 20);
		ExtraTurnCardNumber.setEditable(false);
		ExtraTurnCardNumber.setLocation(245-50, 375+70);
		panel.add(ExtraTurnCardNumber);
		
		AddConnectionCardNumber = new JTextField(resource.AddConnectionText);
		AddConnectionCardNumber.setFont(resource.ActionCardsFont);
		AddConnectionCardNumber.setSize(25, 20);
		AddConnectionCardNumber.setEditable(false);
		AddConnectionCardNumber.setLocation(245-50, 396+70);
		panel.add(AddConnectionCardNumber);
		
		RemoveConnectionCardNumber = new JTextField(resource.RemoveConnectionText);
		RemoveConnectionCardNumber.setFont(resource.ActionCardsFont);
		RemoveConnectionCardNumber.setSize(25, 20);
		RemoveConnectionCardNumber.setEditable(false);
		RemoveConnectionCardNumber.setLocation(245-50, 417+70);
		panel.add(RemoveConnectionCardNumber);
		
		MovePlayerCardNumber = new JTextField(resource.MovePlayerText);
		MovePlayerCardNumber.setFont(resource.ActionCardsFont);
		MovePlayerCardNumber.setSize(25, 20);
		MovePlayerCardNumber.setEditable(false);
		MovePlayerCardNumber.setLocation(245-50, 438+70);
		panel.add(MovePlayerCardNumber);
		
		LoseTurnCardNumber = new JTextField(resource.LoseTurnText);
		LoseTurnCardNumber.setFont(resource.ActionCardsFont);
		LoseTurnCardNumber.setSize(25, 20);
		LoseTurnCardNumber.setEditable(false);
		LoseTurnCardNumber.setLocation(245-50, 459+70);
		panel.add(LoseTurnCardNumber);
		
		//PLAYER CONTROL
		
		Players = new JLabel();
		Players.setText("<html><u>Players:</u></html>");
		Players.setFont(resource.ActionCardsFont);
		Players.setSize(150, 30);
	    Players.setLocation(380-50, 345+70);
		panel.add(Players);
		
		Player1 = new JButton("Player1");
		Player1.setFont(resource.ActionCardsFont);
		Player1.setIcon(new ImageIcon("Tile-O/src/Image/BoxPlayer1.png"));
		Player1.setMargin(new Insets(0,0,0,0));
		Player1.setLocation(380-50, 370+80);
		Player1.setSize(120, 40);
		Player1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(resource.state == State.Player1) resource.state = State.Nothing;
				else resource.state = State.Player1;
				refresh();
			}
			
		});
		panel.add(Player1);
		
		Player2 = new JButton("Player2");
		Player2.setFont(resource.ActionCardsFont);
		Player2.setIcon(new ImageIcon("Tile-O/src/Image/BoxPlayer2.png"));
		Player2.setMargin(new Insets(0,0,0,0));
		Player2.setLocation(500-50, 370+80);
		Player2.setSize(120, 40);
		Player2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(resource.state == State.Player2) resource.state = State.Nothing;
				else resource.state = State.Player2;
				refresh();
			}
			
		});
		panel.add(Player2);
		
		if(control.hasPlayer(3)){
			Player3 = new JButton("Player3");
			Player3.setFont(resource.ActionCardsFont);
			Player3.setIcon(new ImageIcon("Tile-O/src/Image/BoxPlayer3.png"));
			Player3.setMargin(new Insets(0,0,0,0));
			Player3.setLocation(380-50, 410+80);
			Player3.setSize(120, 40);
			Player3.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(resource.state == State.Player3) resource.state = State.Nothing;
					else resource.state = State.Player3;
					refresh();
				}
				
			});
			panel.add(Player3);
		}
		
		if(control.hasPlayer(4)){
			Player4 = new JButton("Player4");
			Player4.setFont(resource.ActionCardsFont);
			Player4.setIcon(new ImageIcon("Tile-O/src/Image/BoxPlayer4.png"));
			Player4.setMargin(new Insets(0,0,0,0));
			Player4.setLocation(500-50, 410+80);
			Player4.setSize(120, 40);
			Player4.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(resource.state == State.Player4) resource.state = State.Nothing;
					else resource.state = State.Player4;
					refresh();
				}
				
			});
			panel.add(Player4);
		}
		/*
		private JLabel Players;
		private JButton Player1;
		private JButton Player2;
		private JButton Player3;
		private JButton Player4;
		*/
		
		// BOARD MODIFICATION BUTTONS\
		PlaceTile = new JButton("Place Tile");
		PlaceTile.setFont(new Font("Arial",Font.BOLD,15));
		PlaceTile.setMargin(new Insets(0,0,0,0));
		PlaceTile.setLocation(70-50, 490+80);
		PlaceTile.setSize(90, 40);
		PlaceTile.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(resource.state == State.PlaceTile) resource.state = State.Nothing;
				else resource.state = State.PlaceTile;
				refresh();
			}
			
		});
		panel.add(PlaceTile);
		
		
		PlaceActionTile = new JButton("Place Action Tile");
		PlaceActionTile.setFont(new Font("Arial",Font.BOLD,15));
		PlaceActionTile.setLocation(160-50, 490+80);
		PlaceActionTile.setMargin(new Insets(0,0,0,0));
		PlaceActionTile.setSize(140, 40);
		PlaceActionTile.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(resource.state == State.PlaceActionTile) resource.state = State.Nothing;
				else resource.state = State.PlaceActionTile;
				refresh();
			}
			
		});
		panel.add(PlaceActionTile);
		
		PlaceHiddenTile = new JButton("Place Hidden Tile");
		PlaceHiddenTile.setFont(new Font("Arial",Font.BOLD,15));
		PlaceHiddenTile.setLocation(300-50, 490+80);
		PlaceHiddenTile.setMargin(new Insets(0,0,0,0));
		PlaceHiddenTile.setSize(140, 40);
		PlaceHiddenTile.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(resource.state == State.PlaceWinTile) resource.state = State.Nothing;
				else resource.state = State.PlaceWinTile;
				refresh();
			}
			
		});
		panel.add(PlaceHiddenTile);
		
		AddConnection = new JButton("Add Connection");
		AddConnection.setFont(new Font("Arial",Font.BOLD,15));
		AddConnection.setLocation(70-50, 530+80);
		AddConnection.setMargin(new Insets(0,0,0,0));
		AddConnection.setSize(140, 40);
		AddConnection.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(resource.state == State.AddConnection1) resource.state = State.Nothing;
				else resource.state = State.AddConnection1;
				refresh();
			}
			
		});
		panel.add(AddConnection);
		
		RemoveConnection= new JButton("Remove Connection");
		RemoveConnection.setFont(new Font("Arial",Font.BOLD,15));
		RemoveConnection.setLocation(210-50, 530+80);
		RemoveConnection.setMargin(new Insets(0,0,0,0));
		RemoveConnection.setSize(180, 40);
		RemoveConnection.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(resource.state == State.RemoveConnection1) resource.state = State.Nothing;
				else resource.state = State.RemoveConnection1;
				refresh();
			}
			
		});
		panel.add(RemoveConnection);
		
		RemoveTile= new JButton("Remove Tile");
		RemoveTile.setFont(new Font("Arial",Font.BOLD,15));
		RemoveTile.setLocation(440-50, 490+80);
		RemoveTile.setMargin(new Insets(0,0,0,0));
		RemoveTile.setSize(140, 40);
		RemoveTile.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(resource.state == State.RemoveTile) resource.state = State.Nothing;
				else resource.state = State.RemoveTile;
				refresh();
			}
			
		});
		panel.add(RemoveTile);
		
		state_button.put(State.PlaceTile, PlaceTile);
		state_button.put(State.PlaceWinTile, PlaceHiddenTile);
		state_button.put(State.PlaceActionTile, PlaceActionTile);
		state_button.put(State.AddConnection1, AddConnection);
		state_button.put(State.AddConnection2, AddConnection);
		state_button.put(State.RemoveConnection1, RemoveConnection);
		state_button.put(State.RemoveConnection2, RemoveConnection);
		state_button.put(State.RemoveTile, RemoveTile);
		state_button.put(State.Player1, Player1);
		state_button.put(State.Player2, Player2);
		state_button.put(State.Player3, Player3);
		state_button.put(State.Player4, Player4);
		
		Start= new JButton("START");
		Start.setFont(new Font("Arial",Font.BOLD,15));
		Start.setSize(140, 40);
		Start.setLocation(545,622);
		panel.add(Start);
		
		SaveAndExit= new JButton("SAVE & EXIT");
		SaveAndExit.setFont(new Font("Arial",Font.BOLD,15));
		SaveAndExit.setSize(140, 40);
		SaveAndExit.setLocation(545,582);
		SaveAndExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				control.SaveandExitGame(this_page);
			}
		});
		panel.add(SaveAndExit);
		
		add(panel);
		
		new ActionTile(1, 3, TileOApplication.getCurrentGame(), 3);
		//Finalize
		setVisible(true);
		
		refresh();
	}
	
	class Panel extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 6923187654761568643L;

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
	        
	        if(highlighted) Highlight(g,highlightedTile[0],highlightedTile[1]);
	        for(UIConnection i: resource.uiconnect){
	        	Connection(g,i.isH_V(),i.getFromx(),i.getFromy());
	        }
	        g.setColor(Color.BLACK);
	        
	        // OUTLINE FOR SAVE SECTION
	        /*g.drawRect(553,598, 140, 80);
	        g.drawRect(554,599, 140, 80);
	        g.drawRect(555,600, 140, 80);
	        */
	        // LINE IN MIDDLE
	        g.fillRect(0, 390, 700, 10);
	        
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
