import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.*;

public class pongClient2 extends JFrame implements ActionListener, KeyListener{
	//properties
	JFrame frame = new JFrame("Pong Client");
	JPanel panel = new JPanel();
	SuperSocketMaster ssm;
	JLabel ip = new JLabel("Enter IP");
	JTextField IP = new JTextField();
	JLabel user = new JLabel("Enter username");
	JTextField userName = new JTextField();
	String strIP;
	pongPanel ppanel = new pongPanel();
	
	//methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == IP){
			strIP = IP.getText();
			ssm = new SuperSocketMaster(strIP, 6112, this);
			ssm.connect();
			IP.setEnabled(false);
		}else if(evt.getSource() == ssm){
			if(ssm.readText().equals("leftUp")){
				ppanel.rect1Def = -10;
			}else if(ssm.readText().equals("leftDown")){
				ppanel.rect1Def = 10;
			}else if(ssm.readText().equals("leftStop")){
				ppanel.rect1Def = 0;
			}else if(ssm.readText().equals("4")){
				ppanel.circleDefX = 4;
			}else if(ssm.readText().equals("-4")){
				ppanel.circleDefX = -4;
			}else if(ssm.readText().equals("6")){
				ppanel.circleDefY = 6;
			}else if(ssm.readText().equals("-6")){
				ppanel.circleDefY = -6;
			}else{
				ppanel.strName1 = ssm.readText();
			}
		}else if(evt.getSource() == userName){
			ssm.sendText(userName.getText());
			ppanel.strName2 = userName.getText();
			userName.setEnabled(false);
			frame.setPreferredSize(new Dimension(600, 600));
			frame.addKeyListener(this);
			frame.setResizable(false);
			frame.requestFocus();
			frame.setContentPane(ppanel);
			frame.pack();	
			frame.setVisible(true);	
		}
	}public void keyReleased(KeyEvent evt){
		ssm.sendText("rightStop");
		ppanel.rect2Def = 0;
	}public void keyPressed(KeyEvent evt){
		if(evt.getKeyCode() == KeyEvent.VK_UP){
			ssm.sendText("rightUp");
			ppanel.rect2Def = -10;
		}else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
			ssm.sendText("rightDown");
			ppanel.rect2Def = 10;
		}
	}public void keyTyped(KeyEvent evt){
		
	}
	
	//constructor
	pongClient2(){
		panel.setLayout(null);
		
		ip.setSize(200, 20);
		ip.setLocation(0, 0);
		panel.add(ip);
		
		IP.setLocation(0, 20);
		IP.setSize(100, 20);
		IP.addActionListener(this);
		panel.add(IP);
		
		user.setSize(200, 20);
		user.setLocation(0, 40);
		panel.add(user);
		
		userName.setSize(200, 60);
		userName.setLocation(0, 80);
		userName.addActionListener(this);
		panel.add(userName);
		
		frame.setPreferredSize(new Dimension(300, 600));
		frame.addKeyListener(this);
		frame.setResizable(false);
		frame.requestFocus();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);	
	}
	
	//main
	public static void main(String[] args){
		new pongClient2();
	}
}
