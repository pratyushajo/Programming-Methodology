import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Robot extends GraphicsProgram{
	
	//Width of the head
	private static final int HEAD_WIDTH = 100;
	//Height of the head
	private static final int HEAD_HEIGHT = 150;
	//Radius of eyes
	private static final int EYE_RADIUS = 10;
	private static final int MOUTH_WIDTH = 60;
	private static final int MOUTH_HEIGHT = 20;
	
	public void run(){
		addFace(getWidth()/2, getHeight()/2);
	}
	
	private void addFace(double cx, double cy){
		addHead(cx,cy);
		addEye(cx - HEAD_WIDTH/4, cy - HEAD_HEIGHT/4);
		addEye(cx + HEAD_WIDTH/4, cy - HEAD_HEIGHT/4);
		addMouth(cx, cy + HEAD_HEIGHT/4);
	}
		
	private void addHead(double cx, double cy){
		double x = cx - HEAD_WIDTH / 2;
		double y = cy - HEAD_HEIGHT /2;
		
		GRect head = new GRect(x, y,HEAD_WIDTH,HEAD_HEIGHT);
		head.setColor(Color.gray);
		head.setFilled(true);
		add(head);	
	}	
		
	private void addEye(double cx, double cy){
		GOval eye = new GOval(cx - EYE_RADIUS , cy - EYE_RADIUS, 2 * EYE_RADIUS, 2 * EYE_RADIUS);
		eye.setColor(Color.YELLOW);
		eye.setFilled(true);
		add(eye);
	}
	
	private void addMouth(double cx, double cy){
		double x = cx - MOUTH_WIDTH/2;
		double y = cy - MOUTH_HEIGHT/ 2;
		GRect mouth = new GRect(x, y , MOUTH_WIDTH, MOUTH_HEIGHT);
		mouth.setColor(Color.WHITE);
		mouth.setFilled(true);
		add(mouth);
	}
	
}
