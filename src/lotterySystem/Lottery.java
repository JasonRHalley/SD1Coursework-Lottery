package lotterySystem;

import java.util.Random;

public class Lottery {
	//instance variables
	final int NUMBER_OF_NUMBERS = 6;
	public int winningNumbers[] = new int[NUMBER_OF_NUMBERS];
	private final int MAX = 50;
	private final int MIN = 1;
	private Random rand = new Random();

	//picks 6 random numbers and places them in an array
	public void drawNumbers() {
		for(int count = 0; count < NUMBER_OF_NUMBERS; count++) {
			this.winningNumbers[count] = rand.nextInt(this.MAX) + this.MIN;
			for(int loop = 0; loop < this.winningNumbers.length; loop++) {
				if(this.winningNumbers[count] == this.winningNumbers[loop]) {
					this.winningNumbers[count] = rand.nextInt(this.MAX) + this.MIN;
				}//end if
			}//end for
		}//end for
	}//end drawNumbers

	//return the array of winning numbers
	public int[] getWinningNumbers() {
		return winningNumbers;
	}

	public void setWinningNumbers(int[] winningNumbers) {
		this.winningNumbers = winningNumbers;
	}
}//end class
