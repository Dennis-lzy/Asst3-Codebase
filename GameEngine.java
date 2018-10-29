package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.Pane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.util.Pair;
/**
 * The game engine class is responsible for storing game information, reading configurations,
 * as well as caculate and store ball and table properties.
 *
 */
public class GameEngine {
	private Table table;
	private List<Ball> balls = new ArrayList<>();
	private Ball cueBall = null;
	private List<Pockets> pockets = new ArrayList<>();


	public void clearAll(){
		this.table=null;
		this.balls = new ArrayList<>();
		this.cueBall = null;
		this.pockets = new ArrayList<>();
	}




	/**
	 *
	 * @return Table used in the game
	 */
	public Table getTable() {
		return table;
	}
	/**
	 * 
	 * @return A list of balls used in the game
	 */
	public List<Ball> getBalls() {
		return balls;
	}

	public void setBalls(List<Ball> balls) {
		this.balls = balls;
	}

	/**
	 * Sets the state of balls for memento
	 */



	public List<Pockets> getPockets() {
		return pockets;
	}

	public void setPockets(List<Pockets> pockets) {
		this.pockets = pockets;
	}

	/**
	 * This constructor takes a filePath name and load game information
	 * stored in the given file
	 * @param filePath: file path to read configuration from
	 */
	public GameEngine(String filePath) {
		readConfig(filePath);
	}
	
	/**
	 * Change all balls velocity due to given table friction
	 */
	public void applyFriction() {
		Ball temp;

		for(int i = 0; i < balls.size(); i++) {	// Calculate for every ball
			temp = balls.get(i);
			// Set x velocity
			if(temp.getxVelocity() > 0) {	// Positive velocity
				temp.setxVelocity(Math.max(0,temp.getxVelocity() - this.getTable().getFriction()*0.4));
			}
			else if(temp.getxVelocity() < 0){
				temp.setxVelocity(Math.min(0,temp.getxVelocity() + this.getTable().getFriction()*0.4));
			}
			// Set y velocity
			if(temp.getyVelocity() > 0) {	// Positive velocity
				temp.setyVelocity(Math.max(0,temp.getyVelocity() - this.getTable().getFriction()*0.4));
			}
			else if(temp.getyVelocity() < 0){
				temp.setyVelocity(Math.min(0,temp.getyVelocity() + this.getTable().getFriction()*0.4));
			}
		}
	}
	/**
	 * Read configuration from the given filepath and load it into GameEngine class.
	 * The actual reading of the data is delegated to respective reader and producer classes
	 * @param filePath: location of the file
	 */
	public void readConfig(String filePath) {
		// Create table reader and ball reader
		Configuration tableConf = ConfigurationProducer.getInstance().geConfiguration("TABLE");
		Configuration ballConf = ConfigurationProducer.getInstance().geConfiguration("BALL");
		Configuration pocketConf = ConfigurationProducer.getInstance().geConfiguration("POCKETS");
		JSONParser parser = new JSONParser();
		try {
			Object object = parser.parse(new FileReader(filePath));
			// convert Object to JSONObject
			JSONObject jsonObject = (JSONObject) object;
			// reading the Table section:
			JSONObject jsonTable = (JSONObject) jsonObject.get("Table");
			//reading the Pockets section:
			JSONArray jsonPockets = (JSONArray) jsonTable.get("Pockets");
			System.out.println(jsonPockets);
			// reading the "Balls" section:
			JSONObject jsonBalls = (JSONObject) jsonObject.get("Balls");
			// reading the "Balls: ball" array:
			JSONArray jsonBallsBall = (JSONArray) jsonBalls.get("ball");
			// reading from the array:

			Ball temp;
			// Create ball object for every ball
			for (Object obj : jsonBallsBall) {
				JSONObject jsonBall = (JSONObject) obj;
				temp = ballConf.getBall(jsonBall);
				if(temp.getColour().equals("white")) {
					this.cueBall = temp;
				}
				balls.add(temp);
				
			}
			// Create Pocket object for every pocket:
			Pockets p;
			for (Object obj: jsonPockets){
				System.out.println("test");
				JSONObject pocket = (JSONObject) obj;
				p = pocketConf.getPockets(pocket);
				pockets.add(p);
			}
			// Create a table object
			this.table = tableConf.getTable(jsonTable);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public String toString() {
		return "GameConfiguration [table=" + table + ", balls=" + balls + "]";
	}
	/**
	 * Returns the cueball
	 * @return Cueball
	 */
	public CueBall getCueBall() {
		return (CueBall)cueBall;
	}
	/**
	 * Assign the cueball
	 * @param cueBall: 
	 */
	public void setCueBall(Ball cueBall) {
		this.cueBall = cueBall;
	}
	/**
	 * Update velocities of all ball by considering collisions
	 */
	public void updateCollision() {
		for(int i = 0; i < this.getBalls().size(); i++) {	// Check every ball against all other balls
			Ball ball1 = this.getBalls().get(i);
			Ball ball2;
			for(int j = 0; j < this.getBalls().size(); j++) {
				if(i == j)	// Do not check a ball against itself
					continue;
				ball2 = this.getBalls().get(j);
				if(ball1.isColliding(ball2)) {
					// Construct points to pass on to the physics engine
					Point2D posA = new Point2D(ball1.getView().getCenterX(), ball1.getView().getCenterY());
					Point2D velA = new Point2D(ball1.getxVelocity(), ball1.getyVelocity());
					double massA = ball1.getMass();

					Point2D posB = new Point2D(ball2.getView().getCenterX(), ball2.getView().getCenterY());
					Point2D velB = new Point2D(ball2.getxVelocity(), ball2.getyVelocity());
					double massB = ball2.getMass();
					
					// Invoke the physics engine with calculated points
					Pair<Point2D, Point2D> results = PhysicsEngine.calculateCollision(posA, velA, massA, posB, velB, massB);
					
					// Set velocities of colliding balls according to calculated velocity
					ball1.setxVelocity(results.getKey().getX());
					ball1.setyVelocity(results.getKey().getY());
					ball2.setxVelocity(results.getValue().getX());
					ball2.setyVelocity(results.getValue().getY()); 
				}
			}        			
		}
	}

	public void checkPockets(Pane root) {
		for(Pockets p : this.getPockets()){
			Pockets pocket = p;
			Ball ball;
			for(Ball b :this.getBalls()){
				ball = b;
				if (pocket.containsBall(ball)){
					if(ball == cueBall){
						cueBall.setxVelocity(0);
						cueBall.setyVelocity(0);
						cueBall.setSunk(true);
						System.out.println("cue ball at rest test");
					}
					pocket.removeSunkBalls(root, ball, balls);

					System.out.println("ball sunk");
				}

			}
		}
	}



	/**
	 * Move all balls on table according to their speeds. This method does not detect to change position due to collision.
	 * Collision is handled in a seperate method with parameter of ball positions in the next frame
	 * @param tableBounds
	 */
	public void moveBalls(Bounds tableBounds) {
		Ball ball;
		// Move every ball on the table
		for(int i = 0; i < this.getBalls().size(); i++) {
			ball = this.getBalls().get(i);
			ball.calculatePosition(tableBounds);
		}
	}
}
