import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class help extends JFrame implements ActionListener{
	//properties
	Timer timer = new Timer(1000/60, this);
	JButton hit = new JButton("Hit");
	JButton stay = new JButton("Stay");
	JButton nextPage = new JButton("Next page");
	
	//methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == timer){
			this.repaint();
		}else if(evt.getSource() == nextPage){
			
		}else if(evt.getSource() == hit){
			
		}else if(evt.getSource() == stay){
			
		}
		
	}
	
	public void paintComponent(Graphics g){
		
		
	}
	
	//constructor
	help(){
		super();
		setPreferredSize(new Dimension (1280, 720));
		setLayout(null);
		
		
		timer.start();
	}


}
