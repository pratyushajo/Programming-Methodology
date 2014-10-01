/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	// You fill in this part
	public void run()
	{
		while(true)
		{
			putBeeper();
			if(frontIsClear())
				move();
			else
			{
				if(facingEast())
				{
	    			if(leftIsClear())
	    				moveUpStreetLeft();
	    			else
	    				break;
				}
				else
				{
					if(rightIsClear())
	    				moveUpStreetRight(); 
					else
	    				break;
				}
			}
			if(frontIsClear())
				move();
			else
			{
				if(facingEast())
				{
	    			if(leftIsClear())
	    				moveUpStreetLeft();
	    			else
	    				break;
				}
				else
				{
					if(rightIsClear())
	    				moveUpStreetRight(); 
					else
	    				break;
				}
			}
		}
	}
    
    private void moveUpStreetLeft()
    {
    	turnLeft();
		move();
		turnLeft();
    }
    
    private void moveUpStreetRight()
    {
    	turnRight();
    	move();
    	turnRight();
    	//turnAround();
    }
}
