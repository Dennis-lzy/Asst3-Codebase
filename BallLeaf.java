package application;

import javafx.scene.shape.Circle;

public class BallLeaf extends Ball{
    /**
     * Construct Ball Object with given parameters
     *
     * @param colour    : colour of the ball
     * @param xPosition : x position of the ball relative to the scene
     * @param yPosition : y position of the ball relative to the scene
     * @param xVelocity : x velocity of the ball
     * @param yVelocity : y velocity of the ball
     * @param mass      : mass of the ball
     * @param view      : stores a circle that visually represents the ball on screen
     */
    public BallLeaf(String colour, double xPosition, double yPosition, double xVelocity, double yVelocity, double mass, Circle view) {
        super(colour, xPosition, yPosition, xVelocity, yVelocity, mass, view);
    }

    
}
