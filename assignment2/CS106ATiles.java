/*
 * File: CS106ATiles.java
 * Name: 
 * Section Leader: 
 * ----------------------
 * This file is the starter file for the CS106ATiles problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class CS106ATiles extends GraphicsProgram {
	
	/** Amount of space (in pixels) between tiles */
	private static final int TILE_SPACE = 20;
	//Width of the rectangles
	private static final int RECT_WIDTH = 150;
	//Height of the rectangles
	private static final int RECT_HEIGHT = 50;

	public void run() {
		/* You fill this in. */
		int x = (getWidth() - RECT_WIDTH)/2;
		int y = (getHeight() - RECT_HEIGHT - TILE_SPACE)/2;
		GRect program = new GRect(x, y ,RECT_WIDTH,RECT_HEIGHT);
		add(program);
		GRect cProgram = new GRect(x-(RECT_WIDTH-TILE_SPACE), y+(RECT_WIDTH-TILE_SPACE) ,RECT_WIDTH,RECT_HEIGHT);
		add(cProgram);
	}
}

