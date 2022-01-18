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
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class pongPanel extends JPanel implements ActionListener{
	//properties
	Timer timer = new Timer(1000/60, this);
	int rect1Y = 250;
	int rect2Y = 250;
	int rect1Def = 0;
	int rect2Def = 0;
	int intScore1 = 0;
	int intScore2 = 0;
	int circleX = 295;
	int circleY = 295;
	int circleDefX;
	int circleDefY;
	BufferedImage img1;
	BufferedImage img2;
	BufferedImage sword1;
	BufferedImage sword2;
	String strName2;
	String strName1;
	
	//methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == timer){
			this.repaint();
		}
	}
	public void paintComponent(Graphics g){
		if(intScore1 < 5 && intScore2 <5){
			if(intScore1 > intScore2){
				if(intScore1 == 1){
					g.setColor(new Color(1, 50, 32));
					g.fillRect(0, 0, 600, 600);
				}else if(intScore1 == 2){
					g.setColor(new Color(0, 0, 139));
					g.fillRect(0, 0, 600, 600);
				}else if(intScore1 == 3){
					g.setColor(new Color(240,230,140));
					g.fillRect(0, 0, 600, 600);
				}else if(intScore1 == 4){
					g.setColor(new Color(128,0,128));
					g.fillRect(0, 0, 600, 600);
				}
			}else if(intScore1 < intScore2){
				if(intScore2 == 1){
					g.setColor(new Color(1, 50, 32));
					g.fillRect(0, 0, 600, 600);
				}else if(intScore2 == 2){
					g.setColor(new Color(0, 0, 139));
					g.fillRect(0, 0, 600, 600);
				}else if(intScore2 == 3){
					g.setColor(new Color(240,230,140));
					g.fillRect(0, 0, 600, 600);
				}else if(intScore2 == 4){
					g.setColor(new Color(128,0,128));
					g.fillRect(0, 0, 600, 600);
				}
			}else if((intScore1 == 0 && intScore2 == 0) || (intScore1 == 1 && intScore2 == 1) || (intScore1 == 2 && intScore2 == 2) || (intScore1 == 3 && intScore2 == 3) || (intScore1 == 4 && intScore2 == 4)){
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, 600, 600);
			}
			g.setColor(Color.WHITE);
			g.drawString(strName1 +" Score: " + intScore1, 20, 10);
			g.drawString(strName2 +" Score: " + intScore2, 500, 10);
			try{
				sword1 = ImageIO.read(new File("sword.jpg"));
				sword2 = ImageIO.read(new File("sword.jpg"));
			}catch(IOException e){
				System.out.println("unable to load image");
			}
			g.drawImage(sword1, 15, rect1Y, null);
			g.drawImage(sword2, 570, rect2Y, null);
			//g.fillRect(15, rect1Y, 15, 100);
			//g.fillRect(770, rect2Y, 15, 100);
			//deflection for paddkes. only Y-axis needed, x-axis is constant
			rect1Y = rect1Y + rect1Def;
			rect2Y = rect2Y + rect2Def;
			
			g.fillRect(299, 0, 2, 600); //rect in middle
			
			g.setColor(Color.RED);
			g.fillOval(circleX, circleY, 10, 10);
			circleX = circleDefX + circleX;
			circleY = circleY + circleDefY;
			//bouncing off top and bottom
			if(circleY <= 0){
				circleDefY = circleDefY * -1;
			}else if(circleY >= 590){
				circleDefY = circleDefY * -1;
			}
			//collisions with right rectangle
			if((circleX +10 >= 570 && circleX +10 <=585) && (circleY >rect2Y && circleY < rect2Y +100)){
				circleDefX = circleDefX * -1;
			}else if((circleX >= 15 && circleX <= 30) &&(circleY > rect1Y && circleY < rect1Y + 100)){ //collisions with left rectangle
				circleDefX = circleDefX * -1;
			}
			
			//if out of bounds. reset the ball, add points
			if(circleX < 0){
				circleX = 295;
				circleY = 295;
				circleDefX = 0;
				circleDefY = 0;
				intScore2++;
			}else if(circleX > 600){
				circleX = 295;
				circleY = 295;
				circleDefX = 0;
				circleDefY = 0;
				intScore1++;
			}
		}
		else if(intScore1 == 5){
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 600, 600);
			g.setColor(Color.WHITE);
			g.drawString("Game Over! Player 1 Wins!", 0, 10);
			if(intScore1 == 5 && intScore2 == 0){
				g.drawString("Haha player 2 got destroyed", 200, 10);
			}	
		}else if(intScore2 == 5){
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 600, 600);
			g.setColor(Color.WHITE);
			g.drawString("Game Over! Player 2 Wins!", 0, 10);
			if(intScore2 == 5 && intScore1 == 0){
				g.drawString("Haha player 1 got destroyed", 200, 10);
			}	
		}
	}
	public int random(){
		int intRand = (int)(Math.random() * 2 + 3);
		return intRand;
	}
	public int random2(){
		int intRand = (int)(Math.random() * 2 + 3);
		return intRand;
	}
	
	//constructor
	public pongPanel(){
		super();
		this.setPreferredSize(new Dimension(600, 600));
		this.setLayout(null);
		timer.start();
	}
}
