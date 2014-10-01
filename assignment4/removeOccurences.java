import acm.program.*;
import acm.util.*;

public class removeOccurences extends ConsoleProgram{
	
	public void run(){
		println(removeAllOccurences("This is a test", 't'));
		println(removeAllOccurences("Summer is here!", 'e'));
		println(removeAllOccurences("---0---", '-'));
		println(removeDoubledLetters("bookkeeper"));
	}
	
	private String removeAllOccurences(String str, char ch){
		String result = "";
		for(int i = 0; i<str.length(); i++){
			char c = str.charAt(i);
			if (ch != c)
				result = result + c;
		}
		return result;
	}
	
	private String removeDoubledLetters (String str){
		String result = "";
		for(int i=0; i < str.length(); i++){
			char c = str.charAt(i);
			if(i == 0 || c != str.charAt(i-1)){
				result += c;
			}
		}
		return result;
	}
}
