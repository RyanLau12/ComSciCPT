import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.*;

public class randNum implements ActionListener{
	//properties
	JFrame frame = new JFrame("Random Number Guessing Game");
	JPanel panel = new JPanel();
	SuperSocketMaster ssm;
	JButton server = new JButton("Server");
	JButton client = new JButton("Client");
	JButton start = new JButton("Start");
	JTextField IP = new JTextField();
	JTextArea info = new JTextArea("Welcome. Select client or server mode");
	JTextField guess = new JTextField();
	JTextField chat = new JTextField();
	JLabel winner = new JLabel("Closest: "); 
	JTextArea chatDisplay = new JTextArea(); 
	JScrollPane chatScroll = new JScrollPane();
	JLabel guessLabel = new JLabel("Guess");
	JLabel chatLabel = new JLabel("Chat");
	String strSplit[] = new String[10];
	String strNet;
	JTextField username = new JTextField();
	String strUser;
	double low;
	double high;
	double random;
	double dblGuess;
	double difference;
	double dblClose = 10000;
	int guessCount = 0;
	int userCount = 1;
	JLabel guessing = new JLabel();
	JLabel userLabel = new JLabel("Enter username");
	
	//methods
	public void actionPerformed(ActionEvent evt){
		//server button
		if(evt.getSource() == server){
			ssm = new SuperSocketMaster(2188, this);	
			ssm.connect();
			server.setEnabled(false);	
			client.setEnabled(false);
			start.setEnabled(true);
			info.setText("Get ready!");
		}else if(evt.getSource() == client){ //client button
			//ssm = new SuperSocketMaster("127.0.0.1", 2188, this);	
			ssm = new SuperSocketMaster(IP.getText(), 2188, this);	
			ssm.connect();
			userCount++;
			server.setEnabled(false);	
			client.setEnabled(false);
			info.setText("Get ready!");
			ssm.sendText("clientConnected," + username.getText());
			IP.setEditable(false);
		}else if(evt.getSource() == start){ //starting game
			dblClose = 10000;
			guess.setEditable(true);
			low = Math.round(Math.random()*(199));
			high = Math.round(Math.random()*(600)+400);
			random = Math.round((Math.random() *(high-low) + low));
			ssm.sendText("start," + random +"," + low + "," + high + "," +userCount);
		}else if(evt.getSource() == guess){ //user does a guess
			guess.setEditable(false);
			ssm.sendText("guess," + guess.getText() + "," + random + "," + username.getText());
		}else if(evt.getSource() == chat){ //chat text field
			ssm.sendText("chat," + chat.getText() + "," + username.getText());
		}else if(evt.getSource() == username){ //username text field
			server.setEnabled(true);
			client.setEnabled(true);
			IP.setEnabled(true);
			username.setEditable(false);
		}else if(evt.getSource() == ssm){
			strNet = ssm.readText();
			strSplit = strNet.split(",");
			if(strSplit[0].equals("start")){ //start game, set text area, tell users range
				dblClose = 10000;
				guessCount = 1;
				guess.setEditable(true);
				random = Double.parseDouble(strSplit[1]);
				info.setText("Guess a number between " + strSplit[2] +" and " + strSplit[3]);
				winner.setText("Closest:");
				ssm.sendText("serverGuessLabel," + strSplit[1] +","+ strSplit[2] +","+ strSplit[3]);
				userCount = Integer.parseInt(strSplit[4]);
			}else if(strSplit[0].equals("guess")){ //send guess
				ssm.sendText("sendingGuess," + strSplit[3] +"," + strSplit[1]);
				guessing.setText(strSplit[3] +" guessed " + strSplit[1]);
				difference = Math.abs(Double.parseDouble(strSplit[1]) - Double.parseDouble(strSplit[2]));
				if(difference < dblClose){ //make calculations to see if its the new closest value
					dblClose = difference;	
					ssm.sendText("close," + strSplit[1] + "," + strSplit[3]);			
				}
			}else if(strSplit[0].equals("chat")){	//add chat to chat area
					chatDisplay.append(strSplit[2] +": " + strSplit[1] + "\n");
			}else if(strSplit.equals("username")){
				strUser = strSplit[1];
			}else if(strSplit[0].equals("serverGuessLabel")){
				info.setText("Guess a number between " +strSplit[2] + " and " + strSplit[3]);
				winner.setText("Closest:");
			}else if(strSplit[0].equals("sendingGuess")){
				guessing.setText(strSplit[1] +" guessed " + strSplit[2]);
			}else if(strSplit[0].equals("clientConnected")){
				userCount++;	
			}else if(strSplit[0].equals("close")){ 
				winner.setText("Closest:  " + strSplit[2] + ", Guess: " + strSplit[1]);
				ssm.sendText("serverWinner," + strSplit[2]  +"," + strSplit[1]);
			}else if(strSplit[0].equals("serverWinner")){
				winner.setText("Closest:  " + strSplit[1] + ", Guess: " + strSplit[2]);
				
			}
		}
	}
	
	//constructor
	public randNum(){
		panel.setLayout(null);
		
		info.setSize(220, 40);
		info.setLocation(0, 0);
		info.setEditable(false);
		panel.add(info);
		
		guessing.setSize(300, 20);
		guessing.setLocation(0, 40);
		panel.add(info);
		
		guessLabel.setSize(200, 10);
		guessLabel.setLocation(0, 60);
		panel.add(guessLabel);
		
		guess.setSize(300, 20);
		guess.setLocation(0, 80);
		guess.addActionListener(this);
		panel.add(guess);
		
		guessing.setSize(300, 20);
		guessing.setLocation(0, 100);
		panel.add(guessing);
		
		winner.setSize(300, 20);
		winner.setLocation(0, 120);
		panel.add(winner);
		
		chatLabel.setSize(200, 10);
		chatLabel.setLocation(0, 140);
		panel.add(chatLabel);
		
		chat.setSize(300, 20);
		chat.setLocation(0, 160);
		chat.addActionListener(this);
		panel.add(chat);
		
		chatScroll = new JScrollPane(chatDisplay);
		chatScroll.setSize(290, 350);
		chatScroll.setLocation(0, 190);
		chatDisplay.setEditable(false);
		panel.add(chatScroll);
		
		start.setSize(200, 20);
		start.addActionListener(this);
		start.setLocation(50, 560);
		start.setEnabled(false);
		panel.add(start);
		
		server.setSize(200, 20);
		server.setLocation(50, 580);
		server.addActionListener(this);
		server.setEnabled(false);
		panel.add(server);
		
		client.setSize(200, 20);
		client.setLocation(50, 600);
		client.addActionListener(this);
		client.setEnabled(false);
		panel.add(client);
		
		IP.setSize(200, 20);
		IP.setLocation(50, 620);
		IP.setEnabled(false);
		panel.add(IP);
		
		username.setSize(200, 20);
		username.setLocation(50, 540);
		username.addActionListener(this);
		panel.add(username);
		
		userLabel.setSize(200, 20);
		userLabel.setLocation(50, 520);
		panel.add(userLabel);
		
		frame.setResizable(false);
		panel.setPreferredSize(new Dimension(300, 640));
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	//main
	public static void main(String[] args){
		new randNum();
	}

}
