package application;

import org.json.simple.JSONObject;

import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
/**
 * This class carries out the steps in building a cue ball
 *
 */
public class CueBallBuilder implements BallBuilder {
	//FIXME define in super class
	private final String colour = "white";
	private double xPosition;
	private double yPosition;
	private double xVelocity;
	private double yVelocity;
	private double mass;
	private double radius = 10.0;	// Default radius 10.0cm
	private Circle view;
	@Override
	public void setColour(JSONObject jsonBall) {	}	// Colour has to be white
	@Override
	public void setpositionX(JSONObject jsonBall) {
		Double positionX = (Double) ((JSONObject) jsonBall.get("position")).get("x");
		this.xPosition = positionX;
		
	}
	@Override
	public void setpositionY(JSONObject jsonBall) {
		Double positionY = (Double) ((JSONObject) jsonBall.get("position")).get("y");
		this.yPosition = positionY;
		
	}
	@Override
	public void setvelocityX(JSONObject jsonBall) {
		Double velocityX = (Double) ((JSONObject) jsonBall.get("velocity")).get("x");
		this.xVelocity = velocityX;
		
	}
	@Override
	public void setvelocityY(JSONObject jsonBall) {
		Double velocityY = (Double) ((JSONObject) jsonBall.get("velocity")).get("y");
		this.yVelocity = velocityY;
		
	}
	@Override
	public void setmass(JSONObject jsonBall) {
		Double mass = (Double)jsonBall.get("mass");
		this.mass = mass;	
	}
	
	@Override
	public void setView(JSONObject jsonBall) {
		this.view = new Circle(this.xPosition,this.yPosition,this.radius,Paint.valueOf(this.colour));
		
	}
	/**
	 * Returns the built cue ball object
	 */
	public Ball getResult() {
		return new CueBall("white", this.xPosition, this.yPosition, this.xVelocity, this.yVelocity, this.mass,this.view);
	}
	

}
