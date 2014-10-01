/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		/* You fill this in */
		int a;
		int b;
		int step = 0;
		a = readInt("Enter a number: ");
		while(a != 1)
		{
			if(a % 2 == 0)
			{
			    println(a + " is even, so I take half: " + a/2);
			    a = a/2;
			}
			else
			{
				println(a + " is odd, so I make 3n+1: " + ((3*a)+1));
			    a = (3*a)+1;
			}
			step++;
		}
		println("The process took " + step + " to reach 1.");
	}
}

