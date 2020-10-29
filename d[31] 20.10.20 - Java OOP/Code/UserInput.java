
public class UserInput {

	private LuckyGame luckyGame;
	protected int validInputs;
	protected String validInputString = " ";
	protected boolean invalid = false;
	
	public UserInput(LuckyGame luckyGame) {
		this.luckyGame = luckyGame;
	}
	
	public boolean checkValidRange(int number) {
		return number >= this.luckyGame.lowerValue && number <= this.luckyGame.higherValue;
	}
	
	public boolean checkRepeatedInput(int number, String integer) {
		
		if(this.validInputString != "") {
			if(this.validInputString.contains(integer)) {
				this.invalid = true;
				return false;
			}
		}	
		
		this.validInputString += number + " ";
		this.validInputs++;
		return true;
	}
	
	public String getPowerballFromValidInputs() {
		return this.validInputString.split(" ")[this.validInputs];
	}
	

	public String[] winningAndSuplimentaryNumbers() {
		String suplimentaryNumbers = " ";
		String winningNumbers = this.winningNumberString(this.luckyGame.numberOfRandoms-2);
		
		suplimentaryNumbers += this.luckyGame.randomNumbers[this.luckyGame.numberOfRandoms-2] + " " + this.luckyGame.randomNumbers[this.luckyGame.numberOfRandoms-1] + " ";
		
		String[] returnArray = {winningNumbers, suplimentaryNumbers};
		return returnArray;		
	}
	
	public String winningNumberString(int numberOfWinningBalls) {
		String winningNumbers = " ";
		
		int i;
		for(i=0; i <numberOfWinningBalls; i++) {
			winningNumbers += this.luckyGame.randomNumbers[i] + " ";
		}
		
		return winningNumbers;		
	}
}
