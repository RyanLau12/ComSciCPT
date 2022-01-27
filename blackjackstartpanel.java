import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class blackjackstartpanel extends JPanel implements ActionListener{
	//Properties
	Timer thetimer = new Timer(1000/60, this);
	BufferedImage theimg = null;
	BufferedImage iAC = null;
	BufferedImage i2C = null;
	BufferedImage i3C = null;
	BufferedImage i4C = null;
	BufferedImage i5C = null;
	BufferedImage i6C = null;
	BufferedImage i7C = null;
	BufferedImage i8C = null;
	BufferedImage i9C = null;
	BufferedImage i10C = null;
	BufferedImage iJC = null;
	BufferedImage iQC = null;
	BufferedImage iKC = null;
	BufferedImage iAH = null;
	BufferedImage i2H = null;
	BufferedImage i3H = null;
	BufferedImage i4H = null;
	BufferedImage i5H = null;
	BufferedImage i6H = null;
	BufferedImage i7H = null;
	BufferedImage i8H = null;
	BufferedImage i9H = null;
	BufferedImage i10H = null;
	BufferedImage iJH = null;
	BufferedImage iQH = null;
	BufferedImage iKH = null;
	BufferedImage iAD = null;
	BufferedImage i2D = null;
	BufferedImage i3D = null;
	BufferedImage i4D = null;
	BufferedImage i5D = null;
	BufferedImage i6D = null;
	BufferedImage i7D = null;
	BufferedImage i8D = null;
	BufferedImage i9D = null;
	BufferedImage i10D = null;
	BufferedImage iJD = null;
	BufferedImage iQD = null;
	BufferedImage iKD = null;
	BufferedImage iAS = null;
	BufferedImage i2S = null;
	BufferedImage i3S = null;
	BufferedImage i4S = null;
	BufferedImage i5S = null;
	BufferedImage i6S = null;
	BufferedImage i7S = null;
	BufferedImage i8S = null;
	BufferedImage i9S = null;
	BufferedImage i10S = null;
	BufferedImage iJS = null;
	BufferedImage iQS = null;
	BufferedImage iKS = null;
	
	
	Boolean blnserver = false;
	int intclientnumber = 0;
	int intxoffset;
	int intyoffset;
	//Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == thetimer){
			this.repaint();
		}
	}
	
	//Override
	public void paintComponent(Graphics g){
		g.drawImage(theimg, 0, 0, null);
		if(intclientnumber == 1){
			intxoffset = 0;
			intyoffset = 0;
		}
		if(intclientnumber == 2){
			intxoffset = 0;
			intyoffset = 0;
		}
	}
	
	//Constructor
	public blackjackstartpanel(){
		super();
		try{
			theimg = ImageIO.read(new File("blackjackstart.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}
try{
	iAH = ImageIO.read(new File("AH.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i2H = ImageIO.read(new File("2H.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i3H = ImageIO.read(new File("3H.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i4H = ImageIO.read(new File("4H.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i5H = ImageIO.read(new File("5H.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i6H = ImageIO.read(new File("6H.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i7H = ImageIO.read(new File("7H.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i8H = ImageIO.read(new File("8H.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i9H = ImageIO.read(new File("9H.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i10H = ImageIO.read(new File("10H.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	iJH = ImageIO.read(new File("JH.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	iQH = ImageIO.read(new File("QH.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	iKH = ImageIO.read(new File("KH.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	iAC = ImageIO.read(new File("AC.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i2C = ImageIO.read(new File("2C.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i3C = ImageIO.read(new File("3C.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i4C = ImageIO.read(new File("4C.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i5C = ImageIO.read(new File("5C.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i6C = ImageIO.read(new File("6C.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i7C = ImageIO.read(new File("7C.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i8C = ImageIO.read(new File("8C.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i9C = ImageIO.read(new File("9C.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i10C = ImageIO.read(new File("10C.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	iJC = ImageIO.read(new File("JC.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	iQC = ImageIO.read(new File("QC.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	iKC = ImageIO.read(new File("KC.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	iAD = ImageIO.read(new File("AD.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i2D = ImageIO.read(new File("2D.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i3D = ImageIO.read(new File("3D.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i4D = ImageIO.read(new File("4D.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i5D = ImageIO.read(new File("5D.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i6D = ImageIO.read(new File("6D.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i7D = ImageIO.read(new File("7D.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i8D = ImageIO.read(new File("8D.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i9D = ImageIO.read(new File("9D.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i10D = ImageIO.read(new File("10D.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	iJD = ImageIO.read(new File("JD.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	iQD = ImageIO.read(new File("QD.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	iKD = ImageIO.read(new File("KD.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	iAS = ImageIO.read(new File("AS.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i2S = ImageIO.read(new File("2S.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i3S = ImageIO.read(new File("3S.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i4S = ImageIO.read(new File("4S.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i5S = ImageIO.read(new File("5S.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i6S = ImageIO.read(new File("6S.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i7S = ImageIO.read(new File("7S.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i8S = ImageIO.read(new File("8S.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i9S = ImageIO.read(new File("9S.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	i10S = ImageIO.read(new File("10S.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	iJS = ImageIO.read(new File("JS.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	iQS = ImageIO.read(new File("QS.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
try{
	iKS = ImageIO.read(new File("KS.png"));
}catch(IOException e){
	System.out.println("Unable to load image");
}
		this.setPreferredSize(new Dimension(1280, 720));
		this.setLayout(null);
		this.thetimer.start();
	}
}
	
