//------------------------//
//Program: Pong
//Author: Ryan Lau
//Date: November 19th, 2021
//Version Number: 1.0
//------------------------//
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;


public class pongNetwork2 implements KeyListener, ActionListener{
	//properties
	JFrame frame = new JFrame("Pong");
	pongPanel panel = new pongPanel();
	//boolean blnGetBall = true;
	int rand1;
	int rand2;
	SuperSocketMaster ssm;
	JTextField serverName = new JTextField("Enter Name");

	//methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == ssm){
			if(ssm.readText().equals("rightUp")){
				panel.rect2Def = -10;
			}else if(ssm.readText().equals("rightDown")){
				panel.rect2Def = 10;
				
			}else if(ssm.readText().equals("rightStop")){
				panel.rect2Def = 0;
			}else{
				panel.strName2 = ssm.readText();
			}
		}else if(evt.getSource() == serverName){
			panel.strName1 = serverName.getText();
			ssm.sendText(serverName.getText());
			serverName.setEnabled(false);
		}
		
	}
	public void keyReleased(KeyEvent evt){
		//release key means to stop moving platform
		if(evt.getKeyChar() == 'w' || evt.getKeyChar() == 's'){
			panel.rect1Def = 0;
			ssm.sendText("leftStop");
		}
	}
	public void keyPressed(KeyEvent evt){
		//pressed key means to move platform
		if(evt.getKeyChar() == 'w'){
			panel.rect1Def = -10;
			ssm.sendText("leftUp");
		}else if(evt.getKeyChar() == 's'){
			panel.rect1Def = 10;
			ssm.sendText("leftDown");
		}else if(evt.getKeyCode() == KeyEvent.VK_SPACE){
			if(panel.circleX == 295 && panel.circleY == 295){ //only able to "serve" ball again if its back in the middle
				rand1 = panel.random();
				rand2 = panel.random2();
				if(rand1 == 3){
					panel.circleDefX = 4;
					ssm.sendText("4");
				}else if(rand1 == 4){
					panel.circleDefX = -4;
					ssm.sendText("-4");
				}if(rand2 == 3){
					panel.circleDefY = 6;
					ssm.sendText("6");
				}else if(rand2 == 4){
					panel.circleDefY = -6;
					ssm.sendText("-6");
				}
			}
		}
	}
	public void keyTyped(KeyEvent evt){
			
	}
	
	//constructor
	public pongNetwork2(){
		serverName.setSize(80, 20);
		serverName.setLocation(260, 580);
		serverName.addActionListener(this);
		panel.add(serverName);
		
		frame.addKeyListener(this);
		frame.setResizable(false);
		frame.requestFocus();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);	
		ssm = new SuperSocketMaster(6112, this);
		ssm.connect();
	}
	//method
	public static void main(String[] args){
		new pongNetwork2();
	}


}
