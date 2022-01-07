import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class mynetapp2 implements ActionListener{
	//creating a server program to send basic text to a server
	//properties
	JFrame frame = new JFrame("Basic Server Net App");
	JPanel panel = new JPanel();
	JLabel label = new JLabel("Erina Nakiri Best Waifu");
	SuperSocketMaster ssm = new SuperSocketMaster(7777, this);
	
	//methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == ssm){
			label.setText(ssm.readText());
		}
	}
	
	//constructor
	public mynetapp2(){
		panel.add(label);
		panel.setPreferredSize(new Dimension(300, 300));
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		ssm.connect();
	}
	
	//main method
	public static void main(String[] args){
		new mynetapp2();
	}

}
