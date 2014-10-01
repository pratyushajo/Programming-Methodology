import acm.program.*;
import java.util.*;

public class uniqueName extends ConsoleProgram{
	public void run(){
		
		ArrayList<String> names = new ArrayList<String>();
		while(true){
			String newName = readLine("Enter name: ");
			if(newName.equals("")) break;
			if(!(names.contains(newName)))
					names.add(newName);
		}
	
		println("Unique name list contains: ");
		for(int i=0;i<names.size();i++)
			println(names.get(i));
	}
	
	
}
