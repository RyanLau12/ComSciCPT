import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class blackjackstart implements ActionListener, KeyListener{
	//Properties
	JFrame theframe = new JFrame("Blackjack");
	JPanel thepanel = new blackjackstartpanel();
	JButton theserver = new JButton("Start as Server");
	JTextField thename = new JTextField("Enter Username");
	JButton theclient = new JButton("Start as Client");
	JTextField theip = new JTextField("127.0.0.1");
	JButton thehelp = new JButton("Help/Rules");
	JButton thestart = new JButton("Start Game");
	SuperSocketMaster ssm;
	String strstuff;
	String strsplit[] = new String[10];
	JTextArea thechatdisplay = new JTextArea(); 
	JScrollPane thechatscroll = new JScrollPane();
	JTextField thechat = new JTextField();
	
	//help screen variables
	JButton helpBackButton = new JButton("Back");
	
	//player variables
	playerTesting player;
	String strname;
	JLabel thecards1 = new JLabel("Cards: ");
	JLabel thecards2 = new JLabel("Cards: ");
	JLabel thecards3 = new JLabel("Cards: ");
	JTextField bet1 = new JTextField("Bet:");
	JTextField bet2 = new JTextField("Bet:");
	JTextField bet3 = new JTextField("Bet:");
	JLabel thebank1 = new JLabel("Bank:");
	JLabel thebank2 = new JLabel("Bank:");
	JLabel thebank3 = new JLabel("Bank:");
	JLabel dealercards = new JLabel("Cards");
	//game variables
	int usercount = 1;
	String thedeck[][];
	int cardcount;
	
	//Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == theserver){
			ssm = new SuperSocketMaster(2188, this);	
			ssm.connect();
			theserver.setEnabled(false);	
			theclient.setEnabled(false);
			thestart.setEnabled(true);
			player = new playerTesting(strname, 5000, 0);
			System.out.println("hi");
		}else if(evt.getSource() == theclient){
			ssm = new SuperSocketMaster(theip.getText(), 2188, this);
			ssm.connect();
			theclient.setEnabled(false);
			theserver.setEnabled(false);
			player = new playerTesting(strname, 5000, 0);
			ssm.sendText("clientConnected");
		}else if(evt.getSource() == thename){
			strname = thename.getText();
			thename.setEditable(false);
			theserver.setEnabled(true);
			theclient.setEnabled(true);
		}else if(evt.getSource() == thestart){ //get new panel for main program screen
			thepanel = new blackjackmainpanel();
			thepanel.add(thechat);
			thepanel.add(thechatscroll);
			thepanel.add(thebank1);
			thepanel.add(bet1);
			if(usercount == 2){
				thepanel.add(bet2);
				thepanel.add(thebank2);
			}else if(usercount == 3){
				thepanel.add(bet2);
				thepanel.add(thebank2);
				thepanel.add(bet3);
				thepanel.add(thebank3);
			}
			theframe.setContentPane(thepanel);
			theframe.pack();
			thedeck = deckArray.theDeck();
			ssm.sendText("start," + usercount);
		}else if(evt.getSource() == thehelp){ //help panel
			thepanel = new blackjackhelppanel();
			thepanel.add(helpBackButton);
			theframe.setContentPane(thepanel);
			theframe.pack();
		}else if(evt.getSource() == helpBackButton){ //back to main panel
			thepanel = new blackjackstartpanel();
			thepanel.add(theserver);
			thepanel.add(theclient);
			thepanel.add(thename);
			thepanel.add(theip);
			thepanel.add(thestart);
			thepanel.add(thehelp);
			theframe.setContentPane(thepanel);
			theframe.pack();
		}else if(evt.getSource() == thechat){
			thechatdisplay.append(player.name + ": " + thechat.getText() + "\n"); //display ur own msg in chat before ssm it
			ssm.sendText("chat," + player.name + "," +thechat.getText());
		}else if(evt.getSource() == ssm){
			strstuff = ssm.readText();
			strsplit = strstuff.split(",");
			if(strsplit[0].equals("clientConnected")){
				usercount++;
			}else if(strsplit[0].equals("start")){
				thepanel = new blackjackmainpanel();
				thepanel.add(thechat);
				thepanel.add(thechatscroll);
				theframe.setContentPane(thepanel);
				theframe.pack();
				if(strsplit[1].equals("2")){
					thepanel.add(thebank1);
					thepanel.add(bet1);
					thepanel.add(thebank2);
					thepanel.add(bet2);
					theframe.setContentPane(thepanel);
					theframe.pack();
				}else if(strsplit[1].equals("3")){
					thepanel.add(thebank1);
					thepanel.add(bet1);
					thepanel.add(thebank2);
					thepanel.add(bet2);
					thepanel.add(thebank3);
					thepanel.add(bet3);
					theframe.setContentPane(thepanel);
					theframe.pack();
				}
			}else if(strsplit[0].equals("chat")){
				thechatdisplay.append(strsplit[1] + ": " + strsplit[2] + "\n"); //display username + chat msg
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
		thepanel.setLayout(null);
		theserver.setSize(300,50);
		theserver.setHorizontalAlignment(SwingConstants.CENTER);
		theserver.setLocation(320,450);
		theserver.addActionListener(this);
		theserver.setEnabled(false);
		thepanel.add(theserver);
		
		theclient.setSize(300,50);
		theclient.setHorizontalAlignment(SwingConstants.CENTER);
		theclient.setLocation(320,525);
		theclient.addActionListener(this);
		theclient.setEnabled(false);
		thepanel.add(theclient);
		
		thename.setSize(300,50);
		thename.setHorizontalAlignment(SwingConstants.CENTER);
		thename.setLocation(665,450);
		thename.addActionListener(this);
		thepanel.add(thename);
		
		theip.setSize(300, 50);
		theip.setLocation(665, 525);
		theip.setEditable(false);
		thepanel.add(theip);
		
		thestart.setSize(300, 50);
		thestart.setLocation(320, 600);
		thestart.addActionListener(this);
		thestart.setEnabled(false);
		thepanel.add(thestart);
		
		thehelp.setSize(300, 50);
		thehelp.setLocation(665, 600);
		thehelp.addActionListener(this);
		thepanel.add(thehelp);
		
		helpBackButton.setSize(200, 60);
		helpBackButton.setLocation(1100, 640);
		helpBackButton.addActionListener(this);
		
		thechat.setSize(300, 20);
		thechat.setLocation(900, 10);
		thechat.addActionListener(this);
		
		thechatscroll = new JScrollPane(thechatdisplay);
		thechatscroll.setSize(300, 350);
		thechatscroll.setLocation(900, 40);
		thechatdisplay.setEditable(false);
		
	
		thebank1.setSize(100, 20);
		thebank1.setLocation(350, 650);
		thebank1.setForeground(Color.white);
		bet1.setSize(100, 20);
		bet1.setLocation(350, 630);
		
		thebank2.setSize(100, 20);
		thebank2.setLocation(550, 650);
		thebank2.setForeground(Color.white);
		bet2.setSize(100, 20);
		bet2.setLocation(550, 630);
		
		thebank3.setSize(100, 20);
		thebank3.setLocation(750, 650);
		thebank3.setForeground(Color.white);
		bet3.setSize(100, 20);
		bet3.setLocation(750, 630);
		
		thepanel.setPreferredSize(new Dimension(1280, 720));
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
