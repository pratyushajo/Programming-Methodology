/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		nameEntryList = new ArrayList<NameSurferEntry>();
	}
	
	private void drawGraph(){
		drawHorizontalLines();
		drawVerticalLines();
		drawDecades();
	}
	
	private void drawHorizontalLines(){
		
		int x = getWidth();
		int y = getHeight() - GRAPH_MARGIN_SIZE;
		GLine bottomLine = new GLine(0, y, x, y);
		add(bottomLine);
		GLine topLine = new GLine(0, GRAPH_MARGIN_SIZE, x, GRAPH_MARGIN_SIZE);
		add(topLine);
		drawVerticalLines();
	}
	
	private void drawVerticalLines(){
		double widthBetweenLines = 0;
		widthBetweenLines = getWidth() / NDECADES;
		double y =  0;
		y = getHeight();
		
		for(int i = 0; i < NDECADES; i++){
			
			double x = i * widthBetweenLines;
			GLine verticalLine = new GLine(x, 0, x, y);
			add(verticalLine);
			
		}
	}
	
	private void drawDecades(){
		int x = 0;
		int y = getHeight() - GRAPH_MARGIN_SIZE/4;
		for(int i = 1900; i<2010;){
			String label = Integer.toString(i);
			GLabel decade = new GLabel(label, x, y);
			add(decade);
			i += 10;
			x += getWidth() / NDECADES;
		}
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		// You fill this in //
		nameEntryList.clear();
	}
	
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
		nameEntryList.add(entry);
	}
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		// You fill this in //
		removeAll();
		drawGraph();
		if(nameEntryList.size() >= 0){
			for(int i=0; i <= nameEntryList.size(); i++){
				NameSurferEntry entry = nameEntryList.get(i);
				drawEntryGraph(entry, i);
			}
		}
	}
	
	private void drawEntryGraph(NameSurferEntry entry, int entryNumber){
		String name = "";
		for(int i=0; i<NDECADES; i++){
			int x1 = i * (getWidth() / NDECADES);
			int x2 = (i+1) * (getWidth() / NDECADES);
			int y1 = 0;
			int y2 = 0;
			int ranking1 = entry.getRank(i);
			int ranking2 = entry.getRank(i+1);
			if(ranking1 == 0 && ranking2 == 0){
				y1 = (getHeight() - GRAPH_MARGIN_SIZE);
				y2 = (getHeight() - GRAPH_MARGIN_SIZE);
				name = entry.getName() + "*";
			}
			else if(ranking1 == 0){
				y1 = (getHeight() - GRAPH_MARGIN_SIZE);
				y2 = ranking2;
			}
			else if(ranking2 == 0){
				y1 = ranking1;
				y2 = (getHeight() - GRAPH_MARGIN_SIZE);
				name = entry.getName() + "*";
			}
			else if(ranking1 > ranking2){
				if(ranking2 == 0){
					y1 = ranking1;
					y2 = (getHeight() - GRAPH_MARGIN_SIZE);
				}
				else{
					y1 = ranking1;
					y2 = ranking2;
				}
				name = " " + entry.getName() + " " + Integer.toString(entry.getRank(i));
			}
			else if(ranking1 < ranking2){
				if(ranking1 == 0){
					y1 = (getHeight() - GRAPH_MARGIN_SIZE);
					y2 = ranking2;
				}
				else{
					y1 = ranking1;
					y2 = ranking2;
				}
				name = " " + entry.getName() + " " + Integer.toString(entry.getRank(i));
			}
			GLine line = new GLine(x1, y1, x2, y2);
			add(line);
			GLabel label = new GLabel(name, x1, y1);
			add(label);
			if(entryNumber%4 == 1){
				line.setColor(Color.RED);
				label.setColor(Color.RED);
			}
			else if(entryNumber%4 == 2){
				line.setColor(Color.BLUE);
				label.setColor(Color.BLUE);
			}
			else if(entryNumber%4 == 3){
				line.setColor(Color.MAGENTA);
				label.setColor(Color.MAGENTA);
			}
		}
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	
	//Instance variables
	//private NameSurferEntry lastEntry;
	private ArrayList<NameSurferEntry> nameEntryList;
	//private NameSurferEntry newEntry;
}
