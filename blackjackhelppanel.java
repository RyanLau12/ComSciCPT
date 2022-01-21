import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class blackjackhelppanel extends JPanel implements ActionListener{
	//Properties
	Timer thetimer = new Timer(1000/60, this);
	BufferedImage theimg;
	BufferedImage card1;
	BufferedImage card2;
	BufferedImage card3;
	BufferedImage card4;
	int cardCount = 2;
	JButton hit = new JButton("Hit");
	JButton stay = new JButton("Stay");
	JLabel label = new JLabel("Start game with 2 cards. Cards have values faces, but K/Q/J are worth 11");
	JLabel label2 = new JLabel("Goal is to get as close to 21 as possible.");
	JLabel label3 = new JLabel("Aces count as 11, but if this will cause you to go over 21, they count as 1");
	JLabel label4 = new JLabel("Going over counts as a 'bust.' Every round, you choose to either 'hit' or 'stay'");
	JLabel label5 = new JLabel("Hitting will draw another card, staying means you keep what you have");
	JLabel label6 = new JLabel("At the start of your turn, you will bet before you draw a card.");
	JLabel label7 = new JLabel("If you get 21 with your initial 2 cards, you get 3x of your bet back. This is called Blackjack");
	JLabel label8 = new JLabel("If, at the end of the round, you are closer 21 than everyone else, you get 2x of your bet back");
	JLabel bust = new JLabel("You busted because you went over 21!");
	JLabel stay1 = new JLabel("You chose stay. The sum of your cards is 13.");
	JLabel stay2 = new JLabel("You chose stay. The sum of your cards is 15.");
	JLabel common = new JLabel("To determine who wins, the program will compare everyone's sum");
	
	//Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == thetimer){
			this.repaint();
		}else if(evt.getSource() == hit){
			cardCount++;
		}else if(evt.getSource() == stay){
			stay.setEnabled(false);
			hit.setEnabled(false);
			if(cardCount == 2){
				stay1.setSize(800, 20);
				stay1.setLocation(650, 430);
				stay1.setForeground(Color.white);
				this.add(stay1);
				
				common.setSize(800, 20);
				common.setLocation(650, 450);
				common.setForeground(Color.white);
				this.add(common);
			}else if(cardCount == 3){
				stay2.setSize(800, 20);
				stay2.setLocation(650, 430);
				stay2.setForeground(Color.white);
				this.add(stay2);
				
				common.setSize(800, 20);
				common.setLocation(650, 450);
				common.setForeground(Color.white);
				this.add(common);
			}
		}
	}
	
	//Override
	public void paintComponent(Graphics g){
		g.drawImage(theimg, 0, 0, null);
		g.drawImage(card1, 600, 400, null);
		g.drawImage(card2, 725, 400, null);
		if(cardCount == 3){
			g.drawImage(card1, 600, 400, null);
			g.drawImage(card2, 725, 400, null);
			g.drawImage(card3, 850, 400, null);
		}else if(cardCount == 4){
			g.drawImage(card1, 600, 400, null);
			g.drawImage(card2, 725, 400, null);
			g.drawImage(card3, 850, 400, null);
			g.drawImage(card4, 975, 400, null);
			hit.setEnabled(false);
			stay.setEnabled(false);
			bust.setSize(800, 20);
			bust.setLocation(650,450);
			bust.setForeground(Color.white);
			this.add(bust);
		}
	}
	
	//Constructor
	public blackjackhelppanel(){
		super();
		
		label.setSize(800, 20);
		label.setLocation(0, 350);
		label.setForeground(Color.white);
		this.add(label);
		
		label2.setSize(800, 20);
		label2.setLocation(0, 370);
		label2.setForeground(Color.white);
		this.add(label2);
		
		label3.setSize(800, 20);
		label3.setLocation(0, 390);
		label3.setForeground(Color.white);
		this.add(label3);
		
		label4.setSize(800, 20);
		label4.setLocation(0, 410);
		label4.setForeground(Color.white);
		this.add(label4);
		
		label5.setSize(800, 20);
		label5.setLocation(0, 430);
		label5.setForeground(Color.white);
		this.add(label5);
		
		label6.setSize(800, 20);
		label6.setLocation(0, 450);
		label6.setForeground(Color.white);
		this.add(label6);
		
		label8.setSize(800, 20);
		label8.setLocation(0, 470);
		label8.setForeground(Color.white);
		this.add(label8);
		
		hit.setSize(200, 60);
		hit.setLocation(700,640);
		hit.addActionListener(this);
		this.add(hit);
		
		stay.setSize(200, 60);
		stay.setLocation(900, 640);
		stay.addActionListener(this);
		this.add(stay);
		
		try{
			theimg = ImageIO.read(new File("Background.png"));
			card1 = ImageIO.read(new File("5H.png"));
			card2 = ImageIO.read(new File("8S.png"));
			card3 = ImageIO.read(new File("2D.png"));
			card4 = ImageIO.read(new File("KD.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}
		this.setPreferredSize(new Dimension(1280, 720));
		this.setLayout(null);
		this.thetimer.start();
	}
}
	
