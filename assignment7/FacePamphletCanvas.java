/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;

import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		// You fill this in
	}
	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		// You fill this in
		double x = getWidth()/2 - msg.length()/2;
		double y = getHeight() - BOTTOM_MESSAGE_MARGIN;
		while(getElementAt(lastX,lastY) != null){
			remove(getElementAt(lastX,lastY));
		}
		lastX = x;
		lastY = y;
		GLabel label = new GLabel(msg, x, y);
		label.setFont(MESSAGE_FONT);
		add(label);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		// You fill this in 
		removeAll();
		String name = profile.getName();
		displayName(name);
		GImage image = profile.getImage();
		displayImage(image);
		displayStatus(profile.getStatus(), name);
		displayFriends(profile);
	}

	private void displayName(String name){
		double x = LEFT_MARGIN;
		double y = TOP_MARGIN;
		while(getElementAt(x,y) != null){
			remove(getElementAt(x,y));
		}
		nameLabel = new GLabel(name,x,y);
		nameHeight = nameLabel.getHeight();
		nameLabel.setFont(PROFILE_NAME_FONT);
		nameLabel.setColor(Color.BLUE);
		add(nameLabel);
	}
	
	private void displayImage(GImage image){
		double x = LEFT_MARGIN;
		double y = TOP_MARGIN + nameHeight + IMAGE_MARGIN;
		if(image != null){
			while(getElementAt(x,y) != null){
				remove(getElementAt(x,y));
			}
			image.setBounds(x, y, IMAGE_WIDTH, IMAGE_HEIGHT);
			add(image);
		}
		else{
			while(getElementAt(x,y) != null){
				remove(getElementAt(x,y));
			}
			imageBoxOutline = new GRect(x, y , IMAGE_WIDTH, IMAGE_HEIGHT);
			add(imageBoxOutline);
			imageLabel = new GLabel("No Image");
			imageLabel.setFont(PROFILE_IMAGE_FONT);
            double labelWidth = x + IMAGE_WIDTH/2 - imageLabel.getWidth()/2;
            double labelHeight = y + IMAGE_HEIGHT/2;
            add(imageLabel, labelWidth, labelHeight);
		}
	}
	
	private void displayStatus(String status, String name){
		double x = LEFT_MARGIN;
		double y = TOP_MARGIN + nameHeight + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN;
		if(!status.equals("")){
			while(getElementAt(x,y) != null){
				remove(getElementAt(x,y));
			}
			status = name + " is " + status;
			statusLabel = new GLabel(status, x, y);
		}
		else{
			while(getElementAt(x,y) != null){
				remove(getElementAt(x,y));
			}
			statusLabel = new GLabel("No current status", x, y);
		}
		statusLabel.setFont(PROFILE_STATUS_FONT);
		add(statusLabel);
	}
	
	private void displayFriends(FacePamphletProfile profile){
		double x = getWidth()/2;
		double y = TOP_MARGIN + nameHeight;
		GLabel friendsLabel = new GLabel("Friends:", x, y);
		friendsLabel.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(friendsLabel);
		Iterator<String> it = profile.getFriends();
		y += friendsLabel.getHeight();
		while(it.hasNext()){
			String nextFriend = it.next();
			GLabel friends = new GLabel(nextFriend, x, y);
			friends.setFont(PROFILE_FRIEND_FONT);
			add(friends);
			y += friends.getHeight();
		}
	}
	
	private double nameHeight = 0;
	private GLabel statusLabel = null;
	private GLabel nameLabel = null;
	private GRect imageBoxOutline = null;
	private GLabel imageLabel = null;
	private double lastX, lastY;
}
