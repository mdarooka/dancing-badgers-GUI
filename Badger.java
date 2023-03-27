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

/**
 * The Badger class extends the MovingThing class and implements the Clickable 
 * interface. It represents a badger object with the ability to dance and be 
 * dragged by the user's mouse. The Badger object contains an array storing the 
 * dance show steps, a boolean indicating whether the Badger is dancing or not, 
 * a boolean indicating whether the Badger is being dragged or not, and a float 
 * array storing the next dance position of the Badger. Additionally, the class 
 * stores the old mouse x and y positions, the index position of the current 
 * dance step, and the speed of the Badger. The class contains a constructor to 
 * initialize a new Badger object and a series of methods to control the dragging 
 * and dancing behavior of the Badger.
 * @author Manav Darooka
 *
 */
public class Badger extends MovingThing implements Clickable{
  // array storing this Badger's dance show steps
  private DanceStep[] danceSteps;
  // indicates whether this badger is dancing or not
  private boolean isDancing;
  // indicates whether this badger is being dragged or not
  private boolean isDragging;
  // stores the next dance (x, y) position of this Badger. 
  // nextDancePosition[0]: x-position 
  // nextDancePosition[1]: y-position
  private float[] nextDancePosition;
  // old x-position of the mouse
  private static int oldMouseX;
  // old y-position of the mouse
  private static int oldMouseY;
  // index position of the current dance step of this badger
  private int stepIndex;
  
  /**
   * Creates a new Badger object positioned at a specific position
   * of the display window and whose moving speed is 2. When created,
   * a new badger is not dragging and is not dancing.  This constructor
   * also sets the danceSteps of the created Badger to the one provided
   * as input and initializes stepIndex to 1.
   * @param x
   * @param y
   * @param danceSteps
   */
  public Badger(float x, float y, DanceStep[] danceSteps) {
    super(x, y, 2, "badger.png");
    this.isDancing = false;
    this.isDragging = false;
    this.stepIndex = 1;
    this.danceSteps = danceSteps;
  }
  
  /**
   * Draws this badger to the display window. Also, this method: 
   * - calls the drag() behavior if this Badger is dragging 
   * - calls the dance() behavior if this Badger is dancing
   */
  @Override
  public void draw() {
    if(isDragging) {
      drag();
    }
    if(isDancing) {
      dance();
    }
    super.draw();
  }
  
  /**
   * Checks whether this badger is being dragged
   * @return isDragging true if the badger is being dragged, false otherwise
   */
  public boolean isDragging() {
    return isDragging;
  }
  
  /**
   * Helper method to drag this Badger object to follow the mouse moves
   */
  private void drag() {
    int dx = processing.mouseX - oldMouseX;
    int dy = processing.mouseY - oldMouseY;
    x = x + dx;
    y = y + dy;
    x = Math.max(0, (Math.min(x, processing.width)));
    y = Math.max(0, (Math.min(y, processing.height)));
    oldMouseX = processing.mouseX;
    oldMouseY = processing.mouseY;
  }
  
  /**
   * Starts dragging this badger
   */
  public void stopDragging() {
    isDragging = false;
  }
  
  /**
   * Stops dragging this Badger object
   */
  public void startDragging() {
    oldMouseX = processing.mouseX;
    oldMouseY = processing.mouseY;
    isDragging = true;
    drag();
    
  }
  
  /**
   * Defines the behavior of this Badger when it is clicked. 
   * If the mouse is over this badger and this badger is NOT dancing, 
   * this method starts dragging this badger. 
   */
  public void mousePressed() {
    if(this.isMouseOver()) {
      this.startDragging();
    }
  }
  
  /**
   * Defines the behavior of this Badger when the mouse is released. 
   * If the mouse is released, this badger stops dragging.
   */
  public void mouseReleased() {
    isDragging = false;
  }
  
  /**
   * This helper method moves this badger one speed towards its 
   * nextDancePosition. Then, it checks whether this Badger is facing 
   * right and updates the isFacingRight data field accordingly. After 
   * making one move dance, a badger is facing right if the x-move towards 
   * its next dance position is positive, otherwise, it is facing left.
   * @return true if this Badger almost reached its next dance position, 
   * meaning that the distance to its next dance position is less than 2 
   * times its speed. Otherwise, return false.
   */
  private boolean makeMoveDance() {
    float dx = nextDancePosition[0] - x;
    float dy = nextDancePosition[1] - y;
    float d = (float) Math.sqrt(dx * dx + dy * dy);
    x = x + ((float) speed * dx / d);
    y = y + ((float) speed * dy / d);
    
    isFacingRight = dx > 0;
    
    boolean nextMove = d < 2*speed;
    return nextMove;  
  }
  
  /**
   * Implements the dance behavior of this Badger. This method prompts 
   * the Badger to make one move dance. If the makeMoveDance method call 
   * returns true (meaning the badger almost reached its nextDancePosition), 
   * this method MUST: 
   * - update its next dance position (see DanceStep.getPositionAfter()), 
   * - increment the stepIndex. 
   */
  private void dance() {
    if(makeMoveDance()) {
      stepIndex = (stepIndex + 1) % danceSteps.length;
      nextDancePosition = danceSteps[stepIndex].getPositionAfter(x, y); 
    }
  } 
  
  /**
   * Prompts this badger to start dancing. This method: 
   * - updates the isDancing data field
   * - stops dragging this badger
   * - sets stepIndex to zero
   * - Resets the nextDancePosition 
   */
  public void startDancing() {
    isDancing = true;
    stepIndex = 0;
    nextDancePosition = danceSteps[0].getPositionAfter(x, y);
    stopDragging();
    dance();
  }
  
  /**
   * Prompts this badger to stop dancing. Sets the isDancing data field to false.
   */
  public void stopDancing() {
    isDancing = false;
  }
}