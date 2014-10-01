
import java.awt.event.*;
import acm.graphics.*;
import acm.program.*;

public class drawLine extends GraphicsProgram {
	public void init(){
		addMouseListeners();
	}
	
	public void mousePressed(MouseEvent e){
		double x = e.getX();
		double y = e.getY();
		line = new GLine(x,y,x,y);
		add(line);
	}
	
	public void mouseDragged(MouseEvent e){
		double x = e.getX();
		double y = e.getY();
		line.setEndPoint(x, y);
	}

	public GLine line;
}
