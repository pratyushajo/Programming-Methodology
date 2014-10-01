import acm.graphics.*;
import acm.program.*;
import acm.util.*;

public class randomCircles extends GraphicsProgram{
	
	public void run(){
		int i;
		for(i=0;i<10;i++){
			drawCircle();
		}
	}
	
	private void drawCircle(){
		double r = rgen.nextDouble(5, 50);
		double x = rgen.nextDouble(0, getWidth() - 2 * r);
		double y = rgen.nextDouble(0, getHeight() - 2 * r);
		GOval circle = new GOval(x, y, 2*r, 2*r);
		circle.setFilled(true);
		circle.setColor(rgen.nextColor());
		add(circle);
	}
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
