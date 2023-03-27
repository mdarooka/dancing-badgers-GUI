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
import java.util.Random;
import java.util.ArrayList;
import processing.core.PApplet;

/** 
 * MovingThing class represents a Thing that can move on the screen. It extends
 *  the Thing class and implements the Comparable interface. MovingThing has 
 *  a movement speed and a boolean flag indicating whether it is facing right 
 *  or not. MovingThing can be created with a specified speed, image file name,
 *  and initial x and y position. It can also be drawn to the screen and compared 
 *  with other MovingThing objects based on their speed.
*/
public class MovingThing extends Thing implements Comparable<MovingThing>{
	  // indicates whether this MovingThing is facing right or not
	  protected boolean isFacingRight;
	  // movement speed of this MovingThing
	  protected int speed;
	  
	  /** Creates a new MovingThing and sets its speed, image file, and 
	   * initial x and y position.
	   * 
	   * @param x
	   * @param y
	   * @param speed
	   * @param imageFileName
	   */
	  public MovingThing(float x, float y, int speed, String imageFileName){
	    super(x, y, imageFileName);
	    this.speed = speed;
	    this.isFacingRight = true;
	  }
	  
	  /** 
	   * Overrides the draw method of the parent class to draw the MovingThing
	   *  object to the display window using its image and position. The 
	   *  image is drawn at the x and y position, with the 
	   *  option to flip the image horizontally if it is not facing right.
	   *  @Override
	   */
	  @Override
	  public void draw() {
	    processing.pushMatrix();
	    processing.rotate(0.0f);
	    processing.translate(x, y);
	    if(!isFacingRight)
	      processing.scale(-1.0f, 1.0f);
	    processing.image(image(), 0.0f, 0.0f);
	    processing.popMatrix();
	  }
	  
	  /** 
	   * Overrides the compareTo method of the Comparable interface to compare 
	   * this MovingThing object with another MovingThing object based on their 
	   * speed. If the speed of this MovingThing is equal to the speed of the 
	   * other MovingThing, it returns 0. If the speed of this MovingThing is 
	   * less than the speed of the other MovingThing, it returns -1. If the 
	   * speed of this MovingThing is greater than the speed of the other 
	   * MovingThing, it returns 1.
	   * @param other
	   * @return 0 If the speed of this MovingThing is equal to the speed of the 
	   * other MovingThing, -1 if the speed of this movingThing is less than the 
	   * speed of the other MovingThing, 1 if the speed of this MovingThing is 
	   * greater than the speed of the other MovingThing
	   */
	  @Override
	  public int compareTo(MovingThing other) {
	    if(speed == other.speed) {
	      return 0;
	    } else if(speed < other.speed) {
	      return -1;
	    } else {
	      return 1;
	    }
	  }
	  
	}