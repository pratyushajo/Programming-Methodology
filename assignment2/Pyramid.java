/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Height of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 20;
	
	public void run() {
		/* You fill this in. */
		int z = BRICKS_IN_BASE;
		int x = (getWidth() - BRICKS_IN_BASE*BRICK_WIDTH)/2;
		int y = getHeight() - BRICKS_IN_BASE;
		for(int i=z;i>0;i--){
			for(int j=0;j<z;j++){
			GRect brick = new GRect(x,y,BRICK_WIDTH,BRICK_HEIGHT);
			add(brick);
			x += BRICK_WIDTH;
			}
			x = (getWidth() - z*BRICK_WIDTH)/2;
			x += BRICK_WIDTH/2;
			y -= BRICK_HEIGHT;
			z--;
		}
	}
}

