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
	JButton thehit = new JButton ("Hit");
	JButton thestay = new JButton("Stay");
	
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
	JLabel thebank1 = new JLabel("Bank: 5000");
	JLabel thebank2 = new JLabel("Bank: 5000");
	JLabel thebank3 = new JLabel("Bank: 5000");
	JLabel dealercards = new JLabel("Cards");
	int playernumber = 1; //default player number is 1, the server. will increase to 2 or if its client
	//game variables
	int usercount = 1;
	String thedeck[][];
	int cardcount;
	int betslocked = 0;
	
	//Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == theserver){
			ssm = new SuperSocketMaster(2188, this);	
			ssm.connect();
			theserver.setEnabled(false);	
			theclient.setEnabled(false);
			thestart.setEnabled(true);
			player = new playerTesting(strname, 5000, 0);
		}else if(evt.getSource() == theclient){
			ssm = new SuperSocketMaster(theip.getText(), 2188, this);
			ssm.connect();
			theclient.setEnabled(false);
			theserver.setEnabled(false);
			player = new playerTesting(strname, 5000, 0);
			ssm.sendText("clientConnected");
		}else if(evt.getSource() == thename){ //set username
			strname = thename.getText();
			thename.setEditable(false);
			theserver.setEnabled(true);
			theclient.setEnabled(true);
			theip.setEditable(true);
		}else if(evt.getSource() == thestart){ //get new panel for main program screen
			thedeck = deckArray.theDeck();
			thepanel = new blackjackmainpanel();
			thepanel.add(thehit);
			thepanel.add(thestay);
			thepanel.add(thechat);
			thestay.setEnabled(false);
			thehit.setEnabled(false);
			thepanel.add(thechatscroll);
			thepanel.add(thebank1);
			thepanel.add(bet1);
			if(usercount == 2){
				thepanel.add(thebank2);
				thepanel.add(bet2);
			}else if(usercount == 3){
				thepanel.add(thebank3);
				thepanel.add(thebank2);
				thepanel.add(bet2);
				thepanel.add(bet3);
			}
			theframe.setContentPane(thepanel);
			theframe.pack();
			//send start, usercount, and cards of dealer and all 3 players
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
		}else if(evt.getSource() == thehit){
			
		}else if(evt.getSource() == thestay){
			
		}else if(evt.getSource() == bet1){
			betslocked++;
			bet1.setEnabled(false);
			bet2.setEnabled(false);
			bet3.setEnabled(false);
			if(betslocked == usercount && usercount == 1){
				//set screen for server if its single player
				thehit.setEnabled(true);
				thestay.setEnabled(true);
				dealercards.setText(thedeck[0][0] + thedeck[0][1]);
				thecards1.setText(thedeck[1][0] +thedeck[1][1]+ ";" + thedeck[2][0] + thedeck[2][1]);
				thepanel.add(dealercards);
				thepanel.add(thecards1);
			}else if(betslocked == usercount && usercount !=1){
				//set screen for server if its not single player
				thehit.setEnabled(true);
				thestay.setEnabled(true);
			}
		}else if(evt.getSource() == bet2 || evt.getSource() == bet3){
			ssm.sendText("bet");
			if(evt.getSource() == bet2){
				bet2.setEnabled(false);
				bet3.setEnabled(false);
			}else if(evt.getSource() == bet3){
				bet3.setEnabled(false);
				bet2.setEnabled(false);
			}	
		}else if(evt.getSource() == ssm){
			strstuff = ssm.readText();
			strsplit = strstuff.split(",");
			if(strsplit[0].equals("clientConnected")){ //for keeping track of users
				usercount++;
				ssm.sendText("playernumber," + usercount);
			}else if(strsplit[0].equals("playernumber")){
				if(strsplit[1].equals("2")){
					playernumber = Integer.parseInt(strsplit[1]); //assigns each client an individual player number
				}else if(strsplit[1].equals("3") && playernumber != 2 && playernumber!=1){
					playernumber = Integer.parseInt(strsplit[1]); //assigns each client an individual player number
					//will assign player the as number 3 assuming that it is not already player 2
				}
				System.out.println(playernumber);
				//since the default number is 1, the server will always have 1 
			}else if(strsplit[0].equals("bet")){ 
				betslocked++;
				if(betslocked == usercount){ 
					//set screen for server
					dealercards.setText(thedeck[0][0] + thedeck[0][1]);
					thepanel.add(dealercards);
					if(usercount == 1){
						thecards1.setText(thedeck[1][0] + thedeck[1][1] + ";" + thedeck[2][0] + thedeck[2][1]);
						thepanel.add(thecards1);
					}else if(usercount == 2){
						thecards1.setText(thedeck[1][0] + thedeck[1][1] + ";" + thedeck[2][0] + thedeck[2][1]);
						thepanel.add(thecards1);
						thecards2.setText(thedeck[3][0] + thedeck[3][1] + ";" + thedeck[4][0] + thedeck[4][1]);
						thepanel.add(thecards2);
					}else if(usercount == 3){
						thecards1.setText(thedeck[1][0] + thedeck[1][1] + ";" + thedeck[2][0] + thedeck[2][1]);
						thepanel.add(thecards1);
						thecards2.setText(thedeck[3][0] + thedeck[3][1] + ";" + thedeck[4][0] + thedeck[4][1]);
						thecards3.setText(thedeck[5][0] + thedeck[5][1] + ";" + thedeck[6][0] + thedeck[6][1]);
						thepanel.add(thecards2);
						thepanel.add(thecards3);
					}
					theframe.pack();
					ssm.sendText("betslocked," + usercount + "," + thecards1.getText() + "," + thecards2.getText() + "," + thecards3.getText() + "," + dealercards.getText());
				}
			}else if(strsplit[0].equals("start")){
				thepanel = new blackjackmainpanel();
				thepanel.add(thehit);
				thepanel.add(thestay);
				thepanel.add(thechat);
				thepanel.add(thechatscroll);
				thepanel.add(thebank1);	
				if(strsplit[1].equals("2")){
					thepanel.add(thebank2);
					thepanel.add(bet2);
				}else if(strsplit[1].equals("3")){
						thepanel.add(thebank2);
						thepanel.add(thebank3);
						thepanel.add(bet3);
						thepanel.add(bet2);
				}
				theframe.setContentPane(thepanel);
				theframe.pack();
			}else if(strsplit[0].equals("betslocked")){
				dealercards.setText(strsplit[5]);
				if(strsplit[1].equals("3")){
					thecards1.setText(strsplit[2]);
					thecards2.setText(strsplit[3]);
					thecards3.setText(strsplit[4]);
					thepanel.add(thecards1);
					thepanel.add(thecards2);
					thepanel.add(thecards3);
				}else if(strsplit[1].equals("2")){
					thecards1.setText(strsplit[2]);
					thecards2.setText(strsplit[3]);
					thepanel.add(thecards1);
					thepanel.add(thecards2);
					thepanel.add(thecards3);
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
		theip.setHorizontalAlignment(SwingConstants.CENTER);
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
		
		thehit.setSize(150, 20);
		thehit.setLocation(900, 400);
		thehit.addActionListener(this);
		thehit.setEnabled(false);
		
		thestay.setSize(150, 20);
		thestay.setLocation(1050, 400);
		thestay.addActionListener(this);
		thehit.setEnabled(true);
	
		thebank1.setSize(100, 20);
		thebank1.setLocation(350, 650);
		thebank1.setForeground(Color.white);
		bet1.setSize(100, 20);
		bet1.setLocation(350, 630);
		bet1.addActionListener(this);
		thecards1.setSize(100, 20);
		thecards1.setLocation(350, 610);
		thecards1.setForeground(Color.white);
		
		thebank2.setSize(100, 20);
		thebank2.setLocation(550, 650);
		thebank2.setForeground(Color.white);
		bet2.setSize(100, 20);
		bet2.setLocation(550, 630);
		bet2.addActionListener(this);
		thecards2.setSize(100, 20);
		thecards2.setLocation(550, 610);
		thecards2.setForeground(Color.white);
		
		thebank3.setSize(100, 20);
		thebank3.setLocation(750, 650);
		thebank3.setForeground(Color.white);
		bet3.setSize(100, 20);
		bet3.setLocation(750, 630);
		bet3.addActionListener(this);
		thecards3.setSize(100, 20);
		thecards3.setLocation(750, 610);
		thecards3.setForeground(Color.white);
		
		dealercards.setSize(100, 20);
		dealercards.setLocation(300, 450);
		dealercards.setForeground(Color.white);
		
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
