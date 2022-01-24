import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class blackjackstartTest implements ActionListener, KeyListener{
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
	int intCount;
	String strcardstuff;
	String strcardsplit[] = new String[20];
	
	//help screen variables
	JButton helpBackButton = new JButton("Back");
	
	//player variables
	playerTesting player;
	String strcards;
	String strname;
	JLabel thecards1 = new JLabel("Cards: ");
	JLabel thecards2 = new JLabel("Cards: ");
	JLabel thecards3 = new JLabel("Cards: ");
	JTextField thebet= new JTextField();
	JLabel bet = new JLabel("Bet: ");
	JLabel thebank = new JLabel();
	JLabel bank = new JLabel("Bank: ");
	JLabel dealercards = new JLabel("Cards");
	int playernumber = 1; //default player number is 1, the server. will increase to 2 or if its client
	//game variables
	int usercount = 1;
	String thedeck[][];
	int currentcardindex;
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
			player.position = "server";
		}else if(evt.getSource() == theclient){
			ssm = new SuperSocketMaster(theip.getText(), 2188, this);
			ssm.connect();
			theclient.setEnabled(false);
			theserver.setEnabled(false);
			player = new playerTesting(strname, 2000, 0);
			player.position = "client";
			ssm.sendText("clientConnected");
		}else if(evt.getSource() == thename){ //set username
			strname = thename.getText();
			thename.setEditable(false);
			theserver.setEnabled(true);
			theclient.setEnabled(true);
			theip.setEditable(true);
		}else if(evt.getSource() == thestart){ //get new panel for main program screen
			thedeck = deckArray.theDeck();
			for(intCount =0; intCount <52;intCount++){
				System.out.println(thedeck[intCount][0] + "," + thedeck[intCount][1] + "," + thedeck[intCount][2] + "," + thedeck[intCount][3] +"\n");
			}
			thepanel = new blackjackmainpanel();
			thepanel.add(thehit);
			thepanel.add(thestay);
			thepanel.add(thechat);
			thepanel.add(thechatscroll);
			thepanel.add(bet);
			thepanel.add(thebet);
			thebank.setText(player.money + "");
			thepanel.add(thebank);
			thepanel.add(bank);
			theframe.setContentPane(thepanel);
			theframe.pack();
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
		}else if(evt.getSource() == thehit){ //if you hit
			if(player.position.equals("client")){
				ssm.sendText("hit," + player.name);
			}else if(player.position.equals("server")){
				//draw a new card
				thecards1.setText(thecards1.getText() + ";" + thedeck[currentcardindex+1][0] + thedeck[currentcardindex+1][1]);
				currentcardindex++;
				//get the cards currently in the hand, split by ; into array
				strcardstuff = thecards1.getText();
				strcardsplit = strcardstuff.split(";");
				for(intCount = 0; intCount < strcardsplit.length; intCount++){
					//take out the suit, leave only number
					strcards = strcardsplit[intCount];
					if(strcards.length() == 3){ //only card with length of 3 is 10
						strcards = strcards.charAt(0) + strcards.charAt(1) + "";
					}else if(strcards.length() == 2){
						strcards = strcards.charAt(0) + "";
					}
					//put it back into the array, but now it only has the number
					strcardsplit[intCount] = strcards;
				}
				for(intCount = 0; intCount < strcardsplit.length; intCount++){ //for testing that the suits were actually removed
					thechatdisplay.append(strcardsplit[intCount] + "\n");
				} 
				for(intCount = 0; intCount< strcardsplit.length; intCount++){
					if(strcardsplit[intCount].equals("J") || strcardsplit[intCount].equals("Q") || strcardsplit[intCount].equals("K")){
							player.score = player.score + 11;
					}else if(strcardsplit[intCount].equals("A")){
						if(player.score + 11 > 21){
							player.score = player.score +1;
						}else{
							player.score = player.score + 11;
						}
					}else{
						player.score = player.score + Integer.parseInt(strcardsplit[intCount]);
					}
				}
				thechatdisplay.append("Sum: " + player.score + "\n");
			}
		}else if(evt.getSource() == thestay){
			thestay.setEnabled(false);
			thehit.setEnabled(false);
			
		}else if(evt.getSource() == thebet){
			thehit.setEnabled(true);
			thestay.setEnabled(true);
			player.bet = Integer.parseInt(thebet.getText());
			player.money = player.money-player.bet;
			thebank.setText(player.money + "");
			if(player.position.equals("client")){
				ssm.sendText("bet," + thebet.getText());
			}else if(player.position.equals("server")){
				betslocked++;
				if(betslocked == usercount){
					//note that this will only trigger if the server is the last to bet.
					//set the screen for the server.add different number of cards depending on number of players
					dealercards.setText(thedeck[0][0] + thedeck[0][1]);
					thepanel.add(dealercards);
					if(usercount == 1){
						thecards1.setText(thedeck[1][0] + thedeck [1][1] + ";" + thedeck[2][0] + thedeck[2][1]);
						thepanel.add(thecards1);
						currentcardindex = 2;
					}else if(usercount == 2){
						thecards1.setText(thedeck[1][0] + thedeck [1][1] + ";" + thedeck[2][0] + thedeck[2][1]);
						thecards2.setText(thedeck[3][0] + thedeck [3][1] + ";" + thedeck[4][0] + thedeck[4][1]);
						thepanel.add(thecards1);
						thepanel.add(thecards2);
						currentcardindex = 4;
					}else if(usercount == 3){
						thecards1.setText(thedeck[1][0] + thedeck [1][1] + ";" + thedeck[2][0] + thedeck[2][1]);
						thecards2.setText(thedeck[3][0] + thedeck [3][1] + ";" + thedeck[4][0] + thedeck[4][1]);
						thecards3.setText(thedeck[5][0] + thedeck [5][1] + ";" + thedeck[6][0] + thedeck[6][1]);
						thepanel.add(thecards1);
						thepanel.add(thecards2);
						thepanel.add(thecards3);
						currentcardindex = 6;
					}
					theframe.pack();
					ssm.sendText("allbetsin," + usercount + "," + dealercards.getText() + "," + thecards1.getText() + "," + thecards2.getText() + "," + thecards3.getText()); 
					//let clients know that betting is over
				}
			}
			thebet.setEnabled(false);
		}else if(evt.getSource() == ssm){
			strstuff = ssm.readText();
			strsplit = strstuff.split(",");
			if(strsplit[0].equals("clientConnected")){ //for keeping track of users
				usercount++;
			}else if(strsplit[0].equals("start")){
				thepanel = new blackjackmainpanel();
				thepanel.add(thehit);
				thepanel.add(thestay);
				thepanel.add(thechat);
				thepanel.add(thechatscroll);
				thebank.setText(player.money +"");
				thepanel.add(thebank);	
				thepanel.add(bank);
				thepanel.add(bet);
				thepanel.add(thebet);

				theframe.setContentPane(thepanel);
				theframe.pack();
			}else if(strsplit[0].equals("chat")){
				thechatdisplay.append(strsplit[1] + ": " + strsplit[2] + "\n"); //display username + chat msg
			}else if(strsplit[0].equals("bet")){  //note: "bet" is sent from client to server
				betslocked++;
				//if the client is the last to bet, the server must also let clients know that betting is over
				if(betslocked == usercount){
					dealercards.setText(thedeck[0][0] + thedeck[0][1]);
					thepanel.add(dealercards);
					if(usercount == 2){
						thecards1.setText(thedeck[1][0] + thedeck [1][1] + ";" + thedeck[2][0] + thedeck[2][1]);
						thecards2.setText(thedeck[3][0] + thedeck [3][1] + ";" + thedeck[4][0] + thedeck[4][1]);
						thepanel.add(thecards1);
						thepanel.add(thecards2);
						currentcardindex = 4;
					}else if(usercount == 3){
						thecards1.setText(thedeck[1][0] + thedeck [1][1] + ";" + thedeck[2][0] + thedeck[2][1]);
						thecards2.setText(thedeck[3][0] + thedeck [3][1] + ";" + thedeck[4][0] + thedeck[4][1]);
						thecards3.setText(thedeck[5][0] + thedeck [5][1] + ";" + thedeck[6][0] + thedeck[6][1]);
						thepanel.add(thecards1);
						thepanel.add(thecards2);
						thepanel.add(thecards3);
						currentcardindex = 6;
					}
					theframe.pack();
					ssm.sendText("allbetsin," + usercount + "," + dealercards.getText() + "," + thecards1.getText() + "," + thecards2.getText() + "," + thecards3.getText()); 
					//because usercount is stored in the server, this is needed to let clients know that bets done
				}
			}else if(strsplit[0].equals("allbetsin")){ //allbetsin is sent from server to client
				//set screens for clients
				if(strsplit[1].equals("2")){
					dealercards.setText(strsplit[2]);
					thecards1.setText(strsplit[3]);
					thecards2.setText(strsplit[4]);
					thepanel.add(dealercards);
					thepanel.add(thecards1);
					thepanel.add(thecards2);
				}else if(strsplit[1].equals("3")){
					dealercards.setText(strsplit[2]);
					thecards1.setText(strsplit[3]);
					thecards2.setText(strsplit[4]);
					thecards3.setText(strsplit[5]);
					thepanel.add(thecards1);
					thepanel.add(dealercards);
					thepanel.add(thecards2);
					thepanel.add(thecards3);
				}
				theframe.pack();
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
	public blackjackstartTest(){
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
		thestay.setEnabled(false);
	
		bank.setSize(200, 20);
		bank.setLocation(900, 420);
		bank.setForeground(Color.white);
		thebank.setSize(200, 20);
		thebank.setLocation(900, 440);
		thebank.setForeground(Color.white);
		
		bet.setSize(200, 20);
		bet.setLocation(900, 460);
		bet.setForeground(Color.white);
		thebet.setSize(200, 20);
		thebet.setLocation(900, 480);
		thebet.addActionListener(this);
		
		thecards1.setSize(200, 20);
		thecards1.setLocation(350, 610);
		thecards1.setForeground(Color.white);
		
		thecards2.setSize(200, 20);
		thecards2.setLocation(550, 610);
		thecards2.setForeground(Color.white);
		
		thecards3.setSize(200, 20);
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
		new blackjackstartTest();
	}
}
