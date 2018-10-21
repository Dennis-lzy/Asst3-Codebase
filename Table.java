package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
/**
 * This class defines a table class for the pool game.
 *
 */
public class Table {
	private Node view;
	private String colour;
	private double friction;
	private long x;	// x,y determine table size
	private long y;
	/**
	 * @return Colour of the table
	 */
	public String getColour() {
		return this.colour;
	}
	/**
	 * Construct a table object with the given parameters.
	 * @param colour: colour of the table
	 * @param friction: friction of the table
	 * @param x: width of the table
	 * @param y: height of the table
	 */
	public Table(String colour, double friction, long x, long y) {
		super();
		this.colour = colour;
		this.friction = friction;
		this.x = x;
		this.y = y;
		this.setView();
		
	}
	/**
	 * @param colour: colour of the table
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}
	/**
	 * Gets friction  of the table
	 * @return friction of the table
	 */
	public double getFriction() {
		return friction;
	}
	/**
	 * Sets friction  of the table
	 * @param friction  of the table
	 */
	public void setFriction(double friction) {
		this.friction = friction;
	}
	/**
	 * Gets x position of the table
	 * @return x: x position of the table
	 */
	public long getX() {
		return x;
	}
	/**
	 * Sets x position of the table
	 * @param x: x position  of the table
	 */
	public void setX(long x) {
		this.x = x;
	}
	/**
	 * Gets y position of the table
	 * @return: y position of the table
	 */
	public long getY() {
		return y;
	}
	/**
	 * Sets the y position of the table
	 * @param y: y position of the table
	 */
	public void setY(long y) {
		this.y = y;
	}
	/**
	 * Gets the view of the table
	 * @return: view of the table
	 */
	public Node getView() {
		return view;
	}
	/**
	 * Sets the view of the table as a rectangle with specified parameters
	 */
	public void setView() {
		this.view = new Rectangle(this.x, this.y, Paint.valueOf(this.colour));
	}

	@Override
	public String toString() {
		return "Table [colour=" + colour + ", friction=" + friction + ", x=" + x + ", y=" + y + "]";
	}
}
