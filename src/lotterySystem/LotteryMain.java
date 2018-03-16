package lotterySystem;

//required imports
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JOptionPane;

public class LotteryMain {
	//required properties
	static ArrayList <Bet> allBets = new ArrayList <Bet> ();
	static int numberOfBets = 0;
	final static int MAX_BETS = 20;
	static int totalWinnings = 0;
	final static int NUMBER_OF_NUMBERS = 6;
	private final static int MAX = 50;
	private final static int MIN = 1;
	private final static int ZERO_NUMBER_PRIZE = 0;
	private final static int ONE_NUMBER_PRIZE = 3;
	private final static int TWO_NUMBER_PRIZE = 6;
	private final static int THREE_NUMBER_PRIZE = 25;
	private final static int FOUR_NUMBER_PRIZE = 750;
	private final static int FIVE_NUMBER_PRIZE = 5000;
	private final static int SIX_NUMBER_PRIZE = 5000000;

	//main method - shows menu and calls chosen option
	public static void main(String[] args) {
		boolean menu = true;
		String selection;
		//shows the menu after each option has finished, unless exit is selected
		while(menu) {
			//makes draw if the max number of bets has been reached
			if(numberOfBets == MAX_BETS) {
				JOptionPane.showMessageDialog(null, "Max number of bets reached, we'll now draw the lottery", null, JOptionPane.PLAIN_MESSAGE);
				makeDraw();
			}
			selection = JOptionPane.showInputDialog("Choose an option:" + '\n' + "1 - Bet with chosen numbers" + '\n' + "2 - Bet with a lucky dip" + '\n' + "3 - Make the draw" + '\n' + "0 - Exit");

			//calls the method for the selected menu option
			switch (selection) {
			case "1":
				playWithNumbers();
				break;
			case "2":
				luckyDip();
				break;
			case "3":
				makeDraw();
				break;
			case "0":
				menu = false;
				System.exit(0);
				break;
			default:
				//ask for menu option again, if its not 1, 2, 3, or 0
				JOptionPane.showMessageDialog(null, "Invalid input, try again", null, JOptionPane.PLAIN_MESSAGE);
				break;
			}//end switch
		}//end while
	}//end psvm

	
	//calls the lottery to make the draw, then gets the array of winning numbers and passes this to the method seeWhosWon
	private static void makeDraw() {
		JOptionPane.showMessageDialog(null, "About to draw the lottery ..... good luck to all players", null, JOptionPane.PLAIN_MESSAGE);
		Lottery draw1 = new Lottery();
		int winningNums [] = new int[NUMBER_OF_NUMBERS];
		draw1.drawNumbers();
		System.arraycopy(draw1.winningNumbers, 0, winningNums, 0, winningNums.length);
		JOptionPane.showMessageDialog(null, "The winning numbers are: " + Arrays.toString(winningNums), null, JOptionPane.PLAIN_MESSAGE);
		seeWhosWon(winningNums);
	}

	//allows the user to place a lucky dip of 6 random numbers
	private static void luckyDip() {
		Random rand = new Random();
		//asks for user's name
		String tempName = JOptionPane.showInputDialog("Enter your name");
		int num1 = 0, num2 = 0, num3 = 0, num4 = 0, num5 = 0, num6 = 0, tempNum;
		boolean anotherBet = true;

		while(anotherBet){
			//generates 6 random numbers
			for(int counter = 0; counter < NUMBER_OF_NUMBERS; counter++) {
				tempNum = rand.nextInt(MAX) + MIN;
				switch(counter) {
				case 0: num1 = tempNum;
				break;
				case 1: num2 = tempNum;
				break;
				case 2: num3 = tempNum;
				break;
				case 3: num4 = tempNum;
				break;
				case 4: num5 = tempNum;
				break;
				case 5: num6 = tempNum;
				break;
				}//end switch
			}

			//creates a new bet, passing the name and 6 numbers
			Bet b1 = new Bet(tempName, num1, num2, num3, num4, num5, num6);
			JOptionPane.showMessageDialog(null, "Name: " + b1.getName() + '\n' + "Numbers: " + Arrays.toString(b1.getNumbers()), null, JOptionPane.PLAIN_MESSAGE);
			allBets.add(b1);
			numberOfBets ++;
			b1 = null;
			//if max number of bets now reached, calls the draw
			if(numberOfBets == MAX_BETS) {
				JOptionPane.showMessageDialog(null, "Max number of bets reached, we'll now draw the lottery", null, JOptionPane.PLAIN_MESSAGE);
				makeDraw();
			}
			//allows user to play another lucky dip
			int reply = JOptionPane.showConfirmDialog(null, "Do you want another lucky dip?");
			if(reply == JOptionPane.NO_OPTION) {
				anotherBet = false;
			}//end if
		}//end while
	}//end luckyDip

