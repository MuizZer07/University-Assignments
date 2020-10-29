
public class PowerBallGame extends LuckyGame{
	
	private int powerball;
	private int userChosenPowerball;
	private String powerballWinner =  "don't ";
	
	public PowerBallGame(String name, String dayOfGame, int lowerValue, int higherValue) {
		super(name, dayOfGame, lowerValue, higherValue);
		this.setNumberOfRandoms();
		this.generatePowerball();
		this.userInput = new UserInput(this);
	}

	@Override
	public void setNumberOfRandoms() {
		this.numberOfRandoms = 8;
		this.dayOfGame = "Thursday";	
		this.randomNumbers = new String[super.numberOfRandoms];
		this.createRandomStrings();
	}

	@Override
	public void collectUserInput(String userInput) {
		String[] inputs = userInput.split(" ");
		String Numbers = this.userInput.winningNumberString(7);
		int i = 0;
		
		for(i=0; i<inputs.length-1; i++) {
			int number = Integer.parseInt(inputs[i]);
			if(this.userInput.checkValidRange(number)) {	
				if(inputs[i].length() == 1)
					inputs[i] = " " + inputs[i] + " ";
				
				if(!this.userInput.checkRepeatedInput(number, inputs[i]))
					continue;
				
				
				if(inputs[i].length() == 1)
					inputs[i] = " " + inputs[i] + " ";
				
				if(Numbers.contains(inputs[i]) && i != this.numberOfRandoms-1)
					this.winners++;
			}else {
				this.userInput.invalid = true;
			}
		}
		
		String powerBallString = this.userInput.getPowerballFromValidInputs();
		int powerBallNumber = Integer.parseInt(powerBallString);
		this.userChosenPowerball = powerBallNumber;
		
		if(this.powerball == this.userChosenPowerball) {
			this.powerballWinner = "";
		}
		
		if(this.userInput.invalid) {
			System.out.println("Invalid input");
		}
	}
	
	public void generatePowerball() {
		int number = (int) (Math.random() * (20 - 1 + 1) + 1);
		this.randomNumbers[this.numberOfRandoms-1] = String.valueOf(number);
		this.powerball = number;
	}
	
	
	public String toString() {
		String str = "The user chosen powerball is "+ this.userChosenPowerball  +" \n";
		
		str += this.dayOfGame + " PowerBall numbers are: \n";
		str += this.userInput.winningNumberString(7).trim();
		str += " and the POWERBALL is " + this.powerball + "\n";
		str += this.userInput.validInputs + " user picks between " + this.lowerValue + " and " + this.higherValue + " are\n";
		str += this.userInput.validInputString.trim() + "\n";
		str += "No. of winners " +  this.winners + " and you " + this.powerballWinner + "have the POWERBALL";
		
		return str; 
	}
	
	
}
