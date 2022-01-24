public class playerTesting{
	//properties
	public String name;
	public int score;
	public int money;
	public int bet;
	public String strCards;
	public String position;
	
	
	//methods
	
	//constructor
	public playerTesting(String username, int money, int sum){
		this.score = sum;
		this.money = money;
		this.name = username;
	}
	
}
