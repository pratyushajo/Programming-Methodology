/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the top of the window.
 */
	public void init() {
	    // You fill this in, along with any helper methods //
		graph = new NameSurferGraph();
		add(graph);
		
		createInteractors();
		addActionListeners();
		
		file = new NameSurferDataBase(NAMES_DATA_FILE);
	}
	
	private void createInteractors(){
		textField = new JTextField(MAX_SIZE);
		textField.addActionListener(this);
		
		add(new JLabel("Name"), SOUTH);
		add(textField, SOUTH);
		
		graphButton = new JButton("Graph");
		clearButton = new JButton("Clear");
		
		add(graphButton, SOUTH);
		add(clearButton, SOUTH);
		
	
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		if(e.getActionCommand().equals("Clear")){
			graph.clear();
			graph.update();
		}
		else{
			String enteredText = textField.getText();
			entry = file.findEntry(enteredText);
			if(entry != null){
				graph.addEntry(entry);
				graph.update();
			}
		}
	}
	
	private static final int MAX_SIZE = 30;
	
	//instance variables
	private JTextField textField;
	private JButton graphButton;
	private JButton clearButton; 
	
	private NameSurferGraph graph;
	private NameSurferDataBase file;
	
	private NameSurferEntry entry;
}
