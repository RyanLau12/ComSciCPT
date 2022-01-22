import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class blackjackstart implements ActionListener, KeyListener{
	//Properties
	JFrame theframe = new JFrame("Blackjack");
	JPanel startpanel = new blackjackstartpanel();
	JPanel mainpanel = new blackjackmainpanel();
	JPanel helppanel = new blackjackhelppanel();
	JButton theserver = new JButton("Start as Server");
	JTextField thename = new JTextField("Enter Username");
	JButton theclient = new JButton("Start as Client");
	JTextField theip = new JTextField("127.0.0.1");
	JButton thehelp = new JButton("Help/Rules");
	JButton thestart = new JButton("Start Game");
	SuperSocketMaster ssm;
	String strstuff;
	String strsplit[] = new String[10];
	
	//player variables
	String strname;
	//game variables
	int intusercount;
	String thedeck[][];
	
	//Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == theserver){
			ssm = new SuperSocketMaster(2188, this);	
			ssm.connect();
			theserver.setEnabled(false);	
			theclient.setEnabled(false);
			thestart.setEnabled(true);
			System.out.println("hi");
		}else if(evt.getSource() == theclient){
			ssm = new SuperSocketMaster(theip.getText(), 2188, this);
			ssm.connect();
			theclient.setEnabled(false);
			theserver.setEnabled(false);
			ssm.sendText("clientConnected");
		}else if(evt.getSource() == thename){
			strname = thename.getText();
			thename.setEditable(false);
		}else if(evt.getSource() == thestart){
			thedeck = deckArray.theDeck();
			startpanel.requestFocus();
			theframe.setContentPane(startpanel);
			theframe.pack();
		}else if(evt.getSource() == thehelp){
			startpanel.requestFocus();
			theframe.setContentPane(helppanel);
			theframe.pack();
		}else if(evt.getSource() == ssm){
			strstuff = ssm.readText();
			strsplit = strstuff.split(",");
			if(strsplit[0].equals("clientConnected")){
				intusercount++;
				ssm.sendText("clientNumber,intusercount");
			}
			else if(strsplit[0].equals("hit")){
			}
			else if(strsplit[0].equals("stay")){
			}
			else if(strsplit[0].equals("chat")){
			}
			else if(strsplit[0].equals("bet")){
			}
		}
	}
	public void keyReleased(KeyEvent evt){
	}
	public void keyPressed(KeyEvent evt){
	}
	public void keyTyped(KeyEvent evt){
	}
	
	//Constructor
	public blackjackstart(){
		startpanel.setLayout(null);
		theserver.setSize(300,50);
		theserver.setHorizontalAlignment(SwingConstants.CENTER);
		theserver.setLocation(320,450);
		theserver.addActionListener(this);
		startpanel.add(theserver);
		
		theclient.setSize(300,50);
		theclient.setHorizontalAlignment(SwingConstants.CENTER);
		theclient.setLocation(320,525);
		theclient.addActionListener(this);
		startpanel.add(theclient);
		
		thename.setSize(300,50);
		thename.setHorizontalAlignment(SwingConstants.CENTER);
		thename.setLocation(665,450);
		thename.addActionListener(this);
		startpanel.add(thename);
		
		theip.setSize(300, 50);
		theip.setLocation(665, 525);
		theip.setEditable(false);
		startpanel.add(theip);
		
		thestart.setSize(300, 50);
		thestart.setLocation(320, 600);
		thestart.addActionListener(this);
		thestart.setEnabled(false);
		startpanel.add(thestart);
		
		thehelp.setSize(300, 50);
		thehelp.setLocation(665, 600);
		thehelp.addActionListener(this);
		startpanel.add(thehelp);
		
		startpanel.setPreferredSize(new Dimension(1280, 720));
		theframe.setContentPane(startpanel);
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.setResizable(false);
		startpanel.requestFocus();
		theframe.pack();
		theframe.setVisible(true);
	}
	
	//Main Program
	public static void main(String[] args){
		new blackjackstart();
	}
}
