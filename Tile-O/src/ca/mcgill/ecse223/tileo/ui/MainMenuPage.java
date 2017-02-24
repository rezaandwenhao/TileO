package ca.mcgill.ecse223.tileo.ui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

public class MainMenuPage extends TileOGamePage {

	private JLabel Title;
	private JLabel DesignMode;
	private JLabel PlayMode;
	private JButton NewDesign;
	private JButton LoadDesign;
	private JButton NewGame;
	private JButton LoadGame;
	
	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initialize() {
		Border something = BorderFactory.createLineBorder(Color.BLACK, 10, false);
		JPanel panel = new JPanel();
		panel.setBorder(something);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx=0;c.gridy=0;
		c.weightx=0.0;c.weighty=12.0;
		c.gridwidth=2;
		Title = new JLabel("Tile O");
		Title.setFont(new Font("Arial",Font.ITALIC,108));
		Title.setBorder(something);
		panel.add(Title,c);
		
		c.gridwidth=2;
		c.gridx=0;c.gridy=1;
		c.weightx=0.0;c.weighty=1.5;
		DesignMode = new JLabel("Design Mode");
		DesignMode.setFont(new Font("Arial",Font.BOLD,48));
		panel.add(DesignMode,c);
		
		c.gridwidth=1;
		c.gridx=0;c.gridy=2;
		c.weightx=1.0;c.weighty=1.5;
		NewDesign = new JButton("New Design");
		NewDesign.setFont(new Font("Arial",Font.BOLD,32));
		panel.add(NewDesign,c);
		
		c.gridx=1;c.gridy=2;
		c.weightx=1.0;c.weighty=1.5;
		LoadDesign = new JButton("Load Design");
		LoadDesign.setFont(new Font("Arial",Font.BOLD,32));
		panel.add(LoadDesign,c);
		
		c.gridwidth=2;
		c.gridx=0;c.gridy=3;
		c.weightx=0.0;c.weighty=1.5;
		PlayMode = new JLabel("Play Mode");
		PlayMode.setFont(new Font("Arial",Font.BOLD,48));
		panel.add(PlayMode,c);
		
		c.gridwidth=1;
		c.gridx=0;c.gridy=4;
		c.weightx=1.0;c.weighty=1.5;
		NewGame = new JButton("New Game");
		NewGame.setFont(new Font("Arial",Font.BOLD,32));
		panel.add(NewGame,c);
		
		c.gridx=1;c.gridy=4;
		c.weightx=1.0;c.weighty=1.5;
		LoadGame = new JButton("Load Game");
		LoadGame.setFont(new Font("Arial",Font.BOLD,32));
		panel.add(LoadGame,c);
		
		add(panel);
	}

}