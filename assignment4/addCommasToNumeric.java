import acm.program.*;
import acm.util.*;

public class addCommasToNumeric extends ConsoleProgram{
	
	public void run(){
		while(true){
			String digits = readLine("Enter a numeric string: ");
			if (digits.length() == 0) break;
			println(addCommasToNumericString(digits));
		}
	}
	
	private String addCommasToNumericString (String str){
	
		String result = "";
		int len = str.length();
		int NDigits = 0;
		for(int i=len - 1; i >= 0; i--){
			result = str.charAt(i) + result;
			NDigits++;
			if((NDigits % 3 == 0) && i > 0)
				result = "," + result;
		}
		return result;
	}
}
