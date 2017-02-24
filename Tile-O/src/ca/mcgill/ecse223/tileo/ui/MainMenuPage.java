package ca.mcgill.ecse223.tileo.ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

public class MainMenuPage extends TileOGamePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel page;
	
	private JLabel Title;
	private JFrame this_page;
	private JPanel bottom;
		private JLabel DesignMode;
		private JLabel PlayMode;
		private JButton NewDesign;
		private JPanel PlayersNumber;
			private JLabel PlayersNumber_Text;
			private JTextField Enter_Number;
		private JButton LoadDesign;
		private JButton NewGame;
		private JButton LoadGame;
	
	private JFileChooser run;
	@Override
	public void refresh() {
		//No need to refresh in title page
	}

	@Override
	public void initialize() {
		page = new JPanel();
		page.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		page.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		add(page);
		
		c.gridx=0;c.gridy=0;
		c.weightx=0.0;c.weighty=2.0;
		c.gridwidth=1;
		c.fill = GridBagConstraints.BOTH;
		
		Title = new JLabel("Tile O",SwingConstants.CENTER);
		Title.setFont(new Font("Arial",Font.ITALIC,108));
		Title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		page.add(Title,c);
		
		c.gridx=0;c.gridy=1;
		c.weightx=0.0;c.weighty=1.0;
		c.gridwidth=1;
		bottom = new JPanel();
		bottom.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		bottom.setLayout(new GridBagLayout());
		page.add(bottom,c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth=2;
		c.gridx=0;c.gridy=0;
		c.weightx=0.0;c.weighty=1.5;
		DesignMode = new JLabel("Design Mode");
		DesignMode.setFont(new Font("Arial",Font.BOLD,48));
		bottom.add(DesignMode,c);
		
		c.gridwidth=1;
		c.gridx=0;c.gridy=1;
		c.weightx=1.0;c.weighty=1.5;
		NewDesign = new JButton("New Design");
		NewDesign.setFont(new Font("Arial",Font.BOLD,32));
		NewDesign.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		bottom.add(NewDesign,c);
		
		
		c.gridx=1;c.gridy=1;
		c.weightx=1.0;c.weighty=1.5;
		LoadDesign = new JButton("Load Design");
		LoadDesign.setFont(new Font("Arial",Font.BOLD,32));
		LoadDesign.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				run = new JFileChooser();
				int check = run.showOpenDialog(this_page);
				
			}
			
		});
		bottom.add(LoadDesign,c);
		
		c.gridwidth=2;
		c.gridx=0;c.gridy=2;
		c.weightx=0.0;c.weighty=1.5;
		PlayMode = new JLabel("Play Mode");
		PlayMode.setFont(new Font("Arial",Font.BOLD,48));
		bottom.add(PlayMode,c);
		
		c.gridwidth=2;
		c.gridx=0;c.gridy=3;
		c.weightx=0.0;c.weighty=0.5;
		c.fill = GridBagConstraints.BOTH;
		PlayersNumber = new JPanel();
		bottom.add(PlayersNumber,c);
		PlayersNumber_Text=new JLabel("Number of Players:");
		PlayersNumber_Text.setFont(new Font("Arial",Font.BOLD,32));
		PlayersNumber.add(PlayersNumber_Text);
		Enter_Number=new JTextField("4");
		Enter_Number.setEditable(true);
		Enter_Number.setFont(new Font("Arial",Font.BOLD,32));
		PlayersNumber.add(Enter_Number);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth=1;
		c.gridx=0;c.gridy=4;
		c.weightx=1.0;c.weighty=1.5;
		NewGame = new JButton("New Game");
		NewGame.setFont(new Font("Arial",Font.BOLD,32));
		NewGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		bottom.add(NewGame,c);
		
		c.gridx=1;c.gridy=4;
		c.weightx=1.0;c.weighty=1.5;
		LoadGame = new JButton("Load Game");
		LoadGame.setFont(new Font("Arial",Font.BOLD,32));
		LoadGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		bottom.add(LoadGame,c);
		
		
	}

}
