import acm.program.*;
import java.util.*;

public class countOccurence extends ConsoleProgram{
	
	private String name;
	private int count;
	
	public void run(){
		Map<String, Integer> nameList = new HashMap<String,Integer>();
		while(true){
			name = readLine("Enter name: ");
			if(name.equals("")) break;
			if(nameList.containsKey(name)){
				count = nameList.get(name);
				count = new Integer(count+1);
				nameList.put(name, count);
			}
			else{
				count = new Integer(1);
				nameList.put(name, count);
			}
		}
		printCount(nameList);
	}
	
	private void printCount(Map<String,Integer> map){
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			int count = map.get(key);
			println("Entry [" + key + "] has count " + count);
		}
	}
}
