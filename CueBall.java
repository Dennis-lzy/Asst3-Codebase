package application;

import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;

/**
 * This class defines characteristics of a cue ball which extends the ball class
 */
public class CueBall extends Ball {


	/**
	 * Construct the cue ball with given parameters
	 * @param colour: colour of the cueball
	 * @param xPosition: x position of the cueball
	 * @param yPosition: y position of the cueball
	 * @param xVelocity: x velocity of the cueball
	 * @param yVelocity: y velocity of the cueball
	 * @param mass: mass of the cueball
	 * @param view: what is displayed visually on screen
	 */
	public CueBall(String colour, double xPosition, double yPosition, double xVelocity, double yVelocity,
			double mass, Circle view) {
		super(colour, xPosition, yPosition, xVelocity, yVelocity, mass,view);
		this.colour = "white";
	}

	@Override
	public String toString() {
		return "CueBall [colour=" + colour + ", xPosition=" + xPosition + ", yPosition=" + yPosition + ", xVelocity="
				+ xVelocity + ", yVelocity=" + yVelocity + ", mass=" + mass + "]";
	}



    /**
	 * Returns If cueball is at rest
	 * @return If cueball is at rest
	 */
	public boolean atRest() {
		return Double.compare(this.getxVelocity(), 0) == 0 && Double.compare(this.getyVelocity(),0) == 0;
	}
	
	/**
	 * This method takes location where the mouse was clicked, calculate distance based on the location of the
	 * mouse and cue ball, and apply the new velocity to the cue ball
	 * @param x: x position of where the mouse was clicked
	 * @param y: y position of where the mouse was clicked
	 */
	public void registerShot(double x, double y) {
		this.setxVelocity((this.getView().getCenterX() - x)*2);
		this.setyVelocity((this.getView().getCenterY() - y)*2);
	}


	/**
	 * Checks collision
	 * @param ball2: ball to check collision against
	 * @return
	 */
	@Override
	public boolean isColliding(Ball ball2) {
		return super.isColliding(ball2);
	}

	/**
	 * Calculates position
	 * @param tableBounds: boundaries of the table
	 */
	@Override
	public void calculatePosition(Bounds tableBounds) {
		super.calculatePosition(tableBounds);

	}
}
