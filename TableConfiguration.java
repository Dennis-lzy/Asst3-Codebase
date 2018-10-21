package application;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import javafx.scene.paint.Paint;

/**
 * This class constructs Table objects.
 *
 */
public class TableConfiguration extends Configuration{

	@Override
	public Ball getBall(JSONObject jsonBall) {
		return null;
	}

	@Override
	public Table getTable(JSONObject jsonTable) {
		// reading values from the JSON table object
		String tableColour = (String) jsonTable.get("colour");
		Long tableX = (Long) ((JSONObject) jsonTable.get("size")).get("x");
		Long tableY = (Long) ((JSONObject) jsonTable.get("size")).get("y");
		Double tableFriction = (Double) jsonTable.get("friction");
		
		return new Table(tableColour, tableFriction, tableX, tableY);
	}
}
