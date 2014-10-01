import acm.program.*;

public class FibSequence extends ConsoleProgram{
	private static final int MAX_TERM_VALUE = 10000;
	
	public void run(){
		int firstTerm = 0;
		int secondTerm = 1;
		int nextTerm = 0;
		
		println(firstTerm);
		println(secondTerm);
		
		while (nextTerm < MAX_TERM_VALUE ) {
			
			nextTerm = firstTerm + secondTerm;
			if(nextTerm > MAX_TERM_VALUE)
				break;
			println(nextTerm);
			firstTerm = secondTerm;
			secondTerm = nextTerm;	
		}
	}
}
