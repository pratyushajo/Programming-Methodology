/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	public void run() {
		/* You fill this in. */
		int x = getWidth()/2;
		int y = getHeight()/2;
		GOval circle_1 = filledCircle(x,y,72,Color.RED);
		add(circle_1);
		GOval circle_2 = filledCircle(x,y,46.8,Color.WHITE);
		add(circle_2);
		GOval circle_3 = filledCircle(x,y,21.6,Color.RED);
		add(circle_3);
	}
	
	private GOval filledCircle(int x, int y, double r, Color color){
		GOval circle = new GOval(x-r,y-r,r*2,r*2);
		circle.setFilled(true);
		circle.setFillColor(color);
		return circle;
	}
}
