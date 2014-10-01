/*
  * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;
import java.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		/* You fill this in */
		setCategoriesUnselected();    //Setting all the categories as unselected and scores as 0 initially
		for(int k =0; k < N_SCORING_CATEGORIES; k++){
			for(int i=1; i <= nPlayers; i++){
				rollDice(i);
				for(int j=0; j < N_REROLL_ALLOWED; j++)
					reRollDice();
				selectCategory(i);
				calculateCategoryScore(i, category);
				calculateTotalScore(i);
				
			}
		}
		calculateUpperScore();
		calculateLowerScore();
	}
	
	//Called to roll the dice
	private void rollDice(int player){
		String name = playerNames[player - 1];
		display.printMessage(name + "'s turn! Click \"Roll Dice\" button to roll the dice.");
		display.waitForPlayerToClickRoll(player);
		for(int i=0; i< N_DICE; i++){
			dice[i] = rgen.nextInt(1, 6);
		}
		display.displayDice(dice);
	}
	
	//Called to re-roll is each player's turn.
	private void reRollDice(){
		display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\".");
		display.waitForPlayerToSelectDice();
		for(int i=0; i< N_DICE; i++){
			if(display.isDieSelected(i)){
				dice[i] = rgen.nextInt(1, 6);
			}
			display.displayDice(dice);
		}
	}
	
	private void setCategoriesUnselected(){
		categoryArray = new int[nPlayers+1][N_CATEGORIES+1];
		categoryScore = new int[nPlayers+1][N_CATEGORIES+1];
		for(int i=1;i<=nPlayers; i++){
			for(int j=1; j<=N_CATEGORIES; j++){
				categoryArray[i][j] = 0;
				categoryScore[i][j] = 0;
			}
		}
	}
	
	private void selectCategory(int player){
		while(true){
			display.printMessage("Select a category for this roll.");
			category = display.waitForPlayerToSelectCategory();
			if(categoryArray[player][category] == 1)
				display.printMessage("You already picked that category. Please choose another category.");
			
			else if(categoryArray[player][category] == 0){
				calculateCategoryScore(player, category);
				break;
			}
		}
	}
	
	private void calculateCategoryScore(int player, int category){
		categoryArray[player][category] = 1;
		if(checkCategory(player, category) == true){
			updateCategoryScore(player, category);
		}
	}
	
	private boolean checkCategory(int player, int category){
		boolean categoryMatch = false;
		if(category >= ONES && category <= SIXES || category == CHANCE){
			categoryMatch = true;
		}
		else 
		{
			ArrayList<Integer> ones = new ArrayList<Integer>();
			ArrayList<Integer> twos = new ArrayList<Integer>();
			ArrayList<Integer> threes = new ArrayList<Integer>();
			ArrayList<Integer> fours = new ArrayList<Integer>();
			ArrayList<Integer> fives = new ArrayList<Integer>();
			ArrayList<Integer> sixes = new ArrayList<Integer>();
			
			for(int i=0; i < N_DICE; i++){
				if(dice[i] == 1)
					ones.add(dice[i]);
				else if(dice[i] == 2)
					twos.add(dice[i]);
				else if(dice[i] == 3)
					threes.add(dice[i]);
				else if(dice[i] == 4)
					fours.add(dice[i]);
				else if(dice[i] == 5)
					fives.add(dice[i]);
				else if(dice[i] == 6)
					sixes.add(dice[i]);
			}
			
			if(category == THREE_OF_A_KIND){
				if(ones.size() >= 3 || twos.size() >= 3 || threes.size() >= 3 || fours.size() >= 3 || fives.size() >= 3 || sixes.size()>=3)
					categoryMatch = true;
			}
			else if(category == FOUR_OF_A_KIND){
				if(ones.size() >= 4 || twos.size() >= 4 || threes.size() >= 4 || fours.size() >= 4 || fives.size() >= 4 || sixes.size()>=4)
					categoryMatch = true;
			}
			else if(category == FULL_HOUSE){
				if(ones.size() >= 3 || twos.size() >= 3 || threes.size() >= 3 || fours.size() >= 3 || fives.size() >= 3 || sixes.size()>=3){
					if(ones.size() >= 2 || twos.size() >= 2 || threes.size() >= 2 || fours.size() >= 2 || fives.size() >= 2 || sixes.size()>=2)
						categoryMatch = true;
				}	
			}
			else if(category == YAHTZEE){
				if(ones.size() == 5 || twos.size() == 5 || threes.size() == 5 || fours.size() == 5 || fives.size() == 5 || sixes.size()== 5)
					categoryMatch = true;
			}
			else if(category == SMALL_STRAIGHT){
				if(ones.size() >= 1 && twos.size() >= 1 && threes.size() >= 1 && fours.size() >= 1)
					categoryMatch = true;
				else if(twos.size() >= 1 && threes.size() >= 1 && fours.size() >= 1 && fives.size() >= 1) 
					categoryMatch = true;
				else if(threes.size() >= 1 && fours.size() >= 1 && fives.size() >= 1 && sixes.size() >= 1)
					categoryMatch = true;
			}
			else if(category == LARGE_STRAIGHT){
				if(ones.size() == 1 && twos.size() == 1 && threes.size() == 1 && fours.size() == 1 && fives.size() == 1) 
					categoryMatch = true;
				else if(twos.size() == 1 && threes.size() == 1 && fours.size() == 1 && fives.size() == 1 && sixes.size() == 1)
					categoryMatch = true;
			}
		}
		return categoryMatch;
	}
	
	private void updateCategoryScore(int currentPlayer, int category){
		score = 0;
		if(category >= ONES || category <= SIXES){
			for(int i=0; i < N_DICE; i++){
				if(dice[i] == category){
					score += category;
				}
			}
		}
		else if(category == THREE_OF_A_KIND || category == FOUR_OF_A_KIND || category == CHANCE){
			for(int i=0; i < N_DICE; i++){
				score += dice[i];
			}
		}
		
		else if(category == FULL_HOUSE){
			score = 25;
		}
		else if(category == SMALL_STRAIGHT){
			score = 30;
		}
		else if(category == LARGE_STRAIGHT){
			score = 40;
		}
		else if(category == YAHTZEE){
			score = 50;
		}

		categoryScore[currentPlayer][category] = score;
		display.updateScorecard(category, currentPlayer, categoryScore[currentPlayer][category]);
	}
	
	private void calculateUpperScore(){
		for(int i=1; i <= nPlayers; i++){
			for(int j=1; j <= 6; j++){
				upperScore += categoryScore[i][j];
			}
			display.updateScorecard(UPPER_SCORE, i, upperScore);
			if(upperScore > 62){
				int upperScoreBonus = 35;
				display.updateScorecard(UPPER_BONUS, i, upperScoreBonus);
			}
			else
				display.updateScorecard(UPPER_SCORE, i, upperScore);
		}
	}
	
	private void calculateLowerScore(){
		for(int i=1; i <= nPlayers; i++){
			for(int j=9; j <= 15; j++){
				lowerScore += categoryScore[i][j];
			}
			display.updateScorecard(LOWER_SCORE, i, lowerScore);
		}
	}
	
	private void calculateTotalScore(int player){
		totalScore = new int[nPlayers+1];
		for(int i=1; i<=N_CATEGORIES; i++){
			totalScore[player] += categoryScore[player][i];
		}
		display.updateScorecard(17, player, totalScore[player]);
	}
	
		
/* Private instance variables */
	private int nPlayers;
	private int category;
	private int[][] categoryArray;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private int[] dice = new int[N_DICE];   //array for the dice
	private int N_REROLL_ALLOWED = 2;    //variable for the number of re-rolls that are allowed for each player in each turn.
	private int upperScore, lowerScore;
	private int[][] categoryScore;
	private int score;
	private int totalScore[];
	private RandomGenerator rgen = new RandomGenerator();

}
