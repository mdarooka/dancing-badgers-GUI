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
 * Creating a visual application to mimic the badger dance act on a basketball 
 * court is part of this task. The user can add or delete a number of badgers 
 * from the display window with this application, which goes by the name of 
 * Dancing Badgers. The mouse can be used to move them about the display window at 
 * will. The user has the ability to make the dancing badgers on the screen.
 * @author Manav Darooka
 *
 */
public class DancingBadgers extends processing.core.PApplet{
  // backgound image
  private static processing.core.PImage backgroundImage;
  // Maximum number of Badger objects allowed in the basketball court
  private static int badgersCountMax;
  // array storing badgers's dance show steps
  private static DanceStep[] badgersDanceSteps = new DanceStep[] {DanceStep.LEFT, 
      DanceStep.RIGHT, DanceStep.RIGHT, DanceStep.LEFT, DanceStep.DOWN, DanceStep.LEFT,
      DanceStep.RIGHT, DanceStep.RIGHT, DanceStep.LEFT, DanceStep.UP};
  // Tells whether the dance show is on.
  private boolean danceShowOn;
  // Generator of random numbers
  private static Random randGen;
  // array storing the positions of the dancing badgers at the start of the 
  // dance show
  private static float[][] startDancePositions = new float[][] {
    {300, 250}, {364, 250}, {428, 250}, {492, 250}, {556, 250}};
  // arraylist storing Thing objects
  private static ArrayList<Thing> things;
  
  /** 
   * Empty constructor that takes no argument and returns nothing
   */
  public DancingBadgers() {
    
  }
  
  /**
   * Driver method to run this graphic application
   * @param args
   */
  public static void main(String[] args) {
    PApplet.main("DancingBadgers");
    
  }
  
  /**
   * Sets the size of the display window of this graphic application
   */
  @Override
  public void settings() {
    this.size(800, 600);
  }
  
  /**
   * Sets the title and defines the initial environment properties of 
   * this graphic application. This method initializes all the data 
   * fields defined in this class.
   */
  @Override
  public void setup() {
    this.getSurface().setTitle("P05 Dancing Badgers");
    this.textAlign(3,3);
    this.imageMode(3);
    this.focused = true;
    
    randGen = new Random();
    
    backgroundImage = loadImage("images" + File.separator + "background.png");
    
    badgersCountMax = 5;
    
    Thing.setProcessing(this);
    things = new ArrayList<Thing>();
    things.add(new Thing(50,50,"target.png"));
    things.add(new Thing(750,550,"target.png"));
    things.add(new Thing(750,50,"shoppingCounter.png"));
    things.add(new Thing(50,550,"shoppingCounter.png"));
    
    things.add(new StarshipRobot(things.get(0),things.get(2),3));
    things.add(new StarshipRobot(things.get(1),things.get(3),5));
    
    Badger.setProcessing(this);
    things.add(new Basketball(50,300));
    things.add(new Basketball(750,300));
  }
  
  /**
   * Callback method that draws and updates the application display window. 
   * This method runs in an infinite loop until the program exits.
   */
  @Override
  public void draw() {
    background(color(255, 218, 185));
    image(backgroundImage, 400, 300);
    for (int i = 0; i < things.size(); i++) {
      things.get(i).draw();
    }
  }
  
  /**
   * Callback method called each time the user presses the mouse. This method 
   * iterates through the list of things. If the mouse is over a Clickable 
   * object, it calls its mousePressed method, then returns.
   */
  @Override
  public void mousePressed() {
    for(Thing thing: things) {
      if(thing instanceof Clickable && thing.isMouseOver()) {
        Clickable clickableThing = (Clickable)thing;
        clickableThing.mousePressed();
      }
    }
  }
  
  /** 
   * Callback method called each time the mouse is released. This method calls
   * the mouseReleased() method on every Clickable object stored in the things 
   * list.
   */
  @Override
  public void mouseReleased() {
    for(Thing thing: things) {
      if(thing instanceof Clickable) {
        Clickable clickableThing = (Clickable)thing;
        clickableThing.mouseReleased();
      }

    }
  }
  
