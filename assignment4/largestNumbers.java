import com.sun.org.apache.xpath.internal.operations.Bool;

import acm.program.*;

public class largestNumbers extends ConsoleProgram{

	private static final int SENTINEL = 0;
	
	public void run(){
		int num1, num2;
		int a;
		a = readInt("? ");
		num1 = num2 = a;
		
		while (a != SENTINEL){
			a = readInt("? ");
			if (a >= num1){
				num2 = num1;
				num1 = a;
			}
		}
		println("The largest value is " + num1);
		println("The second largest is " + num2);
	}
}
