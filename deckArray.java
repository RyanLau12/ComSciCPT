//------------------------//
//Program: Blackjack
//Author:
//Date: 
//Version Number: 
//------------------------//
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class deckArray{
	//properties
	
	
	//methods
	public static String[][] theDeck(){
		String[][] deck = new String[52][4]; //card name, suit,random number, image file name
		double dblRand;
		int intCount;
		int intCount1;
		int intCount2;
		deck[0][0] = "A";
		deck[1][0] = 2 + "";
		deck[2][0] = 3 + "";
		deck[3][0] = 4 + "";
		deck[4][0] = 5 + "";
		deck[5][0] = 6 + "";
		deck[6][0] = 7 + "";
		deck[7][0] = 8 + "";
		deck[8][0] = 9 + "";
		deck[9][0] = 10 + "";
		deck[10][0] = "J";
		deck[11][0] = "Q";
		deck[12][0] = "K";
		
		deck[13][0] = "A";
		deck[14][0] = 2 + "";
		deck[15][0] = 3 + "";
		deck[16][0] = 4 + "";
		deck[17][0] = 5 + "";
		deck[18][0] = 6 + "";
		deck[19][0] = 7 + "";
		deck[20][0] = 8 + "";
		deck[21][0] = 9 + "";
		deck[22][0] = 10 + "";
		deck[23][0] = "J";
		deck[24][0] = "Q";
		deck[25][0] = "K";
		
		deck[26][0] = "A";
		deck[27][0] = 2 + "";
		deck[28][0] = 3 + "";
		deck[29][0] = 4 + "";
		deck[30][0] = 5 + "";
		deck[31][0] = 6 + "";
		deck[32][0] = 7 + "";
		deck[33][0] = 8 + "";
		deck[34][0] = 9 + "";
		deck[35][0] = 10 + "";
		deck[36][0] = "J";
		deck[37][0] = "Q";
		deck[38][0] = "K";
		
		deck[39][0] = "A";
		deck[40][0] = 2 + "";
		deck[41][0] = 3 + "";
		deck[42][0] = 4 + "";
		deck[43][0] = 5 + "";
		deck[44][0] = 6 + "";
		deck[45][0] = 7 + "";
		deck[46][0] = 8 + "";
		deck[47][0] = 9 + "";
		deck[48][0] = 10 + "";
		deck[49][0] = "J";
		deck[50][0] = "Q";
		deck[51][0] = "K";
		
		//loading suits
		for(intCount = 0; intCount <52; intCount++){
			if(intCount <13){
				deck[intCount][1] = "S";
			}else if(intCount >=13 && intCount <26){
				deck[intCount][1] = "H";
			}else if(intCount >=26 && intCount< 39){
				deck[intCount][1] = "C";
			}else if(intCount >= 39){
				deck[intCount][1] = "D";
			}
			dblRand = Math.random()*2984; //random number for 3rd coloumn
			deck[intCount][2] = dblRand + "";
		}
		//add image file names. NOT ACTUALLY LOADING IMAGES, only file names
		for(intCount = 0; intCount<51; intCount++){
			deck[intCount][3] = deck[intCount][0] + deck[intCount][1] + ".png";
		}
		
		//bubble sort by the random number
		for(intCount = 0; intCount < 50; intCount++){
			for(intCount2 = 0; intCount2 < 50; intCount2++){
				if(Double.parseDouble(deck[intCount2][2]) > Double.parseDouble(deck[intCount2+1][2])){
					String strTemp0 = deck[intCount2][0];	
					String strTemp1 = deck[intCount2][1];
					String strTemp2 = deck[intCount2][2];
					String strTemp3 = deck[intCount2][3];
					
					deck[intCount2][0] = deck[intCount2+1][0];
					deck[intCount2][1] = deck[intCount2+1][1];
					deck[intCount2][2] = deck[intCount2+1][2];
					deck[intCount2][3] = deck[intCount2+1][3];
					
					deck[intCount2+1][0] = strTemp0;
					deck[intCount2+1][1] = strTemp1;
					deck[intCount2+1][2] = strTemp2;
					deck[intCount2+1][3] = strTemp3;
				} 
			}
		
		}
		return deck;
	}
	//constructor
	public deckArray(){
			
	}
	
	public static void main(String[] args){
		int intCount;
		String hihi[][] = deckArray.theDeck();
		for(intCount = 3; intCount < 51; intCount++){
			System.out.println(hihi[intCount][0]);
			System.out.println(hihi[intCount][1]);
			System.out.println(hihi[intCount][2]);
			System.out.println(hihi[intCount][3]);
		}
		
	}

}

