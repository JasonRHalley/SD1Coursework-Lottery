package lotterySystem;

public class Bet {
	//instance variables
	final int NUMBER_OF_NUMBERS = 6;
	private String playerName;
	public int[] numbers = new int[NUMBER_OF_NUMBERS];
	public int numberOfMatches = 0;
	public int winnings = 0;

	//constructor - places the name and numbers passed in to the name property and the numbers array
	public Bet(String playerName, int num1, int num2, int num3, int num4, int num5, int num6) {
		this.playerName = playerName;
		for(int counter = 0; counter < NUMBER_OF_NUMBERS; counter ++) {
			switch(counter) {
			case 0: numbers[counter] = num1;
			break;
			case 1: numbers[counter] = num2;
			break;
			case 2: numbers[counter] = num3;
			break;
			case 3: numbers[counter] = num4;
			break;
			case 4: numbers[counter] = num5;
			break;
			case 5: numbers[counter] = num6;
			break;
			}//end switch
		}//end for
	}//end bet

	//returns the player's name and their numbers as a string
	public String toString() {
		String output;
		output = this.playerName + '\n';
		for(int counter = 0; counter < numbers.length; counter++) {
			output += Integer.toString(numbers[counter]) + '\n';
		}//end for
		return output;
	}//end toString

	public String displayDetails() {
		return toString();
	}//end displayDetails

	//gets the player name
	public String getName() {
		return this.playerName;
	}//end getName

	//sets the player name
	public void setName(String name) {
		this.playerName = name;
	}//end setName

	//gets the array on winning numbers
	public int[] getNumbers() {
		return numbers;
	}//end getNumbers

	//sets the array of winning numbers
	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}//end SetNumbers

	//gets the number of matches
	public int getNumberOfMatches() {
		return numberOfMatches;
	}//end getNumberOfMatches

	//sets the number of matches
	public void setNumberOfMatches(int numberOfMatches) {
		this.numberOfMatches = numberOfMatches;
	}//end setNumberOfMatches

	// Increments the number of matching numbers
	public void addMatchingNumber() {
		this.numberOfMatches ++;
	}//end addMatchingNumber

	//gets the player's name and their winnings
	public String getWinningDetails() {
		String output;
		output = playerName + " " + "£" + winnings;
		return output;
	}//end getWinningDetails

}//end class
