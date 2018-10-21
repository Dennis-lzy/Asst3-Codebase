package application;

import javafx.scene.Node;
import javafx.scene.shape.Circle;

/**
 * This is a subclass of ball. 
 *
 */
public class TargetBall extends Ball {
	/**
	 * Construct Target Ball Object with givern parameters
	 * @param colour: colour of the ball
	 * @param xPosition: x position of the ball relative to the scene
	 * @param yPosition: y position of the ball relative to the scene
	 * @param xVelocity: x velocity of the ball
	 * @param yVelocity: y velocity of the ball
	 * @param mass: mass of the ball
	 * @param view: stores a circle that visually represents the ball on screen
	 */
	public TargetBall(String colour, double xPosition, double yPosition, double xVelocity, double yVelocity,
			double mass, Circle view) {
		    super(colour, xPosition, yPosition, xVelocity, yVelocity, mass, view);
	}

	@Override
	public String toString() {
		return "TargetBall [colour=" + colour + ", xPosition=" + xPosition + ", yPosition=" + yPosition + ", xVelocity="
				+ xVelocity + ", yVelocity=" + yVelocity + ", mass=" + mass + "]";
	}

}
