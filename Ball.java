package application;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
/**
 * This is an abstract class that defines the object type ball.
 *
 */
public abstract class Ball  {
	private Circle view;
	protected String colour;
	protected double xPosition;
	protected double yPosition;
	protected double xVelocity;
	protected double yVelocity;
	protected double mass;
	protected double radius;
	/**
	 * Construct Ball Object with given parameters
	 * @param colour: colour of the ball
	 * @param xPosition: x position of the ball relative to the scene
	 * @param yPosition: y position of the ball relative to the scene
	 * @param xVelocity: x velocity of the ball
	 * @param yVelocity: y velocity of the ball
	 * @param mass: mass of the ball
	 * @param view: stores a circle that visually represents the ball on screen
	 */
	public Ball(String colour, double xPosition, double yPosition, double xVelocity, double yVelocity, double mass, Circle view) {
		super();
		this.colour = colour;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		this.mass = mass;
		this.radius = 10.0;	// Default radius 10.0cm
		this.view = view;
	}

	public Ball(Ball b){
	    super();
        this.colour = b.getColour();
        this.xPosition = b.getxPosition();
        this.yPosition = b.getyPosition();
        this.xVelocity = b.getxVelocity();
        this.yVelocity = b.getyVelocity();
        this.mass = b.getMass();
        this.radius = 10.0;	// Default radius 10.0cm
        this.view = b.getView();
    }
	/**
	 *  Return colour of the ball
	 * @return colour of the ball
	 */
	public String getColour() {
		return colour;
	}
	/**
	 * Sets the colour of the abll
	 * @param colour colour of the ball
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}
	/**
	 * Returns the x position of the ball
	 * @return x position of the ball
	 */
	public double getxPosition() {
		return xPosition;
	}
	/**
	 *  Sets the x position of the ball
	 * @param xPosition x position of the ball
	 */
	public void setxPosition(double xPosition) {
		this.xPosition = xPosition;
	}
	/**
	 * 
	 * @return
	 */
	public double getyPosition() {
		return yPosition;
	}
	/**
	 * Sets the y position of the ball
	 * @param yPosition: y position of the ball
	 */
	public void setyPosition(double yPosition) {
		this.yPosition = yPosition;
	}
	/**
	 * Gets the x velocity of the ball
	 * @return x velocity of the ball
	 */
	public double getxVelocity() {
		return xVelocity;
	}
	/**
	 * Sets the x velocity of the ball
	 * @param xVelocity: x velocity of the ball
	 */
	public void setxVelocity(double xVelocity) {
		this.xVelocity = xVelocity;
	}
	/**
	 * Gets the y velocity of the ball
	 * @return y velocity of the ball
	 */
	public double getyVelocity() {
		return yVelocity;
	}
	/**
	 * Sets the y velocity of the ball
	 * @param yVelocity: y velocity of the ball
	 */
	public void setyVelocity(double yVelocity) {
		this.yVelocity = yVelocity;
	}
	/**
	 * Gets the mass of the ball
	 * @return mass of the ball
	 */
	public double getMass() {
		return mass;
	}
	/**
	 * Sets the mass of the ball
	 * @param mass: mass of the ball
	 */
	public void setMass(double mass) {
		this.mass = mass;
	}
	/**
	 * Returns the radius of the ball
	 * @return: radius of the ball
	 */

	public double getRadius() {
		return radius;
	}
	/**
	 * Sets the radius of the ball
	 * @param radius: radius of the ball
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}
	/**
	 * Returns the view of the ball
	 * @return view: of the ball
	 */
	public Circle getView() {
		return view;
	}
	/**
	 * Sets the view of the ball
	 * @param view: view of the ball
	 */
	public void setView(Circle view) {
		this.view = view;
	}
	/**
	 * Calculate position of the ball in next frame, change velocity if touching wall
	 * @param tableBounds: boundaries of the table
	 */
	public void calculatePosition(Bounds tableBounds) {
		double x = this.getView().getCenterX();
		double y = this.getView().getCenterY();
		// Do nothing if ball at best
		if(new Double(x).equals(new Double(0)) && new Double(y).equals(new Double(0))) {
			return;
		}
		// Increase x and y velocity based on animation shows 60 frames per second	
		x += this.getxVelocity() / 60;
		y += this.getyVelocity() / 60;
		// Detect if balls are touching the borders
		boolean atRightBorder = x > tableBounds.getWidth() - this.getRadius();
		boolean atLeftBorder = x < tableBounds.getMinX() + this.getRadius();
		boolean atTopBorder = y < tableBounds.getMinY() + this.getRadius();
		boolean atBottomBorder = y > tableBounds.getHeight() - this.getRadius();
		if(atLeftBorder || atRightBorder) {	// Left/right border
			this.setxVelocity(this.getxVelocity() * -1);
			return;
		}
		if(atTopBorder || atBottomBorder) {	// Top/bottom border
			this.setyVelocity(this.getyVelocity() * -1);
			return;
		}
		// Update position of the ball
		this.getView().setCenterX(x);
		this.getView().setCenterY(y);
		this.setxPosition(x);
		this.setyPosition(y);
	}
	
	/**
	 * If ball will collide in the next frame
	 * @param ball2: ball to check collision against
	 * @return boolean denoting if balls will collide in the next frame
	 */
	public boolean isColliding(Ball ball2) {
		// Create Node to represent location of the balls in the next frame and check if colliion
		// will happen in next frame
		double x = this.getView().getCenterX() + this.getxVelocity() / 60;
		double y = this.getView().getCenterY() + this.getyVelocity() / 60;
		double x2 = ball2.getView().getCenterX() + ball2.getxVelocity() / 60;
		double y2 = ball2.getView().getCenterY() + ball2.getyVelocity() / 60;
		Circle temp = new Circle(x, y, this.getView().getRadius());
		Circle temp2 = new Circle(x2, y2, ball2.getView().getRadius());
		// Detect collision based on if boundaries are touching each other
		Bounds bounds = temp.getBoundsInParent();
		Bounds bounds2 = temp2.getBoundsInParent();
		if(bounds.intersects(bounds2))
			return true;
		else 
			return false;
	}
}
