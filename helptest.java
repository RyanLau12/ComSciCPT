import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class helpTest implements ActionListener{
	//properties
	JFrame theframe = new JFrame("Help");
	JPanel thepanel = new blackjackhelppanel();
	
	
	
	
	//methods
	public void actionPerformed(ActionEvent evt){
	
	}
	
	//constructor
	public helpTest(){
		thepanel.setLayout(null);
		
		thepanel.setPreferredSize(new Dimension(1280, 720));
		theframe.setContentPane(thepanel);
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.setResizable(false);
		theframe.pack();
		theframe.setVisible(true);
	}
	//main program
	public static void main(String[] args){
		new helpTest();
	}
}