	//makes a bets with 6 chosen numbers
	private static void playWithNumbers() {
		String tempName;
		String tempNumAsString;
		int tempNum;
		int num1 = 0, num2 = 0, num3 = 0, num4 = 0, num5 = 0, num6 = 0;
		boolean anotherBet = true;
		//asks user's name
		tempName = JOptionPane.showInputDialog("Enter your name");

		while(anotherBet){
			for(int counter = 0; counter < NUMBER_OF_NUMBERS; counter++) {
				//takes a number input
				tempNumAsString = JOptionPane.showInputDialog("Enter a number between 1 & 50");
				tempNum = Integer.parseInt(tempNumAsString);
				//validates number is within max and min
				while(tempNum < MIN || tempNum > MAX) {
					tempNumAsString = JOptionPane.showInputDialog("Invalid number - Enter a number between 1 & 50");
					tempNum = Integer.parseInt(tempNumAsString);
				}//end while
				//validates number is unique
				while(tempNum == num1 || tempNum == num2 || tempNum == num3 || tempNum == num4 || tempNum == num5 || tempNum == num6) {
					tempNumAsString = JOptionPane.showInputDialog("Duplicate number - Enter a unique number");
					tempNum = Integer.parseInt(tempNumAsString);
				}//end while
				switch(counter) {
				case 0: num1 = tempNum;
				break;
				case 1: num2 = tempNum;
				break;
				case 2: num3 = tempNum;
				break;
				case 3: num4 = tempNum;
				break;
				case 4: num5 = tempNum;
				break;
				case 5: num6 = tempNum;
				break;
				}//end switch
			}//end for
			//declares new bet, passing name and 6 chosen numbers
			Bet b1 = new Bet(tempName, num1, num2, num3, num4, num5, num6);
			allBets.add(b1);
			numberOfBets ++;
			b1 = null;
			//calls the draw if the number of bets has reached its max
			if(numberOfBets == MAX_BETS) {
				JOptionPane.showMessageDialog(null, "Max number of bets reached, we'll now draw the lottery", null, JOptionPane.PLAIN_MESSAGE);
				makeDraw();
			}
			//asks user do they want to be again
			int reply = JOptionPane.showConfirmDialog(null, "Do you want another bet?");
			if(reply == JOptionPane.NO_OPTION) {
				anotherBet = false;
			}//end if
		}//end while
	}//end playWithNumbers

	//tests each players bet against the winning numbers to see how many they've matched and how much they've won
	public static void seeWhosWon(int[] winningNums) {
		//tests every player's bet
		for (Bet tempBet : allBets) {
			//tests every number in a player's bet
			for(int count = 0; count < NUMBER_OF_NUMBERS; count ++) {
				//tests player's number against all winning numbers
				for(int count1 = 0; count1 < NUMBER_OF_NUMBERS; count1++) {
					if(tempBet.numbers[count] == winningNums[count1]) {
						tempBet.addMatchingNumber();
						break;
					}//end if
				}//end for
			}//end for
			
			//sets player's winning to relevant value for the number of matches they have
			switch (tempBet.numberOfMatches) {
			case 0:
				tempBet.winnings = ZERO_NUMBER_PRIZE;
				totalWinnings += ZERO_NUMBER_PRIZE;
				break;
			case 1:
				tempBet.winnings = ONE_NUMBER_PRIZE;
				totalWinnings += ONE_NUMBER_PRIZE;
				break;
			case 2:
				tempBet.winnings = TWO_NUMBER_PRIZE;
				totalWinnings += TWO_NUMBER_PRIZE;
				break;
			case 3:
				tempBet.winnings = THREE_NUMBER_PRIZE;
				totalWinnings += THREE_NUMBER_PRIZE;
				break;
			case 4:
				tempBet.winnings = FOUR_NUMBER_PRIZE;
				totalWinnings += FOUR_NUMBER_PRIZE;
				break;
			case 5: 
				tempBet.winnings = FIVE_NUMBER_PRIZE;
				totalWinnings += FIVE_NUMBER_PRIZE;
				break;
			case 6:
				tempBet.winnings = SIX_NUMBER_PRIZE;
				totalWinnings += SIX_NUMBER_PRIZE;
				break;
			}//end switch
		}//end for each

		//shows the name and winnings for each player
		String output;
		output = "And the winners are... " + '\n';
		for (Bet tempBet : allBets) {
			output += tempBet.getWinningDetails() + '\n';
		}//end for each

		JOptionPane.showMessageDialog(null, output, null, JOptionPane.PLAIN_MESSAGE);
		profitLoss();
	}//end seeWhosWon

	//calculates how much money has been made or lost
	public static void profitLoss() {
		String output = null;
		int profitLoss;
		output = "Amount raised in bets " + "£" + numberOfBets + '\n';
		output += "Amount of money paid out in prizes " + "£" + totalWinnings + '\n';
		profitLoss = numberOfBets - totalWinnings;
		output += "Overal profit/loss " + "£" + profitLoss;
		//displays how much was made, how much was given in prizes, and the overall profit/loss figure
		JOptionPane.showMessageDialog(null, output, null, JOptionPane.PLAIN_MESSAGE);
		JOptionPane.showMessageDialog(null, "That's all folks, thanks for playing the Edinburgh Napier University Lottery", null, JOptionPane.PLAIN_MESSAGE);
		//terminates the program
		System.exit(0);
	}//end profitLoss
}//end class
