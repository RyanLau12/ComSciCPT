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
	JButton back = new JButton("back");
	
	
	
	
	//methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == back){
			System.out.println("back");
		}
	}
	
	//constructor
	public helpTest(){
		thepanel.setLayout(null);
		
		back.setSize(200, 60);
		back.setLocation(1000, 100);
		back.addActionListener(this);
		thepanel.add(back);
		
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
