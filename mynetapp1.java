import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class mynetapp1 implements ActionListener{
	//creating a client program to send basic text to a server
	//properties
	JFrame frame = new JFrame("Basic Client Net App");
	JPanel panel = new JPanel();
	JTextField txtField = new JTextField("Erina Nakiri best waifu");
	SuperSocketMaster ssm = new SuperSocketMaster("127.0.0.1", 7777, this);
	
	//methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == txtField){
			System.out.println("hello there");
			ssm.sendText(txtField.getText());
		}
	}
	
	//constructor
	public mynetapp1(){
		txtField.addActionListener(this);
		panel.add(txtField);
		panel.setPreferredSize(new Dimension(300, 300));
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		ssm.connect();
	}
	
	//main method
	public static void main(String[] args){
		new mynetapp1();
	}

}
