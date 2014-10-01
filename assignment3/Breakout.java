/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels.  On some platforms 
  * these may NOT actually be the dimensions of the graphics canvas. */
	public static final int APPLICATION_WIDTH = 600;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board.  On some platforms these may NOT actually
  * be the dimensions of the graphics canvas. */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 14;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 14;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
	private static final int DELAY = 7;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		for(int i=0 ; i < NTURNS; i++){
			NBRICKS = NBRICKS_PER_ROW * NBRICK_ROWS;
			NBOUNCES_PADDLE = 0;
			setup();
			playGame();
			//pause(DELAY);
			if(NBRICKS > 0){
				removeAll();
			}
			else if (NBRICKS == 0){
				printWinner();
				break;
			}
			//printScore();
		}
		
		if(NBRICKS > 0){
			label = new GLabel("You Lost! Game Over!" + " Your score is " + score);
			label.setFont("Times New Roman-16");
			add(label, getWidth()/2, getHeight()/2);
		}
	}
	
	private void setup(){
		createBricks();
		createPaddle();
		createBall();
	}
	
	private void createBricks(){
		int x = (WIDTH - (BRICK_WIDTH * NBRICKS_PER_ROW))/2;
		int y = 0;
		for(int i=0;i<NBRICK_ROWS;i++){
			for(int j=0; j<NBRICKS_PER_ROW; j++){
				brick = new GRect(x,y,BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				if(i<=2)
					brick.setFillColor(Color.red);
				else if(i<=5)
					brick.setFillColor(Color.orange);
				else if(i<=8)
					brick.setFillColor(Color.yellow);
				else if(i<=11)
					brick.setFillColor(Color.green);
				else //if(i<=14)
					brick.setFillColor(Color.cyan);
				add(brick);
				x += BRICK_WIDTH;
			}
			x = (WIDTH - (BRICK_WIDTH * NBRICKS_PER_ROW))/2 ;
			y += BRICK_HEIGHT + BRICK_SEP ;
		}
	}
	
	private void createPaddle(){
		double x = getWidth()/2 - PADDLE_WIDTH/2;
		double y = HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT; 
		paddle = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
		addMouseListeners();
	}
	
	public void mouseMoved(MouseEvent e){
		PADDLE_LASTX = e.getX();
		if ((e.getX() < getWidth() - PADDLE_WIDTH/2) && (e.getX() > PADDLE_WIDTH/2)) {
			paddle.setLocation(PADDLE_LASTX - PADDLE_WIDTH/2, HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
		}
	}
	
	private void createBall(){
		int x = getWidth()/2 - BALL_RADIUS;
		int y = getHeight()/2 - BALL_RADIUS;
		ball = new GOval(x, y, 2 * BALL_RADIUS, 2 * BALL_RADIUS);
		ball.setFilled(true);
		add(ball);
	}
	
	private void playGame(){
		waitForClick();
		getBallVelocity();
		while(true){
			moveBall();
			if(ball.getY() >= getHeight()){
				break;
			}
			
			if(NBRICKS == 0){
				break;
			}
		}
	}
	
	private void getBallVelocity(){
		vy = 3.0;
		vx = rgen.nextDouble(1.0,3.0);
		//if(rgen.nextBoolean(0.5))
			//vx = -vx;
	}
	
	private void moveBall(){
		ball.move(vx, vy);
		if ((ball.getX() - vx <= 0 && vx < 0 ) || (ball.getX() + vx >= (getWidth() - BALL_RADIUS*2) && vx>0)) {
			vx = -vx;
		}
		if ((ball.getY() - vy <= 0 && vy < 0)) {
			vy = -vy;
		}
		
		//Checking for collisions with paddle or brick.
		collider = getCollidingObject();
		if(collider == paddle && vy >= 0){
			bounceClip.play();
			vy = -vy;
			NBOUNCES_PADDLE++;
			if(NBOUNCES_PADDLE > 7){
				vx += 0.1;
			}
		}
		else if(collider != null){
			bounceClip.play();
			remove(collider);
			if (brick.getFillColor() == Color.CYAN){
				score += 10;
			}
			if (brick.getFillColor() == Color.GREEN){
				score += 20;
			}
			if (brick.getFillColor() == Color.YELLOW){
				score += 30;
			}
			if (brick.getFillColor() == Color.ORANGE){
				score += 40;
			}
			if (brick.getFillColor() == Color.RED){
				score += 50;
			}
			NBRICKS--;
			vy = -vy;
		}
		pause(DELAY);
	}
	
	private GObject getCollidingObject(){
		if(getElementAt(ball.getX(),ball.getY()) != null){
				return getElementAt(ball.getX(),ball.getY());
		}
		else if(getElementAt(ball.getX() + 2 * BALL_RADIUS,ball.getY()) != null){ 
				return getElementAt(ball.getX() + 2 * BALL_RADIUS,ball.getY());
		}
		else if(getElementAt(ball.getX(),ball.getY() + 2 * BALL_RADIUS) != null){
			return getElementAt(ball.getX(),ball.getY() + 2 * BALL_RADIUS);
		}
		else if(getElementAt(ball.getX() + 2 * BALL_RADIUS ,ball.getY() + 2 * BALL_RADIUS) != null){
			return getElementAt(ball.getX() + 2 * BALL_RADIUS ,ball.getY() + 2 * BALL_RADIUS);
		}
		else
			return null;
	}
	
	private void printWinner(){

		label = new GLabel("You Won!" + " Your score is " + score);
		label.setFont("Times New Roman-16");
		add(label, getWidth()/2, getHeight()/2);
			
	}
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private GRect brick;
	private GRect paddle;
	private GOval ball;
	private double PADDLE_LASTX;
	private double vx, vy;
	private GObject collider;
	private int NBRICKS; //to keep a count of the number of bricks.
	private GLabel label;
	private GLabel label1;
	private int NBOUNCES_PADDLE;  //to keep the count of the number of times it bounces on the paddle.
	private int score = 0;
	AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
}
