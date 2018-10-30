package application;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class is responsible for composite ball objects with children
 */
public class BallComposite extends Ball {
    private List<Ball> balls = new ArrayList<Ball>();
    private Circle view;
    protected String colour;
    protected double xPosition;
    protected double yPosition;
    protected double xVelocity;
    protected double yVelocity;
    protected double mass;
    protected double radius;
    private boolean isSunk = false;
    private double strength;

    /**
     * Constructs BallComposite
     * @param colour
     * @param xPosition
     * @param yPosition
     * @param xVelocity
     * @param yVelocity
     * @param balls
     * @param strength
     * @param view
     */

    public BallComposite(String colour, double xPosition, double yPosition, double xVelocity, double yVelocity, List<Ball> balls, double strength, Circle view) {
        super();
        this.colour = colour;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.radius = 10.0;	// Default radius 10.0cm
        this.view = view;
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
     * Checks if ball has been sunk
     * @return isSunk
     */
    public boolean isSunk() {
        return isSunk;
    }

    /**
     * Sets sunk status of ball
     * @param sunk
     */
    public void setSunk(boolean sunk) {
        this.isSunk = sunk;
    }

    /**
     * Adds ball to children
     * @param ball
     */
    public void add(Ball ball){
        balls.add(ball);
    }

    /**
     * removes ball child
     * @param ball
     */
    public void remove(Ball ball){
        balls.remove(ball);
    }

    /**
     * Gets a child ball
     * @param i
     * @return ball.get(i)
     */
    public Ball get(int i){
        return balls.get(i);
    }

    /**
     * Returns ball list
     * @return balls
     */
    public List<Ball> getBalls(){
        return balls;
    }

    /**
     * Calculates position while iterating through children
     * @param tableBounds: boundaries of the table
     */
    @Override
    public void calculatePosition(Bounds tableBounds) {
        super.calculatePosition(tableBounds);

        Iterator ballIterator = balls.iterator();
        while (ballIterator.hasNext()){
            Ball ball = (Ball) ballIterator.next();
            ball.calculatePosition(tableBounds);
        }
    }

    /**
     * Checks collision in ball and children
     * @param ball2: ball to check collision against
     * @return
     */
    @Override
    public boolean isColliding(Ball ball2) {

        float ballStrength;
        float ballRadius;
        Point2D preCollisionVelocity;
        Point2D deltaV;
//        float energyOfCollision = ball2.getMass()*pow(deltaV, 2);
//        if (this.strength < energyOfCollision) {
//            float energyPerBall = energyOfCollision / balls.size();
//            Point2D pointOfCollision = (-deltaV.normalize())*this.radius;
//
//            //for each component ball
//            Point2D componentBallVelocity = preCollisionVelocity + sqrt(energyPerBall / componentBallMass) * (componentBallPosition - pointOfCollision).normalize();
//        }


        Iterator ballIterator = balls.iterator();
        while (ballIterator.hasNext()){
            Ball ball = (Ball) ballIterator.next();
            ball.isColliding(ball2);
        }

        return super.isColliding(ball2);
    }
}
