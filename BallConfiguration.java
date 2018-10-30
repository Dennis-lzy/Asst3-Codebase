package application;

import org.json.simple.JSONObject;
/**
 * This class has the responsibility to build balls.
 *
 */
public class BallConfiguration extends Configuration {
	BallDirector director = new BallDirector();

	@Override
	/**
	 * Based on the passed information, this method delegates the ball building task to respective builder class
	 */
	public Ball getBall(JSONObject jsonBall) {
		BallBuilder builder;
		String colour = (String)jsonBall.get("colour");
        boolean isComposite = jsonBall.containsKey("mass");
		if(colour.equalsIgnoreCase("white")) {
			builder = new CueBallBuilder();
			director.constructCueBall(builder, jsonBall);
			return builder.getResult();
		}
		else if (jsonBall.containsKey("mass") == false) {
		    builder = new CompositeBallBuilder();
		    director.constructCompositeBall(builder, jsonBall);
            return builder.getResult();
		} else {
			builder = new PoolBallBuilder();
			director.constructTargetBall(builder, jsonBall);
			return builder.getResult();
		}
	}

	@Override
	/**
	 * Stub implementation. This class does not build these
	 */
	public Table getTable(JSONObject jsonTable) {
		return null;
	}
	@Override
	Pockets getPockets(JSONObject jsonPockets) {return null;}

}