  /**
   * Gets the number of Badger objects present in the basketball arena
   * @return count
   */
  public int badgersCount() {
    int badgerCount = 0;
    for(Thing thing: things) {
      if(thing instanceof Badger) {
        badgerCount++;
      }
    }
    return badgerCount;
  }
  
  /**
   * Sets the badgers start dance positions. The start dance positions 
   * of the badgers are provided in the startDancePositions array. 
   * The array startDancePositions contains badgersCountMax dance positions. 
   * If there are fewer Badger objects in the basketball arena, they will be 
   * assigned the first positions.
   */
  private void setStartDancePositions() {
    int count = 0;
    for(Thing thing: things) {
      if(thing instanceof Badger && count < startDancePositions.length) {
        thing.x = startDancePositions[count][0];
        thing.y = startDancePositions[count][1];
        count++;
      }
    }
  }
  
  /**
   * Callback method called each time the user presses a key. b-key: If the 
   * b-key is pressed and the danceShow is NOT on, a new badger is added to 
   * the list of things. Up to badgersCountMax can be added to the basketball 
   * arena. c-key: If the c-key is pressed, the danceShow is terminated 
   * (danceShowOn set to false), and ALL MovingThing objects are removed from 
   * the list of things. This key removes MovingThing objects ONLY. d-key: This 
   * key starts the dance show if the danceShowOn was false, and there is at 
   * least one Badger object in the basketball arena. Starting the dance show, 
   * sets the danceShowOn to true, sets the start dance positions of the Badger 
   * objects, and calls the startDancing() method on every Badger object present 
   * in the list of things. Pressing the d-key when the danceShowOn is true or 
   * when there are no Badger objects present in the basketball arena has no 
   * effect.r-key: If the danceShow is NOT on and the r-key is pressed when the 
   * mouse is over a Badger object, this badger is removed from the list of things. 
   * If the mouse is over more than one badger, the badger at the lowest index position 
   * will be deleted.s-key: If the s-key is pressed, the dancdShow is terminated and all 
   * the Badger objects stop dancing. Pressing the s-key does not remove any thing.
   */
  @Override
  public void keyPressed() {
    if(key == 'b' || key == 'B') {
      if(!danceShowOn) {
    	if(badgersCount() < badgersCountMax) {
    	  int widthBadger = randGen.nextInt(width);
    	  int heightBadger = randGen.nextInt(height);
          things.add(new Badger(widthBadger, heightBadger, badgersDanceSteps));
    	}
      }
    }
    else if(key == 'c' || key == 'C') {
      danceShowOn = false;
      
      for (int i = 0; i < things.size(); i++) {
        if (things.get(i) instanceof MovingThing) {
        	
          if (things.get(i) instanceof Badger) {
            ((Badger) things.get(i)).stopDancing();
          }
          things.remove(i--);
        }
      }
    }
    else if(key == 'd' || key == 'D') {

      int badgerCount = 0;
      for (Thing thing : things) {
        if (thing instanceof Badger) {
          Badger badger = (Badger) thing;

          badger.x = startDancePositions[badgerCount][0];
          badger.y = startDancePositions[badgerCount][1];     
          badgerCount = badgerCount + 1;
          
          badger.startDancing();
        }
      }
      if (!(badgerCount == 0)) {
        danceShowOn = true;
      }
    }
    else if(key == 'r' || key == 'R') {
      for (int i = 0; i < things.size(); i++) {
        Thing thing = things.get(i);
        if (!danceShowOn) {
          if(thing instanceof Badger && thing.isMouseOver())
            things.remove(i--);
        }
      }
    }
    else if(key == 's' || key == 'S') {
      danceShowOn = false;
      for (Thing thing : things) {
        if (thing instanceof Badger) {
          ((Badger) thing).stopDancing();
        }
      }
    }
  }
}