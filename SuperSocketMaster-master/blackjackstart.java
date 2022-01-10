import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class blackjackstart implements ActionListener, KeyListener{
	//Properties
	JFrame theframe = new JFrame("Blackjack");
	JPanel thepanel = new JPanel();
	JLabel theserverlabel = new JLabel("Start as Server");
	JTextField thename = new JTextField();
	JLabel theiplabel = new JLabel("Enter IP");
	JTextField theip = new JTextField("127.0.0.1");
	SuperSocketMaster ssm;
	
	//Methods
	public void actionPerformed(ActionEvent evt){
	}
	public void keyReleased(KeyEvent evt){
	}
	public void keyPressed(KeyEvent evt){
	}
	public void keyTyped(KeyEvent evt){
	}
	
	//Constructor
	public blackjackstart(){
		thepanel.add(theserverlabel);
		
		thepanel.setPreferredSize(new Dimension(300, 300));
		theframe.setContentPane(thepanel);
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.setResizable(false);
		thepanel.requestFocus();
		theframe.pack();
		theframe.setVisible(true);
	}
	
	//Main Program
	public static void main(String[] args){
		new blackjackstart();
	}
	
}
