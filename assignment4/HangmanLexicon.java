/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.io.*;
import java.util.*;
import acm.program.*;

public class HangmanLexicon extends ConsoleProgram{
	
	private ArrayList<String> strList = new ArrayList<String>();

	public HangmanLexicon(){
		try{
			BufferedReader rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while(true){
				try{
					String line = rd.readLine();
					if(line == null) break;
					strList.add(line);
				}catch(IOException ex){
					println("Bad file.");
				}
			}
			rd.close();
		}catch(IOException exep){
			println("File not found");
		}
	}
	
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return strList.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return strList.get(index);
		/*switch (index) {
			case 0: return "BUOY";
			case 1: return "COMPUTER";
			case 2: return "CONNOISSEUR";
			case 3: return "DEHYDRATE";
			case 4: return "FUZZY";
			case 5: return "HUBBUB";
			case 6: return "KEYHOLE";
			case 7: return "QUAGMIRE";
			case 8: return "SLITHER";
			case 9: return "ZIRCON";
			default: throw new ErrorException("getWord: Illegal index");
		}*/
	};
	
}
