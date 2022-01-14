import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class help extends JPanel implements ActionListener{
	//properties
	Timer timer = new Timer(1000/60, this);
	JButton hit = new JButton("Hit");
	JButton stay = new JButton("Stay");
	JButton nextPage = new JButton("Next page");
	JButton lastPage = new JButton("Previous page");
	JButton exitHelp = new JButton("ExitHelp");
	JLabel title = new JLabel("How to play");
	JTextField text1 = new JTextField("You start the game with 2 cards. All cards have the value of their faces, King, Queen, Jack, are valued at 11. You want to get as close as possible to the sum of 21 as possible. Aces count as 11 unless you are going to bust in which case they count as 1. If you go over 21 you bust.");
	int intpage = 0;
	int intcard = 2;
	int intcardvalue = 15;
	boolean blnstay = false;
	//methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == timer){
			this.repaint();
		}
		else if(evt.getSource() == nextPage){
			intpage = intpage + 1;
		}
		else if(evt.getSource() == lastPage){	
			intpage = intpage + 1;
		}
		else if(evt.getSource() == hit){
			intcard = intcard + 1;
		}
		else if(evt.getSource() == stay){
			blnstay = true;
		}
	}	
	public void paintComponent(Graphics g){
		
		if(intpage == 0){
		}
		else if(intpage == 1){
		}
		else if(intpage == 2){
		}
		
		if(blnstay == true){ 
			if(intcardvalue > 21){
			
			}
			else if(intcardvalue < 21){
				
			}
		}
	}
	
	//constructor
	help(){
		super();
		setPreferredSize(new Dimension (1280, 720));
		hit.addActionListener(this);
		stay.addActionListener(this);
		nextPage.addActionListener(this);
		lastPage.addActionListener(this);
		exitHelp.addActionListener(this);
		text1.addActionListener(this);
		hit.setLocation(1000,600);
		hit.setSize(100,100);
		stay.setLocation(1100,600);
		stay.setSize(100,100);
		nextPage.setLocation(200,100);
		nextPage.setSize(100,100);
		lastPage.setLocation(200,300);
		lastPage.setSize(100,100);
		exitHelp.setLocation(200,400);
		exitHelp.setSize(100,100);
		text1.setLocation(200,500);
		text1.setSize(500,500);
		add(hit);
		add(stay);
		add(nextPage);
		add(lastPage);
		add(exitHelp);
		add(text1);
		timer.start();
	}
}
