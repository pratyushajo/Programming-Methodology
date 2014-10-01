import acm.program.*;
import java.awt.event.*;
import javax.swing.*;
import acm.graphics.*;
import java.util.*;

public class boxDiagram extends GraphicsProgram{
	
	public void init(){
		
		contents = new HashMap<String,GObject>();
		componentList();
		addActionListeners();
		addMouseListeners();
		
	}
	
	private void componentList(){
		
		textField = new JTextField(25);
		textField.addActionListener(this);
		
		addButton = new JButton("Add") ; 
		removeButton = new JButton("Remove") ; 
		clearButton = new JButton("Clear") ; 
		
		add(new JLabel("Name"), SOUTH);
		add(textField, SOUTH);
		add(addButton, SOUTH);
		add(removeButton, SOUTH);
		add(clearButton, SOUTH);
		
	}
	
	private void drawRectangle(String name){
		box = new GCompound();
		rect = new GRect(BOX_WIDTH, BOX_HEIGHT);
		GLabel label = new GLabel(name);
		box.add(rect, -BOX_WIDTH/2, -BOX_HEIGHT/2);
		box.add(label, -label.getWidth()/2, label.getAscent()/2);
		add(box, getWidth()/2, getHeight()/2);
		contents.put(name, box);
	}
	
	private void removeBox(String name){
		GObject obj = contents.get(name);
		if(obj != null){
			remove(obj);
		}
	}
	
	private void clearScreen(){
		removeAll();
		contents.clear();
	}
	
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
		if( source == textField || source == addButton){
			drawRectangle(textField.getText());
		}

		else if(source == removeButton){
			removeBox(textField.getText());
		}
		
		else if (e.getActionCommand().equals("Clear")) {
			clearScreen(); // Clears the canvas
		}
	}
	
	public void mousePressed(MouseEvent e){
		last = new GPoint(e.getPoint());
		currentObject = getElementAt(last);
	}
	
	public void mouseDragged(MouseEvent e){
		if(currentObject != null){
			currentObject.move(e.getX() - last.getX(), e.getY() - last.getY());
			last = new GPoint(e.getPoint());
		}
	}
	
	public void mouseClicked(MouseEvent e){
		if(currentObject != null){
			currentObject.sendToFront();
		}
	}
	
	private JTextField textField;
	private GRect rect;
	private GCompound box;
	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;
	private JButton addButton;
	private JButton removeButton;
	private JButton clearButton;
	private HashMap<String,GObject> contents;
	private GPoint last;
	private GObject currentObject;
}
