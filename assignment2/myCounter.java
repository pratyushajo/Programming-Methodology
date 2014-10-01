
public class myCounter {

	public myCounter(int startValue){
		counter = startValue;
	}
	
	public myCounter(){
		counter = 1;
	}
	
	public int nextValue() {
		int temp = counter;
		counter++;
		return temp;
	}
	
	private int counter;
}
