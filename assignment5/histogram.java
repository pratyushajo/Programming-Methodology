import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import acm.program.*;

public class histogram extends ConsoleProgram{
	
	public void run(){
		createHistogram();
		readFile();
		displayHistogram();
		
	}
	
	private void createHistogram(){
		histogramArray = new int[11];
		for(int i=0; i<11; i++){
			histogramArray[i] = 0;
		}
	}
	
	private void readFile(){
		int score;
		try{
			BufferedReader rd = new BufferedReader(new FileReader("MidtermScores.txt"));
			while(true){
				String line = rd.readLine();
				if(line.equals("")) break;
				score = Integer.parseInt(line);
				if(score < 0 || score > 100)
					println("Score is out of range: " + score);
				else { 
					int range = score/10;
					histogramArray[range]++;
				}
			}
		}catch(IOException ex){
			println("Bad file.");
		}
	}
	
	private void displayHistogram(){
		println("00-09: " + printStars(1));
		println("10-19: " + printStars(2));
		println("20-29: " + printStars(3));
		println("30-39: " + printStars(4));
		println("40-49: " + printStars(5));
		println("50-59: " + printStars(6));
		println("60-69: " + printStars(7));
		println("70-79: " + printStars(8));
		println("80-89: " + printStars(9));
		println("90-99: " + printStars(10));
		println("100: " + printStars(11));
	}
	
	private String printStars(int star){
		String stars = "";
		for(int i =0; i < histogramArray[star - 1]; i++)
			stars += "*";
		return stars;
	}
	
	private int[] histogramArray;
}
