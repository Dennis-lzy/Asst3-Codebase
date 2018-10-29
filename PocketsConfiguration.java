package application;

import org.json.simple.JSONObject;

public class PocketsConfiguration extends Configuration {


    @Override
    Ball getBall(JSONObject jsonBall) {
        return null;
    }

    @Override
    Table getTable(JSONObject jsonTable) {
        return null;
    }

    @Override
    Pockets getPockets(JSONObject jsonPockets) {
        Double pocketX = (Double) ((JSONObject) jsonPockets.get("position")).get("x");
        Double pocketY = (Double) ((JSONObject) jsonPockets.get("position")).get("y");
        Double radius = (Double) jsonPockets.get("radius");

        return new Pockets(pocketX,pocketY,radius);
    }
}
