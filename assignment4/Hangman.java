/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.program.*;
import acm.util.*;


public class Hangman extends ConsoleProgram {
	
	private RandomGenerator rgen = RandomGenerator.getInstance();

	private HangmanLexicon hangmanWords = new HangmanLexicon();
	
	//maximum number of guesses allowed in the game
	private int numberOfGuesses = 8;
	
	public void init(){
		canvas = new HangmanCanvas();
		add(canvas);
	}
	
	public void run(){
		canvas.reset();
		println("Welcome to Hangman!");
		println("The word now looks like this: " + hiddenWord);
		playGame(word);
		
	}
	
	//choosing random word from the list of words given in file
	private String getWord(){
		int wordCount = hangmanWords.getWordCount();
		int index = rgen.nextInt(0, wordCount);
		word = hangmanWords.getWord(index);
		
		return word;
	}
	
	private void playGame(String word){
		while(true){
			displayHiddenWord(word);
			takeGuess(word);
			if(numberOfGuesses == 0) break;
		}
	}
	
	//to display the word in the form of '-' initially.
	private String displayHiddenWord(String word){
		String result = "";
		for(int i=0; i < word.length(); i++){
			result = result + '-';	
		}
		return result;
	}
	
	//playing the game. allowing the user to guess the letters.
	private void takeGuess(String word){
		while(numberOfGuesses > 0 && !(word.matches(hiddenWord))){
			guessesLeft();
			String guess = readLine("Your guess: ");
			ch = guess.charAt(0);
			while(true){
				if(guess.length() > 1){
					guess = readLine("You can enter only one letter at a time. Please try again: ");
				}
				
				if(guess.length() == 1) break;
			}
			
			if(Character.isLowerCase(ch)){
				ch = Character.toUpperCase(ch);
			}
			
			for(int i=0; i<word.length(); i++){
				if(ch == word.charAt(i)){
					println("That guess is correct.");
					if(i > 0){
						hiddenWord = hiddenWord.substring(0, i) + ch + hiddenWord.substring(i+1);
						canvas.displayWord(hiddenWord);
					}
					if(i == 0){
						hiddenWord = ch + hiddenWord.substring(1);
						canvas.displayWord(hiddenWord);
					}
					if(!(word.matches(hiddenWord)))
						println("The word now looks like this: " + hiddenWord);
					letterFound = true;
				}
			}
			
			if(letterFound == false){
				println("There are no " + ch + " in the word.");
				canvas.noteIncorrectGuess(ch);
				canvas.displayWord(hiddenWord);
				if(numberOfGuesses > 1)
					println("The word now looks like this: " + hiddenWord);
				numberOfGuesses--;
			}
			//displayWord(word,0);
			//guessesLeft();
			letterFound = false;
			
			if(word.matches(hiddenWord)){
				println("You guessed the word: " + word);
				println("You win.");
				canvas.displayWord(hiddenWord);
			}
		}
		
		if(numberOfGuesses == 0 && letterFound == false){
			println("You are completely hung.");
			println("The word was: " + word);
			println("You lose.");
			canvas.displayWord(hiddenWord);
		}
		
	}
	
	//To display the number of chances left to guess
	private void guessesLeft(){
		println("You have " + numberOfGuesses + " guesses left.");
	}
	
	private String word = getWord();
	private String hiddenWord = displayHiddenWord(word);
	private char ch;
	private boolean letterFound = false;
	private HangmanCanvas canvas;

}
