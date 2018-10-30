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

	/**
	 * Stub implementation. This class does not build these
	 */
	@Override
	Pockets getPockets(JSONObject jsonPockets) {
		return null;
	}

	@Override
	public Ball getBall(JSONObject jsonBall) {
		return null;
	}

    /**
     * Constructs a Table based on read parameters
     * @param jsonTable
     * @return
     */

	@Override
	public Table getTable(JSONObject jsonTable) {
		// reading values from the JSON table object
		JSONObject tableImage = (JSONObject) jsonTable.get("image");
		String path = (String) tableImage.get("path");
		String tableColour = (String) jsonTable.get("colour");
		Long tableX = (Long) ((JSONObject) jsonTable.get("size")).get("x");
		Long tableY = (Long) ((JSONObject) jsonTable.get("size")).get("y");
		Double tableFriction = (Double) jsonTable.get("friction");

        /**
         * Constructs Table with image if provided otherwise uses color property
         */
		if (path != null){
            Long offsetX = (Long) ((JSONObject) tableImage.get("offset")).get("x") ;
            Long offsetY = (Long) ((JSONObject) tableImage.get("offset")).get("y") ;
            Long imgX = (Long) ((JSONObject) tableImage.get("size")).get("x") ;
            Long imgY = (Long) ((JSONObject) tableImage.get("size")).get("y") ;
            System.out.println(path);

            return new Table(path, tableFriction, imgX, imgY, offsetX, offsetY);
        }
		
		return new Table(tableColour, tableFriction, tableX, tableY);
	}


}
