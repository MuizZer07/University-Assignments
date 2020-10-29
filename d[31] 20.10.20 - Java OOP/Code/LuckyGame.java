
public abstract class LuckyGame {
	
	protected String name;
	protected String dayOfGame;
	protected int numberOfRandoms;
	protected int lowerValue;
	protected int higherValue;
	protected String[] randomNumbers;
	protected int winners = 0;
	protected int supps = 0;
	protected UserInput userInput;
	
	public LuckyGame(String name, String dayOfGame, int lowerValue, int higherValue) {
		this.name = name;
		this.dayOfGame = dayOfGame;
		this.lowerValue = lowerValue;
		this.higherValue = higherValue;
	}
	
	public void createRandomStrings() {
		for(int i=0; i <this.numberOfRandoms; i++) {
			int number = (int) (Math.random() * (this.higherValue - this.lowerValue + 1) + this.lowerValue);
			
			this.randomNumbers[i] = String.valueOf(number);
		}
	}
	
	public String getName() {
		return this.name; 
	}
	
	public String getDay() {
		return this.dayOfGame;
	}
	
	public abstract void setNumberOfRandoms();
	
	public abstract void collectUserInput(String userInput);
}
