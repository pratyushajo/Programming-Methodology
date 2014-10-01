/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import com.sun.org.apache.xpath.internal.operations.Bool;

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	private static final int SENTINEL = 0;
	
	public void run() {
		/* You fill this in */
		int min = 0;
		int max = 0;
		int a = 0;
		println("This program finds the largest and smallest numbers.");
		
		a = readInt("? ");
		min = max = a;
		
		if(min == SENTINEL && max == SENTINEL)
			println("No integer has been entered.");
		
		while(a != SENTINEL)
		{
			a = readInt("? ");
			
			 if(a < min) 
			 {
			      if (a != SENTINEL)
			        min = a;
			 }
			
			 else if(a > max)
			 {
			       if (a != SENTINEL)
			    	max = a; 
			 }
		}
		println("Smallest: " + min);
		println("Largest: " + max);
	}
}

