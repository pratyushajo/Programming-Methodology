import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import acm.program.*;

public class wordCount extends ConsoleProgram{
	
	private int lineNumber = 0;
	private int charNumber = 0;
	private int wordCount = 0;
	private int punctuationCount = 0;
	
	public void run(){
		try{
			BufferedReader rd = openFileReader("File: ");
			try{
				while(true){
						String line = rd.readLine();
						if(line == null) break;
						lineNumber++;
						for(int i=0; i<line.length();i++){
							char ch = line.charAt(i);
							if(Character.isLetterOrDigit(ch)){
								charNumber++;
							}
							else if(Character.isSpaceChar(ch)){
								wordCount++;
								charNumber++;
							}
							else{
								punctuationCount++;
							}
						}
						wordCount++;
				}
			}catch(IOException ex){
				println("Bad file.");
			}
			rd.close();
		}catch(IOException ex){
			println("Bad file.");
		}
		charNumber = charNumber + punctuationCount;
		println("Lines = " + lineNumber);
		println("Words = " + wordCount);
		println("Chars = " + charNumber);
	}
	
	private BufferedReader openFileReader (String prompt){
		
		BufferedReader rd = null;
		while(rd == null){
			String fileName = readLine(prompt);
			try{
				rd = new BufferedReader(new FileReader(fileName));
			}catch(IOException ex){
				println("Bad file.");
			}
		}
		return rd;
	}
}
