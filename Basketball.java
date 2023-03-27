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

import java.io.File;
import processing.core.PApplet;

/** The Basketball class extends the Thing class and implements the Clickable 
 * interface. It represents a rotating basketball object that can be clicked 
 * and rotated. The class includes a constructor to create a new Basketball object,
 * a draw method to draw the basketball to the display window, and mousePressed and
 * mouseReleased methods to handle mouse input. Additionally, it includes a rotate 
 * method that increments the number of rotations that the basketball has made. 
 * The rotation angle of the basketball is defined by the rotation variable in 
 * radians, and the total number of rotations made is stored in the private variable
 * rotations. The initial rotation angle of the basketball object 
 * is set to PApplet.PI/2 when it is created.
 * @author Manav Darooka
*/
public class Basketball extends Thing implements Clickable{
	// Defines the rotation angle in radians that this Basketball object make 
	// when clicked.
	public float rotation; 
	// Total number of rotations this Basketball object has made since it was 
	// created.
	private int rotations;
	
	/* Creates a new Basketball object located at (x,y) position whose image 
	 * filename is "basketball.png", and sets its rotation angle to PApplet.PI/2.
	 *  Initially, when created, the basketball has made zero rotations. */
	public Basketball(float x, float y) {
		super(x, y, "basketball.png");
		this.x = x;
		this.y = y;
		rotation = PApplet.PI/2;
		rotations = 0;
	}
	
	/* Draws this rotating Basketball object to the display window. */
	@Override
	public void draw() {
		processing.pushMatrix();
		processing.translate(x, y);
		processing.rotate(this.rotations * rotation);
		processing.image(image(), 0.0f, 0.0f);
		processing.popMatrix();
	}
	
	/* Defines the behavior of this basketball when the mouse is pressed. 
	 * The basketball rotates when it is clicked (the mouse is over it when 
	 * pressed). */
	public void mousePressed() {
		if(isMouseOver()) {
			rotate();
		}
	}
	/* Called when the mouse is released. A basketball object does nothing 
	 * when the mouse is released. This is a method with an empty body. */
	public void mouseReleased() {}
	
	
	/* This method rotates this basketball object by incrementing the number 
	 * of its rotations by one. */
	public void rotate() {
		rotations++;
	}
}
