import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class blackjackstartpanel extends JPanel implements ActionListener{
	//Properties
	Timer thetimer = new Timer(1000/60, this);
	BufferedImage theimg = null;
	Boolean blnserver = false;
	int intclientnumber = 0;
	int intxoffset;
	int intyoffset;
	//Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == thetimer){
			this.repaint();
		}
	}
	
	//Override
	public void paintComponent(Graphics g){
		g.drawImage(theimg, 0, 0, null);
		if(intclientnumber == 1){
			intxoffset = 0;
			intyoffset = 0;
		}
		if(intclientnumber == 2){
			intxoffset = 0;
			intyoffset = 0;
		}
	}
	
	//Constructor
	public blackjackstartpanel(){
		super();
		try{
			theimg = ImageIO.read(new File("blackjackstart.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}
		this.setPreferredSize(new Dimension(1280, 720));
		this.setLayout(null);
		this.thetimer.start();
	}
}
	
