/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		// You fill this in
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		createInteractors();
		addActionListeners();
		canvas = new FacePamphletCanvas();
		add(canvas);
		
    }
    
	private void createInteractors(){
		/*North interactors */
		add(new JLabel("Name"), NORTH);
		
		textField = new JTextField(TEXT_FIELD_SIZE);
		textField.addActionListener(this);
		
		add(textField, NORTH);
		
		JButton addButton = new JButton("Add");
		JButton deleteButton = new JButton("Delete");
		JButton lookupButton = new JButton("Lookup");
		
		add(addButton, NORTH);
		add(deleteButton, NORTH);
		add(lookupButton, NORTH);
		
		/*West interactors */
		statusTextField = new JTextField(TEXT_FIELD_SIZE);
		statusTextField.addActionListener(this);
		add(statusTextField, WEST);
		
		JButton statusButton = new JButton("Change Status");
		add(statusButton, WEST);
		
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		pictureTextField = new JTextField(TEXT_FIELD_SIZE);
		pictureTextField.addActionListener(this);
		add(pictureTextField, WEST);
		
		JButton pictureButton = new JButton("Change Picture");
		add(pictureButton, WEST);
		
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		friendTextField = new JTextField(TEXT_FIELD_SIZE);
		friendTextField.addActionListener(this);
		add(friendTextField, WEST);
		
		JButton friendButton = new JButton("Add Friend");
		add(friendButton, WEST);
		
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		
	}
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		// You fill this in as well as add any additional methods
    	String cmd = e.getActionCommand();
    	String name = textField.getText();
    	profile = new FacePamphletProfile(name);    //current profile
    	
    	//Add profile
    	if(cmd.equals("Add")){
    		if(!name.equals("")){
    			if(profiledb.containsProfile(name)){
    				profile = profiledb.getProfile(name);
    				canvas.showMessage("Profile of " + name + " already exists: ");
    			}
    			else{
    				String msg = "Displaying " + profile.getName();
    				profiledb.addProfile(profile);
    				canvas.displayProfile(profile);
    				canvas.showMessage(msg);
    			}
    				
    		}
    	}
    	//Delete profile
    	else if(cmd.equals("Delete")){
    		if(!name.equals("")){
    			if(profiledb.containsProfile(name)){
    				profile = profiledb.getProfile(name);
    				canvas.showMessage("Profile of " + name + " deleted");
    				profiledb.deleteProfile(name);
    			}
    			else{
    				canvas.showMessage("Profile with name " + name + " does not exist");
    			}
    		}
    	}
    	//Lookup profile
    	else if(cmd.equals("Lookup")){
    		if(!name.equals("")){
    			if(profiledb.containsProfile(name)){
    				profile = profiledb.getProfile(name);
    				String msg = "Displaying " + profile.getName();
    				canvas.displayProfile(profile);
    				canvas.showMessage(msg);
    			}
    			else{
    				String msg = "No profile found";
    				canvas.showMessage(msg);
    			}
    		}
    	}
    	//Change status
    	else if(cmd.equals("Change Status") || e.getSource() == statusTextField){
    		if(!name.equals("")){
    			if(profiledb.containsProfile(name)){
    				profile = profiledb.getProfile(name);
    				profile.setStatus(statusTextField.getText());
    				canvas.displayProfile(profile);
    			}
    			else
    				canvas.showMessage("No such profile in database");
    		}
    	}
    	//Change picture
    	else if(cmd.equals("Change Picture") || e.getSource() == pictureTextField){
    		if(!pictureTextField.getText().equals("")){
    			if(profiledb.containsProfile(name)){
    				profile = profiledb.getProfile(name);
    				GImage image = null;
    				try{
    					image = new GImage(pictureTextField.getText());
    					profile.setImage(image);
    					canvas.displayProfile(profile);
    				}catch(ErrorException ex){
    					image = null;
    				}
    			}
    			else
    				canvas.showMessage("No profile found");
    		}
    	}
    	//Add friends
    	else if(cmd.equals("Add Friend") || e.getSource() == friendTextField){
    		if(!friendTextField.getText().equals("")){
    			profile = profiledb.getProfile(name);
    			String friendName = friendTextField.getText();
    			if(friendName.equals(profile.getName())){
    				canvas.showMessage("Cannot add yourself as friend");
    			}
    			else if(profiledb.containsProfile(friendName) && profiledb.containsProfile(profile.getName())){
    				FacePamphletProfile currentProfile = profiledb.getProfile(profile.getName());
    	    		FacePamphletProfile reciprocalFriend = profiledb.getProfile(friendName);
    	    		if(currentProfile.addFriend(friendName) == true){
    	    			currentProfile.addFriend(friendName);
    	    			//Adding in the current profile in the named friend
    	    			reciprocalFriend.addFriend(currentProfile.getName());
    	    			canvas.displayProfile(currentProfile);
    	    		}
    	    		else
    	    			canvas.showMessage("Friend already exists");
    	    	}
    	    	else
    	    		canvas.showMessage("No profile found");
    		}
    		else
    			canvas.showMessage("Please select a profile.");
    	}
	}
    
    
    //instance variables
    private JTextField textField;
    private JTextField statusTextField;
    private JTextField pictureTextField;
    private JTextField friendTextField;
    
    private FacePamphletProfile profile;
    private FacePamphletDatabase profiledb = new FacePamphletDatabase();
    private FacePamphletCanvas canvas;
}

