import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class blackjackmainpanel extends JPanel implements ActionListener{
	//Properties
	Timer thetimer = new Timer(1000/60, this);
	BufferedImage theimg = null;
	
	//Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == thetimer){
			this.repaint();
		}
	}
	
	//Override
	public void paintComponent(Graphics g){
		g.drawImage(theimg, 0, 0, null);
		
	}
	
	//Constructor
	public blackjackmainpanel(){
		super();
		try{
			theimg = ImageIO.read(new File("Background.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}
		this.setPreferredSize(new Dimension(1280, 720));
		this.setLayout(null);
		this.thetimer.start();
	}
}
	
