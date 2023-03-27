//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    (descriptive title of the program making use of this file)
// Course:   CS 300 Spring 2023
//
// Author:   Manav Darooka
// Email:    mdarooka@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    No Partner
// Partner Email:   No Partner
// Partner Lecturer's Name: No Partner
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         No one
// Online Sources:  No one
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Random;
import java.util.ArrayList;
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/** The Thing class represents a graphic object that can be drawn to a display 
 * window using a PImage object. The class contains instance variables for the 
 * image, x and y positions, and a static PApplet object that represents the 
 * display window of the graphic application. The class also includes methods 
 * for drawing the object to the display window, setting the PApplet object, 
 * getting the image associated with the object, and checking if the mouse is 
 * currently over the object.
*/
public class Thing extends Object{
	// image of this graphic thing of type PImage
	private processing.core.PImage image;
	// x-position of this thing in the display window
	protected float x;
	// y-position of this thing in the display window
	protected float y;
	// PApplet object that represents the display window of this graphic application
	protected static processing.core.PApplet processing;
	
	/**
	 * Constructs a new Thing object with the given x and y position and image filename.
	 * 
	 * @param x the x-position of this Thing
	 * @param y the y-position of this Thing
	 * @param imageFilename the filename of the image to be loaded for this object
	 */
	public Thing(float x, float y, String imageFilename) {
		// x: x-position of this Thing
		// y: y-position of this Thing
		// imageFileName: filename of the image to be loaded for this object
		this.x = x;
		this.y = y;
		image = processing.loadImage("images" + File.separator + imageFilename);
	}
	
	/**
	 * Draws this Thing object to the display window using its image and position. 
	 * The image is drawn at the Thing's x and y position.
	*/
	public void draw() {
		processing.image(this.image, x, y);
	}
	
	/**
	 * Sets the static processing variable to the given PApplet object.
	 * @param processing the PApplet object to set as the processing variable
	*/
	public static void setProcessing(processing.core.PApplet processing) {
		Thing.processing = processing;
	}
	
	/**
	 * Returns the PImage object associated with this Thing object.
	 * 
	 * @return image
	 */
	public processing.core.PImage image() {
		return image;
	}
	
	/**
	 * Checks if the mouse is currently over this Thing object by comparing
	 *  the position of the mouse to the position of the object's image.
	 * 
	 * @return true if the mouse is over this Thing object, false otherwise
	 */
	public boolean isMouseOver() {
		boolean condition1 = x - (float)image.width/2 <= processing.mouseX;
		boolean condition2 = x + (float)image.width/2 >= processing.mouseX;
		boolean condition3 = y - (float)image.height/2 <= processing.mouseY;
		boolean condition4 = y + (float)image.height/2 >= processing.mouseY;
		
		return ( condition1 && condition2 && condition3 && condition4);
		        
	}
}

