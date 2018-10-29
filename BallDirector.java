package application;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 * This class defines different ways to build ball objects based on the object type
 *
 */
public class BallDirector {
	/**
	 * This methods delegates the task of building balls to a builder and
	 * let that builder build a ball with given data read from jsonBall object
	 * @param builder: a builder object with the knowledge of how to build a ball
	 * @param jsonBall: json object to read ball information from
	 */
	public void constructCueBall(BallBuilder builder, JSONObject jsonBall) {
		builder.setpositionX(jsonBall);
		builder.setpositionY(jsonBall);
		builder.setvelocityX(jsonBall);
		builder.setvelocityY(jsonBall);
		builder.setmass(jsonBall);
		builder.setView(jsonBall);
	}
	/**
	 * This methods delegates the task of building tables to a builder and
	 * let that builder build a table with given data read from jsonBall object
	 * @param builder: a builder object with the knowledge of how to build a table
	 * @param jsonTable: json object to read table information from
	 */

	public void constructTargetBall(BallBuilder builder, JSONObject jsonBall) {
		builder.setColour(jsonBall);
		builder.setpositionX(jsonBall);
		builder.setpositionY(jsonBall);
		builder.setvelocityX(jsonBall);
		builder.setvelocityY(jsonBall);
		builder.setmass(jsonBall);
		builder.setView(jsonBall);
	}

	public void constructCompositeBall(BallBuilder builder, JSONObject jsonBall){
		builder.setColour(jsonBall);
		builder.setpositionX(jsonBall);
		builder.setpositionY(jsonBall);
		builder.setvelocityX(jsonBall);
		builder.setvelocityY(jsonBall);

		builder.setView(jsonBall);
	}


}
