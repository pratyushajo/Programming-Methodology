/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;
import acm.util.ErrorException;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		/* You fill this in */
		int x = (getWidth()/2 - BEAM_LENGTH);
		int y = (getHeight()/2 - (SCAFFOLD_HEIGHT)/2);
		removeAll();
		scaffold = new GLine(x, y, x, (y + SCAFFOLD_HEIGHT));
		add(scaffold);
		beam = new GLine(x, y, (x + BEAM_LENGTH), y);
		add(beam);
		rope = new GLine((x + BEAM_LENGTH), y, (x + BEAM_LENGTH), (y + ROPE_LENGTH));
		add(rope);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		/* You fill this in */
		int x = (getWidth()/2 - BEAM_LENGTH - 10);
		int y = (getHeight() - 30);
		if(getElementAt(x,y) != null){
			remove(wordDisplay);
		}
		wordDisplay = new GLabel(word);
		wordDisplay.setFont("Times New Roman-22");
		add(wordDisplay, x, y);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		/* You fill this in */
		int x = (getWidth()/2 - BEAM_LENGTH - 10);
		int y = (getHeight() - 10);
		result = result + letter;
		if(getElementAt(x,y) != null){
			remove(incorrectGuess);
		}
		incorrectGuess = new GLabel(result);
		incorrectGuess.setFont("Times New Roman-16");
		add(incorrectGuess, x, y);
		numOfGuess = result.length();
		drawMan(numOfGuess);
	}
	
	private void drawMan(int num){
		int x = getWidth()/2;
		int y = (getHeight()/2 - (SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH);
		int ArmY = (y + (2 * HEAD_RADIUS) + ARM_OFFSET_FROM_HEAD);
		int LegY = y + (2 * HEAD_RADIUS) + BODY_LENGTH;
		switch(num){
			case 1: GOval face = new GOval((x- HEAD_RADIUS), y, 2 * HEAD_RADIUS, 2 * HEAD_RADIUS);
					add(face);
					break;
			case 2: GLine body = new GLine(x, (y + (2 * HEAD_RADIUS)), x, (y + (2 * HEAD_RADIUS) + BODY_LENGTH));
					add(body);
					break;
			case 3: GLine leftHand = new GLine(x, ArmY , x - UPPER_ARM_LENGTH, ArmY);
					GLine leftArm = new GLine(x - UPPER_ARM_LENGTH, ArmY, x - UPPER_ARM_LENGTH, ArmY + LOWER_ARM_LENGTH);
					add(leftHand);
					add(leftArm);
					break;
			case 4: GLine rightHand = new GLine(x, ArmY, x + UPPER_ARM_LENGTH, ArmY);
					GLine rightArm = new GLine(x + UPPER_ARM_LENGTH, ArmY, x + UPPER_ARM_LENGTH, ArmY + LOWER_ARM_LENGTH);
					add(rightHand);
					add(rightArm);
					break;
			case 5: GLine leftHip = new GLine(x, LegY, x - HIP_WIDTH, LegY);
					GLine leftLeg = new GLine(x - HIP_WIDTH, LegY, x - HIP_WIDTH, (LegY + LEG_LENGTH));
					add(leftHip);
					add(leftLeg);
					break;
			case 6: GLine rightHip = new GLine(x, LegY, x + HIP_WIDTH, LegY);
					GLine rightLeg = new GLine(x + HIP_WIDTH, LegY, x + HIP_WIDTH, (LegY + LEG_LENGTH));
					add(rightHip);
					add(rightLeg);
					break;
			case 7: GLine leftFoot = new GLine((x - HIP_WIDTH), (LegY + LEG_LENGTH) ,(x - HIP_WIDTH - FOOT_LENGTH), (LegY + LEG_LENGTH));
					add(leftFoot);
					break;
			case 8: GLine rightFoot = new GLine((x + HIP_WIDTH), (LegY + LEG_LENGTH) ,(x + HIP_WIDTH + FOOT_LENGTH), (LegY + LEG_LENGTH));
					add(rightFoot);
					break;
			default: throw new ErrorException("getWord: Illegal index");
		}
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

	private GLine scaffold;
	private GLine beam;
	private GLine rope;
	private GLabel incorrectGuess;
	private String result = "";
	private GLabel wordDisplay;
	private int numOfGuess;
}
