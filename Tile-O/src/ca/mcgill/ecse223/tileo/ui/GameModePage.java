package ca.mcgill.ecse223.tileo.ui;

import javax.swing.*;
import java.awt.*;

import javax.swing.border.Border;

import ca.mcgill.ecse223.tileo.ui.DesignModePage.Panel;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import ca.mcgill.ecse223.tileo.controller.TileoController;


public class GameModePage extends TileOGamePage {
	private JLabel Title;
	
	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		Panel panel = new Panel();
		panel.setLayout(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
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
		playerTurn.setText("<html><u>Action Cards</u></html>");
		playerTurn.setFont(new Font("Arial",Font.BOLD,20));
		playerTurn.setSize(150, 30);
	        playerTurn.setLocation(80-50, 345+70);
		panel.add(playerTurn);
		
		// elements for spare connections:
		numSpareConnection = new JLabel();
		numSpareConnection.setText("<html><u>Players</u></html>");
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
		tileType = new JLabel();
		
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
		
		add(panel);
	}
	
	private void rollDiceButtonActionPerformed(java.awt.event.ActionEvent evt) {
	}
	
	private void drawCardButtonActionPerformed(java.awt.event.ActionEvent evt) {
	}
	
	private void saveExistButtonActionPerformed(java.awt.event.ActionEvent evt) {
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
	        for(int x = 0; x <= 19; x++){
	        	for(int y = 0; y <= 10; y++){
	        		g.setColor(Color.LIGHT_GRAY);
	        		g.fillRect(20 + 33*x, 40 + 33*y, 20, 20);
	        	}
			}
	    }  
	}
}
