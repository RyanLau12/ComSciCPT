import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class helptest{
	
	help helppanel = new help();
	JFrame theframe = new JFrame();
	
	public helptest(){
		
		helppanel.setLayout(null);
		theframe.setContentPane(helppanel);
		theframe.pack();
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.setResizable(false);
		theframe.setVisible(true); 
		
	}
	public static void main(String[] args){
	new helptest();
	}
}
