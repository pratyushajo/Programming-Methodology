/*
 * File: TextAndGraphics.java
 * --------------------------
 * This program shows an example of using text and two different
 * graphics canvases in a ConsoleProgram
 */

import acm.program.*;
import acm.graphics.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TextAndGraphics extends ConsoleProgram {
	
	public void init() {
		setLayout(new GridLayout(1, 3));
		
		// Note: console is first element of our layout
		
		leftCanvas = new GCanvas();
		add(leftCanvas);
		
		rightCanvas = new GCanvas();
		add(rightCanvas);
		
		textField = new JTextField(10);
		add(new JLabel("Some text"), SOUTH);
		add(textField, SOUTH);
		textField.addActionListener(this);
		
		add(new JButton("Draw left"), SOUTH);
		add(new JButton("Draw right"), SOUTH);
		
		addActionListeners();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == textField) {
	         println("You typed: " + textField.getText());
	    }
		String cmd = e.getActionCommand();
		if (cmd.equals("Draw left")) {
			leftCanvas.add(createFilledRect(), 20, leftY);
			leftY += SPACER;
		} else if (cmd.equals("Draw right")) {
			rightCanvas.add(createFilledRect(), 20, rightY);
			rightY += SPACER;
		}
	}
	
	private GRect createFilledRect() {
		// Yes, we should have made the 50 and 20 constants...
		GRect rect = new GRect(50, 20);
		rect.setFilled(true);
		return rect;
	}
	
	/* Constants */
	private static double SPACER = 30;
	
	/* Private instance variables */
	private GCanvas leftCanvas;
	private GCanvas rightCanvas;
	private JTextField textField;

	private double leftY = 10;
	private double rightY = 10;
}
