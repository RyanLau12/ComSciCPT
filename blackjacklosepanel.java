import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class blackjacklosepanel extends JPanel implements ActionListener{
	//Properties
	Timer thetimer = new Timer(1000/60, this);
	JLabel label = new JLabel("YOU LOSE!");
	
	//Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == thetimer){
			this.repaint();
		}
	}
	
	//Override
	public void paintComponent(Graphics g){
		g.fillRect(0, 0, 1280, 720);
	}
	
	//Constructor
	public blackjacklosepanel(){
		super();
		
		label.setSize(200, 60);
		label.setLocation(400, 300);
		this.add(label);
		this.setPreferredSize(new Dimension(1280, 720));
		this.setLayout(null);
		this.thetimer.start();
	}
}
	
