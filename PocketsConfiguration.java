package application;

import org.json.simple.JSONObject;

/**
 * This class is responsible for building pockets
 */

public class PocketsConfiguration extends Configuration {

    /**
     * Stub implementation. This class does not build these
     */
    @Override
    Ball getBall(JSONObject jsonBall) {
        return null;
    }

    @Override
    Table getTable(JSONObject jsonTable) {
        return null;
    }

    /**
     * Builds pockets based on read parameters
     * @param jsonPockets
     * @return Pocket
     */
    @Override
    Pockets getPockets(JSONObject jsonPockets) {
        Double pocketX = (Double) ((JSONObject) jsonPockets.get("position")).get("x");
        Double pocketY = (Double) ((JSONObject) jsonPockets.get("position")).get("y");
        Double radius = (Double) jsonPockets.get("radius");

        return new Pockets(pocketX,pocketY,radius);
    }
}
