
public class TattslottoGame extends LuckyGame {

	public TattslottoGame(String name, String dayOfGame, int lowerValue, int higherValue) {
		super(name, dayOfGame, lowerValue, higherValue);
		this.setNumberOfRandoms();
		this.userInput = new UserInput(this);
	}
	
	public String toString() {
		String str = this.dayOfGame + " " + this.name + " numbers are:\n";
		String[] numbers = this.userInput.winningAndSuplimentaryNumbers();
		
		str += numbers[0].trim() + " supplementary numbers: " + numbers[1].trim() + "\n";
		str += this.userInput.validInputs + " user picks between " + this.lowerValue + " and " + this.higherValue + " are\n";
		str += this.userInput.validInputString.trim() + "\n";
		str += "No. of winners " +  this.winners + " + " + this.supps + " supps match";
		
		return str; 
	}
	

	@Override
	public void setNumberOfRandoms() {
		switch(this.name) {
			case "Tattslotto":
				this.numberOfRandoms = 8;
				this.dayOfGame = "Saturday";
				this.randomNumbers = new String[super.numberOfRandoms];
				this.createRandomStrings();
				break;
			case "OZ Lotto":
				this.numberOfRandoms = 9;
				this.dayOfGame = "Tuesday";
				this.randomNumbers = new String[super.numberOfRandoms];
				this.createRandomStrings();
				break;
		}
	}

	@Override
	public void collectUserInput(String userInput) {
		String[] inputs = userInput.split(" ");
		
		String[] Numbers = this.userInput.winningAndSuplimentaryNumbers();
		int i;
		
		for(i=0; i<inputs.length; i++) {
			int number = Integer.parseInt(inputs[i]);

			if(this.userInput.checkValidRange(number)) {				
				if(inputs[i].length() == 1)
					inputs[i] = " " + inputs[i] + " ";
				
				if(!this.userInput.checkRepeatedInput(number, inputs[i]))
					continue;
				
				if(inputs[i].length() == 1)
					inputs[i] = " " + inputs[i] + " ";

				if(Numbers[0].contains(inputs[i]))
					this.winners++;
				
				if(Numbers[1].contains(inputs[i]))
					this.supps++;
			}else {
				this.userInput.invalid = true;
			}		

		}	
		
		if(this.userInput.invalid) {
			System.out.println("Invalid input");
		}
	}

	
	
}
