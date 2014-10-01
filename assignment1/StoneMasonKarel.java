/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass solves the "repair the quad"
 * problem from Assignment 1.  It builds the columns by 
 * placing beepers at every 4th avenue till it reaches the end
 * of the street.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run()
	{
		if(frontIsClear())
		{
			turnLeft();
		    buildColumn();
		    turnAround();
		    moveToWall();
		    turnLeft();
		    if(frontIsClear())
		        moveToNextColumn();
	    }
		else
		{
		    turnLeft();
		    buildColumn();
	        turnAround();
	        moveToWall();
	        turnLeft();
		}
	}
	
	//Building the column by putting beepers
	//as long as Karel finds a wall
	private void buildColumn()
	{
		while(frontIsClear())
		{
			while(frontIsClear())
			{
				if(noBeepersPresent())
			    {
				   putBeeper();
				   move();
			    }
				else
				{
					move();
				}
			}
			if(noBeepersPresent())
			    putBeeper();
		}
	}
	
	//moving to the next square to build the next column 
	private void moveToNextColumn()
	{
		for(int i=0;i<4;i++)
		{
		    if(frontIsClear())
		    {
		    	move();
		    }
		}
		run();   
	}
	
	//Return to the bottom of the avenue 
	//after building the column
    private void moveToWall()
    {
	   while(frontIsClear())
	   {
		   move();
	   }
    }
}
