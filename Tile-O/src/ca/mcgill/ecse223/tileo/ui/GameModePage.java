package ca.mcgill.ecse223.tileo.ui;

import javax.swing.*;
import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import ca.mcgill.ecse223.tileo.ui.DesignModePage.Panel;



public class GameModePage extends TileOGamePage {
	private JLabel Title;
	
	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		Panel something = new Panel();
		add(something);
		setSize(700,700);
		setVisible(true);
		Title = new JLabel("Play Mode");
		something.setLayout(null);
		something.add(Title);
		something.setLocation(330, 100);
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
