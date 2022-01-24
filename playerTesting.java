public class playerTesting{
	//properties
	public String name;
	public int score;
	public int money;
	public int bet;
	//public String strCards;
	public String position;
	
	
	//methods
	public static int sum(String string){
		int intCount;
		String strcards = string;
		String strcardsplit[] = strcards.split(";");
		int sum = 0;
		String strtemp;
		
		for(intCount = 0; intCount < strcardsplit.length; intCount++){
			//take out the suit, leave only number
			strtemp = strcardsplit[intCount];
			if(strtemp.length() == 3){ //only card with length of 3 is 10
				strtemp = "10";
			}else if(strtemp.length() == 2){
				strtemp = strtemp.charAt(0) + "";
			}
			//put it back into the array, but now it only has the number
			strcardsplit[intCount] = strtemp;
		}
		for(intCount = 0; intCount< strcardsplit.length; intCount++){
			if(strcardsplit[intCount].equals("J") || strcardsplit[intCount].equals("Q") || strcardsplit[intCount].equals("K")){
				sum = sum + 11;
			}else if(strcardsplit[intCount].equals("A")){
				if(sum + 11 > 21){
					sum = sum +1;
				}else{
					sum = sum + 11;
				}
			}else{
				sum = sum + Integer.parseInt(strcardsplit[intCount]);
			}
		}
		return sum;
	}
	
	//constructor
	public playerTesting(String username, int money, int sum){
		this.score = sum;
		this.money = money;
		this.name = username;
	}
	
}
