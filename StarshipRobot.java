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
 * This is a class named StarshipRobot which extends the class MovingThing. 
 * It represents a robot moving towards a destination point to deliver food 
 * to badgers. It has a private field named destination which is the point 
 * where the robot is heading to, and another private field named source 
 * which is the point from where the robot starts its journey. The class has 
 * a constructor that takes the source, destination, and speed as input 
 * parameters and sets the start point of the robot to the (x,y) position 
 * of the source. The class overrides the draw() method of the parent class 
 * and updates the position of the robot by calling the go() method and then 
 * draws the image of the robot on the display window. The class also provides 
 * methods to check whether the robot is over a specific thing, move the robot 
 * towards the destination, and make the robot go towards the destination by 
 * swapping the source and destination and flipping the robot horizontally if 
 * it has reached its destination.
 * @author Manav Darooka
 *
 */
public class StarshipRobot extends MovingThing{
  /* destination point of this StarshipRobot at its current journey 
  delivering food to badgers */
  private Thing destination;
  /* source point of this StarshipRobot at its current journey 
  delivering food to badgers */
  private Thing source;
  
  /** Creates a new StarshipRobot and sets its source, destination,
   *  and speed. The start point of this this StarshipRobot is set 
   *  to the (x,y) position of source. 
   * 
   * @param source
   * @param destination
   * @param speed
   */
  StarshipRobot(Thing source, Thing destination, int speed) {
    super(source.x, source.y, speed, "starshipRobot.png");
    this.source = source;
    this.destination = destination;
    if(source.x < destination.x) {
      isFacingRight = true;
    } else {
      isFacingRight = false;
    }
  }
  
/**
 * Overrides the draw method of the parent class and calls the go 
 * method to update the position of the Thing object, then calls 
 * the draw method of the parent class to draw the image of the 
 * Thing object to the display window.
 */
  @Override
  public void draw() {
    go();
    super.draw();
  }
  
  /** Checks whether this StarshipRobot is over a specific Thing
   * 
   * @param thing
   * @return true if this StarshipRobot is over the Thing object passed 
   * as input, otherwise, returns false.
   */
  public boolean isOver(Thing thing) {
    boolean condition1 = x - (float)image().width / 2 < thing.x + (float)thing.image().width / 2;
	boolean condition2 = x + (float)image().width / 2 > thing.x - (float)thing.image().width / 2;
	boolean condition3 = y - (float)image().height / 2 < thing.y + (float)thing.image().height / 2;
	boolean condition4 = y + (float)image().height / 2 > thing.y - (float)thing.image().height / 2;
		
	return ( condition1 && condition2 && condition3 && condition4);  

  }
  
  /**
   * This method moves an object towards a given destination by calculating
   * the distance between the current position of the object and the destination,
   * and moving the object in the direction of the destination with a given speed.
   */
  private void moveTowardsDestination() {
    float dx = destination.x - x;
    float dy = destination.y - y;
    float d = (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    x = x + ((float) speed * dx / d);
    y = y + ((float) speed * dy / d);
  }
  
  /** 
   * Moves this object towards its destination by calling moveTowardsDestination() method. 
   * If this object is over its destination, swaps the source and destination, and flips 
   * the object horizontally.
  */
  public void go() {
    moveTowardsDestination();
    if(isOver(destination)) {
      Thing temp = source;
      source = destination;
      destination = temp;
      isFacingRight = !isFacingRight;
    }
  }
}