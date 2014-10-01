/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends Karel {
	
	// You fill in this part
   public void run()
   {
	   moveToNewspaper();
	   returnHome();
   }

   //method to make Karel go to the newspaper and pick it up
   private void moveToNewspaper()
   {
	   moveWhileFrontIsClear();
	   turnRight();
	   move();
	   turnLeft();
	   move();
	   pickBeeper();
   }
   
   private void turnRight()
   {
	   turnLeft();
	   turnLeft();
	   turnLeft();
   }
   
   private void turnAround()
   {
	   turnLeft();
	   turnLeft();
   }
   
   //move Karel till it reaches a wall
   private void moveWhileFrontIsClear()
   {
	   while(frontIsClear())
	   {
		   move();
	   }
   }
   
   //After picking the newspaper, 
   //Karel returns to its original position
   private void returnHome()
   {
	   turnAround();
	   moveWhileFrontIsClear();
	   turnRight();
	   move();
	   turnRight();
	   
   }
}